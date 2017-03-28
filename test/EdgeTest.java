import junit.framework.TestCase;
import org.junit.Assert;

public class EdgeTest extends TestCase {
    public void testTo() {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("A");

        Edge e = new Edge(v1, v2, 10);
        assertEquals(v2, e.to(v1));
        assertEquals(v1, e.to(v2));
        assertNotSame(v3, e.to(v1));

        try {
            e.to(v3);
            Assert.fail("Expected IllegalArgumentException.");
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    public void testWeight() {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Edge e = new Edge(v1, v2, 10);
        assertEquals(10, e.weight());
    }
}