import junit.framework.TestCase;

import java.util.Arrays;

public class GraphTest extends TestCase {

    public void testAdd_Edges() throws Exception {
        Graph g = new Graph();
        g.add_edge("V1", "V2", 7);
        g.add_edge("V1", "V3", 4);
        g.add_edge("V4", "V2", 11);

        // Print graph
        System.out.println(g);

        // Assert neighbors are correct.
        Vertex[] neighbors = g.vertex("V1").neighbors();
        assertEquals(g.vertex("V2"), g.vertex(neighbors[0].name()));
        assertEquals(g.vertex("V1").edges().get(0).to(g.vertex("V1")), g.vertex("V2"));
    }
}