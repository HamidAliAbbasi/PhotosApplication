package controllers;

import java.util.ArrayList;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.*;


/**
 * Opens up the "specificAlbumView" stage
 * @author Hamid Ali Abbasi
 * @author Omar Atieh
 */

public class specificAlbumViewController {
	
	@FXML ListView<Photos> photosList;
	@FXML TitledPane titlePane;
	@FXML TextField captions, dateField;

	@FXML ListView<Tags> tagsList;
	@FXML Button addTag;
	@FXML Button add;
	@FXML Button delTag;
	@FXML Button delete;
	@FXML Button quit;
	@FXML Button back;

	@FXML Button move, prev, next;
	@FXML Button copy;
	@FXML ImageView imageShow;
	private Stage stage;
	private Users user;
	private Album album;
	private ObservableList<Photos> obs;
	private ObservableList<Tags> tags;
	public String starter = "file:///";
	
	
    /**
     * Initializes controllers private fields and sets up controller for stage
     * @param stage Stage that this controller controls
     * @param user User whose album is being viewed
     * @param select Album user selected to view
     */
    public void start (Stage stage, Users user, Album select) {
        
        this.user = user;
		this.album = select;
		this.stage=stage;
		titlePane.setText(select.getName());
		displayPhotos();
	}
    
    /**
	 * Opens up the "addPhotoView" stage
	 * @param event the ActionEvent that prompted the button 
	 * @throws IOException
	 */
	public void add (ActionEvent event) throws IOException {
        
        try {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addPhotoView.fxml"));
		    Parent parent = (Parent) loader.load();
            addPhotoController controller = loader.<addPhotoController>getController();
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            controller.start(user,album);
            stage.setScene(scene);
	    } catch (Exception exception) {
		    exception.printStackTrace();
	
        }
    }
    
