import org.junit.Assert.assertEquals;
import org.junit.Test;

public class AssertTest {
    @Test
    public void evaluateToMethod() {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Edge e = new Edge(v1, v2, 10);
        assertEquals(v2, e.to(v1));
        assertEquals(v1, e.to(v2));
    }

    @Test
    public void evaluateWeight() {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Edge e = new Edge(v1, v2, 10);
        assertEquals(10, e.weight());
    }
}