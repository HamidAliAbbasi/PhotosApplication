package controllers;


import java.io.IOException;

import java.util.ArrayList;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Users;

import controllers.save;
import controllers.*;

/** 
 * Controls the "adminView" stage
 * @author Hamid Ali Abbasi
 * @author Omar Atieh
 */
public class adminController {

	@FXML ListView<Users> ListViewer;
	@FXML Button add;
	@FXML Button delete;
	@FXML Button logout;
	@FXML Button quit;

	private ObservableList<Users> users;
	Stage stage;
    
    /**
	 * Initializes controller's private fields and sets up controller for stage
	 * @param stage is the Stage that this controller controls
     * @throws ClassNotFoundException
     * @throws IOException
	 */
    public void start(Stage stage) throws ClassNotFoundException, IOException {
		this.stage=stage;
		displayUsers();
	}
	
	/**
	 * Opens up the "adduserView" stage
	 * @param e the ActionEvent that prompted the button 
	 * @throws IOException 
     * @throws ClassNotFoundException
	 */
	public void addUser (ActionEvent e) throws IOException, ClassNotFoundException {

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/adduserView.fxml"));
				Parent parent = (Parent) loader.load();
				adduserController controller = loader.<adduserController>getController();
				Scene scene = new Scene(parent);
				Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				controller.start(stage);
				stage.setScene(scene);
				stage.show();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			
			displayUsers();

		}
	
	
	/**
	 * Deletes the highlighted user
	 * @param e the ActionEvent that prompted the button 
	 * @throws ClassNotFoundException
     * @throws IOException
	 */
	public void deleteUser (ActionEvent e) throws ClassNotFoundException, IOException{
		// Get the selected user's name and check if anything was selected
		Users selectedUser = ListViewer.getSelectionModel().getSelectedItem();
		if (selectedUser == null) {
            
            System.out.println("nothing was selected.");
			return;
		}
        ArrayList<Users> storedUsers = deserialize.deserialize();

        for (Users user : storedUsers) {
        
            if (user.getUserName().equals(selectedUser.getUserName())){
        
                storedUsers.remove(user);
                break;
            }
        }
        

        save.save(storedUsers);

		
		// After deleting a user, re-display to update view
		displayUsers();
	}
    
    /**
	 * Closes the current Window
     * @param event the ActionEvent that prompted the button
     */
	public void closeWindow(ActionEvent event) {
        
        stage.close();
		
    }
    
	/**
	 * Returns the User to the login window
	 * @param event the ActionEvent that prompted the button 
	 * @throws IOException
	 */
	public void logOut(ActionEvent event) {

		try {
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/loginView.fxml"));
			Parent parent = (Parent) loader.load();
			loginController controller = loader.<loginController>getController();
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			controller.start();
			stage.setScene(scene);
			stage.show();
        
        } catch (Exception exception) {
        
            exception.printStackTrace();
		}
	}
		
	/**
     * helper method to display all users
     * @throws ClassNotFoundException
     * @throws IOException
     */
	public void displayUsers () throws ClassNotFoundException, IOException {
		// Create a new ObserableList
        users = FXCollections.observableArrayList();
        
		ArrayList<Users> savedUsers = deserialize.deserialize();
        
        //arraylist of all the users are in savedUser
        for (Users user : savedUsers) users.add(user);
    
		// Display ObservableList
    
        ListViewer.setItems(users);
		ListViewer.setCellFactory(new Callback<ListView<Users>, ListCell<Users>>(){  
            @Override
            public ListCell<Users> call(ListView<Users> p) {                 
                ListCell<Users> cell = new ListCell<Users>(){ 
                    @Override
                    protected void updateItem(Users user, boolean bln) {
                        super.updateItem(user, bln);
                        if (user != null) setText(user.getUserName());
                        else setText(null);
                    }
 
                };
                 
                return cell;
            }
        });
	}
}
