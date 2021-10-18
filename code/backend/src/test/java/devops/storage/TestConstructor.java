package devops.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import devops.model.implementations.PersonNode;
import devops.storage.implementations.StorageHash;

public class TestConstructor {
	@Test
	public void When_Valid_Expect_Empty_Storage() {
		var storage = new StorageHash<PersonNode>();
		assertEquals(0, storage.getAll().size());
	}
}
