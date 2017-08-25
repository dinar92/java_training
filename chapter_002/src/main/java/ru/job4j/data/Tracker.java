package ru.job4j.data;

import ru.job4j.model.Item;

import java.util.ArrayList;

/**An emulator of database.
*@author gimazetdinov
*@version 1.0
*@since 05.02.2017
*/
public class Tracker {

	/**Max amount of items.*/
	public static final int MAX_AMOUNT = 30;

	/**Data store.*/
	private ArrayList<Item> items = new ArrayList<>(MAX_AMOUNT);

	/**The number of filled elements.*/
	private int position = 0;

	/**To add an item.
	*@param item - item
	*@return item - added item
	*/
	public Item add(Item item) {
		this.items.add(this.position, item);
		return this.items.get(this.position++);
	}

	/**To find item by his ID.
	*@param id - id
	*@return item - item found
	*/
	public Item findById(String id) {
		Item foundItem = null;
		for (int i = 0; i < this.position; i++) {
			if (this.items.get(i).getId().equals(id)) {
				foundItem = this.items.get(i);
				break;
			}
		}
		return foundItem;
	}

	/**Modifies item.
	*@param updated - updated item
	*/
	public void update(Item updated) {
		for (int i = 0; i < this.position; i++) {
			if (this.items.get(i).getId().equals(updated.getId())) {
				this.items.set(i, updated);
			}
		}
	}

	/**Return all items.
	*@return items - all items
	*/
	public ArrayList<Item> getAll() {
		return (ArrayList<Item>) this.items.clone();
	}

	/**Returns the array of ids of all items.
	*@return array of ids*/
	public ArrayList<String> getIds() {
		ArrayList<String> ids = new ArrayList<>(this.getAll().size());
		for (int index = 0; index < this.getAll().size(); index++) {
			ids.add(index, this.getAll().get(index).getId());
		}
		return ids;
	}

	/**To find item by his name.
	*@param name - name
	*@return item - item found
	*/
	public ArrayList<Item> findByName(String name) {
		int counter = 0;
		for (int i = 0; i < this.position; i++) {
			if (this.items.get(i).getName().equals(name)) {
				counter++;
			}
		}
		ArrayList<Item> sameName = new ArrayList<>(counter);
		counter = 0;
		for (int i = 0; i < this.position; i++) {
			if (this.items.get(i).getName().equals(name)) {
				sameName.add(counter++, this.items.get(i));
			}
		}
		return sameName;
	}

	/** Removes the first occurrence of the specified element from this list.
	*@param item - specified item
	*/
	public void delete(Item item) {
		this.items.remove(item);
	}
}
