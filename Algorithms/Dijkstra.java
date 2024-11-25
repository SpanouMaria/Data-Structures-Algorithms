import java.util.*;

class Dijkstra {
    static class Edge {
        int destination, weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Graph {
        // Number of vertices in the graph
        private int vertices; 
        
        // Adjacency list to store weighted edges
        private List<Edge>[] adjacencyList;

        public Graph(int vertices) {
            this.vertices = vertices;
            // Initialize the adjacency list without type-casting
            adjacencyList = new ArrayList[vertices];
            for (int i = 0; i < vertices; i++) {
                // Initialize each list
                adjacencyList[i] = new ArrayList<>(); 
            }
        }

        // Adds a weighted edge from the source to the destination
        public void addEdge(int source, int destination, int weight) {
            adjacencyList[source].add(new Edge(destination, weight));
        }

        // Performs Dijkstra's algorithm to find shortest paths from the start vertex
        public void dijkstra(int startVertex) {
            // Store shortest distances
            int[] distances = new int[vertices]; 

            // Initialize distances to infinity
            Arrays.fill(distances, Integer.MAX_VALUE); 

            // Distance to start vertex is 0
            distances[startVertex] = 0; 

            // Min-heap (priority queue) to fetch the vertex with the smallest distance
            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
            pq.add(new Edge(startVertex, 0));

            // Track visited vertices
            boolean[] visited = new boolean[vertices]; 

            while (!pq.isEmpty()) {
                // Get the vertex with the smallest distance
                Edge current = pq.poll(); 
                int vertex = current.destination;

                // Skip if already visited
                if (visited[vertex]) continue; 

                // Mark as visited
                visited[vertex] = true; 

                // Update distances for all neighbors
                for (Edge edge : adjacencyList[vertex]) {
                    int neighbor = edge.destination;
                    int newDist = distances[vertex] + edge.weight;

                    // If a shorter path is found
                    if (newDist < distances[neighbor]) { 
                        // Update distance
                        distances[neighbor] = newDist; 
                        // Push updated distance to the queue
                        pq.add(new Edge(neighbor, newDist)); 
                    }
                }
            }

            // Print the shortest distances from the start vertex
            System.out.println("Dijkstra's Shortest Path:");
            for (int i = 0; i < vertices; i++) {
                System.out.println("Vertex " + i + ": " + distances[i]);
            }
        }
    }
}
