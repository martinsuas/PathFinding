# PathFinding
A simple graph traversal program that finds the best path using different methodologies
(A*, BFS, Backtracking). The A* heuristic function is simply the number of nodes between
the current node and the destination.

To compile:
javac *.java

To run:
java PathMain [a_star|bfs|backtracking] $filename

The file representing the graph should be placed in the 'src' folder. 
Each line in the file should use the following pattern:
V1,V2,E
V1,V3,E

Where V is the name of a vertex or node, and E is the weight of the edge.
Example:
USA,MEXICO,10
USA,CANADA,15
