import java.util.Stack;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Represents a graph made out of vertices and edges. Includes three methods of traversals:
 * DFS, Backtracking, and A*.
 */
public class Graph {
    // A map with the name of the buildings as key and its respective vertex object as value.
    private HashMap<String, Vertex> buildings;
    // Stores edges; this is never directly accessed except when the references are passed to each vertex
    // that the edge connects or when referenced from the edge pointers in each vertex.
    private Stack<Edge> paths;

    /**
     * Constructor
     */
	Graph() {
		this.buildings = new HashMap<String, Vertex>();
		this.paths = new Stack<Edge>();
	}

    /**
     * Adds an edge to the graph with it's two respective vertex, if they don't exist.
     * @param v1 - Vertex A
     * @param v2 - Vertex B
     * @param weight - the weight of the edge connecting the two vertices.
     */
	void add_edge(String v1, String v2, int weight ) {
	    // If the vertex/vertices do not exist yet, create them in the map.
		if (!buildings.containsKey(v1)) {
            buildings.put(v1, new Vertex(v1));
        }
        if (!buildings.containsKey(v2)) {
		    buildings.put(v2, new Vertex(v2));
        }

        // Add connecting edge to both
        paths.push(new Edge(buildings.get(v1), buildings.get(v2), weight));
		buildings.get(v1).addEdge(paths.peek());
        buildings.get(v2).addEdge(paths.peek());
	}

    /**
     * Get the vertex with the given name.
     * @param name String - name of the building
     * @return Vertex object
     */
	Vertex vertex(String name) {
	    return buildings.get(name);
    }

    /**
     * For debugging/printing purposes; prints every vertex per line with its respective neighbors.
     * @return String
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
	    for (Vertex v : buildings.values()) {
            sb.append(v.toString()); sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Performs BFS on the graph to find a path from Vertex A to Vertex B.
     * @param sA - Name of Vertex A
     * @param sB - Name of Vertex B
     * @return - A string representation of the path.
     */
    String BFS(String sA, String sB) {
	    Vertex A = buildings.get(sA);
	    Vertex B = buildings.get(sB);
        if (A == null || B == null) {
            throw new IllegalArgumentException("At least one of the vertices in this function is not in the graph.");
        }
        // Handle case where A == B
        if (A == B) {
            return "Path from " + A.name() + " to " + B.name() + ":\n\t" + A.name();
        }

        // The following will be utilized as queues.
        LinkedList<Vertex> frontier = new LinkedList<Vertex>();
        LinkedList<Vertex> visited = new LinkedList<Vertex>();
        frontier.add(A);
        while(!frontier.isEmpty()) {
            Vertex F = frontier.pop();
            visited.add(F);
            for (Vertex N : F.neighbors()) {
                // If solution is found.
                if (N == B) {
                    N.setParent(F);
                    // Build a path and then return it.
                    Stack<Vertex> path = new Stack<Vertex>();
                    path.add(N);
                    Vertex C = N;
                    while (C.hasParent()) {
                        path.add(C.getParent());
                        C = C.getParent();
                    }
                    return getPath(path, A, B);
                }
                // If neighbor is not in either one of the queues.
                if (!visited.contains(N) && !frontier.contains(N)) {
                    N.setParent(F);
                    frontier.add(N);
                }
            }
        }
        return "No path from " + A.name() + " to " + B.name() + " could be found.";
    }

    /**
     * Performs Backtracking on the graph to find a path from Vertex A to Vertex B.
     * @param sA - Name of Vertex A
     * @param sB - Name of Vertex B
     * @return - A string representation of the path.
     */
    String Backtracking(String sA, String sB) {
        Vertex A = buildings.get(sA);
        Vertex B = buildings.get(sB);
        if (A == null || B == null) {
            throw new IllegalArgumentException("At least one of the vertices in this function is not in the graph.");
        }
        // Handle case where A == B
        if (A == B) {
            return "Path from " + A.name() + " to " + B.name() + ":\n\t" + A.name();
        }

        LinkedList<Vertex> visited = new LinkedList<Vertex>();
        Stack<Vertex> result = backtracking_helper(A, B, visited);
        if (result != null) {
            return getPath(result, A, B);
        } else {
            return "No path from " + A.name() + " to " + B.name() + " could be found.";
        }
    }

