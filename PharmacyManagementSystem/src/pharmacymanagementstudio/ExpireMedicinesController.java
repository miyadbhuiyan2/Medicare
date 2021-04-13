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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ExpireMedicinesController implements Initializable {

    @FXML
    private JFXButton backBtn;

    /**
     * Initializes the controller class.
     */
     String authentication,query;
    ResultSet resultSet;
    ConnectDatabase connect2=new ConnectDatabase();
    ObservableList<ExpireMedicineTable> data;
    @FXML
    private TableView<ExpireMedicineTable> expiredMedicine;
    @FXML
    private TableColumn<?, ?> expireDate;
    @FXML
    private TableColumn<?, ?> medicineId;
    @FXML
    private TableColumn<?, ?> medicineName;
    @FXML
    private TableColumn<?, ?> batch;
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
        query="Select Provide.ExpireDate,Provide.Medicine_id,Medicine.MedicineName,Provide.Batch_no,Provide.Quantity\n" +
        "from Provide inner join Medicine on Provide.Medicine_id=Medicine.Medicine_id where Provide.ExpireDate<=GETDATE() AND Provide.Quantity>0;"; 
        try {
                resultSet=connect2.connectDB(query);
                while(resultSet.next()){
                    data.add(new ExpireMedicineTable(resultSet.getString("ExpireDate"),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Batch_no"),resultSet.getString("Quantity")));
                   }    
            } catch (SQLException ex) {
                  //Logger.getLogger(ExpireMedicinesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        expiredMedicine.setItems(data);
        
        expireDate.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        medicineId.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        medicineName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        batch.setCellValueFactory(new PropertyValueFactory<>("batchNo"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    } 
public class ExpireMedicineTable{
    private String expireDate;
    private String medicineId;
    private String medicineName;
    private String batchNo;
    private String quantity;
    
    public ExpireMedicineTable(String expireDate,String medicineId,String medicineName,String batchNo,String  quantity){
    this.expireDate=expireDate;
    this.medicineId=medicineId;
    this.medicineName=medicineName;
    this.batchNo=batchNo;
    this.quantity=quantity;      
    }
    
        /**
         * @return the expireDate
         */
        public String getExpireDate() {
            return expireDate;
        }

        /**
         * @param expireDate the expireDate to set
         */
        public void setExpireDate(String expireDate) {
            this.expireDate = expireDate;
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
         * @return the batchNo
         */
        public String getBatchNo() {
            return batchNo;
        }

        /**
         * @param batchNo the batchNo to set
         */
        public void setBatchNo(String batchNo) {
            this.batchNo = batchNo;
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
