/**
 * This class represents the edge of a graph.
 */
public class Edge {
    // The two vertices this edge connects
    private Vertex v1, v2;
    // The cost of this edge.
    private int weight;

    /**
     * Constructor.
     * @param v1 Vertex A
     * @param v2 Vertex B
     * @param weight Cost of the edge.
     */
    Edge(Vertex v1, Vertex v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    /**
     * For printing and debugging purposes.
     * @return String - Displays the edge.
     */
    public String toString() {
        return "[" + v1 + "] -- " + weight + " -- [" + v2 + "]";
    }

    /**
     * Given one of the two vertices of this edge, find the connecting one.
     * @param v - Should be the current vertex.
     * @return Vertex - The connecting vertex.
     */
    Vertex to(Vertex v) {
        if (v == this.v1) {
            return v2;
        } else if (v == this.v2) {
            return v1;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Get the weight of thiss edge.
     * @return int
     */
    int weight() {
        return this.weight;
    }
}