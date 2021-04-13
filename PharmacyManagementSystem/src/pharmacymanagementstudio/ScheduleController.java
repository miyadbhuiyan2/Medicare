/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacymanagementstudio;


import com.jfoenix.controls.JFXButton;
import static java.awt.SystemColor.text;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.text.Document;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ScheduleController implements Initializable {

    @FXML
    private TableView<ScheduleTable> scheduleTable;
    @FXML
    private TableColumn<?,?> scheduleId;
    @FXML
    private TableColumn<?,?> date;
    @FXML
    private JFXButton backBtn;

    /**
     * Initializes the controller class.
     */
    String authentication,query;
    ResultSet resultSet;
    ConnectDatabase connect2=new ConnectDatabase();
    ObservableList<ScheduleTable> data;
    @FXML
    private TableColumn<?, ?> customerId;
    @FXML
    private TableColumn<?, ?> medicineId;
    @FXML
    private TableColumn<?, ?> medicineName;
    @FXML
    private TableColumn<?, ?> quantity;
    @FXML
     public void backButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Admin_menu.fxml"));
         Parent adminmenuParent=(Parent)loader.load();
         
         Admin_menuController amc=loader.getController();
         amc.setAuthenticationLabel(authentication);
         
         Scene adminmenuScene = new Scene(adminmenuParent);
         Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
         window.setScene(adminmenuScene);
         window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         data=FXCollections.observableArrayList();
        query="Select SCHD.Schedule_id,SCHD.Medicine_id,SCHD.Customer_id,SCHD.Quantity,SCHD.is_Pending,SCHD.MedicineName,SCHD.Date from \n" +
        "(select Medicine.Medicine_id as Medicine_id,sum(Provide.Quantity) as TotalQuantity  from Medicine inner join PROVIDE on PROVIDE.Medicine_id = Medicine.Medicine_id \n" +
        "where PROVIDE.ExpireDate>GETDATE() group by Medicine.Medicine_id) Provide_Medicine join (Select SCHEDULE.Schedule_id,SCHEDULE.Date,SCHEDULE.Medicine_id,SCHEDULE.Customer_id,SCHEDULE.Quantity,SCHEDULE.is_Pending,Medicine.MedicineName from Medicine inner join SCHEDULE on SCHEDULE.Medicine_id=Medicine.Medicine_id) SCHD \n" +
        "on SCHD.Medicine_id=Provide_Medicine.Medicine_id where SCHD.Quantity<=Provide_Medicine.TotalQuantity AND SCHD.is_Pending='YES' AND SCHD.Date<=GETDATE();"; 
        try {
                resultSet=connect2.connectDB(query);
                while(resultSet.next()){
                    data.add(new ScheduleTable(resultSet.getString("Schedule_id"),resultSet.getString("Date"),resultSet.getString("Customer_id"),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Quantity")));
                   }    
            } catch (SQLException ex) {
                  //Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        scheduleTable.setItems(data);
        
        scheduleId.setCellValueFactory(new PropertyValueFactory<>("scheduleId"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        medicineId.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        medicineName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

     
    }    
public class ScheduleTable{
    private String scheduleId;
    private String date;
    private String customerId;
    private String medicineId;
    private String medicineName;
    private String quantity;
    
    public ScheduleTable(String scheduleId,String date,String customerId,String medicineId,String medicineName,String  quantity){
    this.scheduleId=scheduleId;
    this.date=date;
    this.customerId=customerId;        
    this.medicineId=medicineId;
    this.medicineName=medicineName;
    this.quantity=quantity;

    }

        /**
         * @return the scheduleId
         */
        public String getScheduleId() {
            return scheduleId;
        }

        /**
         * @param scheduleId the scheduleId to set
         */
        public void setScheduleId(String scheduleId) {
            this.scheduleId = scheduleId;
        }

        /**
         * @return the date
         */
        public String getDate() {
            return date;
        }

        /**
         * @param date the date to set
         */
        public void setDate(String date) {
            this.date = date;
        }

        /**
         * @return the customerId
         */
        public String getCustomerId() {
            return customerId;
        }

        /**
         * @param customerId the customerId to set
         */
        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        /**
         * @return the medicineId
         */
        public String getMedicineId() {
            return medicineId;
        }

        /**
         * @param medicineId the medicineId to set
         */
        public void setMedicineId(String medicineId) {
            this.medicineId = medicineId;
        }

        /**
         * @return the medicineName
         */
        public String getMedicineName() {
            return medicineName;
        }

        /**
         * @param medicineName the medicineName to set
         */
        public void setMedicineName(String medicineName) {
            this.medicineName = medicineName;
        }

        /**
         * @return the quantity
         */
        public String getQuantity() {
            return quantity;
        }

        /**
         * @param quantity the quantity to set
         */
        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }
    }     public void setAuthenticationLabel(String strin){
       
         authentication=strin;
   }
}
