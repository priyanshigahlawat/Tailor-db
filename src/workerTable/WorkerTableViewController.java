/**
 * Sample Skeleton for 'WorkerTableView.fxml' Controller Class
 */

package workerTable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import worker_console.MySqlConnection;

public class WorkerTableViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tblWorker"
    private TableView<WorkerBean> tblWorker; // Value injected by FXMLLoader
    
    PreparedStatement pst;

    @FXML
    void doShow(ActionEvent event) {
    	fillColumns();
    	
    	try {
			pst = con.prepareStatement("select * from workers");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ObservableList<WorkerBean> list = fetchAllRecords(pst);
    	tblWorker.setItems(list);
    }
    
    ObservableList<WorkerBean> fetchAllRecords(PreparedStatement pst) {
    	ObservableList<WorkerBean> ary = FXCollections.observableArrayList();
    	try {
			ResultSet table = pst.executeQuery();
			while(table.next()) {
				String wname = table.getString(1);
				String mobile = table.getString(2);
				String address = table.getString(4);
				String spl = table.getString(5);
				String dor = table.getString(6);
				System.out.println("worker name: " + wname + "    mobile: " + mobile + "    address: " + address + "    spl: " + "    dor: " + dor);
				WorkerBean bean = new WorkerBean(wname, mobile, address, spl, dor);
				ary.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return ary;
    }
    
    void fillColumns() {
    	TableColumn<WorkerBean, String> wname = new TableColumn<WorkerBean, String>("Worker name");
    	wname.setCellValueFactory(new PropertyValueFactory<WorkerBean, String>("wname"));
    	wname.setMinWidth(100);
    	
    	TableColumn<WorkerBean, String> mob = new TableColumn<WorkerBean, String>("Mobile");
    	mob.setCellValueFactory(new PropertyValueFactory<WorkerBean, String>("mobile"));
    	mob.setMinWidth(100);
    	
    	TableColumn<WorkerBean, String> address = new TableColumn<WorkerBean, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<WorkerBean, String>("address"));
    	address.setMinWidth(100);
    	
    	TableColumn<WorkerBean, String> spl = new TableColumn<WorkerBean, String>("Speciality");
    	spl.setCellValueFactory(new PropertyValueFactory<WorkerBean, String>("spl"));
    	spl.setMinWidth(100);
    	
    	TableColumn<WorkerBean, String> dor = new TableColumn<WorkerBean, String>("Date of recieving");
    	dor.setCellValueFactory(new PropertyValueFactory<WorkerBean, String>("dor"));
    	dor.setMinWidth(100);
    	
    	tblWorker.getColumns().clear();
    	tblWorker.getColumns().addAll(wname, mob, address, spl, dor);
    	
    }

    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert tblWorker != null : "fx:id=\"tblWorker\" was not injected: check your FXML file 'WorkerTableView.fxml'.";
        con = MySqlConnection.doConnect();
    }
}