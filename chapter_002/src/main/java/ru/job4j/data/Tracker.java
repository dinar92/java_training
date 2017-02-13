package ru.job4j.data;

import ru.job4j.model.Item;
import java.util.Arrays;

/**An emulator of database.
*@author gimazetdinov
*@version 1.0
*@since 05.02.2017
*/
public class Tracker {

	/**Max amount of items.*/
	public static final int MAX_AMOUNT = 30;

	/**Data store.*/
	private Item[] items = new Item[MAX_AMOUNT];

	/**The number of filled elements.*/
	private int position = 0;

	/**To add an item.
	*@param item - item
	*@return item - added item
	*/
	public Item add(Item item) {
		this.items[this.position] = item;
		return this.items[this.position++];
	}

	/**To find item by his ID.
	*@param id - id
	*@return item - item found
	*/
	public Item findById(String id) {
		Item foundItem = new Item();
		for (int i = 0; i < this.position; i++) {
			if (this.items[i].getId().equals(id)) {
				foundItem = this.items[i];
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
			if (this.items[i].getId().equals(updated.getId())) {
				this.items[i] = updated;
			}
		}
	}

	/**Return all items.
	*@return items - all items
	*/
	public Item[] getAll() {
		return Arrays.copyOf(this.items, position);
	}

	/**To find item by his name.
	*@param name - name
	*@return item - item found
	*/
	public Item[] findByName(String name) {
		int counter = 0;
		for (int i = 0; i < this.position; i++) {
			if (this.items[i].getName().equals(name)) {
				counter++;
			}
		}
		Item[] sameName = new Item[counter];
		for (int i = 0; i < sameName.length; i++) {
			if (this.items[i].getName().equals(name)) {
				sameName[i] = this.items[i];
			}
		}
		return sameName;
	}

	/**Deletes specified item.
	*@param item - specified item
	*/
	public void delete(Item item) {
		for (int out = 0; out < this.position; out++) {
			if (this.items[out].equals(item)) {
				for (int in = out; out < this.position - 1; in++) {
					this.items[in] = this.items[in + 1];
				}
				this.position--;
				this.items[this.position] = null;
			}
		}
	}
}
