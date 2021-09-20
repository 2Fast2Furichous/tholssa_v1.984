package devops.GraphServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import devops.Storage.interfaces.Storage;
import devops.model.implementations.Person;
import devops.model.interfaces.GraphEdge;
import devops.model.interfaces.GraphNode;
import devops.services.GraphService;

public class Constructor {
	
	@Test
	public void When_Valid_Expect_Empty_Storage(){
		GraphService service = new GraphService();
		
		Storage<GraphNode<Person>> nodeStorage = service.getNodeStorage();
		Storage<GraphEdge<Person>> edgeStorage = service.getEdgeStorage();

		assertEquals(0, nodeStorage.getAll().size());
		assertEquals(0, edgeStorage.getAll().size());
	}
}
