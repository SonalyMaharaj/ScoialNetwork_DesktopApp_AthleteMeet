package gui;

import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.util.Collection;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.Vertex;

import client.Athlete;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Mates;

public class LoginGui extends GridPane
{
	public GridPane loginGrid = new GridPane();
	public Text txtAppName;
	public Image imgLogo;
	public ImageView imgviewLogo;
	private TextField tfUsername;
	private TextField tfPassword;
	private Button btnLogin;
	private Athlete athlete;
	private Graph<Athlete, Mates> graph;
	
	
	public LoginGui(Graph<Athlete, Mates> graph)
	{
		this.graph = graph;
		setLoginGui();
	}
	
	public void setLoginGui()
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

		tfUsername = new TextField("Enter your Username");
		tfPassword = new TextField("Enter your Password");

		//Set button
		btnLogin = new Button("Login"); 
		btnLogin.setStyle("-fx-background-color: #DC143C; "); 
		btnLogin.setPrefWidth(400);

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

		//Setting size for the pane  
		loginGrid.setMinSize(500, 500); 

		//Setting the padding  
		loginGrid.setPadding(new Insets(50, 50, 50, 50)); 

		//Setting the vertical and horizontal gaps between the columns 
		loginGrid.setVgap(5); 
		loginGrid.setHgap(5);       

		//Setting the Grid alignment 
		loginGrid.setAlignment(Pos.CENTER); 

		//Arranging all the nodes in the grid 
		loginGrid.add(txtAppName, 0, 0);
		loginGrid.add(imgviewLogo, 1, 0);
		loginGrid.add(tfUsername, 0, 1);
		loginGrid.add(tfPassword, 0, 2);
		loginGrid.add(btnLogin, 0, 3);
		
		btnLogin.setOnAction(e ->
		{
			if(verifyAthlete())
			{
				//Display the app pane when login button clicked on login page
				Stage stage = (Stage)((javafx.scene.Node)e.getSource()).getScene().getWindow(); 
				AppGui app = new AppGui(athlete, graph); 
				Scene scene = new Scene(app.appGrid); 
				stage.setTitle("Athlete Meet App Page");
				stage.setScene(scene); 
				stage.show();
			}else
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Username and password do not match");
				alert.showAndWait();
			}
			
		});
	}
	
	private boolean verifyAthlete()
	{
		Collection<Vertex<Athlete>> athletes =  graph.vertices();
		for(Vertex<Athlete> a : athletes)
		{
			if(a.element().getUsername().equals(tfUsername.getText()) && a.element().getPassword().equals(tfPassword.getText()))
			{
				athlete = a.element();
				return true;
			}
		}
		return false;
	}
}
