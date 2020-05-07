package controllers;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.*;


/**
 * Controls the "editView" stage
 * @author Omar Atieh
 * @author Hamid Ali Abbasi
 */
public class editController {
	@FXML Button close;
	@FXML Button edit;
	@FXML TextField newName;
	
	Album album;
	Users user;
	Stage stage;
    
    /**
     * Initializes controller's private fields and sets up controller for stage
     * @param stage is the Stage that this controller controls
     * @param user is the current User that's accessing this stage
     * @param album is the album that contains the photo to be edited
     */
    public void start(Stage stage, Users user, Album album) {
        
        this.user = user;
		this.album = album;
		this.stage = stage;
	}
	
    
    /**
     * Changes the name of the album
     * @param event the ActionEvent that prompted the button
     * @throws IOException
     * @throws ClassNotFoundException
     */
	public void edit(ActionEvent event)throws IOException, ClassNotFoundException{
		String name = newName.getText();
		String old = album.getName();
		ArrayList<Users> allUsers = deserialize.deserialize();
		
		
		for (Album album : user.getAlbums()) {
			if (album.getName().equals(name)) {
				error("Duplicate Name");
				return;
			}
		}
		
		
		album.setName(name);
		
		for(Users u: allUsers) {
			if(u.getUserName().equals(user.getUserName())){
				allUsers.set(allUsers.indexOf(u), user);
			}
		}
		
		save.save(allUsers);
		Dialog("Successfully Changed");
		return;
		
	
	}

    /**
     * Brings up an error dialog
     * @param emessage error message to be displayed
     */
	 public void error(String emessage) {
		   Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ALERT ERROR");
			alert.setHeaderText("ERROR");
			alert.setContentText(emessage);
			alert.showAndWait();
    }
    
    /**
     * Brings up a success dialog
     * @param emessage success message to be displayed
     */
	 public void Dialog(String emessage) {
		   Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Album Changed ");
			alert.setHeaderText("Success");
			alert.setContentText(emessage);
			alert.showAndWait();
    }
    
    /**
     * Opens the "albumView" Stage
     * @param event the ActionEvent that prompted the button
     * @throws IOException
     * @throws ClassNotFoundException
     */
	public void close(ActionEvent event) throws IOException, ClassNotFoundException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/albumView.fxml"));
		Parent parent = (Parent) loader.load();
		albumController controller = loader.<albumController>getController();
		Scene scene = new Scene(parent);

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		controller.start(stage,user);
		stage.setScene(scene);
		stage.show();
	}

}
