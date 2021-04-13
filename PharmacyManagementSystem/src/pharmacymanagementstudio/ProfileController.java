/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacymanagementstudio;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ProfileController implements Initializable {

    @FXML
    private JFXButton homeBtn;
    @FXML
    private JFXButton searchBtn;
    @FXML
    private JFXButton addNewBtn;
    @FXML
    private JFXButton sellProductsBtn;
    @FXML
    private JFXButton historyBtn;
    @FXML
    private JFXButton editRecordsBtn;
    @FXML
    private JFXButton profileBtn;
    @FXML
    private JFXButton logOutBtn;
    @FXML
    private JFXButton aboutBtn;
    @FXML
    private Text userID;
    @FXML
    private Text name;
    @FXML
    private Text contactNumber;
    @FXML
    private Text address;
    @FXML
    private JFXButton changePasswordBtn;
    @FXML
    private Label authenticationLabel;

    /**
     * Initializes the controller class.
     */
    String authentication,query,userIdText,passwordText;
    ResultSet resultSet;
     ConnectDatabase connect2=new ConnectDatabase();
    @FXML
    private JFXPasswordField oldpassword;
    @FXML
    private JFXPasswordField newpassword;
    @FXML
    private JFXPasswordField confirmPassword;
    @FXML
    private AnchorPane profileAnchorPane;
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
    public void changePasswordButtonClicked(ActionEvent event)
    {   try{
        if(!(oldpassword.getText().isEmpty()||newpassword.getText().isEmpty()||confirmPassword.getText().isEmpty()))
        {
         if(authentication.contains("OWNER")){
        if(newpassword.getText().equals(confirmPassword.getText())&&passwordText.equals(oldpassword.getText())){
            
            query="UPDATE Owner set Password='"+newpassword.getText()+"' where Owner_id="+userIdText;
            try {
                connect2.connectDBUpdate(query);
                 Stage stage=(Stage) profileAnchorPane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.INFORMATION;
             
            Alert alert=new Alert(type,"");
         
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Your password has been changed succefully");
            alert.getDialogPane().setHeaderText("Done");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
            } catch (SQLException ex) {
              
                //Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                
                //Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);   
            }
            
        }
        else if(!newpassword.getText().equals(confirmPassword.getText()))
        {
            Stage stage=(Stage) profileAnchorPane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("You have entered wrong confirm password");
            alert.getDialogPane().setHeaderText("Failed");
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
        }
        else{
            Stage stage=(Stage) profileAnchorPane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("You have entered wrong old password");
            alert.getDialogPane().setHeaderText("Failed");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
            
        }
    }
    else if(authentication.contains("EMPLOYEE")){
        if(newpassword.getText().equals(confirmPassword.getText())&&passwordText.equals(oldpassword.getText())){
            query="UPDATE Employee set Password="+newpassword.getText()+" where Employee_id="+userIdText;
            try {
                connect2.connectDBUpdate(query);
                 Stage stage=(Stage) profileAnchorPane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.INFORMATION;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Your password has been changed succefully");
            alert.getDialogPane().setHeaderText("Done");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
            } catch (SQLException ex) {
               // Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
              //  Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        else if(!newpassword.getText().equals(confirmPassword.getText()))
        {
            Stage stage=(Stage) profileAnchorPane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("You have entered wrong new confirm password");
            alert.getDialogPane().setHeaderText("Failed");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
        }
        else{
            Stage stage=(Stage) profileAnchorPane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("You have entered wrong old password");
            alert.getDialogPane().setHeaderText("Failed");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
            
        }
        }
        }
        else{
         Stage stage=(Stage) profileAnchorPane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Please fill all the fields first");
            alert.getDialogPane().setHeaderText("Failed");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
        }
        }
        catch(Exception ex){
        
        }

}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setAuthenticationLabel(String strin){
        authentication=strin;
        if(authentication.contains("OWNER")){
            authenticationLabel.setText("OWNER");
            userIdText = authentication.substring(authentication.indexOf("(")+1,authentication.indexOf(")"));
            passwordText = authentication.substring(authentication.indexOf("[")+1,authentication.indexOf("]"));
           
            
            try {
                 query="select * from Owner where Owner_id="+userIdText;
            resultSet=connect2.connectDB(query);
                resultSet.next();
                userID.setText(resultSet.getString("Owner_id"));
                name.setText(resultSet.getString("Name"));
                contactNumber.setText(resultSet.getString("Contact Number"));
                address.setText(resultSet.getString("Address"));
                
                
            } catch (SQLException ex) {
               // Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        }
        else{
            authenticationLabel.setText("EMPLOYEE");
             userIdText = authentication.substring(authentication.indexOf("(")+1,authentication.indexOf(")"));
             passwordText = authentication.substring(authentication.indexOf("[")+1,authentication.indexOf("]"));
              
             try {
                 query="select * from Employee where Employee_id="+userIdText;
                resultSet=connect2.connectDB(query);
                resultSet.next();
                userID.setText(resultSet.getString("Employee_id"));
                name.setText(resultSet.getString("Name"));
                contactNumber.setText(resultSet.getString("Contact Number"));
                address.setText(resultSet.getString("Address"));
                
                
           } catch (SQLException ex) {
               // Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
         
   }  
}
