package ru.job4j.view;

import ru.job4j.data.Tracker;
import ru.job4j.model.Item;
import ru.job4j.model.Task;
import ru.job4j.model.Bug;
import ru.job4j.model.Comment;

/**Implementation of console user interface.
*@version 2.0
*@author gimazetdinov
*/
public class MenuTracker {

	/**Method of interaction with the outside.*/
	private Input input;

	/**The database emulation.*/
	private Tracker tracker;

	/**Sum of actions.*/
	private final int sumOfActions = 8;

	/**The counter of actions.*/
	private int counter = 0;

	/**The array of menu's items.*/
	private UserAction[] actions = new UserAction[sumOfActions];

	/**The constructure sets method of interaction with the outside.
	*@param input - method of interaction with the outside
	*@param tracker - database emulator*/
	public MenuTracker(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	/**Fills menu of local class objects.*/
	public void fillAction() {
		this.actions[counter++] = new AddItem();
		this.actions[counter++] = new ShowTasks();
		this.actions[counter++] = new ShowBugs();
		this.actions[counter++] = new FindById();
		this.actions[counter++] = new FindByName();
		this.actions[counter++] = new DeleteItem();
		this.actions[counter++] = new UpdateItem();
		this.actions[counter++] = new AddComment();
	}

	/**Starts the selected menu's item to execute.
	*@param key - key of the local class*/
	public void select(int key) {
		this.actions[key].execute(this.input, this.tracker);
	}

	/**Shows info and key about every local class.*/
	public void show() {
		for (UserAction action : this.actions) {
			if (action != null) {
				System.out.println(action.info());
			}
		}
	}

	/**Shows the menu for the item adding.*/
	private class AddItem implements UserAction {

		/**The key of the action.*/
		private final int key = 0;

		/**Return the key of the action.
		*@return key - key*/
		public int key() {
			return this.key;
		}

		/**Executions the action.
		*@param input - method of interaction with the outside
		*@param tracker - the database emulation*/
		public void execute(Input input, Tracker tracker) {
			String name = input.ask("Please, enter the item's name: ");
			String description = input.ask("Please, enter the item's description: ");
			String type = input.ask("Please, enter item's type (task/bug): ");
			Item item;
			if ("task".equals(type)) {
				item = new Task();
			} else {
				item = new Bug();
			}
			item.setName(name);
			item.setDescription(description);
			item.generateId();
			item.setCreate(System.currentTimeMillis());
			tracker.add(item);
		}

		/**Shows info about the action.
		*@return info string*/
		public String info() {
			return String.format("%s. %s", this.key(), "Add the new item.");
		}
	}

	/**Shows a menu of tasks.*/
	private class ShowTasks implements UserAction {

		/**The key of the action.*/
		private final int key = 1;

		/**Return the key of the action.
		*@return key - key*/
		public int key() {
			return this.key;
		}

		/**Executions the action.
		*@param input - method of interaction with the outside
		*@param tracker - the database emulation*/
		public void execute(Input input, Tracker tracker) {
			System.out.println("All tasks:");
			for (Item showItem : tracker.getAll()) {
				if (showItem instanceof Task) {
					StringBuilder itemInfo = new StringBuilder();
					itemInfo.append("ID: ")
							.append(showItem.getId())
							.append(" Name: ")
							.append(showItem.getName())
							.append(" Create: ")
							.append(showItem.getCreate());
					System.out.println(itemInfo);
				}
			}
		}

		/**Shows info about the action.
		*@return info string*/
		public String info() {
			return String.format("%s. %s", this.key(), "Show all tasks.");
		}
	}

	/**Shows a menu of bugs.*/
	private class ShowBugs implements UserAction {

		/**The key of the action.*/
		private final int key = 2;

		/**Return the key of the action.
		*@return key - key*/
		public int key() {
			return this.key;
		}

		/**Executions the action.
		*@param input - method of interaction with the outside
		*@param tracker - the database emulation*/
		public void execute(Input input, Tracker tracker) {
			System.out.println("All bug's list:");
			for (Item showItem : tracker.getAll()) {
				if (showItem instanceof Bug) {
					StringBuilder itemInfo = new StringBuilder();
					itemInfo.append("ID: ")
							.append(showItem.getId())
							.append(" Name: ")
							.append(showItem.getName())
							.append(" Create: ")
							.append(showItem.getCreate());
					System.out.println(itemInfo);
				}
			}
		}

		/**Shows info about the action.
		*@return info string*/
		public String info() {
			return String.format("%s. %s", this.key(), "Show all bugs.");
		}
	}

	/**Searches the item by his ID.*/
	private class FindById implements UserAction {

		/**The key of the action.*/
		private final int key = 3;

		/**Return the key of the action.
		*@return key - key*/
		public int key() {
			return this.key;
		}

		/**Executions the action.
		*@param input - method of interaction with the outside
		*@param tracker - the database emulation*/
		public void execute(Input input, Tracker tracker) {
			String answer = input.ask("Please, enter item's ID: ");
			new ShowItem().execute(tracker.findById(answer));
		}

		/**Shows info about the action.
		*@return info string*/
		public String info() {
			return String.format("%s. %s", this.key(), "Show info about an item by ID.");
		}
	}

	/**The additional class for showing item's details.*/
	private class ShowItem {

		/**Shows details by the specified item.
		*@param item - specified item*/
		public void execute(Item item) {
			String type = "";
			if (item instanceof Task) {
				type = "task";
			} else {
				type = "bug";
			}
			StringBuilder itemInfo = new StringBuilder();
			itemInfo.append("Type: ")
					.append(type)
					.append("\n")
					.append("ID: ")
					.append(item.getId())
					.append("\n")
					.append("Create: ")
					.append(item.getCreate())
					.append("\n")
					.append("Name: ")
					.append(item.getName())
					.append("\n")
					.append("Description: ")
					.append(item.getDescription());
			System.out.println(itemInfo);
			System.out.println("Comments list:");
			for (Comment com : item.getComments()) {
				StringBuilder comInfo = new StringBuilder();
				comInfo.append("Author: ")
						.append(com.getAuthor())
						.append("\n")
						.append("Create: ")
						.append(com.getCreate())
						.append("\n")
						.append("Comment: ")
						.append(com.getText());
				System.out.println(comInfo);
			}
		}
	}

	/**Shows field for searching by item's name and shows list of found items.*/
	private class FindByName implements UserAction {

		/**The key of the action.*/
		private final int key = 4;

		/**Return the key of the action.
		*@return key - key*/
		public int key() {
			return this.key;
		}

		/**Executions the action.
		*@param input - method of interaction with the outside
		*@param tracker - the database emulation*/
		public void execute(Input input, Tracker tracker) {
			String answer = input.ask("Please, enter item's name: ");
			for (Item item : tracker.findByName(answer)) {
				new ShowItem().execute(item);
			}
		}

		/**Shows info about the action.
		*@return info string*/
		public String info() {
			return String.format("%s. %s", this.key(), "Show info about an item by name.");
		}
	}

	/**Allows to delete item.*/
	private class DeleteItem implements UserAction {

		/**The key of the action.*/
		private final int key = 5;

		/**Return the key of the action.
		*@return key - key*/
		public int key() {
			return this.key;
		}

		/**Executions the action.
		*@param input - method of interaction with the outside
		*@param tracker - the database emulation*/
		public void execute(Input input, Tracker tracker) {
			String answer = input.ask("Please, enter item's ID: ");
			tracker.delete(tracker.findById(answer));
			System.out.println("The item was deleted!");
		}

		/**Shows info about the action.
		*@return info string*/
		public String info() {
			return String.format("%s. %s", this.key(), "Delete the item.");
		}
	}

	/**Allows to change item's fields.*/
	private class UpdateItem implements UserAction {

		/**The key of the action.*/
		private final int key = 6;

		/**Return the key of the action.
		*@return key - key*/
		public int key() {
			return this.key;
		}

		/**Executions the action.
		*@param input - method of interaction with the outside
		*@param tracker - the database emulation*/
		public void execute(Input input, Tracker tracker) {
			String answer = input.ask("Please, enter item's ID: ");
			Item item = tracker.findById(answer);
			item.setName(input.ask("Please, enter item's new name: "));
			item.setDescription(input.ask("Please, enter item's new description: "));
			tracker.update(item);
			System.out.println("The item was updated!");
		}

		/**Shows info about the action.
		*@return info string*/
		public String info() {
			return String.format("%s. %s", this.key(), "Edit the item.");
		}
	}

	/**Adds comment to the specified item.*/
	private class AddComment implements UserAction {

		/**The key of the action.*/
		private final int key = 7;

		/**Return the key of the action.
		*@return key - key*/
		public int key() {
			return this.key;
		}

		/**Executions the action.
		*@param input - method of interaction with the outside
		*@param tracker - the database emulation*/
		public void execute(Input input, Tracker tracker) {
			String answer = input.ask("Please, enter item's ID: ");
			tracker.findById(answer).addComment(input.ask("Enter comment's text: "),
												input.ask("Enter comment's author: "),
												System.currentTimeMillis());
			System.out.println("Comment was added!");
		}

		/**Shows info about the action.
		*@return info string*/
		public String info() {
			return String.format("%s. %s", this.key(), "Add a comment to the item.");
		}
	}
}
