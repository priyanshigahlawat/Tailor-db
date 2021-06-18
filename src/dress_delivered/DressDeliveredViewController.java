/**
 * Sample Skeleton for 'DressDeliveredView.fxml' Controller Class
 */

package dress_delivered;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import worker_console.MySqlConnection;

public class DressDeliveredViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtOID"
    private TextField txtOID; // Value injected by FXMLLoader

    @FXML // fx:id="txtItem"
    private TextField txtItem; // Value injected by FXMLLoader

    @FXML // fx:id="txtAmount"
    private TextField txtAmount; // Value injected by FXMLLoader

    PreparedStatement pst;
    
    @FXML
    void onDelievered(ActionEvent event) {
    	try {
			pst = con.prepareStatement("Update measurements set status=3 where OID=?");
			pst.setString(1, txtOID.getText());
			int count = pst.executeUpdate();
			if(count == 1)
				showMsg("SMS sent");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void onFetch(ActionEvent event) {
    	try {
			pst = con.prepareStatement("select  dress, amount from measurements where OID=?");
			pst.setInt(1, Integer.parseInt(txtOID.getText()));
			ResultSet table = pst.executeQuery();
			while(table.next()) {
				String item = table.getString(1);
				int price = table.getInt(2);
				txtItem.setText(item);
				txtAmount.setText(String.valueOf(price));  
			}
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
        assert txtOID != null : "fx:id=\"txtOID\" was not injected: check your FXML file 'DressDeliveredView.fxml'.";
        assert txtItem != null : "fx:id=\"txtItem\" was not injected: check your FXML file 'DressDeliveredView.fxml'.";
        assert txtAmount != null : "fx:id=\"txtAmount\" was not injected: check your FXML file 'DressDeliveredView.fxml'.";

        con = MySqlConnection.doConnect();
    }
}