    /**
     * Goes back to the "albumView"
     * @param event the ActionEvent that prompted the button
     * @throws IOException
     */
    public void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/albumView.fxml"));
		Parent parent = (Parent) loader.load();
		albumController controller = loader.<albumController>getController();
		Scene scene = new Scene(parent);

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		controller.start(stage,user);
		stage.setScene(scene);
		stage.show();
	}

	/**
     * Closes the application
     * @param event the ActionEvent that prompted the button
     * @throws IOException
     */
	public void quit(ActionEvent event) throws IOException {
	stage.close();
	}
	
    /**
     * Deletes the photo selected within the album
     * @param event the ActionEvent that prompted the button
     * @throws IOException
     */
	public void delete(ActionEvent event) throws IOException {
		Photos select = photosList.getSelectionModel().getSelectedItem();
		if (select == null) {
            
            Dialog("No selected photo");
			return;
		}
				
		ArrayList<Users> allUsers = deserialize.deserialize();
			album.getAlbumPhotos().remove(select);		
            for(Users u: allUsers) {
                
                if(u.getUserName().equals(user.getUserName())) allUsers.set(allUsers.indexOf(u), user);
                
				/**
				for(Album e: user.getAlbums()) {
					if(e.getName().equals(album.getName())) {
						System.out.println("success");
						e.getAlbumPhotos().remove(select);
					}
				}
				**/
				
			}
			save.save(allUsers);
			displayPhotos();
			return;
		
		
		
	}
    
    /**
     * Opens "copyView" stage
     * @param event the ActionEvent that prompted the button
     * @throws IOException
     */
	public void copy(ActionEvent event) throws IOException {
		Photos select = photosList.getSelectionModel().getSelectedItem();
		
		if (select == null) {
            
            Dialog("No selected photo");
			return;
		}
        
        try {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/copyView.fxml"));
			Parent parent = (Parent) loader.load();
			copyController controller = loader.<copyController>getController();
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			controller.start(stage,user,album,select);
			stage.setScene(scene);
        
        } catch (Exception exception) {
            
            exception.printStackTrace();
		}
		
	}
	
	/**
     * Opens the "moveView" stage
     * @param event the ActionEvent that prompted the button
     * @throws IOException
     */
	public void move(ActionEvent event) throws IOException {
		Photos select = photosList.getSelectionModel().getSelectedItem();
		
		if (select == null) {
            
            Dialog("No selected photo");
			return;
		}
        
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/moveView.fxml"));
			Parent parent = (Parent) loader.load();
			moveController controller = loader.<moveController>getController();
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			controller.start(stage,user,album,select);
			stage.setScene(scene);
		} catch (Exception exception) {
            
            exception.printStackTrace();
		}
		
	}
    
    /**
     * Opens the "addTag" stage
     * @param event the ActionEvent that prompted the button
     * @throws IOException
     */
	public void addTag(ActionEvent event) throws IOException {
		Photos select = photosList.getSelectionModel().getSelectedItem();
		
		if (select == null) {
            
            Dialog("No selected photo");
			return;
		}
        
        try {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addTag.fxml"));
			Parent parent = (Parent) loader.load();
			addTagController  controller = loader.<addTagController >getController();
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			controller.start(stage,user,album,select);
			stage.setScene(scene);
        
        } catch (Exception exception) {
        
            exception.printStackTrace();
		}
        
        ArrayList<Users> savedUsers = deserialize.deserialize();
		// Find currUser in storedUsers and add album to the user
		for (Users u : savedUsers) {
			if (u.equals(user)) savedUsers.set(savedUsers.indexOf(u), user);
        }
        
		save.save(savedUsers);
	}
	
	
	/**
     * Deletes the selected tag from the selected photo within the album
     * @param event the ActionEvent that prompted the button
     * @throws IOException
     */
	public void delTag(ActionEvent event) throws IOException {
		Photos select = photosList.getSelectionModel().getSelectedItem();
		Tags selectTag = tagsList.getSelectionModel().getSelectedItem();
        
        if (select == null || selectTag==null) {
        
            Dialog("No selected photo or Tag");
			return;
		}
        
        ArrayList<Users> allUsers = deserialize.deserialize();
		select.getTags().remove(selectTag);
        
        for(Users u: allUsers) {
        
            if(u.getUserName().equals(user.getUserName())) allUsers.set(allUsers.indexOf(u), user);
		}
		
		save.save(allUsers);
		displayPhotos();
		return;
		
	}
	
	/**
     * Opens the "editCaptionView" stage
     * @param event the ActionEvent that prompted the button
     * @throws IOException
     */
	public void edit (ActionEvent event) throws IOException {
		Photos select = photosList.getSelectionModel().getSelectedItem();

        if (select == null) {

            Dialog("No selected photo");
			return;
		}

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/editCaptionView.fxml"));
			Parent parent = (Parent) loader.load();
			editCaption controller = loader.<editCaption>getController();
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			controller.start(stage,user,album,select);
			stage.setScene(scene);

        } catch (Exception exception) {

            exception.printStackTrace();
		}
		
	}
	
	
    /**
     * Selects the next picture
     * @param e the ActionEvent that prompted the button
     * @throws IOException
     */
	public void next(ActionEvent e) throws IOException {
		int count = photosList.getSelectionModel().getSelectedIndex();
		photosList.getSelectionModel().select(count+1);
		return;
	}
    
    /**
     * Selects the previous picture
     * @param e the ActionEvent that prompted the button
     * @throws IOException
     */
	public void prev(ActionEvent e) throws IOException {

		int count = photosList.getSelectionModel().getSelectedIndex();

        if (count-1 >=0) photosList.getSelectionModel().select(count-1);
        return;

    }
	
	
    /**
	 * Helper method that populates the ListView with the current User's photos
	 */
	private void displayPhotos () {
        
        ArrayList<Photos> photos = album.getAlbumPhotos();
		obs = FXCollections.observableArrayList();
		
		for (Photos picture : photos) obs.add(picture);
		
		photosList.setItems(obs);
		photosList.setCellFactory(param -> new ListCell<Photos>() {
        
            private ImageView imageView = new ImageView();
			
			public void updateItem (Photos picture, boolean check) {
        
                super.updateItem(picture, check);
				if (check) {
        
                    setText (null);
					setGraphic(null);
				} else {
                
                    String pathName = starter + picture.getPath();
					Image image = new Image(pathName, 60, 60, true, true);
					imageView.setImage(image);
					setGraphic(imageView);
				}
			}
		});
		photosList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Photos>() {
    	    @Override
    	    public void changed(ObservableValue<? extends Photos> obsList, Photos initialPicture, Photos updatedPicture) {
    	    	if(updatedPicture!=null) {
    	    		
    	    		String pathName = starter + updatedPicture.getPath();
  	
    	    		Image image = new Image(pathName, true);
    	    		imageShow.setImage(image);
    	    		captions.setText(updatedPicture.getCaption());
    	    		dateField.setText(updatedPicture.getDate());
    	    		tags = FXCollections.observableArrayList();
                
                    for (Tags tag : updatedPicture.getTags()) {
                
                        tags.add(tag);
    	    		}
                
                    tagsList.setItems(tags);
    	    		tagsList.setCellFactory(param -> new ListCell<Tags>() {
    	    	
    	    			public void updateItem (Tags tag, boolean check) {
                
                            super.updateItem(tag, check);
                
                            if (check) {
                
                                setText(null);
    	    				} else {
                                
                                setText(tag.toString());
    	    				}
    	    			}
    	    		});
    	    	}
    	    }
    	});		
	}

    /**
     * Displays error message
     * @param emessage error message to be displayed
     */
	 public void Dialog(String emessage) {
		   Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("error");
			alert.setHeaderText("Error");
			alert.setContentText(emessage);
			alert.showAndWait();
	}
}
