package devops.services.graph_service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import devops.model.implementations.PersonEdge;
import devops.model.implementations.PersonNode;
import devops.services.GraphService;
import devops.storage.interfaces.Storage;

public class TestConstructor {
	
	@Test
	public void When_Valid_Expect_Empty_Storage(){
		GraphService service = new GraphService();
		
		Storage<PersonNode> nodeStorage = service.getNodeStorage();
		Storage<PersonEdge> edgeStorage = service.getEdgeStorage();

		assertEquals(0, nodeStorage.getAll().size());
		assertEquals(0, edgeStorage.getAll().size());
	}
}
