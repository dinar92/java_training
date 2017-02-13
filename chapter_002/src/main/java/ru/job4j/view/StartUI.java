package ru.job4j.view;

import ru.job4j.model.Item;
import ru.job4j.model.Task;
import ru.job4j.model.Bug;
import ru.job4j.model.Comment;
import ru.job4j.data.Tracker;

/**Implementation of console user interface.
*@version 1.0
*@author gimazetdinov
*/
public class StartUI {

	/**Method of interaction with the outside.*/
	private Input input;

	/**The database emulation.*/
	private Tracker tracker = new Tracker();

	/**The constructure sets method of interaction with the outside.
	*@param input - method of interaction with the outside*/
	public StartUI(Input input) {
		this.input = input;
	}

	/**UI's start method.*/
	private void init() {
		StringBuilder mainMenu = new StringBuilder();
		String answer;
		mainMenu.append("Welcome to our tracking system!\n")
				.append("1. Show task's menu\n")
				.append("2. Show bug's menu\n")
				.append("0. Exit of the programm\n");
		do {
			System.out.print(mainMenu);
			answer = input.ask("Please, enter your number: ");
			if (answer.equals("1")) {
				showMenu(new Task());
			} else if (answer.equals("2")) {
				showMenu(new Bug());
			}
		} while (!answer.equals("0"));
	}

	/**Program's start point.
	*@param args - command line arguments*/
	public static void main(String[] args) {
		new StartUI(new ConsoleInput()).init();
	}

	/**Shows a menu based on the object type.
	*@param item - sets the object type*/
	private void showMenu(Item item) {
		String answer;
		StringBuilder tasksMenu = new StringBuilder();
		if (item instanceof Task) {
			tasksMenu.append("Task's menu.\n")
				.append("1. Show all tasks\n")
				.append("2. Find task by name\n")
				.append("3. Find task by ID\n")
				.append("4. Add new task\n")
				.append("0. Back\n");
		} else {
			tasksMenu.append("Task's menu.\n")
				.append("1. Show all bugs\n")
				.append("2. Find bug by name\n")
				.append("3. Find bug by ID\n")
				.append("4. Add new bug\n")
				.append("0. Back\n");
		}
		do {
			System.out.print(tasksMenu);
			answer = input.ask("Please, enter your number: ");
			if (answer.equals("1")) {
				allItems(item);
			} else if (answer.equals("2")) {
				findByName();
			} else if (answer.equals("3")) {
				findById();
			} else if (answer.equals("4")) {
				addItem(item);
			}
		} while (!answer.equals("0"));
	}

	/**Shows the menu for the item adding.
	*@param item - sets the object type*/
	private void addItem(Item item) {
		if (item instanceof Task) {
			Task task = new Task();
			task.setName(input.ask("Enter task's name: "));
			task.setDescription(input.ask("Enter task's description: "));
			task.generateId();
			task.setCreate(System.currentTimeMillis());
			tracker.add(task);
		} else {
			Bug bug = new Bug();
			bug.setName(input.ask("Enter bug's name: "));
			bug.setDescription(input.ask("Enter bug's description: "));
			bug.generateId();
			bug.setCreate(System.currentTimeMillis());
			tracker.add(bug);
		}
	}

	/**Shows the list of all items of a certain type.
	*@param item - sets the object type*/
	private void allItems(Item item) {
		if (item instanceof Task) {
			System.out.println("All task's list:");
			for (Item showItem : tracker.getAll()) {
				if (showItem instanceof Task) {
					StringBuilder itemInfo = new StringBuilder();
					itemInfo.append(showItem.getId())
							.append(" ")
							.append(showItem.getName())
							.append(" ")
							.append(showItem.getCreate());
					System.out.println(itemInfo);
				}
			}
		} else {
			System.out.println("All bug's list:");
			for (Item showItem : tracker.getAll()) {
				if (showItem instanceof Bug) {
					StringBuilder itemInfo = new StringBuilder();
					itemInfo.append(showItem.getId())
							.append(" ")
							.append(showItem.getName())
							.append(" ")
							.append(showItem.getCreate());
					System.out.println(itemInfo);
				}
			}
		}
		findById();
	}

	/**Shows field for searching by item's ID.*/
	private void findById() {
		String answer;
		do {
			answer = input.ask("Please, enter item's ID or '0' to back: ");
			if (!answer.equals("0")) {
				getItem(answer);
			}
		} while (!answer.equals("0"));
	}

	/**Shows field for searching by item's name and shows list of found items.*/
	private void findByName() {
		String answer;
		do {
			answer = input.ask("Please, enter item's name or '0' to back: ");
			if (!answer.equals("0")) {
				for (Item item : tracker.findByName(answer)) {
					StringBuilder showItem = new StringBuilder();
					showItem.append(item.getId())
							.append(" ")
							.append(item.getName())
							.append(" ")
							.append(item.getCreate());
					System.out.println(showItem);
				}
				findById();
			}
		} while (!answer.equals("0"));
	}

	/**Displays information about the item with the specified ID.
	*@param id - specified ID*/
	private void getItem(String id) {
		Item current = tracker.findById(id);
		String answer;
		StringBuilder showMenu = new StringBuilder();
		showMenu.append("1. Update item\n")
				.append("2. Delete item\n")
				.append("3. Add comment\n")
				.append("0. Back\n");
		do {
			StringBuilder showItem = new StringBuilder();
			showItem.append(current.getCreate())
				.append("\n\n")
				.append(current.getName())
				.append("\n\n")
				.append(current.getDescription())
				.append("\n\n")
				.append("Comments:\n\n");
			for (Comment com : current.getComments()) {
				showItem.append(com.getAuthor())
						.append("\n")
						.append(com.getCreate())
						.append("\n")
						.append(com.getText())
						.append("\n\n");
			}
			System.out.print(showItem);
			System.out.print(showMenu);
			answer = input.ask("Please, enter number: ");
			if (answer.equals("1")) {
				update(current);
			} else if (answer.equals("2")) {
				delete(current);
				answer = "0";
			} else if (answer.equals("3")) {
				addComment(current);
			}
		} while (!answer.equals("0"));
		allItems(current);
	}

	/**Allows to change item's fields.
	*@param item - mutable item*/
	private void update(Item item) {
		StringBuilder showMenu = new StringBuilder();
		String answer;
		showMenu.append("1. Edit name\n")
				.append("2. Edit description\n")
				.append("0. Back\n");
		do {
			System.out.print(showMenu);
			answer = input.ask("Please, enter number: ");
			if (answer.equals("1")) {
				item.setName(input.ask("Please, enter new name: "));
				tracker.update(item);
			} else if (answer.equals("2")) {
				item.setDescription(input.ask("Please, enter new description: "));
				tracker.update(item);
			}
		} while (!answer.equals("0"));
	}

	/**Allows to delete item.
	*@param item - deleted item*/
	private void delete(Item item) {
		String answer;
		tracker.delete(item);
		do {
			System.out.println("Item was delete!!!");
			answer = input.ask("Enter '0' to back: ");
		} while (!answer.equals("0"));
	}

	/**Adds comment to the specified item.
	*@param item - item*/
	private void addComment(Item item) {
		item.addComment(input.ask("Enter comment's text: "),
						input.ask("Enter comment's author: "),
						System.currentTimeMillis());
		System.out.println("Comment was added!");
	}
}
