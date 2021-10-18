package devops.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import devops.model.implementations.Person;
import devops.model.implementations.PersonNode;
import devops.storage.implementations.StorageHash;

public class TestGet {
	@Test
	public void When_Valid_Expect_Item() {
		var storage = new StorageHash<PersonNode>();
		var testNode = new PersonNode("test", new Person(0, 0, null, null, null, null, null, null, null, null, null));
		storage.add(testNode);
		var node = storage.get("test");
		assertEquals(testNode, node);
	}

	@Test
	public void When_Null_Expect_Exception() {
		var storage = new StorageHash<PersonNode>();

		assertThrows(IllegalArgumentException.class, () -> {
			storage.get(null);
		});
	}

	@Test
	public void When_Blank_Expect_Exception() {
		var storage = new StorageHash<PersonNode>();

		assertThrows(IllegalArgumentException.class, () -> {
			storage.get("");
		});
	}
}