    /**
     * Helper function for Backtracking() algorithm above.
     * @param C - Current node being visited.
     * @param Goal - Destination node.
     * @param visited - An array containing all visited nodes.
     * @return - A Stack made out of vertices that represents the path in reverse order.
     */
    @SuppressWarnings("unchecked")
    private Stack<Vertex> backtracking_helper(Vertex C, Vertex Goal, LinkedList<Vertex> visited) {
        if (C == Goal) {
            Stack<Vertex> result = new Stack<Vertex>();
            result.push(C);
            return result;
        } else {
            for (Vertex N : C.neighbors()) {
                if (!visited.contains(N)) {
                    LinkedList<Vertex> copy = (LinkedList<Vertex>) visited.clone();
                    copy.add(N);
                    Stack<Vertex> result = backtracking_helper(N, Goal, copy);
                    if (result != null) {
                        result.peek().setParent(C);
                        result.push(C);
                        return result;
                    }
                }
            }
            return null;
        }
    }

    /**
     * Performs A* on the graph to find a path from Vertex A to Vertex B.
     * @param sA - Name of Vertex A
     * @param sB - Name of Vertex B
     * @param heuristic - A hashmap representing a heuristic function; each name of the building maps to the
     *                  respective h(x) value.
     * @return - A string representation of the path.
     */
    String A_Star(String sA, String sB, HashMap<String, Integer> heuristic) {
        Vertex A = buildings.get(sA);
        Vertex B = buildings.get(sB);
        if (A == null || B == null) {
            throw new IllegalArgumentException("At least one of the vertices in this function is not in the graph.");
        }
        // Handle case where A == B
        if (A == B) {
            return "Path from " + A.name() + " to " + B.name() + ":\n\t" + A.name();
        }
        PriorityQueue<Vertex> frontier = new PriorityQueue<>(50, new VertexComparator());
        LinkedList<Vertex> expanded = new LinkedList<>();
        frontier.add(A);
        while(!frontier.isEmpty()) {
            Vertex F = frontier.poll();
            expanded.add(F);
            for (Vertex N : F.neighbors()) {
                // If solution is found.
                if (N == B) {
                    N.updateParentIfBetter(F, heuristic);
                    // Build a path and then return it.
                    Stack<Vertex> path = new Stack<Vertex>();
                    path.add(N);
                    Vertex C = N;
                    while (C.hasParent()) {
                        path.add(C.getParent());
                        C = C.getParent();
                    }
                    return getPath(path, A, B);
                }
                // If neighbor is not in either one of the queues.
                if (!expanded.contains(N)) {
                    N.updateParentIfBetter(F, heuristic);
                    if (!frontier.contains(N)) {
                        frontier.add(N);
                    }
                }
            }
        }
        return "No path from " + A.name() + " to " + B.name() + " could be found.";
    }

    /**
     * Returns a string representing the path in the form:
     *   O -x1-> A -x2-> ... -xn-> D
     * Where O is the origin and D is the destination. xi is the weight of the edge connecting each respective
     * vertex.
     * @param path - A stack containing each vertex of the path in reverse order (destination is on top)
     * @param A - Origin
     * @param B - Destination
     * @return A string representation of the path.
     */
    private String getPath(Stack<Vertex> path, Vertex A, Vertex B) {
        StringBuilder spath = new StringBuilder("Path from " + A.name() + " to " + B.name()
                + ":\n\t" + A.name());

        // Remove A, for easier formatting and since we know it always starts at A.
        path.pop();
        while (!path.isEmpty()) {
            Vertex C = path.pop();
            spath.append(" -");
            spath.append(C.costToParent());
            spath.append("-> ");
            spath.append(C.name());
        }
        return spath.toString();
    }
}