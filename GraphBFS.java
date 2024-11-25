import java.util.*;

public class GraphBFS {
    // Number of vertices in the graph
    private int vertices; 
    
    // Adjacency list to represent the graph
    private LinkedList<Integer>[] adjacencyList; 

    public GraphBFS(int vertices) {
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

    // Performs BFS traversal starting from the given vertex
    public void bfs(int startVertex) {
        boolean[] visited = new boolean[vertices];    // Track visited vertices
        Queue<Integer> queue = new LinkedList<>();    // Queue for BFS
        visited[startVertex] = true;                  // Mark the start vertex as visited
        queue.add(startVertex);                       // Add the start vertex to the queue

        System.out.println("BFS Traversal:");
        while (!queue.isEmpty()) {
            int vertex = queue.poll();      // Dequeue the next vertex
            System.out.print(vertex + " "); // Process the vertex

            // Explore all neighbors of the current vertex
            for (int neighbor : adjacencyList[vertex]) {
                if (!visited[neighbor]) {
                    // Mark the neighbor as visited
                    visited[neighbor] = true; 
                    
                    // Add the neighbor to the queue
                    queue.add(neighbor); 
                }
            }
        }
        // Newline for clean output
        System.out.println(); 
    }
}
