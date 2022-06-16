import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;

import client.Athlete;
import gui.HomeGui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Mates;
import model.UserTesting;

public class Main extends Application
{
	//Main method to launch gui
	public static void main(String[] args) 
	{
		launch(args);
	}

	//Start method declares and displays gui
	@Override
	public void start(Stage stage) throws Exception 
	{	
		//Pass graph to display
		Graph<Athlete, Mates> graph = new GraphEdgeList<>();
		
		//Preset user details for login
		UserTesting test = new UserTesting(graph);
		
		//Initial gui display
		HomeGui home = new HomeGui(graph);
		stage.setScene(new Scene(home.homeGrid));
		stage.setTitle("Athlete Meet Home Page");
		stage.show();
	}

}
