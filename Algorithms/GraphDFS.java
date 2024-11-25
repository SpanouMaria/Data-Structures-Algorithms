import java.util.*;

public class GraphDFS {
    // Number of vertices in the graph
    private int vertices; 

    // Adjacency list to represent the graph
    private LinkedList<Integer>[] adjacencyList; 

    public GraphDFS(int vertices) {
        this.vertices = vertices;
        // Initialize the adjacency list without type-casting
        adjacencyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>(); // Initialize each list
        }
    }

    // Adds an edge from the source vertex to the destination vertex
    public void addEdge(int source, int destination) {
        adjacencyList[source].add(destination);
    }

    // Performs DFS traversal starting from the given vertex
    public void dfs(int startVertex) {
        // Track visited vertices
        boolean[] visited = new boolean[vertices]; 
        System.out.println("DFS Traversal:");

        // Recursive helper for DFS
        dfsHelper(startVertex, visited); 

        // Newline for clean output
        System.out.println(); 
    }

    // Helper method for DFS that recursively visits nodes
    private void dfsHelper(int vertex, boolean[] visited) {
        // Mark the current vertex as visited
        visited[vertex] = true; 

        // Process the vertex
        System.out.print(vertex + " "); 

        // Recursively visit all unvisited neighbors
        for (int neighbor : adjacencyList[vertex]) {
            if (!visited[neighbor]) {
                dfsHelper(neighbor, visited);
            }
        }
    }
}
