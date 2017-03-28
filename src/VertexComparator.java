import java.util.Comparator;

/**
 * Comparator function for the A* Priority Queue.
 */
public class VertexComparator implements Comparator<Vertex> {
    @Override
    public int compare(Vertex A, Vertex B) {
        if (A.getFx() < B.getFx()) {
            return -1;
        } else if (A.getFx() > B.getFx()) {
            return 1;
        } else {
            return 0;
        }
    }
}
