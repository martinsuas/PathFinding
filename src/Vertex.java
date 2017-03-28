import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Class used to represent a vertex, or in this case, a location on the RIT campus.
 */
public class Vertex {
    // A three letter name.
    private String name;
    // Used for retrieving the path from searching algorithms.
    private Vertex parent;
    // Used to build the path with costs.
    private int cost_to_parent;
    // Cumulative cost to get to this node, or g(x).
    private int cumulative;
    // Used for A* algorithm.
    private int fx;
    private static final int LARGE_NUMBER = 100000000;

    // The list of edges this vertex is connected to.
    private ArrayList<Edge> edges;

    /**
     * Constructor
     * @param name - The name and identifier of the vertex.
     */
    Vertex(String name) {
        this.name = name;
        this.parent = null;
        this.edges = new ArrayList<Edge>();
        this.cumulative = 0;
        this.fx = LARGE_NUMBER;
    }

    /**
     * Returns a textual representation of the object in standard output.
     * @return String
     */
    public String toString() {
        StringBuilder res = new StringBuilder(name + ":");
        for ( Edge e : edges ) {
            res.append("  =");
            res.append(e.weight()); res.append("=> "); res.append(e.to(this).name());
        }
        return res.toString();
    }

    /**
     * Add an edge to the vertex.
     * @param e - Edge to be added
     */
    void addEdge(Edge e) {
        edges.add(e);
    }

    /**
     * Return an array list of the edges.
     * @return ArrayList
     */
    ArrayList<Edge> edges() {
        return edges;
    }

    /**
     * Retrieve name of the vertex.
     * @return String
     */
    String name() {
        return name;
    }

    /**
     * Return a list of Vertices, which represent the neighbors of this vertex.
     * @return Vertex[]
     */
    Vertex[] neighbors () {
        Vertex[] result = new Vertex[edges.size()];
        int i = 0;
        for (Edge e : edges) {
            result[i] = e.to(this);
            i++;
        }
        return result;
    }

    /**
     * Set this vertex's parent. Used for obtaining a path for searching algorithms.
     * @param P - Parent vertex
     */
    void setParent(Vertex P) {
        for (Edge E : edges) {
            if (E.to(this) == P) {
                this.parent = P;
                this.cost_to_parent = E.weight();
                this.cumulative = P.cumulative + E.weight();
            }
        }
    }

    boolean updateParentIfBetter(Vertex P, HashMap<String, Integer> heuristic) {
        for (Edge E : edges) {
            if (E.to(this) == P) {
                int gx = P.cumulative + E.weight();
                int hx = heuristic.get(this.name());
                int fx = gx + hx;
                if (this.fx > fx) {
                    this.parent = P;
                    this.cost_to_parent = E.weight();
                    this.cumulative = P.cumulative + E.weight();
                    this.fx = fx;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Does this vertex have a parent?
     * @return boolean
     */
    boolean hasParent() {
        return parent != null;
    }

    /**
     * Retrieve this vertex's parent.
     * @return Vertex
     */
    Vertex getParent() {
        if (!hasParent()) {
            return null;
        }
        return parent;
    }

    /**
     * Get the weight of the edge connecting this vertex to its parent.
     * @return int
     */
    int costToParent() {
        return cost_to_parent;
    }

    /**
     * Get the cummulative cost of getting from the beginning vertex to this one
     * in one of the searching algorithms.
     * @return int
     */
    int getFx() {
        return fx;
    }
}