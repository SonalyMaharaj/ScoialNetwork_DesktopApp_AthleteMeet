package gui;

import java.io.FileInputStream; 
import java.io.FileNotFoundException;

import com.brunomnsilva.smartgraph.graph.Graph;

import client.Athlete;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Mates;

public class HomeGui extends GridPane
{
	private Graph<Athlete, Mates> graph;
	
	//Declare all interface objects
	public GridPane homeGrid = new GridPane();
	private Text txtAppName;
	private Image imgLogo;
	private ImageView imgviewLogo;
	private Button btnSignup;
	private Button btnLogin;
	
	public HomeGui(Graph<Athlete, Mates> graph)
	{
		this.graph = graph;
		setHomeGui();
	}

	public void setHomeGui()
	{
		//Creating interface objects

		//Set heading
		txtAppName = new Text("ATHLETE MEET");
		txtAppName.setFont(Font.font("Verdana", 60));
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
		btnSignup = new Button("Sign Up");
		btnSignup.setStyle("-fx-background-color: #DC143C; ");
		btnSignup.setPrefWidth(225);

		btnLogin = new Button("Login");
		btnLogin.setStyle("-fx-background-color: #DC143C; ");
		btnLogin.setPrefWidth(225);

		//Setting the image view 
		imgviewLogo = new ImageView(imgLogo); 

		//Setting the position of the image 
		imgviewLogo.setX(50); 
		imgviewLogo.setY(25); 

		//setting the fit height and width of the image view 
		imgviewLogo.setFitHeight(300); 
		imgviewLogo.setFitWidth(300); 

		//Setting the preserve ratio of the image view 
		imgviewLogo.setPreserveRatio(true); 
		imgviewLogo.setFitWidth(0);

		//Creating a Grid Pane 
		homeGrid = new GridPane();
		homeGrid.setAlignment(Pos.CENTER);

		//Setting size for the pane  
		homeGrid.setMinSize(500, 500);

		//Setting the padding  
		homeGrid.setPadding(new Insets(50, 50, 50, 50)); 

		//Arranging all the nodes in the pane
		homeGrid.add(txtAppName, 0, 0);
		homeGrid.add(imgviewLogo, 0, 1);
		homeGrid.add(btnSignup, 0, 2);
		homeGrid.add(btnLogin, 1, 2);
		
		btnLogin.setOnAction(e ->
		{
			//Display the login pane when login button clicked on home page
			Stage stage = (Stage)((javafx.scene.Node)e.getSource()).getScene().getWindow(); 
			LoginGui login = new LoginGui(graph); 
			Scene scene = new Scene(login.loginGrid); 
			stage.setTitle("Athlete Meet Login Page");
			stage.setScene(scene); 
			stage.show();
		});
		
		btnSignup.setOnAction(e ->
		{
			//Display the signup pane when signup button clicked on home page
			Stage stage = (Stage)((javafx.scene.Node)e.getSource()).getScene().getWindow(); 
			SignupGui signup = new SignupGui(graph); 
			Scene scene = new Scene(signup.signupGrid); 
			stage.setTitle("Athlete Meet Login Page");
			stage.setScene(scene); 
			stage.show();
		});
	}
}
