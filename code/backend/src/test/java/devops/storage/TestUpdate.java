package devops.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import devops.model.implementations.Person;
import devops.model.implementations.PersonNode;
import devops.storage.implementations.StorageHash;

public class TestUpdate {
	@Test
	public void When_Valid_Expect_Updated_Item() {
		var storage = new StorageHash<PersonNode>();
		var testNode = new PersonNode("test", new Person(0, 0, "test1", null, null, null, null, null, null, null, null));
		var testNode2 = new PersonNode("test", new Person(1, 1, "test2", null, null, null, null, null, null, null, null));
		storage.add(testNode);
		storage.update(testNode2);
		assertEquals(1, storage.getAll().size());
		assertEquals(testNode2, storage.get("test"));
	}

	@Test
	public void When_Non_Existent_Expect_Nothing() {
		var storage = new StorageHash<PersonNode>();
		var testNode = new PersonNode("test",
				new Person(0, 0, "test1", null, null, null, null, null, null, null, null));
		var testNode2 = new PersonNode("test2",
				new Person(1, 1, "test2", null, null, null, null, null, null, null, null));
		storage.add(testNode);
		assertThrows(IllegalArgumentException.class, () -> {
			storage.update(testNode2);
		});
	}

	@Test
	public void When_Null_Expect_Exception() {
		var storage = new StorageHash<PersonNode>();

		assertThrows(IllegalArgumentException.class, () -> {
			storage.update(null);
		});
	}
}
