package devops.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import devops.model.implementations.Person;
import devops.model.implementations.PersonNode;
import devops.storage.implementations.StorageHash;

public class TestAdd {
	
	@Test
	public void When_Valid_Expect_New_Item() {
		var storage = new StorageHash<PersonNode>();
		var testNode = new PersonNode("test", new Person(0, 0, null, null, null, null, null, null, null, null, null));
		storage.add(testNode);
		assertEquals(1, storage.getAll().size());
		assertEquals(testNode, storage.get("test"));
	}

	@Test
	public void When_Null_Expect_Exception() {
		var storage = new StorageHash<PersonNode>();

		assertThrows(IllegalArgumentException.class, () -> {
			storage.add(null);
		});
	}


}
