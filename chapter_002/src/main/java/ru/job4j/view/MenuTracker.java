package ru.job4j.view;

import ru.job4j.data.Tracker;
import ru.job4j.model.Item;
import ru.job4j.model.Task;
import ru.job4j.model.Bug;
import ru.job4j.model.Comment;

import java.util.ArrayList;

/**Implementation of console user interface.
*@version 2.0
*@author gimazetdinov
*/
public class MenuTracker {

	/**Method of interaction with the outside.*/
	private Input input;

	/**The database emulation.*/
	private Tracker tracker;

	/**The constructure sets method of interaction with the outside.
	*@param input - method of interaction with the outside
	*@param tracker - database emulator*/
	public MenuTracker(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	/**Sum of actions.*/
	private final int sumOfActions = 8;

	/**The array of menu's items.*/
	private ArrayList<UserAction> actions = new ArrayList<>(sumOfActions);

	/**Allowable user's input for type of item to be added.*/
	private String[] allowableTypeInput = {"task", "bug"};

	/**Key of the menu item 'AddItem'.*/
	private final int keyAddItem = 0;

	/**Key of the menu item 'ShowTasks'.*/
	private final int keyShowTasks = 1;

	/**Key of the menu item 'ShowBugs'.*/
	private final int keyShowBugs = 2;

	/**Key of the menu item 'FindById'.*/
	private final int keyFindById = 3;

	/**Key of the menu item 'FindByName'.*/
	private final int keyFindByName = 4;

	/**Key of the menu item 'DeleteItem'.*/
	private final int keyDeleteItem = 5;

	/**Key of the menu item 'UpdateItem'.*/
	private final int keyUpdateItem = 6;

	/**Key of the menu item 'AddComment'.*/
	private final int keyAddComment = 7;

	/**Fills menu of local class objects.*/
	public void fillAction() {
		this.actions.add(new AddItem(keyAddItem, "Add the new item"));
		this.actions.add(new ShowTasks(keyShowTasks, "Show all tasks"));
		this.actions.add(new ShowBugs(keyShowBugs, "Show all bugs"));
		this.actions.add(new FindById(keyFindById, "Show info about an item by ID"));
		this.actions.add(new FindByName(keyFindByName, "Show info about an item by name"));
		this.actions.add(new DeleteItem(keyDeleteItem, "Delete the item"));
		this.actions.add(new UpdateItem(keyUpdateItem, "Edit the item"));
		this.actions.add(new AddComment(keyAddComment, "Add a comment to the item"));
	}

	/**Starts the selected menu's item to execute.
	*@param key - key of the local class*/
	public void select(int key) {
		this.actions.get(key).execute(this.input, this.tracker);
	}

	/**Shows info and key about every local class.*/
	public void show() {
		for (UserAction action : this.actions) {
			if (action != null) {
				System.out.println(action.info());
			}
		}
	}

	/**Return arrayList of actions keys.
	*@return array of keys*/
	public ArrayList<Integer> getKeysRange() {
		ArrayList<Integer> keysRange = new ArrayList<>(this.sumOfActions);
		for (int index = 0; index < this.sumOfActions; index++) {
			keysRange.add(this.actions.get(index).key());
		}
		return keysRange;
	}

	/**Shows the menu for the item adding.*/
	private class AddItem extends BaseAction {

		/**The constructor sets key and info of the action.
		*@param key of the action
		*@param info about the menu item*/
		AddItem(int key, String info) {
			super(key, info);
		}

		/**Executions the action.
		*@param input - method of interaction with the outside
		*@param tracker - the database emulation*/
		public void execute(Input input, Tracker tracker) {
			String name = input.ask("Please, enter the item's name: ");
			String description = input.ask("Please, enter the item's description: ");
			String type = input.ask("Please, enter item's type (task/bug): ", allowableTypeInput);
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
	}

	/**Shows a menu of tasks.*/
	private class ShowTasks extends BaseAction {

		/**The constructor sets key and info of the action.
		*@param key of the action
		*@param info about the menu item*/
		ShowTasks(int key, String info) {
			super(key, info);
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
	}

	/**Shows a menu of bugs.*/

	private class ShowBugs extends BaseAction {
		/**The constructor sets key and info of the action.
		*@param key of the action
		*@param info about the menu item*/
		ShowBugs(int key, String info) {
			super(key, info);
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
	}

	/**Shows details by the specified item.
	 *@param item - specified item*/
	private void showItem(Item item) {
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

	/**Shows field for searching by item's name and shows list of found items.*/
	private class FindByName extends BaseAction {

		/**The constructor sets key and info of the action.
		*@param key of the action
		*@param info about the menu item*/
		FindByName(int key, String info) {
			super(key, info);
		}

		/**Executions the action.
		*@param input - method of interaction with the outside
		*@param tracker - the database emulation*/
		public void execute(Input input, Tracker tracker) {
			String answer = input.ask("Please, enter item's name: ");
			for (Item item : tracker.findByName(answer)) {
				MenuTracker.this.showItem(item);
			}
		}

	}

	/**Searches the item by his ID.*/
	private class FindById extends BaseAction {

		/**The constructor sets key and info of the action.
		 *@param key of the action
		 *@param info about the menu item*/
		FindById(int key, String info) {
			super(key, info);
		}

		/**Executions the action.
		 *@param input - method of interaction with the outside
		 *@param tracker - the database emulation*/
		public void execute(Input input, Tracker tracker) {
			String answer = input.ask("Please, enter item's ID: ", tracker.getIds());
			MenuTracker.this.showItem(tracker.findById(answer));
		}
	}

	/**Allows to delete item.*/
	private class DeleteItem extends BaseAction {

		/**The constructor sets key and info of the action.
		*@param key of the action
		*@param info about the menu item*/
		DeleteItem(int key, String info) {
			super(key, info);
		}

		/**Executions the action.
		*@param input - method of interaction with the outside
		*@param tracker - the database emulation*/
		public void execute(Input input, Tracker tracker) {
			String answer = input.ask("Please, enter item's ID: ", tracker.getIds());
			tracker.delete(tracker.findById(answer));
			System.out.println("The item was deleted!");
		}
	}

	/**Allows to change item's fields.*/
	private class UpdateItem extends BaseAction {

		/**The constructor sets key and info of the action.
		*@param key of the action
		*@param info about the menu item*/
		UpdateItem(int key, String info) {
			super(key, info);
		}

		/**Executions the action.
		*@param input - method of interaction with the outside
		*@param tracker - the database emulation*/
		public void execute(Input input, Tracker tracker) {
			String answer = input.ask("Please, enter item's ID: ", tracker.getIds());
			Item item = tracker.findById(answer);
			item.setName(input.ask("Please, enter item's new name: "));
			item.setDescription(input.ask("Please, enter item's new description: "));
			tracker.update(item);
			System.out.println("The item was updated!");
		}
	}

	/**Adds comment to the specified item.*/
	private class AddComment extends BaseAction {

		/**The constructor sets key and info of the action.
		*@param key of the action
		*@param info about the menu item*/
		AddComment(int key, String info) {
			super(key, info);
		}

		/**Executions the action.
		*@param input - method of interaction with the outside
		*@param tracker - the database emulation*/
		public void execute(Input input, Tracker tracker) {
			String answer = input.ask("Please, enter item's ID: ", tracker.getIds());
			Item updated = tracker.findById(answer);
			updated.addComment(input.ask("Enter comment's text: "),
												input.ask("Enter comment's author: "),
												System.currentTimeMillis());
			tracker.update(updated);
			System.out.println("Comment was added!");
		}
	}
}
