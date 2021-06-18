/**
 * Sample Skeleton for 'LoginView.fxml' Controller Class
 */

package login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPassword"
    private PasswordField txtPassword; // Value injected by FXMLLoader

    @FXML
    void doOpenDashboard(ActionEvent event) {
    	if(txtPassword.getText().equals("Khushi@2001")) {
    		try{
        		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("dashboard/DashboardView.fxml")); 
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
    	else {
    		showErrorMsg("Invalid Password!");
    	}
    }
    
    void showErrorMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.ERROR);
    	alert.setTitle("Its Result");
    	alert.setContentText(msg);
    	alert.show();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'LoginView.fxml'.";

    }
}
