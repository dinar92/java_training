package ru.job4j.data;

import ru.job4j.model.Item;

public class Tracker {

	private Item[] items = new Item[30];
	private int position = 0;

	public Item add(Item item) {
		this.items[position] = item;
		this.position++;
		return item;
	}

	public Item findById(String id) {
		int parsedId = Integer.parseInt(id)
		for(Item item : this.items) {
			if(item.getId() == (parsedId))
				return item;
		}
	}

	public Item[] getAll() {
		return this.items;
	}

	public Item[] findByName(String name) {
		int counter = 0;
		for(Item item : this.items) {
			if (item.getName().equals(name)) {
				count++;
			}
		}
		Item[] sameName = new Item[counter];
		counter = 0;
		for (Item item : this.items) {
			if (item.getName().equals(name)) {
				sameName[counter] = item;
				counter++;
			}
		}
		return sameName;
	}
}
