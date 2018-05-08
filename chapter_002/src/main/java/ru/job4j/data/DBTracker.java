package ru.job4j.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.model.Bug;
import ru.job4j.model.Comment;
import ru.job4j.model.Item;
import ru.job4j.model.Task;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Tracker with using database.
 */
public class DBTracker extends Tracker implements AutoCloseable {
    /**
     * The slf4j logger instance.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DBTracker.class);

    /**
     * The database driver.
     */
    private final File driverNameFile;
    /**
     * The database url.
     */
    private final File connectionUrlFile;
    /**
     * Indicator of database existence.
     */
    private boolean databaseInited = false;
    /**
     * The file with script, what initializes table with items.
     */
    private final File initItemsDB = new File("src/main/java/ru/job4j/data/initItemsDB.sql");
    /**
     * The file with script, what initializes table with commentaries.
     */
    private final File initCommentsDB = new File("src/main/java/ru/job4j/data/initCommentsDB.sql");
    /**
     * The file with script, what initializes table with types of items.
     */
    private final File initItemType = new File("src/main/java/ru/job4j/data/initTypeDB.sql");
    /**
     * The file with script, what inserts item in the table.
     */
    private final File insertItemQuery = new File("src/main/java/ru/job4j/data/insertItem.sql");
    /**
     * The file with script, what inserts a commentary in the table.
     */
    private final File insertCommentQuery = new File("src/main/java/ru/job4j/data/insertComment.sql");
    /**
     * The file with script, what returns the item by ID.
     */
    private final File getItemByIDQuery = new File("src/main/java/ru/job4j/data/getItemByID.sql");
    /**
     * The file with script, what returns commentaries of the item.
     */
    private final File getCommentsByItem = new File("src/main/java/ru/job4j/data/getCommentsByItem.sql");
    /**
     * The file with script, what updates the item by ID.
     */
    private final File updateItemByID = new File("src/main/java/ru/job4j/data/updateItemByID.sql");
    /**
     * The file with script, what creates type "BUG" in types table.
     */
    private final File insertBugType = new File("src/main/java/ru/job4j/data/insertBugType.sql");
    /**
     * The file with script, what creates type "TASK" in types table.
     */
    private final File insertTaskType = new File("src/main/java/ru/job4j/data/insertTaskType.sql");
    /**
     * The file with script, what returns list of all IDs.
     */
    private final File getAllID = new File("src/main/java/ru/job4j/data/getAllIDs.sql");
    /**
     * The file with script, what returns items by specified name.
     */
    private final File getItemsByName = new File("src/main/java/ru/job4j/data/getItemsByName.sql");
    /**
     * The file with script, what removes the items.
     */
    private final File deleteItem = new File("src/main/java/ru/job4j/data/deleteItem.sql");

    private final Connection connection;

    /**
     * Sets files with the name of driver and the URL for connection to the database.
     *
     * @param driverNameFile    - the name of the driver.
     * @param connectionUrlFile - the URL of connection.
     */
    public DBTracker(File driverNameFile, File connectionUrlFile) throws SQLException {
        this.driverNameFile = driverNameFile;
        this.connectionUrlFile = connectionUrlFile;
        connection = DriverManager.getConnection(fileLineReader(connectionUrlFile));
    }

