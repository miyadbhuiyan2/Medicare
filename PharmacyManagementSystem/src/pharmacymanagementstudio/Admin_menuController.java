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
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class Admin_menuController implements Initializable {

    @FXML
    private JFXButton homeBtn;
    @FXML
    private JFXButton searchBtn;
    @FXML
    private JFXButton addNewBtn;
    @FXML
    private JFXButton sellProductBtn;
    @FXML
    private JFXButton historyBtn;
    @FXML
    private JFXButton editRecordBtn;
    @FXML
    private JFXButton profileBtn;
    @FXML
    private JFXButton logOutBtn;
    @FXML
    private JFXButton aboutBtn;
    @FXML
    private JFXButton totalMedicineBtn;
    @FXML
    private JFXButton expireMedicineBtn;
    @FXML
    private JFXButton outOfStockBtn;
    @FXML
    private JFXButton preOrderBtn;
    @FXML
    private JFXButton scheduleBtn;
    @FXML
    private Label authenticationLabel;

    /**
     * Initializes the controller class.
     */
    
    String authentication,query;
      ResultSet resultSet;
 ConnectDatabase connect2=new ConnectDatabase();
    @FXML
    private Label totalMedicine;
    @FXML
    private Label expiredMedicine;
    @FXML
    private Label outOfStock;
    @FXML
    private Label preOrder;
    @FXML
    private Label schedule;
    
     public void homeButtonClicked(ActionEvent event) throws IOException
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
    @FXML
     public void searchButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("search.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        SearchController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
     public void addNewButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Admin_addNew.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        Admin_addNewController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
     public void sellProductsButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("sellMedicine.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        SellMedicineController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
      public void historyButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("history.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        HistoryController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
       public void editRecordButtonClicked(ActionEvent event) throws IOException
    {
       FXMLLoader loader=new FXMLLoader(getClass().getResource("editRecords.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        EditRecordsController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
        public void profileButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("profile.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        ProfileController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
         public void logoutButtonClicked(ActionEvent event) throws IOException
    {Stage window2=(Stage)((Node)event.getSource()).getScene().getWindow();
            window2.close();
        Parent adminmenuParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); 
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
          public void aboutButtonClicked(ActionEvent event) throws IOException
    {
       FXMLLoader loader=new FXMLLoader(getClass().getResource("about.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        AboutController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
    public void totalmedicineButtonClicked(ActionEvent event) throws IOException
    {
       FXMLLoader loader=new FXMLLoader(getClass().getResource("totalMedicine.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        TotalMedicineController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
     public void expiremedicinesButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("expireMedicines.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        ExpireMedicinesController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
     public void outofstockNewButtonClicked(ActionEvent event) throws IOException
    {   FXMLLoader loader=new FXMLLoader(getClass().getResource("outOfStock.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        OutOfStockController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
     public void preorderProductsButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("preOrder.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        PreOrderController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
      public void scheduleButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("schedule.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        ScheduleController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }      
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ///////////////////////////TotalMedicine///////////////////////////////////////
            query="select count(Medicine_id) as TotalMedicine from Medicine";
            resultSet=connect2.connectDB(query);
            resultSet.next();
            totalMedicine.setText(resultSet.getString("TotalMedicine"));
           ///////////////////////////ExpireMedicines///////////////////////////////////////
            query="select count(Batch_no) as TotalExpired from Provide where ExpireDate<=GETDATE() AND Quantity>0";
            resultSet=connect2.connectDB(query);
            resultSet.next();
            expiredMedicine.setText(resultSet.getString("TotalExpired"));
           ///////////////////////////OutOfStock///////////////////////////////////////
            query="Select count(B.Medicine_id) as TotalStockOut from\n" +
                  "(Select Medicine_id from Medicine where Quantity=0\n" +
                  "Union\n" +
                  "(Select A.Medicine_id from (SELECT Medicine_id,max(ExpireDate) as ExpDate from Provide group by Medicine_id) \n" +
                  "as A where A.ExpDate<GETDATE())) as B;";
            resultSet=connect2.connectDB(query);
            resultSet.next();
            outOfStock.setText(resultSet.getString("TotalStockOut"));
            ///////////////////////////PreOrder///////////////////////////////////////
            query="Select count(PO.Order_id) as TotalPreOrder from \n" +
                  "(select Medicine.Medicine_id as Medicine_id,sum(Provide.Quantity) as TotalQuantity  from Medicine inner join PROVIDE on PROVIDE.Medicine_id = Medicine.Medicine_id \n" +
                  "where PROVIDE.ExpireDate>GETDATE() group by Medicine.Medicine_id) Provide_Medicine join (Select PreOrder.Order_id,PreOrder.Medicine_id,PreOrder.Customer_id,PreOrder.Quantity,PreOrder.is_Pending,Medicine.MedicineName from Medicine inner join PreOrder on PreOrder.Medicine_id=Medicine.Medicine_id) PO \n" +
                  "on PO.Medicine_id=Provide_Medicine.Medicine_id where PO.Quantity<=Provide_Medicine.TotalQuantity AND PO.is_Pending='YES';";
            resultSet=connect2.connectDB(query);
            resultSet.next();
            preOrder.setText(resultSet.getString("TotalPreOrder"));
            ///////////////////////////Schedule///////////////////////////////////////
            query="Select count(SCHD.Schedule_id) as TotalSchedule from \n" +
                  "(select Medicine.Medicine_id as Medicine_id,sum(Provide.Quantity) as TotalQuantity  from Medicine inner join PROVIDE on PROVIDE.Medicine_id = Medicine.Medicine_id \n" +
                  "where PROVIDE.ExpireDate>GETDATE() group by Medicine.Medicine_id) Provide_Medicine join (Select SCHEDULE.Schedule_id,SCHEDULE.Date,SCHEDULE.Medicine_id,SCHEDULE.Customer_id,SCHEDULE.Quantity,SCHEDULE.is_Pending,Medicine.MedicineName from Medicine inner join SCHEDULE on SCHEDULE.Medicine_id=Medicine.Medicine_id) SCHD \n" +
                  "on SCHD.Medicine_id=Provide_Medicine.Medicine_id where SCHD.Quantity<=Provide_Medicine.TotalQuantity AND SCHD.is_Pending='YES' AND SCHD.Date<=GETDATE();";
            resultSet=connect2.connectDB(query);
            resultSet.next();
            schedule.setText(resultSet.getString("TotalSchedule"));
        } catch (SQLException ex) {
            //Logger.getLogger(Admin_menuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     
    } 
    
   public void setAuthenticationLabel(String strin){
        authentication=strin;
        if(authentication.contains("OWNER")){
            authenticationLabel.setText("OWNER");
            
        }
        else{
            authenticationLabel.setText("EMPLOYEE");
        }
         
   }   
   
}
