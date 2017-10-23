import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;

import java.util.*;


public class Reader {
	public static List<Vertex> findVerticies ()
	{
		List<Vertex> list = new ArrayList<Vertex>();
		try
		{	
			FileReader fr = new java.io.FileReader ("SHORTESTDISTANCE.csv");
			Scanner fileScanner = new Scanner (fr);
			fileScanner.nextLine(); // first line contains a header with no data
			while (fileScanner.hasNextLine())
			{
				String line = fileScanner.nextLine();
				String[] strings = line.split(",");
				Vertex newVertex = new Vertex(strings[0]);
				list.add(newVertex);
			}
			list = addEdges(list);
			
		}
		catch (IOException e)
		{
			System.out.println("I/O Error");
		}
		return list;
	}
	
	/**
	 * Adds edges onto each of the verticies in a given list
	 * 
	 * @param list		list of verticies without edges
	 * @return			Returns a list of verticies with edges from the file
	 */
	public static List<Vertex> addEdges (List<Vertex> list)
	{
		try
		{	
			FileReader fr = new java.io.FileReader ("SHORTESTDISTANCE.csv");
			Scanner fileScanner = new Scanner (fr);
			fileScanner.nextLine(); // first line contains a header with no data
			int i = 0;
			while (fileScanner.hasNextLine())
			{
				String line = fileScanner.nextLine();
				String[] strings = line.split(",");
				
				list.get(i).adjacencies = new Edge[(strings.length - 1) / 2]; //number of edges is half total array - 1
				for (int index = 1; index < strings.length; index += 2)
				{
					list.get(i).adjacencies[(index - 1) / 2] = new Edge(findVertex(list, strings[index]), Double.parseDouble(strings[index + 1]));
				}
				i++;
			}
		}
		catch (IOException e)
		{
			System.out.println("I/O Error");
			
		}
		return list;
	}
	
	/**
	 * Finds a vertex by name in a list of verticies
	 * 
	 * @param list		List containing the desired vertex
	 * @param name		Name of desired vertex
	 * @return			Returns a pointer to the desired vertes
	 */
	public static Vertex findVertex (List<Vertex> list, String name)
	{
		for (Vertex vert : list)
		{
			if (vert.name.equals(name))
			{
				return vert;
			}
		}
		return null;
	}
	
	
	
	
	
	
}