package gui;

import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.Vertex;

import client.Athlete;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Mates;

public class SignupGui 
{
	public Athlete athlete;
	public Graph<Athlete, Mates> graph;

	//Declare all interface objects
	public GridPane signupGrid = new GridPane();
	public Text txtAppName;
	public Image imgLogo;
	public ImageView imgviewLogo;
	public TextField tfName;
	public Label lblAge;
	public ComboBox<String> cbAge;
	public Label lblSport;
	public ComboBox<String> cbSport;
	public Label lblTeam;
	public ComboBox<String> cbTeam;
	public Label lblFitness;
	public ComboBox<String> cbFitness;
	public TextField tfPassword;
	public Button btnSignup;

	public SignupGui(Graph<Athlete, Mates> graph)
	{
		this.graph = graph;
		setSignupGui();
	}

	public void setSignupGui()
	{
		//Creating interface objects

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

		//Set texts and boxes
		tfName = new TextField("Enter your Username");
		lblAge = new Label("Age Range:");
		cbAge = new ComboBox<String>();
		lblSport = new Label("Sport Domain:");
		cbSport = new ComboBox<String>();
		lblTeam = new Label("Supporting Team:");
		cbTeam = new ComboBox<String>();
		lblFitness = new Label("Fitness Level:");
		cbFitness = new ComboBox<String>();
		tfPassword = new TextField("Enter you Password");

		//Set button
		btnSignup = new Button("Sign Up"); 
		btnSignup.setStyle("-fx-background-color: #DC143C; "); 
		btnSignup.setPrefWidth(400);

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
		signupGrid = new GridPane();

		//Setting size for the pane  
		signupGrid.setMinSize(500, 500); 

		//Setting the padding  
		signupGrid.setPadding(new Insets(50, 50, 50, 50)); 

		//Setting the vertical and horizontal gaps between the columns 
		signupGrid.setVgap(5); 
		signupGrid.setHgap(5);       

		//Setting the Grid alignment 
		signupGrid.setAlignment(Pos.CENTER); 

		//Arranging all the nodes in the grid 
		signupGrid.add(txtAppName, 0, 0);
		signupGrid.add(imgviewLogo, 1, 0);
		signupGrid.add(tfName, 0, 1);
		signupGrid.add(lblAge, 0, 2);
		cbAge.getItems().add("15 - 25");
		cbAge.getItems().add("26 - 35");
		cbAge.getItems().add("36 - 45");
		signupGrid.add(cbAge, 1, 2);

		//Set sport box
		cbSport = new ComboBox<>();
		cbSport.getItems().add("Soccer");
		cbSport.getItems().add("Cricket");
		cbSport.getItems().add("Basketball");

		//Set team box
		cbTeam = new ComboBox<>();
		signupGrid.add(lblSport, 0, 3);
		signupGrid.add(cbSport, 1, 3);
		signupGrid.add(lblTeam, 0, 4);
		signupGrid.add(cbTeam, 1, 4);

		//Team choice depends on the port chosen
		cbSport.setOnAction(e ->
		{
			String strSelection = cbSport.getValue();
			cbTeam.getItems().clear();

			switch(strSelection)
			{
			case "Soccer":
				cbTeam.getItems().add("Manchester United");
				cbTeam.getItems().add("Liverpool");
				cbTeam.getItems().add("Chelsea");
				cbTeam.getItems().add("Manchester City");
				cbTeam.getItems().add("Real Madrid");
				break;
			case "Cricket":
				cbTeam.getItems().add("Royal Challengers Bangalore");
				cbTeam.getItems().add("Mumbai Indians");
				cbTeam.getItems().add("Chennai Super Kings");
				cbTeam.getItems().add("Kolkata Knight Riders");
				cbTeam.getItems().add("Delhi Capitals");
				break;
			case "Basketball":
				cbTeam.getItems().add("Los Angeles Lakers");
				cbTeam.getItems().add("Boston Celtics");
				cbTeam.getItems().add("Brooklyn Nets");
				cbTeam.getItems().add("Chicago Bulls");
				cbTeam.getItems().add("Atlanta Hawks");
				break;
			default:
				System.out.println("Error: Unacceptable choice selected.");
				break;
			}
		});

		//Allocate the rest of the interface objects
		signupGrid.add(lblFitness, 0, 5);
		cbFitness.getItems().add("0");
		cbFitness.getItems().add("1");
		cbFitness.getItems().add("2");
		cbFitness.getItems().add("3");
		cbFitness.getItems().add("4");
		cbFitness.getItems().add("5");
		cbFitness.getItems().add("6");
		cbFitness.getItems().add("7");
		cbFitness.getItems().add("8");
		cbFitness.getItems().add("9");
		cbFitness.getItems().add("10");
		signupGrid.add(cbFitness, 1, 5);
		signupGrid.add(tfPassword, 0, 6);
		signupGrid.add(btnSignup, 0, 7);

		btnSignup.setOnAction(e ->
		{
			athlete = new Athlete(tfName.getText(), cbAge.getValue(), cbSport.getValue(), cbTeam.getValue(), cbFitness.getValue(), tfPassword.getText());
			graph.insertVertex(athlete);
			createEdges();
			
			//Display the app pane when signup button clicked on signup page
			Stage stage = (Stage)((javafx.scene.Node)e.getSource()).getScene().getWindow(); 
			AppGui app = new AppGui(athlete, graph); 
			Scene scene = new Scene(app.appGrid); 
			stage.setTitle("Athlete Meet App Page");
			stage.setScene(scene); 
			stage.show();
		});

	}
	
	private void createEdges()
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
					graph.insertEdge(athlete, user, mate);
				}
			}
		}
	}
}
