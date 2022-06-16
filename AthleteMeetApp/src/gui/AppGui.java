package gui;

import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.brunomnsilva.smartgraph.graph.Edge;
import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import client.Athlete;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Mates;
import model.Queue;

public class AppGui extends GridPane
{
	//Declare all interface objects
	public GridPane appGrid = new GridPane();
	private Text txtAppName;
	private Image imgLogo;
	private ImageView imgviewLogo;
	private Button btnMates;
	private TextArea taMates;
	private Button btnTraverse;
	private TextArea taFittest;
	private Button btnDisplayGraph;
	private Athlete athlete;
	private Graph<Athlete, Mates> graph;

	//constructor to initialize app gui
	public AppGui(Athlete athlete, Graph<Athlete, Mates> graph) 
	{
		this.athlete = athlete;
		this.graph = graph;
		setAppGui();
		displayAthleteDetails();
	}

	public void setAppGui()
	{
		//Set heading
		txtAppName = new Text("ATHLETE MEET");
		txtAppName.setFont(Font.font("Verdana", 55));
		txtAppName.setFill(Color.CRIMSON);

		//Set logo image
		try 
		{
			imgLogo = new Image(new FileInputStream("images/logo.jpg"));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

		//Set button
		btnMates = new Button("Meet Mates"); 
		btnMates.setStyle("-fx-background-color: #DC143C; "); 
		btnMates.setPrefWidth(400);

		btnTraverse = new Button("Traverse to Fittest Mate"); 
		btnTraverse.setStyle("-fx-background-color: #DC143C; "); 
		btnTraverse.setPrefWidth(400);
		
		btnDisplayGraph = new Button("Display Graph"); 
		btnDisplayGraph.setStyle("-fx-background-color: #DC143C; "); 
		btnDisplayGraph.setPrefWidth(400);

		//Set texts and boxes
		taMates = new TextArea();
		taFittest = new TextArea();
		taFittest.setPrefHeight(50);

		//Setting the image view 
		imgviewLogo = new ImageView(imgLogo); 

		//Setting the position of the image 
		imgviewLogo.setX(50); 
		imgviewLogo.setY(25); 

		//setting the fit height and width of the image view 
		imgviewLogo.setFitHeight(100); 
		imgviewLogo.setFitWidth(100); 

		//Setting the preserve ratio of the image view 
		imgviewLogo.setPreserveRatio(true); 

		//Creating a Grid Pane 
		appGrid = new GridPane();

		//Setting size for the pane  
		appGrid.setMinSize(500, 500); 

		//Setting the padding  
		appGrid.setPadding(new Insets(50, 50, 50, 50)); 

		//Setting the vertical and horizontal gaps between the columns 
		appGrid.setVgap(5); 
		appGrid.setHgap(5);       

		//Setting the Grid alignment 
		appGrid.setAlignment(Pos.CENTER); 

		//Arranging all the nodes in the grid 
		appGrid.add(txtAppName, 0, 0);
		appGrid.add(imgviewLogo, 1, 0);
		appGrid.add(btnMates, 0, 1);
		appGrid.add(taMates, 0, 2);
		appGrid.add(btnDisplayGraph, 0, 3);
		appGrid.add(btnTraverse, 0, 4);
		appGrid.add(taFittest, 0, 5);

		btnMates.setOnAction(e ->
		{
			displayMates();
		});
		
		btnDisplayGraph.setOnAction(e -> 
		{
			displayGraph();
		});
		
		btnTraverse.setOnAction(e ->
		{
			findFittest();
		});
		
		/*
		 * btnMates.setOnAction(e -> {
		 * taMates.setText("List of Athlete Profiles with similar interests: \n");
		 * taMates.setText(sp.tfName.getText() + ", " + sp.cbAge.getValue() + ", " +
		 * sp.cbSport.getValue() + ", " + sp.cbTeam.getValue() + ", " +
		 * sp.cbFitness.getValue()); });
		 */
	}
	
	//function to display users details in text area
	private void displayAthleteDetails()
	{
		taMates.setText("Name: " + athlete.getUsername() + "\nAge Range: " + athlete.getAge() + 
				"\nSport: " + athlete.getSport() + "\nTeam: " + athlete.getTeam() + "\nFitness: " + athlete.getFitness() + "\n");
	}
	
	private void displayMates()
	{
		Collection<Vertex<Athlete>> athletes = graph.vertices();
		
		for(Vertex<Athlete> a : athletes)
		{
			Athlete user = a.element();
			if(!user.getUsername().equals(athlete.getUsername()))
			{
				Mates mate = new Mates(athlete, user);
				ArrayList<String> arrMate = mate.getMates();
				if(!arrMate.isEmpty())
				{
					taMates.appendText(user.getUsername() + "(" + user.getAge() + ", " + user.getSport() + ", " + user.getTeam() + ", " + user.getFitness() + ")" + "\n");
				}
			}
		}
		
		if(taMates.getText().isBlank())
		{
			taMates.setText("You have no mates.");
		}
	}
	
	private void displayGraph()
	{
		Stage stage = new Stage();
		
		SmartPlacementStrategy smartPlace = new SmartCircularSortedPlacementStrategy();
		SmartGraphPanel<Athlete, Mates> view = new SmartGraphPanel<>(graph, smartPlace);

		stage.setScene(new Scene(view, 500, 500));
		stage.setTitle("Athlete Meet Social Graph");
		stage.show();
		
		view.init();
		view.setAutomaticLayout(true);
		view.getStylableVertex(getAthlete()).setStyleClass("myVertex");
	}
	
	private Vertex<Athlete> getAthlete()
	{
		Collection<Vertex<Athlete>> athletes = graph.vertices();
		for(Vertex<Athlete> a : athletes)
		{
			Athlete ath = a.element();
			if(ath.getUsername().equals(athlete.getUsername()))
			{
				return a;
			}
		}
		return null;
	}
	
	private void BFS(List<Vertex<Athlete>> vertices, Vertex<Athlete> ath)
	{
		ArrayList<Vertex<Athlete>> visited = new ArrayList<>();
		LinkedList<Vertex<Athlete>> queue = new LinkedList<>();
		
		visited.add(ath);
		queue.add(ath);
		
		while(queue.size() != 0)
		{
			ath = queue.poll();
			vertices.add(ath); 
			
			Iterator<Vertex<Athlete>> iterator = graph.vertices().iterator();
			while(iterator.hasNext())
			{
				Vertex<Athlete> a = iterator.next();
				if(!visited.contains(a))
				{
					visited.add(a);
					queue.add(a);
				}
			}
		}
	}
	
	private void findFittest()
	{
		List<Vertex<Athlete>> vertices = new ArrayList<>();
		BFS(vertices, getAthlete());
		
		List<Integer> fitness = new ArrayList<>();
		List<Vertex<Athlete>> athletes = new ArrayList<>();
		for(Vertex<Athlete> a : vertices)
		{
			Athlete user = a.element();
			Mates mates = new Mates(user, athlete);
			ArrayList<String> common = mates.getMates();
			if(!common.isEmpty())
			{
				athletes.add(a);
				fitness.add(Integer.parseInt(a.element().getFitness()));
			}
		}
		
		int max = Collections.max(fitness);
		System.out.println(max);
		
		taFittest.setText("");
		for(Vertex<Athlete> a : athletes)
		{
			if(a.element().getFitness().equals(max + ""))
			{
				taFittest.appendText("MVP: " + a.element().getUsername() + " with a fitness level of " + a.element().getFitness() + "\n");
			}
		}
	}
	
	/*
	 * private void BFS(Vertex<Athlete> currentAthlete, Vertex<Athlete>
	 * fittestAthlete) { Set<Vertex<Athlete>> visited = new HashSet<>();
	 * 
	 * List<Vertex<Athlete>> snapshot = new ArrayList<>(); List<Edge<Athlete,
	 * Mates>> pathtoTake = new ArrayList<>(); int index = 0;
	 * if(!graph.vertices().isEmpty()) { Queue<Vertex<Athlete>> queue = new
	 * Queue<>(); queue.enqueue(currentAthlete);
	 * 
	 * visited.add(currentAthlete); String results = "";
	 * 
	 * while(queue.size() != 0) { System.out.println("iteration number " + index);
	 * Vertex<Athlete> p = queue.dequeue(); snapshot.add(p);
	 * if(!visited.contains(p)) { p.getValue().setUserName("visited" + index); }
	 * 
	 * visited.add(p); results += p.getValue().getUserName()+" -----> " ;
	 * 
	 * if(p.equals(fittestAthlete)) { System.out.println("");
	 * p.getValue().setUserName("visited " + index + " Found lastly!");
	 * 
	 * Vertex<Athlete> currentVertex = p;
	 * 
	 * System.out.println("currentVertex is " + currentVertex); while(currentVertex
	 * != null && currentAthlete.compareTo(currentVertex) != 0) { for(Edge<Athlete>
	 * edge:currentVertex.getEdges()) { if(visited.contains(edge.getFromVertex())) {
	 * 
	 * pathtoTake.add(edge); System.out.println("edge " +
	 * edge.getFromVertex().getValue().getUserName() +"-->" +
	 * edge.getToVertex().getValue().getUserName() + " Added to path");
	 * currentVertex = edge.getToVertex(); System.out.println("currentVertex is " +
	 * currentVertex); break; }
	 * 
	 * } } break; } for(Edge<Athlete, Mates> edge:p.getEdges()) {
	 * if(!visited.contains(edge.getToVertex())) {
	 * queue.enqueue(edge.getToVertex()); }
	 * 
	 * } index++; }
	 * 
	 * currentAthlete.getValue().setUserName("visited 0");
	 * System.out.println(results); }
	 * 
	 * }
	 */
}
