/**
 * Sample Skeleton for 'MeasurementTableView.fxml' Controller Class
 */

package measurementTable;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import workerTable.WorkerBean;
import worker_console.MySqlConnection;

public class MeasurementTableViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboWname"
    private ComboBox<String> comboWname; // Value injected by FXMLLoader

    @FXML // fx:id="comboStatus"
    private ComboBox<String> comboStatus; // Value injected by FXMLLoader

    @FXML // fx:id="txtCustname"
    private TextField txtCustname; // Value injected by FXMLLoader

    @FXML // fx:id="dateDeadline"
    private DatePicker dateDeadline; // Value injected by FXMLLoader

    @FXML // fx:id="tblMeas"
    private TableView<MeasurementBean> tblMeas; // Value injected by FXMLLoader

    PreparedStatement pst;
    
    void fillColumns() {
    	TableColumn<MeasurementBean, String> cname = new TableColumn<MeasurementBean, String>("Customer name");
    	cname.setCellValueFactory(new PropertyValueFactory<MeasurementBean, String>("custname"));
    	cname.setMinWidth(100);
    	
    	TableColumn<MeasurementBean, String> cmob = new TableColumn<MeasurementBean, String>("Customer mobile");
    	cmob.setCellValueFactory(new PropertyValueFactory<MeasurementBean, String>("custmobile"));
    	cmob.setMinWidth(100);
    	
    	TableColumn<MeasurementBean, String> wname = new TableColumn<MeasurementBean, String>("Worker");
    	wname.setCellValueFactory(new PropertyValueFactory<MeasurementBean, String>("spl"));
    	wname.setMinWidth(100);
    	
    	TableColumn<MeasurementBean, String> dress = new TableColumn<MeasurementBean, String>("Dress");
    	dress.setCellValueFactory(new PropertyValueFactory<MeasurementBean, String>("dress"));
    	dress.setMinWidth(100);
    	
    	TableColumn<MeasurementBean, String> doo = new TableColumn<MeasurementBean, String>("Date of order");
    	doo.setCellValueFactory(new PropertyValueFactory<MeasurementBean, String>("doo"));
    	doo.setMinWidth(100);
    	
    	TableColumn<MeasurementBean, String> dod = new TableColumn<MeasurementBean, String>("Date of delivery");
    	dod.setCellValueFactory(new PropertyValueFactory<MeasurementBean, String>("dod"));
    	dod.setMinWidth(100);
    	
    	TableColumn<MeasurementBean, String> amount = new TableColumn<MeasurementBean, String>("Amount");
    	amount.setCellValueFactory(new PropertyValueFactory<MeasurementBean, String>("amount"));
    	amount.setMinWidth(100);
    	
    	tblMeas.getColumns().clear();
    	tblMeas.getColumns().addAll(cname, cmob, dress, wname, doo, dod, amount);
    	
    }
    
    @FXML 
    void doFetchAll(ActionEvent event) {
    	fillColumns();

    	try {
			pst = con.prepareStatement("select * from measurements");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ObservableList<MeasurementBean> list = fetch(pst);
    	tblMeas.setItems(list);
    }

    @FXML
    void doFetchByCust(ActionEvent event) {
    	fillColumns();
    	
    	String cname = txtCustname.getText();
    	try {
			pst = con.prepareStatement("SELECT * FROM measurements WHERE custname=?");
			pst.setString(1, cname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ObservableList<MeasurementBean> list = fetch(pst);
    	tblMeas.setItems(list);
    }

    @FXML
    void doFetchByDeadline(ActionEvent event) {
    	fillColumns();
    	
    	try {
			pst = con.prepareStatement("SELECT * FROM measurements where dod<=?");
			pst.setDate(1, Date.valueOf(dateDeadline.getValue()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ObservableList<MeasurementBean> list = fetch(pst);
    	tblMeas.setItems(list);
    }

    @FXML
    void doFetchByWorker(ActionEvent event) {
    	String wname = comboWname.getEditor().getText();
    	String status2 = comboStatus.getEditor().getText();
    	int status = getStatus();
    	fillColumns();
    	
    	if(status2.equals("All")) {
    		try {
    			pst = con.prepareStatement("SELECT * FROM measurements WHERE spl=?");
    			pst.setString(1, wname);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	} else {
    		try {
    			pst = con.prepareStatement("SELECT * FROM measurements WHERE spl=? AND status=?");
    			pst.setString(1, wname);
    			pst.setInt(2, status);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	
    	
    	ObservableList<MeasurementBean> list = fetch(pst);
    	tblMeas.setItems(list);
    }
    
    int getStatus() {
    	String status = comboStatus.getEditor().getText();
    	if(status.equals("New"))
    		return 1;
    	else if(status.equals("In progress"))
    		return 2;
    	else if(status.equals("Finished"))
    		return 3;
    	else
    		return 0;
    }
    
    ObservableList<MeasurementBean> fetch(PreparedStatement pst) {
    	ObservableList<MeasurementBean> ary = FXCollections.observableArrayList();
    	try {
			ResultSet table = pst.executeQuery();
			while(table.next()) {
				int oid = table.getInt("OID");
				String cname = table.getString("custname");
				String cmob = table.getString("custmobile");
				String dress = table.getString("dress");
				String worker = table.getString("spl");
				String doo = table.getString("doo");
				String dod = table.getString("dod");
				int ammount = table.getInt("amount");
				MeasurementBean bean = new MeasurementBean(cname, cmob, dress, worker,  doo, dod, ammount);
				System.out.println("name: " + cname + "    mobile: " + cmob + "    dress: " + dress + "    worker: " + worker + "    doo: " + doo + "    dod: " + dod + "    ammount: " + ammount);
				ary.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ary;
    }

    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comboWname != null : "fx:id=\"comboWname\" was not injected: check your FXML file 'MeasurementTableView.fxml'.";
        assert comboStatus != null : "fx:id=\"comboStatus\" was not injected: check your FXML file 'MeasurementTableView.fxml'.";
        assert txtCustname != null : "fx:id=\"txtCustname\" was not injected: check your FXML file 'MeasurementTableView.fxml'.";
        assert dateDeadline != null : "fx:id=\"dateDeadline\" was not injected: check your FXML file 'MeasurementTableView.fxml'.";
        assert tblMeas != null : "fx:id=\"tblMeas\" was not injected: check your FXML file 'MeasurementTableView.fxml'.";
        
        con = MySqlConnection.doConnect();
        
        ArrayList<String> statusList = new ArrayList<String>(Arrays.asList("New", "In progress", "Finished", "All"));
        comboStatus.getItems().addAll(statusList);
        
        ArrayList<String> workerList = getWorkers();
        comboWname.getItems().addAll(workerList);
    }
    
    ArrayList getWorkers() {
    	ArrayList<String> w = new ArrayList<String>();
    	try {
			pst = con.prepareStatement("select distinct spl from measurements");
			ResultSet table = pst.executeQuery();
			while(table.next()) {
				String worker = table.getString(1);
				w.add(worker);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return w;
    }
}
