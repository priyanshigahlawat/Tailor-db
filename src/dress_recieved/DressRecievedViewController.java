/**
 * Sample Skeleton for 'DressRecievedView.fxml' Controller Class
 */

package dress_recieved;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import worker_console.MySqlConnection;

public class DressRecievedViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="AnchorPane"
    private AnchorPane AnchorPane; // Value injected by FXMLLoader

    @FXML // fx:id="txtOID"
    private TextField txtOID; // Value injected by FXMLLoader

    PreparedStatement pst;
    
    @FXML
    void onRecieved(ActionEvent event) {
    	try {
			pst = con.prepareStatement("Update measurements set status=2 where OID=?");
			pst.setString(1, txtOID.getText());
			int count = pst.executeUpdate();
			if(count == 1)
				showMsg("SMS sent");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Its Result");
    	alert.setContentText(msg);
    	alert.show();
    }

    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert AnchorPane != null : "fx:id=\"AnchorPane\" was not injected: check your FXML file 'DressRecievedView.fxml'.";
        assert txtOID != null : "fx:id=\"txtOID\" was not injected: check your FXML file 'DressRecievedView.fxml'.";
        AnchorPane.styleProperty().set("-fx-background-colour: #ccffbd");
        
        con = MySqlConnection.doConnect();
    }
}
