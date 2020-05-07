package controllers;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//import javax.print.DocFlavor.URL;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;

/** 
 * Controls the "addPhotoView" stage
 * @author Hamid Ali Abbasi
 * @author Omar Atieh
 */
public class addPhotoController {

    @FXML TextField cap;
	@FXML TextField path;
	@FXML Button close;
	@FXML Button add;
	@FXML Button getUrl;

    private Users user;
    private Album album;
    private File file;

    /**
	 * Initializes controller's fields
	 * @param User is the current User
	 * @param album is the current users album that a photo will be added to
	 */

    public void start(Users user, Album album) {

    this.user=user;
	this.album = album;
    }
	
	/**
	if(album.getName().equals("stock") ){
	
		
		ArrayList<Users> saved = deserialize.deserialize();
		
		java.net.URL url = getClass().getResource("/data/5.png");
		
		File file = new File(url.getPath());
		//File file = new File("/data/5.png");
		System.out.println(url.getPath());
		album.addPhoto(new Photos(file));
        
        ArrayList<Album> temp = user.getAlbums();
       // Find currUser in storedUsers and add album to the user
        
        for( Album t: temp) {
        
            if(t.getName().equals(album.getName())) {
        
                temp.set(temp.indexOf(t),album);
                user.setAlbums(temp);
            }
        }
        
        for (Users u : saved) {
        
            if (u.equals(user)) saved.set(saved.indexOf(u), user);
        
        }
        
        save.save(saved);
        
}}
	
**/
    /**
	 * Adds the chosen photo to the current users album
	 */

    public void add (ActionEvent event) throws IOException {    

        ArrayList<Users> saved = deserialize.deserialize();
        
        if (file == null) return;
        
        if (file.exists()) {
            
            String caption = cap.getText();
            //System.out.println(caption);
            album.addPhoto(new Photos(file, caption));}
            
        else album.addPhoto(new Photos(file));
        
        ArrayList<Album> temp = user.getAlbums();
        // Find currUser in storedUsers and add album to the user
        
        for( Album t: temp) {
        
            if(t.getName().equals(album.getName())) {
        
                temp.set(temp.indexOf(t),album);
                user.setAlbums(temp);
            }
        }
        
        for (Users u : saved) {
        
            if (u.equals(user)) saved.set(saved.indexOf(u), user);
        
        }
        
        save.save(saved);
        
        try {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/specificAlbumView.fxml"));
            Parent parent = (Parent) loader.load();
            specificAlbumViewController controller = loader.<specificAlbumViewController>getController();
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            controller.start(stage,user, album);
            stage.setScene(scene);
            stage.show();
        
        } 
        
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
            
    /**
	 * Prompts the user to choose a file and gets its path
	 */
    public void getUrl (ActionEvent event) throws IOException {
        FileChooser browser = new FileChooser();
        file = browser.showOpenDialog(null);
        System.out.println(file);
        if (file != null) {
            path.setText(file.getAbsolutePath());
        }
    }

    /**
     * Closes the add photo window
     */
    public void close(ActionEvent e) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/specificAlbumView.fxml"));
            Parent parent = (Parent) loader.load();
            specificAlbumViewController controller = loader.<specificAlbumViewController>getController();
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            controller.start(stage,user, album);
            stage.setScene(scene);
            stage.show();
        } 
        
        catch (IOException ex) {
        
            System.out.println(ex);
        }
    }

}


