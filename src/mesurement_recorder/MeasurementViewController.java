/**
 * Sample Skeleton for 'MeasurementView.fxml' Controller Class
 */

package mesurement_recorder;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import worker_console.MySqlConnection;

public class MeasurementViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtName"
    private TextField txtName; // Value injected by FXMLLoader

    @FXML // fx:id="txtMobile"
    private TextField txtMobile; // Value injected by FXMLLoader

    @FXML // fx:id="comboDress"
    private ComboBox<String> comboDress; // Value injected by FXMLLoader

    @FXML // fx:id="comboSpl"
    private ComboBox<String> comboSpl; // Value injected by FXMLLoader

    @FXML // fx:id="txtMeasur"
    private TextField txtMeasur; // Value injected by FXMLLoader

    @FXML // fx:id="comboDays"
    private ComboBox<Integer> comboDays; // Value injected by FXMLLoader

    @FXML // fx:id="comboDod"
    private DatePicker comboDod; // Value injected by FXMLLoader

    @FXML // fx:id="txtPrice"
    private TextField txtPrice; // Value injected by FXMLLoader

    @FXML // fx:id="txtWish"
    private TextField txtWish; // Value injected by FXMLLoader

    PreparedStatement pst;
    @FXML
    void doFind(ActionEvent event) {
    	try {
			pst = con.prepareStatement("SELECT measurement,spl FROM measurements WHERE custname=? AND dress=?");
			pst.setString(1, txtName.getText());
			pst.setString(2, comboDress.getEditor().getText());
			ResultSet table = pst.executeQuery();
			boolean flag = false;
			while(table.next()) {
				flag = true;
				String m = table.getString(1);
				String spl = table.getString(2);
				txtMeasur.setText(m);
				comboSpl.getSelectionModel().select(spl);
			}
			if(flag == false)
				showErrorMsg("Record not found");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doNew(ActionEvent event) {
    	txtName.setText("");
    	txtMobile.setText("");
    	comboDress.getSelectionModel().clearSelection();
    	comboSpl.getItems().clear();
    	txtMeasur.setText("");
    	txtWish.setText("");
    	txtPrice.setText("");
    	comboDays.getEditor().setText("");
    	comboDod.getEditor().clear();
    }

    @FXML
    void doSave(ActionEvent event) {
    	try {
			pst = con.prepareStatement("insert into measurements (custname, custmobile, dress, spl, measurement, doo, dod, amount, wish, status) values(?,?,?,?,?,current_date(),date_add(CURRENT_DATE(),interval ? Day),?,?,?)");
			pst.setString(1, txtName.getText());
			pst.setString(2, txtMobile.getText());
			pst.setString(3, comboDress.getEditor().getText());
			pst.setString(4, comboSpl.getEditor().getText());
			pst.setString(5, txtMeasur.getText());
			pst.setInt(6, Integer.parseInt(comboDays.getEditor().getText()));
			pst.setInt(7, Integer.parseInt(txtPrice.getText()));
			pst.setString(8, txtWish.getText());
			pst.setInt(9, 1);
		    pst.executeUpdate();
		    
		    ResultSet rs = pst.getGeneratedKeys();
		    while(rs.next()) {
		    	showMsg("Record Saved Successfully \n your order ID is: " + rs.getInt(1));
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
	
    @FXML
    void onDays(ActionEvent event) {
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //format specify
    	String data = df.format(new java.util.Date());  // current date
    	LocalDate date = LocalDate.parse(data);  //convert string to local date
    	int n = Integer.parseInt(comboDays.getEditor().getText()); // No.of days selected
    	//ading n days
    	LocalDate date2 = date.plusDays(n);  //add no. of days
    	System.out.println("date: "  + date + " plus " + n + " days: " + date2);
    	comboDod.setValue(date2);
    	
    	
//    	int daysSel = comboDays.getSelectionModel().getSelectedItem();
//    	try {
//    		pst = con.prepareStatement("SELECT DATE_ADD(current_date(), INTERVAL ? DAY) as d");
//    		pst.setInt(1, daysSel);
//    		ResultSet rs = pst.executeQuery();
//    		while(rs.next()) {
//    			comboDod.getEditor().setText(String.valueOf(rs.getDate("d")));
//    		}
//    	} catch(SQLException e) {};
    }
    
    @FXML
    void doDaysSelect(ActionEvent event) {
    	
    }

    @FXML
    void onDress(ActionEvent event) {
    	int s = comboDress.getSelectionModel().getSelectedIndex();
    	if(s==0)
    		txtPrice.setText("1000");
    	else if(s==1)
    		txtPrice.setText("1800");
    	else if(s==2)
    		txtPrice.setText("2000");
    	else if(s==3)
    		txtPrice.setText("800");
    	
    	comboSpl.getItems().clear();
    	ArrayList<String> wList = new ArrayList<String>();
    	try {
			pst = con.prepareStatement("select spl, wname from workers");
			ResultSet table = pst.executeQuery();
			String dress = comboDress.getEditor().getText();
			while(table.next()) {
				String spl = table.getString(1);
				String worker = table.getString(2);
				if(spl.contains(dress))
					wList.add(worker);
			}
			comboSpl.getItems().addAll(wList);
			
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
    
    void showErrorMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.ERROR);
    	alert.setTitle("Its Result");
    	alert.setContentText(msg);
    	alert.show();
    }


    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'MeasurementView.fxml'.";
        assert txtMobile != null : "fx:id=\"txtMobile\" was not injected: check your FXML file 'MeasurementView.fxml'.";
        assert comboDress != null : "fx:id=\"comboDress\" was not injected: check your FXML file 'MeasurementView.fxml'.";
        assert comboSpl != null : "fx:id=\"comboSpl\" was not injected: check your FXML file 'MeasurementView.fxml'.";
        assert txtMeasur != null : "fx:id=\"txtMeasur\" was not injected: check your FXML file 'MeasurementView.fxml'.";
        assert comboDays != null : "fx:id=\"comboDays\" was not injected: check your FXML file 'MeasurementView.fxml'.";
        assert comboDod != null : "fx:id=\"comboDod\" was not injected: check your FXML file 'MeasurementView.fxml'.";
        assert txtPrice != null : "fx:id=\"txtPrice\" was not injected: check your FXML file 'MeasurementView.fxml'.";	
        assert txtWish != null : "fx:id=\"txtWish\" was not injected: check your FXML file 'MeasurementView.fxml'.";
        
        con = MySqlConnection.doConnect();
        
        ArrayList<String> dressList = new ArrayList<String>(Arrays.asList("Women's suit", "Men's suit", "Lahenga", "Men's Kurta"));
        comboDress.getItems().addAll(dressList);
        
        ArrayList<Integer> dayList = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30));
        comboDays.getItems().addAll(dayList);
        
        
    }
}
