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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class OutOfStockController implements Initializable {

    @FXML
    private JFXButton backBtn;

    /**
     * Initializes the controller class.
     */
    String authentication,query;
    ResultSet resultSet;
    ConnectDatabase connect2=new ConnectDatabase();
    ObservableList<OutOfStockTable> data;
    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<OutOfStockTable> outOfStock;
    @FXML
    private TableColumn<?, ?> medicineId;
    @FXML
    private TableColumn<?, ?> medicineName;
    @FXML
    private TableColumn<?, ?> quantity;
    @FXML
    private TableColumn<?, ?> company;
    @FXML
     public void backButtonClicked(ActionEvent event) throws IOException
    {    FXMLLoader loader=new FXMLLoader(getClass().getResource("Admin_menu.fxml"));
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
        query="Select Medicine.Medicine_id,Medicine.MedicineName,Medicine.Quantity,Medicine.CompanyName from Medicine inner join\n" +
              "(Select Medicine_id from Medicine where Quantity<=5\n" +
              "Union\n" +
              "(Select A.Medicine_id from (SELECT Medicine_id,max(ExpireDate) as ExpDate from Provide group by Medicine_id) \n" +
              "as A where A.ExpDate<GETDATE())) as B on B.Medicine_id=Medicine.Medicine_id;"; 
        try {
                resultSet=connect2.connectDB(query);
                while(resultSet.next()){
                    data.add(new OutOfStockTable(resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Quantity"),resultSet.getString("CompanyName")));
                   }    
            } catch (SQLException ex) {
                  //Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        outOfStock.setItems(data);
        
        medicineId.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        medicineName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        company.setCellValueFactory(new PropertyValueFactory<>("companyName"));
    } 
public class OutOfStockTable{
    private String medicineId;
    private String medicineName;
    private String quantity;
    private String companyName;
    
    public OutOfStockTable(String medicineId,String medicineName,String  quantity,String companyName){
    this.medicineId=medicineId;
    this.medicineName=medicineName;
    this.quantity=quantity;
    this.companyName=companyName;        
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

        /**
         * @return the companyName
         */
        public String getCompanyName() {
            return companyName;
        }

        /**
         * @param companyName the companyName to set
         */
        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }
    }    
     public void setAuthenticationLabel(String strin){
       
         authentication=strin;
   }
}
