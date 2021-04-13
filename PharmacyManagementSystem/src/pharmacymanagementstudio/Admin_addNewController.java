/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacymanagementstudio;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */

public class Admin_addNewController implements Initializable {

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
    private JFXButton logoutBtn;
    @FXML
    private JFXButton aboutBtn;
    @FXML
    private JFXButton addMedicine;
    @FXML
    private JFXButton restockBtn;
    @FXML
    private JFXButton addSupplierBtn;
    @FXML
    private JFXButton addCustomerBtn;
    @FXML
    private JFXButton addEmployeeAdminBtn;
    @FXML
    private JFXButton addPreOrderBtn;
    @FXML
    private JFXButton addScheduleBtn;
    @FXML
    private Label authenticationLabel;

    /**
     * Initializes the controller class.
     */
    String authentication;
    @FXML
    private AnchorPane pane;
    @FXML
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
   /////////////////////////////////////////////////////////////////////////////////////////////////////////       
    @FXML
     public void addmedicineButtonClicked(ActionEvent event) throws IOException
    {
        try{
            if(authentication.contains("OWNER")){
            FXMLLoader loader=new FXMLLoader(getClass().getResource("addMedicine.fxml"));
            Parent adminmenuParent=(Parent)loader.load();

            AddMedicineController amc=loader.getController();
            amc.setAuthenticationLabel(authentication);

            Scene adminmenuScene = new Scene(adminmenuParent);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(adminmenuScene);
            window.show();
            }
            else{
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Only Owner can access this");
            alert.getDialogPane().setHeaderText("Access Denied");
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){} 
        }
        }catch(Exception ex){}           
    }
    @FXML
     public void restockButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("restock.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        RestockController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
     public void addsupplierButtonClicked(ActionEvent event) throws IOException
    {
        try{
            if(authentication.contains("OWNER")){
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AddSupplier.fxml"));
            Parent adminmenuParent=(Parent)loader.load();

            AddSupplierController amc=loader.getController();
            amc.setAuthenticationLabel(authentication);

            Scene adminmenuScene = new Scene(adminmenuParent);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(adminmenuScene);
            window.show();
            }
            else{
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Only Owner can access this");
            alert.getDialogPane().setHeaderText("Access Denied");
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){} 
        }
        }catch(Exception ex){}
        
    }
    @FXML
     public void addcustomerProductsButtonClicked(ActionEvent event) throws IOException
    {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("AddCustomer.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        AddCustomerController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
      public void addscheduleButtonClicked(ActionEvent event) throws IOException
    {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("AddSchedule.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        AddScheduleController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }  
    @FXML
     public void addpreorderButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AddPreOrder.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        AddPreOrderController amc=loader.getController();
        amc.setAuthenticationLabel(authentication);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }  
    @FXML
      public void addemployeeadminButtonClicked(ActionEvent event) throws IOException
    {

        try{
            if(authentication.contains("OWNER")){
                FXMLLoader loader=new FXMLLoader(getClass().getResource("AddEmloyeeAdmin.fxml"));
                Parent adminmenuParent=(Parent)loader.load();

                AddEmloyeeAdminController amc=loader.getController();
                amc.setAuthenticationLabel(authentication);

                Scene adminmenuScene = new Scene(adminmenuParent);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(adminmenuScene);
                window.show();
            }
            else{
                Stage stage=(Stage) pane.getScene().getWindow();
                Alert.AlertType type=Alert.AlertType.ERROR;
                Alert alert=new Alert(type,"");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(stage);
                alert.getDialogPane().setContentText("Only Owner can access this");
                alert.getDialogPane().setHeaderText("Access Denied");
                Optional<ButtonType> result=alert.showAndWait();
                if(result.get()==ButtonType.OK){} 
        }
        }catch(Exception ex){}
}  
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
