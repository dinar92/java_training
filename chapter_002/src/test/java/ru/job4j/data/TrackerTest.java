package ru.job4j.data;

import ru.job4j.model.Item;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
//import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

/**Testing Tracker class.
*@author gimazetdinov
*/
public class TrackerTest {

	/**Item to add in the tracker.*/
	private Item itemToAdd = new Item();

	/**ID of the item.*/
	private String id;

	/**Time in milliseconds.*/
	public static final long TIME_IN_MILLIS = 1485959504531L;

	/**Max amount of items.*/
	public static final int MAX_AMOUNT = 30;

	/**Setting an item's fields.*/
	public TrackerTest() {
		itemToAdd.setName("Nick");
		itemToAdd.setDescription("Descr");
		itemToAdd.generateId();
		id = itemToAdd.getId();
		itemToAdd.setCreate(TIME_IN_MILLIS);
	}

	/**Testing add().*/
	@Test
	public void whenAddItemThenItemInTracker() {
		Tracker track = new Tracker();
		assertThat(track.add(itemToAdd), is(itemToAdd));
	}

	/**Testing findById().*/
	@Test
	public void whenSearchByIdThenItem() {
		Tracker track = new Tracker();
		track.add(itemToAdd);
		assertThat(track.findById(id), is(itemToAdd));
	}

	/**Testing update().*/
	@Test
	public void whenItemUpdatedThenItemInTrack() {
		Tracker track = new Tracker();
		track.add(itemToAdd);
		Item newItem = track.findById(id);
		newItem.setName("Mike");
		track.update(newItem);
		assertThat(track.findById(id), is(newItem));
	}

	/**Testing getAll().*/
	@Test
	public void whenGetAllThenReturnAllItems() {
		Tracker track = new Tracker();
		track.add(itemToAdd);
		Item[] expectItems = new Item[1];
		expectItems[0] = itemToAdd;
		for (int i = 0; i < track.getAll().length; i++) {
			assertThat(track.getAll()[i], is(expectItems[i]));
		}
	}

	/**Testing findByName().*/
	@Test
	public void whenSearchByNameThenItemsArray() {
		Tracker track = new Tracker();
		Item[] expectItem = new Item[1];
		expectItem[0] = itemToAdd;
		track.add(itemToAdd);
		assertThat(track.findByName("Nick"), is(expectItem));
	}

	/**Testing delete().*/
	@Test
	public void whenDeleteItemThenTrackWithoutItem() {
		Tracker track = new Tracker();
		track.add(itemToAdd);
		track.delete(itemToAdd);
		Item[] expectItems = new Item[0];
		assertThat(track.getAll(), is(expectItems));
	}
}

