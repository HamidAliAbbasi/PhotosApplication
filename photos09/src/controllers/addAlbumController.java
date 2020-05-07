package controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.*;


/**
 * Controls the "addAlbumView" Stage
 * @author Omar Atieh
 * @author Hamid Ali Abbasi
 */
public class addAlbumController {
	@FXML TextField albumName;
	private Users user;
	public ArrayList<Users> users;
    
    /**
     * Initializes controller's private fields and sets up controller for stage
     * @param user is the current User that's accessing this stage
     */
	public void start(Users user) {
		this.user=user;
	}
	
    
    /**
     * adds albums to user account
     * @param event the ActionEvent that prompted the button
     * @throws IOException
     * @throws ClassNotFoundException
     */
	public void add(ActionEvent event)throws IOException, ClassNotFoundException {

        String name = albumName.getText();
		System.out.print(name);
        if(!name.isEmpty()) {

            if(user.getAlbums()!=null) {

                for (Album album : user.getAlbums()) {

                    if (album.getName().equals(name)) {

                        error("Albums cannot have the same name.");
                        return;
                    }
				}
		} 
            
		try {
		
		Album sample = new Album(name);
		user.addAlbums(sample);
		Success("Success");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/albumView.fxml"));
        Parent parent = (Parent) loader.load();
        albumController controller = loader.<albumController>getController();
        Scene scene = new Scene(parent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        controller.start(stage,user);
        stage.setScene(scene);
        stage.show();
        saves(user);
        return;
    }
		catch(java.lang.NullPointerException exception){
        }
      
		
		//to avoid null pointer and must make sure that there is an arraylist of albums in the class
		
			
			Album sample = new Album(name);
			ArrayList<Album> t = new ArrayList<>();
			t.add(sample);
			user.setAlbums(t);
			Success("Success");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/albumView.fxml"));
			Parent parent = (Parent) loader.load();
			albumController controller = loader.<albumController>getController();
			Scene scene = new Scene(parent);

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			controller.start(stage,user);
			stage.setScene(scene);
			stage.show();
		    saves(user);
		    return;
				
        }
}
			
	
	
	  
    /**
     * saves the albums
     * @param User
     */
	
	public void saves(Users user){
	ArrayList<Users> savedUsers = deserialize.deserialize();
	// Find currUser in storedUsers and add album to the user
	for (Users u : savedUsers) {
		if (u.equals(user)) {
			savedUsers.set(savedUsers.indexOf(u), user);
		}
	}
	save.save(savedUsers);
		
		
			}
    
    
    /**
     * Brings up a success dialog
     * @param emessage success message to be displayed
     */
	public void Success(String emessage) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("AalbumChanged ");
        alert.setHeaderText("Success");
        alert.setContentText(emessage);
        alert.showAndWait();
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
