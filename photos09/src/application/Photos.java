package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import controllers.deserialize;
import controllers.save;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;


/**
 * @author omar atieh
 * @author hamid abbassi
	 */
public class Photos extends Application {
	/**
	* Initializes main method and login view 
	 * @param stage is the Stage that this controller controls
	 */
		@Override
		public void start(Stage primaryStage) {
			try {
				AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
				Scene scene = new Scene(root,350,300);
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) throws IOException {
				launch(args);	
				
					}
		
				
		}
	