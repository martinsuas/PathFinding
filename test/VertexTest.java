import junit.framework.TestCase;

/**
 * Created by Martin on 3/26/2017.
 */
public class VertexTest extends TestCase {
    public void testEdges() throws Exception {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");
        Vertex v4 = new Vertex("D");

        Edge e1 = new Edge(v1, v2, 3);
        v1.addEdge(e1);
        v2.addEdge(e1);
        Edge e2 = new Edge(v1, v3, 4);
        v1.addEdge(e2);
        v3.addEdge(e2);
        Edge e3 = new Edge(v1, v4, 5);
        v1.addEdge(e3);
        v4.addEdge(e3);
        Edge e4 = new Edge(v2, v3, 6);
        v2.addEdge(e4);
        v3.addEdge(e4);
        Edge e5 = new Edge(v2, v4, 7);
        v2.addEdge(e5);
        v4.addEdge(e5);
        Edge e6 = new Edge(v3, v4, 8);
        v3.addEdge(e6);
        v4.addEdge(e6);

        assertEquals(v1.edges().get(0), e1);
        assertEquals(v1.edges().get(1), e2);
        assertEquals(v1.edges().get(2), e3);
    }

    public void testName() throws Exception {
        Vertex v1 = new Vertex("A");
        assertEquals("A", v1.name());
    }

}