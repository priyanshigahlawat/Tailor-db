/**
 * Sample Skeleton for 'DashboardView.fxml' Controller Class
 */

package dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class DashboardViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    void openDeleiverd(MouseEvent event) {
//    	playSong();
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("dress_delivered/DressDeliveredView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
  		//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void openMeasurementRecorder(MouseEvent event) {
//    	playSong();
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("mesurement_recorder/MeasurementView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
  		//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void openMeasurementTable(MouseEvent event) {
//    	playSong();
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("MeasurementTable/MeasurementTableView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
  		//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void openRecieved(MouseEvent event) {
//    	playSong();
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("dress_recieved/DressRecievedView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
  		//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void openWorkerConsole(MouseEvent event) {
//    	playSong();
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("worker_console/WorkerConsoleView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
  		//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void openWorkerTable(MouseEvent event) {
//    	playSong();
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("workerTable/WorkerTableView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
  		//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    URL url;
    Media media;
    MediaPlayer mediaplayer;
    AudioClip audio;

    void playSound(){
    	url=getClass().getResource("crash.wav");
	    audio=new AudioClip(url.toString());
	    audio.play();
    }

    void playSong() {
    	url=getClass().getResource("click.mp3");
	    media=new Media(url.toString());
	    mediaplayer=new MediaPlayer(media);
	    mediaplayer.play();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
}
