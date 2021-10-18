package devops.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import devops.model.implementations.Person;
import devops.model.implementations.PersonNode;
import devops.storage.implementations.StorageHash;

public class TestRemove {
	@Test
	public void When_Valid_Expect_Item_Removed() {
		var storage = new StorageHash<PersonNode>();
		var testNode = new PersonNode("test", new Person(0, 0, null, null, null, null, null, null, null, null, null));
		storage.add(testNode);
		var removedNode = storage.remove("test");
		assertEquals(0, storage.getAll().size());
		assertEquals(testNode, removedNode);
	}

	@Test
	public void When_Null_Expect_Exception() {
		var storage = new StorageHash<PersonNode>();

		assertThrows(IllegalArgumentException.class, () -> {
			storage.remove(null);
		});
	}

	@Test
	public void When_Blank_Expect_Exception() {
		var storage = new StorageHash<PersonNode>();

		assertThrows(IllegalArgumentException.class, () -> {
			storage.remove("");
		});
	}
}
