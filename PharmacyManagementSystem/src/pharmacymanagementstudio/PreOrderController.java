/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacymanagementstudio;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class PreOrderController implements Initializable {

    @FXML
    private JFXButton backBtn;

    /**
     * Initializes the controller class.
     */
     String authentication,query;
    ResultSet resultSet;
    ConnectDatabase connect2=new ConnectDatabase();
    ObservableList<PreOrderTable> data;
    @FXML
    private TableView<PreOrderTable> preOrder;
    @FXML
    private TableColumn<?, ?> orderId;
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
        query="Select PO.Order_id,PO.Medicine_id,PO.Customer_id,PO.Quantity,PO.is_Pending,PO.MedicineName from \n" +
        "(select Medicine.Medicine_id as Medicine_id,sum(Provide.Quantity) as TotalQuantity  from Medicine inner join PROVIDE on PROVIDE.Medicine_id = Medicine.Medicine_id \n" +
        "where PROVIDE.ExpireDate>GETDATE() group by Medicine.Medicine_id) Provide_Medicine join (Select PreOrder.Order_id,PreOrder.Medicine_id,PreOrder.Customer_id,PreOrder.Quantity,PreOrder.is_Pending,Medicine.MedicineName from Medicine inner join PreOrder on PreOrder.Medicine_id=Medicine.Medicine_id) PO \n" +
        "on PO.Medicine_id=Provide_Medicine.Medicine_id where PO.Quantity<=Provide_Medicine.TotalQuantity AND PO.is_Pending='YES';"; 
           try {
                resultSet=connect2.connectDB(query);
                while(resultSet.next()){
                    data.add(new PreOrderTable(resultSet.getString("Order_id"),resultSet.getString("Customer_id"),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Quantity")));
                   }    
            } catch (SQLException ex) {
                  //Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        preOrder.setItems(data);
        
        orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        medicineId.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        medicineName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    } 
public class PreOrderTable{
    private String orderId;
    private String customerId;
    private String medicineId;
    private String medicineName;
    private String quantity;
   
    public PreOrderTable(String orderId,String customerId,String medicineId,String medicineName,String  quantity){
    this.orderId=orderId;
    this.customerId=customerId;
    this.medicineId=medicineId;
    this.medicineName=medicineName;
    this.quantity=quantity;
    }
    
        /**
         * @return the orderId
         */
        public String getOrderId() {
            return orderId;
        }

        /**
         * @param orderId the orderId to set
         */
        public void setOrderId(String orderId) {
            this.orderId = orderId;
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
    }    
     public void setAuthenticationLabel(String strin){
       
         authentication=strin;
   }
}
