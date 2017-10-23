import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra
{
	/**
	 * Computes and assigns the minimum distance value for verticies
	 * 
	 * @param source	The starting vertex
	 */
	public static void computePaths(Vertex source)
	{
		source.minDistance = 0.;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			// Visit edges exiting u
			for (Edge e : u.adjacencies)
			{
				Vertex v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);

					v.minDistance = distanceThroughU ;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}

	/**
	 * Computes the shortest path to the target vertex
	 * @param target		Target vertex
	 * @return				Returns a list with all the verticies you must go through
	 */
	public static List<Vertex> getShortestPathTo(Vertex target)
	{
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex);

		//path is constructed backwards
		Collections.reverse(path);
		return path;
	}

	public static void main(String[] args)
	{
		//read in data and obtain use input
		Scanner imput = new Scanner(System.in);
		List<Vertex> verts = Reader.findVerticies();
		System.out.println("Enter the name of the starting character:");
		Vertex start = Reader.findVertex(verts, imput.nextLine());
		System.out.println("Enter the name of the ending character:");
		Vertex end = Reader.findVertex(verts, imput.nextLine());

		// run Dijkstra
		computePaths(start); 

		//output
		System.out.println("Distance to " + end + ": " + end.minDistance);
		List<Vertex> path = getShortestPathTo(end);
		System.out.println("Path: " + path);
	}
}
