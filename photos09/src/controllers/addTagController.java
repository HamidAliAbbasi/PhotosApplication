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
 * Controls the "addTag" stage
 * @author Omar Atieh
 * @author Hamid Ali Abbasi
 */
public class addTagController {

	@FXML Button close;
	@FXML Button edit;
	@FXML TextField value;
	@FXML TextField type;
	
	Photos select;
	Users user;
	Stage stage;
    Album album;
    
    /**
	 * Initializes controller's private fields and sets up controller
	 * for stage
	 * @param stage is the Stage that this controller controls
     * @param user is the current User that's accessing this stage
     * @param album is the album that contains the photo the tag will be added to
	 * @param select is the Photo that the tag will be added to
	 */
	public void start(Stage stage, Users user, Album album, Photos select) {

        this.user = user;
		this.select = select;
		this.stage = stage;
		this.album = album;
	}
	
    /**
	 * Adds a tag a to the photo
	 */
	public void addTag(ActionEvent event)throws IOException, ClassNotFoundException{
        
        String Type = type.getText();
		String Value = value.getText();
		ArrayList<Users> allUsers = deserialize.deserialize();
		
		if(Type.equals("") || Value.equals("")) {
        
            error("Cannot have a empty parameter");
			return;
		}
        
        Tags tag = new Tags(Type, Value);
		select.getTags().add(tag);
		
		for(Users u: allUsers) {
            
            if(u.getUserName().equals(user.getUserName())) allUsers.set(allUsers.indexOf(u), user);
		}
		
		
		save.save(allUsers);
		Dialog("Successfully added Tag");
		
		//go back
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/specificAlbumView.fxml"));
		Parent parent = (Parent) loader.load();
		specificAlbumViewController controller = loader.<specificAlbumViewController>getController();
		Scene scene = new Scene(parent);

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		controller.start(stage,user,album);
		stage.setScene(scene);
		stage.show();
		
	}

	/**
     * Brings up an error alert
     * @param emessage the error message to be displayed
     */
	public void error(String emessage) {
    
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ALERT ERROR");
        alert.setHeaderText("ERROR");
        alert.setContentText(emessage);
        alert.showAndWait();
    }
    
    /**
     * Brings up a window when a tag is added successfully
     * @param emessage the success message to be displayed
     */
	public void Dialog(String emessage) {
    
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("tag Added");
        alert.setHeaderText("Success");
        alert.setContentText(emessage);
        alert.showAndWait();
    }
    
    /**
	 * Closes the current Window
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