    /**
     * Crates necessary tables in the database if it not exist.
     */
    private void initDatabase() {
        try {
            Class.forName(this.fileLineReader(driverNameFile));
        } catch (ClassNotFoundException e) {
            LOGGER.info(e.getMessage(), e);
        }
        try (Connection connection = DriverManager.getConnection(this.fileLineReader(connectionUrlFile)); Statement statement = connection.createStatement()) {
            statement.execute(this.fileLineReader(initItemsDB));
            statement.execute(this.fileLineReader(initCommentsDB));
            statement.execute(this.fileLineReader(initItemType));
            statement.execute(this.fileLineReader(insertBugType));
            statement.execute(this.fileLineReader(insertTaskType));
            databaseInited = true;
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    /**
     * Reads the line from the specified file (only first line).
     *
     * @param file - the file with line.
     * @return - the line.
     */
    private String fileLineReader(File file) {
        String readeLine = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            readeLine = reader.readLine();
        } catch (FileNotFoundException e) {
            LOGGER.info(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.info(e.getMessage(), e);
        }
        return readeLine;
    }

    @Override
    public Item add(Item item) {
        if (!databaseInited) {
            this.initDatabase();
        }
        try (PreparedStatement itemStatement = connection.prepareStatement(this.fileLineReader(insertItemQuery));
             PreparedStatement commentStatement = connection.prepareStatement(this.fileLineReader(insertCommentQuery))) {
            connection.setAutoCommit(false);
            itemStatement.setInt(1, Integer.parseInt(item.getId()));
            itemStatement.setString(2, item.getName());
            itemStatement.setString(3, item.getDescription());
            itemStatement.setString(4, item.getCreate());
            itemStatement.setString(5, this.getItemType(item).toString());
            itemStatement.executeUpdate();
            try {
                connection.commit();
                Comment[] comments = item.getComments();
                for (Comment comment
                        : comments) {
                    commentStatement.setString(1, comment.getAuthor());
                    commentStatement.setString(2, comment.getCreate());
                    commentStatement.setString(3, comment.getText());
                    commentStatement.setInt(4, Integer.parseInt(item.getId()));
                    commentStatement.executeUpdate();
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                LOGGER.info(e.getMessage(), e);
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
        return item;
    }

    @Override
    public Item findById(String id) {
        if (!databaseInited) {
            this.initDatabase();
        }
        Item item = null;
        try (PreparedStatement statement = connection.prepareStatement(this.fileLineReader(getItemByIDQuery))) {
            statement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("type").equalsIgnoreCase(Type.BUG.toString())) {
                    item = new Bug();
                } else {
                    item = new Task();
                }
                item.setName(resultSet.getString("name"));
                item.setDescription(resultSet.getString("description"));
                item.setCreate(converterDateStringToLong(resultSet.getString("createDate")));
                item.setId(String.valueOf(resultSet.getInt("id")));
            }
            if (item != null) {
                for (Comment comment : this.getComments(item, connection)) {
                    item.addComment(comment.getText(), comment.getAuthor(), this.converterDateStringToLong(comment.getCreate()));
                }
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
        return item;
    }

    @Override
    public void update(Item updated) {
        if (!databaseInited) {
            this.initDatabase();
        }
        try (PreparedStatement updateItemStatement = connection.prepareStatement(this.fileLineReader(updateItemByID));
             PreparedStatement addCommentStatement = connection.prepareStatement(this.fileLineReader(insertCommentQuery))) {
            Item oldItem = this.findById(updated.getId());
            Comment[] oldComments = oldItem.getComments();
            Comment[] newComments = updated.getComments();
            updateItemStatement.setString(1, updated.getName());
            updateItemStatement.setString(2, updated.getDescription());
            updateItemStatement.setString(3, updated.getCreate());
            updateItemStatement.setString(4, this.getItemType(updated).toString());
            updateItemStatement.setString(5, updated.getId());
            updateItemStatement.execute();
            for (int i = oldComments.length; i < newComments.length; i++) {
                addCommentStatement.setString(1, newComments[i].getAuthor());
                addCommentStatement.setString(2, newComments[i].getCreate());
                addCommentStatement.setString(3, newComments[i].getText());
                addCommentStatement.setInt(4, Integer.parseInt(updated.getId()));
                addCommentStatement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    @Override
    public ArrayList<Item> getAll() {
        if (!databaseInited) {
            this.initDatabase();
        }
        ArrayList<Item> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(this.fileLineReader(getAllID));
            while (resultSet.next()) {
                list.add(this.findById(resultSet.getString("id")));
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public ArrayList<Item> findByName(String name) {
        if (!databaseInited) {
            this.initDatabase();
        }
        ArrayList<Item> list = new ArrayList<>();
        Item item;
        try (PreparedStatement statement = connection.prepareStatement(this.fileLineReader(getItemsByName))) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("type").equalsIgnoreCase(Type.BUG.toString())) {
                    item = new Bug();
                } else {
                    item = new Task();
                }
                item.setName(resultSet.getString("name"));
                item.setDescription(resultSet.getString("description"));
                item.setCreate(this.converterDateStringToLong(resultSet.getString("createDate")));
                item.setId(String.valueOf(resultSet.getInt("id")));
                for (Comment comment : this.getComments(item, connection)) {
                    item.addComment(comment.getText(), comment.getAuthor(), this.converterDateStringToLong(comment.getCreate()));
                }
                list.add(item);
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public void delete(Item item) {
        if (!databaseInited) {
            this.initDatabase();
        }
        try (PreparedStatement statement = connection.prepareStatement(this.fileLineReader(deleteItem))) {
            statement.setString(1, item.getId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    @Override
    public ArrayList<String> getIds() {
        ArrayList<String> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(this.fileLineReader(getAllID));
            while (resultSet.next()) {
                list.add(resultSet.getString("id"));
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
        return list;
    }

    /**
     * The service method, which returns the type of item.
     *
     * @param item - the item.
     * @return - the type.
     */
    private Type getItemType(Item item) {
        return (item instanceof Task) ? Type.TASK : Type.BUG;
    }

    /**
     * The service method. Converts string representation of date to time in milliseconds.
     *
     * @param string - the string representation of date.
     * @return - time in milliseconds.
     */
    private long converterDateStringToLong(String string) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yy");
        Date date = null;
        try {
            date = dateFormat.parse(string);
        } catch (ParseException e) {
            LOGGER.info(e.getMessage(), e);
        }
        return date.getTime();
    }

    /**
     * Returns the list of the specified item.
     *
     * @param item       - the item.
     * @param connection - connection to database.
     * @return - the list of commentaries.
     */
    private List<Comment> getComments(Item item, Connection connection) {
        List<Comment> list = new ArrayList<>();
        Comment comment;
        try (PreparedStatement statement = connection.prepareStatement(this.fileLineReader(getCommentsByItem))) {
            statement.setString(1, item.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comment = new Comment();
                comment.setAuthor(resultSet.getString("authorName"));
                comment.setText(resultSet.getString("commentText"));
                comment.setCreate(this.converterDateStringToLong(resultSet.getString("createDate")));
                list.add(comment);
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage(), e);
        }
        return list;
    }

    /**
     * Closes the connection with tracker.
     *
     * @throws Exception - if a tracker access error occurs.
     */
    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
