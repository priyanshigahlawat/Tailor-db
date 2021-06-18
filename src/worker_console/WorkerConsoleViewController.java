/**
 * Sample Skeleton for 'WorkerConsoleView.fxml' Controller Class
 */

package worker_console;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class WorkerConsoleViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="image"
    private ImageView image; // Value injected by FXMLLoader

    @FXML // fx:id="txtName"
    private TextField txtName; // Value injected by FXMLLoader

    @FXML // fx:id="txtMob"
    private TextField txtMob; // Value injected by FXMLLoader

    @FXML // fx:id="txtAddress"
    private TextField txtAddress; // Value injected by FXMLLoader

    @FXML // fx:id="ComboSpl"
    private ComboBox<String> ComboSpl; // Value injected by FXMLLoader

    @FXML // fx:id="txtSpl"
    private TextField txtSpl; // Value injected by FXMLLoader

    PreparedStatement pst;
    String filePath;
    String iconPath = "C:\\Users\\priya\\OneDrive\\Pictures\\icon.png";
    @FXML
    void doBrowse(ActionEvent event) {
    	FileChooser r = new FileChooser();
    	File s = r.showOpenDialog(null);
    	filePath = s.getAbsolutePath();
    	if(s!=null) {
    		try {
				Image images = new Image(new FileInputStream(s.getAbsolutePath().toString()));
				image.setImage(images);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    }

    @FXML
    void doDelete(ActionEvent event) {

    	try {
			pst = con.prepareStatement("delete from workers where wname=?");
			pst.setString(1, txtName.getText());
			int count = pst.executeUpdate();
			if(count == 0)
				showMsg("No record found");
			else showMsg("Deleted!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
    }

    @FXML
    void doFetch(ActionEvent event) {
    	ComboSpl.getSelectionModel().clearSelection();
    	txtSpl.setText("");
    	try {
			pst = con.prepareStatement("select * from workers where wname=?");
			pst.setString(1, txtName.getText());
			ResultSet table = pst.executeQuery();
			boolean flag = false;
			while(table.next()) {
				flag = true;
				String mobile = table.getString(2);
				String path = table.getString(3);
				String addr = table.getString(4);
				String spl = table.getString(5);
				txtMob.setText(mobile);
				txtAddress.setText(addr);
				ArrayList<String> splList = giveList();
//				int index = splList.indexOf(spl);
//				ComboSpl.getSelectionModel().select(index);
				ComboSpl.getSelectionModel().select(spl);
				Image images;
				try {
					images = new Image(new FileInputStream(path));
					image.setImage(images);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(flag == false)
				showErrorMsg("No Record Found!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doNew(ActionEvent event) {
    	txtName.setText("");
    	txtMob.setText("");
    	txtSpl.setText("");
    	txtAddress.setText("");
    	ComboSpl.getEditor().setText("");
    	Image images;
		try {
			images = new Image(new FileInputStream(iconPath));
			image.setImage(images);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doSave(ActionEvent event) {
    	try {
			pst = con.prepareStatement("insert into workers values(?,?,?,?,?,current_date())");
			pst.setString(1, txtName.getText());
			pst.setString(2, txtMob.getText()); 
			pst.setString(3, filePath);
			pst.setString(4, txtAddress.getText());
			pst.setString(5, txtSpl.getText());
		    int count = pst.executeUpdate();
			if(count == 1)
				showMsg("Record Saved");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doUpdate(ActionEvent event) {
    	if(filePath != null) {
    		update();
    	} else 
    		update2();
    }
    
    void update2() {
    	try {
			pst = con.prepareStatement("update workers set mobile=?, address=?, spl=?, dor=current_date() where wname=?");
			pst.setString(1, txtMob.getText());
			pst.setString(2, txtAddress.getText());
			pst.setString(3, txtSpl.getText());
			pst.setString(4, txtName.getText());
			int count = pst.executeUpdate();
			if(count == 0)
				showErrorMsg("Record not found");
			else
				showMsg("Record Updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    void update() {
    	try {
			pst = con.prepareStatement("update workers set mobile=?, picpath=?, address=?, spl=?, dor=current_date() where wname=?");
			pst.setString(1, txtMob.getText());
			pst.setString(2, filePath);
			pst.setString(3, txtAddress.getText());
			pst.setString(4, txtSpl.getText());
			pst.setString(5, txtName.getText());
			int count = pst.executeUpdate();
			if(count == 0)
				showErrorMsg("Record not found");
			else
				showMsg("Record Updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    void setSpl(String s) {
    	String s1 = txtSpl.getText();
    	if(s1.isEmpty())
    		txtSpl.setText(s);
    	else
    		txtSpl.setText(s1 + ", " + s);
    }
    
    @FXML
    void doShowSplList(ActionEvent event) {
    	String s = ComboSpl.getSelectionModel().getSelectedItem();
    	setSpl(s);
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
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'WorkerConsoleView.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'WorkerConsoleView.fxml'.";
        assert txtMob != null : "fx:id=\"txtMob\" was not injected: check your FXML file 'WorkerConsoleView.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'WorkerConsoleView.fxml'.";
        assert ComboSpl != null : "fx:id=\"ComboSpl\" was not injected: check your FXML file 'WorkerConsoleView.fxml'.";
        assert txtSpl != null : "fx:id=\"txtSpl\" was not injected: check your FXML file 'WorkerConsoleView.fxml'.";
        
        con = MySqlConnection.doConnect();
        
        ArrayList<String> splList = giveList();
        ComboSpl.getItems().addAll(splList);
    }
    ArrayList giveList() {
    	ArrayList<String> splList = new ArrayList<String>(Arrays.asList("Women's suit", "Men's suit", "Lahenga", "Men's Kurta"));
    	return splList;
    }
}