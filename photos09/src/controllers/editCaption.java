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
 * Controls the "editCaptionView" stage
 * @author Hamid Ali Abbasi
 * @author Omar Atieh
 */
public class editCaption{

    @FXML Button close;
	@FXML Button edit;
	@FXML TextField newName;
	
	Photos select;
	Users user;
	Stage stage;
	Album album;

    /**
     * Initializes controller's private fields and sets up controller for stage
     * @param stage  is the Stage that this controller controls
     * @param user is the current User that's accessing this stage
     * @param album is the album that contains the photo whose caption is to be edited
     * @param select is the selected image whose caption is to be edited
     */
    public void start(Stage stage, Users user, Album album, Photos select) {

        this.user = user;
		this.select = select;
		this.stage = stage;
		this.album = album;
	}
	
    /**
     * changes caption 
     * @param event the ActionEvent that prompted the button
     * @throws IOException
     * @throws ClassNotFoundException
     */
	public void edit(ActionEvent event)throws IOException, ClassNotFoundException{

        String name = newName.getText();
		String old = select.getCaption();
		
		ArrayList<Users> allUsers = deserialize.deserialize();
		
		select.setCaption(name);
		for(Photos u: album.getAlbumPhotos()) {

            if(u.getPath().equals(select.getPath())){

                album.getAlbumPhotos().set(album.getAlbumPhotos().indexOf(u), select);
			}
		}
				
		for(Users u: allUsers) {
        
            if(u.getUserName().equals(user.getUserName())){
        
                allUsers.set(allUsers.indexOf(u), user);
			}
		}
		
		save.save(allUsers);
		Dialog("Successfully Changed");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/specificAlbumView.fxml"));
		Parent parent = (Parent) loader.load();
		specificAlbumViewController controller = loader.<specificAlbumViewController>getController();
		Scene scene = new Scene(parent);

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		controller.start(stage,user,album);
		stage.setScene(scene);
		stage.show();
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
     * Opens the "specificAlbumView" Stage
     * @param event the ActionEvent that prompted the button
     * @throws IOException
     * @throws ClassNotFoundException
     */
	public void close(ActionEvent event) throws IOException, ClassNotFoundException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/specificAlbumView.fxml"));
		Parent parent = (Parent) loader.load();
		specificAlbumViewController controller = loader.<specificAlbumViewController>getController();
		Scene scene = new Scene(parent);

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		controller.start(stage,user,album);
		stage.setScene(scene);
		stage.show();
	}

}
