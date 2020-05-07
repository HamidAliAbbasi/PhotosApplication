package controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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

import model.Users;

/**
 * Contorls the "loginView" stage
 * @author Hamid Ali Abbasi
 * @author Omar Atieh
 */
public class loginController {
	@FXML
	private Button enter;
	@FXML
	private TextField username;
	

	/**
     * Initializes controller's private fields and sets up controller for stage
     */
	public void start() {
		
	}
	private Users user;


    /**
     * checks if user is admin or a regular user
     * @param e the ActionEvent that prompted the button
     * @throws IOException
     * @throws ClassNotFoundException
     */
	public void enter(ActionEvent e) throws IOException, ClassNotFoundException {
		
		
		ArrayList<Users> users= deserialize.deserialize();
		String name = username.getText();

		user = null;

		for (Users currentUser : users) {

            if (currentUser.getUserName().equals(name)) {

                user = currentUser;

			}
		}
		System.out.println(name);

		if (name.equals("admin") || user != null) {
		

			if (name.equals("admin")) {
			
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/view/adminView.fxml"));
				Parent root = (Parent)loader.load();
				adminController controller = loader.getController();
				
				Stage secondaryStage = new Stage();
				controller.start(secondaryStage);
				
				Scene scene = new Scene(root);
				secondaryStage.setScene(scene);
				secondaryStage.setTitle("Photo App");
				secondaryStage.show();
				
				
				
			} else {
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/albumView.fxml"));
				Parent parent = (Parent) loader.load();
				albumController controller = loader.<albumController>getController();
				Scene scene = new Scene(parent);
		
				Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				controller.start(stage,user);
				stage.setScene(scene);
				stage.show();
			
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Login Error");
			alert.setHeaderText("User not found.");
			alert.setContentText("This user does not exist.");

			alert.showAndWait();
		}
	
}
}

		
		
		
	

	


