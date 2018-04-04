package ru.job4j.sql.jdbc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.sql.*;

/**
 * The application that demonstrates XSLT, JAXB, JDBC technologies.
 * Options for working setting through a ConfBean class.
 */
public class Application {

    /**
     * The name of the database's table, that will whom we will work.
     */
    private final String tableName = "TEST";

    /**
     * The SQL code to create a column in the database.
     */
    private final String queryToCreateColumn = "field INTEGER";
    /**
     * The field for summing fields.
     */
    private long fieldSum = 0L;
    /**
     * The name of database's driver.
     */
    private final String driverName;
    /**
     * The database's URL for connection.
     */
    private final String connectionName;
    /**
     * Count of fields.
     */
    private final int fieldsCount;

    /**
     * Sets the configuration bean.
     *
     * @param confBean - the configuration bean.
     */
    public Application(ConfBean confBean) {
        driverName = confBean.getDriver();
        connectionName = confBean.getDatabaseUrl();
        fieldsCount = confBean.getFieldCount();
    }

    /**
     * The main runner of the application. Invokes methods by order.
     *
     * @throws Exception - some exception.
     */
    public void run() throws Exception {
        this.pushToDB();
        this.createXMLFromDB();
        this.transformXml();
        this.jaxbParseSum();
        this.printSumToConsole();
    }

    /**
     * Inserts into table specified number of values.
     * Creates new table if that not exist, otherwise - clears that.
     *
     * @throws SQLException           - error of SQL query.
     * @throws ClassNotFoundException - error of defining the driver.
     */
    void pushToDB() throws SQLException, ClassNotFoundException {
        Class.forName(driverName);
        try (Connection connection = DriverManager.getConnection(connectionName); Statement statement = connection.createStatement()) {
            SQLTableManager manager = new SQLiteTableManager(connection);
            if (!manager.createTable(tableName, queryToCreateColumn)) {
                manager.clearTable(tableName);
            }
            connection.setAutoCommit(false);

            for (int i = 1; i <= fieldsCount; i++) {
                statement.execute(String.format("INSERT INTO %s (field) VALUES (%d)", tableName, i));
            }
            connection.commit();
        }
    }

    /**
     * Transforms database's structure to XML by DOM.
     *
     * @throws TransformerException         - error of transforming.
     * @throws SQLException                 - error of SQL query.
     * @throws ClassNotFoundException       - error of defining the driver.
     * @throws ParserConfigurationException - error of parsing.
     */
    void createXMLFromDB() throws TransformerException, SQLException, ClassNotFoundException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element results = document.createElement("entries");

        document.appendChild(results);

        Class.forName(driverName);
        try (Connection connection = DriverManager.getConnection(connectionName); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(String.format("SELECT field FROM %s", tableName))) {
            while (resultSet.next()) {
                Element entry = document.createElement("entry");
                Element field = document.createElement("field");
                field.appendChild(document.createTextNode(resultSet.getString("field")));
                entry.appendChild(field);
                results.appendChild(entry);
            }
            DOMSource source = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("1.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, streamResult);
        }
    }

    /**
     * Transforms the XML file to other XML. Uses XSLT.
     *
     * @throws TransformerException- error of transforming.
     */
    void transformXml() throws TransformerException {
        StreamSource source = new StreamSource(new File("1.xml"));
        StreamSource styleSource = new StreamSource(new File("scheme.xsl"));
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(styleSource);
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.transform(source, new StreamResult(new File("2.xml")));
    }

    /**
     * Reads field from XML file and generates sum of fields. Uses JAXB.
     *
     * @throws JAXBException - error of unmarshalling.
     */
    void jaxbParseSum() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Entries entries = (Entries) unmarshaller.unmarshal(new File("2.xml"));
        for (Entry entry
                :
                entries.getEntries()) {
            fieldSum += entry.getField();
        }
    }

    /**
     * Prints sum of all fields to console.
     */
    void printSumToConsole() {
        System.out.println(fieldSum);
    }
}
