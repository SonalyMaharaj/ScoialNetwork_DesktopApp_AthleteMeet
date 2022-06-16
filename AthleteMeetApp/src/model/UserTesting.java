package model;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;

import client.Athlete;

public class UserTesting 
{
	public Graph<Athlete, Mates> graph = new GraphEdgeList<>();
	
	public UserTesting(Graph<Athlete, Mates> graph)
	{
		//create users for loging in (pre existing)
		Athlete Ronaldo = new Athlete("Ronaldo", "36 - 45", "Soccer","Manchester United", "10", "CR7");
		Athlete Tendulkar = new Athlete("Tendulkar", "36 - 45", "Cricket", "Mumbai Indians","7", "Tendlya");
		Athlete Kobe = new Athlete("Kobe", "36 - 45", "Basketball", "Los Angeles Lakers","9", "KB8");
		Athlete Rashford = new Athlete("Rashford", "15 - 25", "Soccer", "Manchester United","9", "Prince");
		Athlete LeBron = new Athlete("LeBron", "36 - 45", "Basketball", "Los Angeles Lakers","5", "James");
		
		//ensure node is set for these users on graph
		graph.insertVertex(Ronaldo);
		graph.insertVertex(Tendulkar);
		graph.insertVertex(Kobe);
		graph.insertVertex(Rashford);
		graph.insertVertex(LeBron);
		
		//create connections/ edges between users with matching properties
		graph.insertEdge(Ronaldo, Tendulkar, new Mates(Ronaldo, Tendulkar));
		graph.insertEdge(Ronaldo, Kobe, new Mates(Ronaldo, Kobe));
		graph.insertEdge(Tendulkar, Kobe, new Mates(Tendulkar, Kobe));
		graph.insertEdge(Rashford, Ronaldo, new Mates(Rashford, Ronaldo));
		graph.insertEdge(LeBron, Kobe, new Mates(LeBron, Kobe));
		graph.insertEdge(LeBron, Ronaldo, new Mates(LeBron, Ronaldo));
		graph.insertEdge(Rashford, Kobe, new Mates(Rashford, Kobe));
		graph.insertEdge(LeBron, Tendulkar, new Mates(LeBron, Tendulkar));
	}
}
