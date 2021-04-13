/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacymanagementstudio;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class EditEmployeeAdminController implements Initializable {

    @FXML
    private JFXTextField userId;
    @FXML
    private JFXButton fetchBtn;
    @FXML
    private JFXTextField Name;
    @FXML
    private JFXTextField contactNumber;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField confirmPassowrd;
    @FXML
    private JFXComboBox<String> authentication;
    @FXML
    private JFXButton backBtn;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXButton editBtn;

    /**
     * Initializes the controller class.
     */
     String authentication2,query,fetchedAuthentication;
     ResultSet resultSet;
     ConnectDatabase connect2=new ConnectDatabase();
    @FXML
    private JFXButton resetBtn;
    @FXML
    private AnchorPane pane;
    @FXML
    public void backButtonClicked(ActionEvent event) throws IOException
    {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("editRecords.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        EditRecordsController amc=loader.getController();
        amc.setAuthenticationLabel(authentication2);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
    public void resetButtonClicked(ActionEvent event)
    {  userId.setText("");
       Name.setText("");
       contactNumber.setText("");
       address.setText("");
       password.setText("");
       confirmPassowrd.setText("");
        authentication.setValue("Select Authentication");
    }
    @FXML
    public void deleteButtonClicked(ActionEvent event)
    { if(!(authentication.getValue().equals("Select Authentication")&&userId.getText().isEmpty())){ 
        
        try {   
             Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.WARNING;
             
            Alert alert=new Alert(type,"");
         
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setHeaderText("Are you sure want to delete this account?");
            alert.getDialogPane().setContentText("Note:This will also delete selling records of this account.This action can't be undone");
            
           ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("YES");
             ButtonType noButton=new ButtonType("NO",ButtonData.NO);
              alert.getDialogPane().getButtonTypes().add(noButton);
            
            Optional<ButtonType> result=alert.showAndWait();
                if(result.get()==ButtonType.OK){
                 if(authentication.getValue().equals("Owner")){
                    query="DELETE FROM Sell where Owner_id="+Integer.parseInt(userId.getText().substring(1, userId.getText().indexOf("]")));
                    connect2.connectDBUpdate(query);
                    
                    query="DELETE FROM Owner where Owner_id="+Integer.parseInt(userId.getText().substring(1, userId.getText().indexOf("]")));
                    connect2.connectDBUpdate(query);
                 }
                else if(authentication.getValue().equals("Employee")){
                    query="DELETE FROM Sell where Employee_id="+Integer.parseInt(userId.getText().substring(1, userId.getText().indexOf("]")));
                    connect2.connectDBUpdate(query);
                    
                    query="DELETE FROM Employee where Employee_id="+Integer.parseInt(userId.getText().substring(1, userId.getText().indexOf("]")));
                    connect2.connectDBUpdate(query);
                 }   
                 
                Stage stage2=(Stage) pane.getScene().getWindow();
                Alert.AlertType type2=Alert.AlertType.INFORMATION;

                Alert alert2=new Alert(type2,"");

                alert2.initModality(Modality.APPLICATION_MODAL);
                alert2.initOwner(stage);
                alert2.getDialogPane().setContentText("The account has been deleted succefully");
                alert2.getDialogPane().setHeaderText("Done");

                Optional<ButtonType> result2=alert2.showAndWait();
                if(result.get()==ButtonType.OK){}
            }
            else  if(result.get()==ButtonType.NO){
              
              }       
        } catch (SQLException ex) {
            
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Error Occured.Try again.");
            alert.getDialogPane().setHeaderText("Failed");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
            //Logger.getLogger(EditEmployeeAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception ex){}
    }
    else{
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("User field and Authentication must be filled");
            alert.getDialogPane().setHeaderText("Failed");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
    }
    }
    @FXML
    public void fetchButtonClicked(ActionEvent event)
    { 
        try {  
        if(!(authentication.getValue().equals("Select Authentication")||userId.getText().isEmpty())){ 
        if(authentication.getValue().equals("Owner")){
         query="SELECT * FROM Owner where Owner_id="+Integer.parseInt(userId.getText().substring(1, userId.getText().indexOf("]")));
        }
        else if(authentication.getValue().equals("Employee")){
        query="SELECT * FROM Employee where Employee_id="+Integer.parseInt(userId.getText().substring(1, userId.getText().indexOf("]")));
        }
        
         
            resultSet=connect2.connectDB(query);
            resultSet.next();
             Name.setText(resultSet.getString("Name"));
             contactNumber.setText(resultSet.getString("Contact Number"));
             address.setText(resultSet.getString("Address"));
             password.setText(resultSet.getString("Password"));
             confirmPassowrd.setText("");
             fetchedAuthentication=authentication.getValue().toString();
   
    }
    else{
         Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("User field and Authentication must be filled");
            alert.getDialogPane().setHeaderText("Failed");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
    }
       } catch (SQLException ex) {
            
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Error Occured.Try again.");
            alert.getDialogPane().setHeaderText("Failed");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
            //Logger.getLogger(EditEmployeeAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception ex){
        
        } 
    }
    @FXML
    public void updateButtonClicked(ActionEvent event)
    {
       try{
        if(!(Name.getText().isEmpty()||contactNumber.getText().isEmpty()||address.getText().isEmpty()||password.getText().isEmpty()||authentication.getValue().equals("Select Authentication")))
        {
          if((!confirmPassowrd.getText().isEmpty()&&password.getText().equals(confirmPassowrd.getText()))||confirmPassowrd.getText().isEmpty()){
              try {
 ///////////////////////////////Owner////////////////////////////////////////////////////////////////// 
            if(authentication.getValue().equals("Owner")){
            if(authentication.getValue().equals(fetchedAuthentication)){
             if(!confirmPassowrd.getText().isEmpty())
                query="UPDATE Owner SET Name='"+Name.getText()+"',Password='"+password.getText()+"',[Contact Number]='"+contactNumber.getText()+"',Address='"+address.getText()+"' where Owner_id="+Integer.parseInt(userId.getText().substring(1, userId.getText().indexOf("]")));
             else
                query="UPDATE Owner SET Name='"+Name.getText()+"',[Contact Number]='"+contactNumber.getText()+"',Address='"+address.getText()+"' where Owner_id="+Integer.parseInt(userId.getText().substring(1, userId.getText().indexOf("]"))); 
             connect2.connectDBUpdate(query);
            
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.INFORMATION;
             
            Alert alert=new Alert(type,"");
         
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("The account has been updated succefully");
            alert.getDialogPane().setHeaderText("Done");
            
            Optional<ButtonType> result=alert.showAndWait();
                if(result.get()==ButtonType.OK){}
            }
            else{
            query="INSERT INTO Owner VALUES ('"+Name.getText()+"','"+password.getText()+"','"+contactNumber.getText()+"','"+address.getText()+"')";
            connect2.connectDBUpdate(query);
            
            query="DELETE FROM Employee WHERE Employee_id="+Integer.parseInt(userId.getText().substring(1, userId.getText().indexOf("]"))); 
            connect2.connectDBUpdate(query);
            
            query="SELECT Owner_id from Owner where Owner_id=(SELECT max(Owner_id) FROM Owner);";
            resultSet=connect2.connectDB(query);
            resultSet.next();
            
               
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.INFORMATION;
             
            Alert alert=new Alert(type,"");
         
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("The account has been shifted to Owner Class succefully. New Owner_id='"+resultSet.getString("Owner_id")+"'");
            alert.getDialogPane().setHeaderText("Done");
            
            Optional<ButtonType> result=alert.showAndWait();
                if(result.get()==ButtonType.OK){}
            }
            
              
            }
  ///////////////////////////////Employee///////////////////////////////////////////////////////////////               
        else if(authentication.getValue().equals("Employee")){           
           if(authentication.getValue().equals(fetchedAuthentication)){
             
             if(!confirmPassowrd.getText().isEmpty())  
             query="UPDATE Employee SET Name='"+Name.getText()+"',Password='"+password.getText()+"',[Contact Number]='"+contactNumber.getText()+"',Address='"+address.getText()+"' where Employee_id="+Integer.parseInt(userId.getText().substring(1, userId.getText().indexOf("]")));
             else
             query="UPDATE Employee SET Name='"+Name.getText()+"',[Contact Number]='"+contactNumber.getText()+"',Address='"+address.getText()+"' where Employee_id="+Integer.parseInt(userId.getText().substring(1, userId.getText().indexOf("]")));
                 
             connect2.connectDBUpdate(query);
            
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.INFORMATION;
             
            Alert alert=new Alert(type,"");
         
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("The account has been updated succefully");
            alert.getDialogPane().setHeaderText("Done");
            
            Optional<ButtonType> result=alert.showAndWait();
                if(result.get()==ButtonType.OK){}
            }
            else{
            query="INSERT INTO Employee VALUES ('"+Name.getText()+"','"+password.getText()+"','"+contactNumber.getText()+"','"+address.getText()+"')";
            connect2.connectDBUpdate(query);
            
            query="DELETE FROM Owner WHERE Owner_id="+Integer.parseInt(userId.getText().substring(1, userId.getText().indexOf("]"))); 
            connect2.connectDBUpdate(query);
            
            query="SELECT Employee_id from Employee where Employee_id=(SELECT max(Employee_id) FROM Employee);";
            resultSet=connect2.connectDB(query);
            resultSet.next();
            
               
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.INFORMATION;
             
            Alert alert=new Alert(type,"");
         
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("The account has been shifted to Employee Class succefully. New Employee_id='"+resultSet.getString("Employee_id")+"'");
            alert.getDialogPane().setHeaderText("Done");
            
            Optional<ButtonType> result=alert.showAndWait();
                if(result.get()==ButtonType.OK){}
            }
            
          
        }
        }catch (SQLException ex) {
            
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR; 
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setHeaderText("Failed");
            
                if(ex.getErrorCode()==2601)
                     alert.getDialogPane().setContentText("Data already exists");//Duplicate kew row
                else if(ex.getErrorCode()==2627)
                      alert.getDialogPane().setContentText("This phone number already exists");//Unique Constraint
                else
                    alert.getDialogPane().setContentText("Error occured.Try again.");// default message
                
                Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
                //Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                
                //Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);   
            }                
    }
    else if(!password.getText().equals(confirmPassowrd.getText()))
        {
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("You have entered wrong new confirm password");
            alert.getDialogPane().setHeaderText("Failed");
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
        }
        
        }
        else{
         Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Please fill all the fields first");
            alert.getDialogPane().setHeaderText("Failed");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
        }
       }catch(Exception ex){
        
        }
    }
    @FXML
    public void comboBoxClicked(ActionEvent event) throws IOException
    {
         ////////////////////////////////////////////////////////////////////////////////////////
        if(authentication.getValue().equals("Owner")){
        ArrayList<String> nameList=new ArrayList<String>();
        nameList.removeAll(nameList);
        query="SELECT Owner_id,Name FROM Owner;";
        try {
            resultSet=connect2.connectDB(query);
            while(resultSet.next()){
            nameList.add("["+resultSet.getString("Owner_id")+"] "+resultSet.getString("Name"));
            }    
                } catch (SQLException ex) {
            //Logger.getLogger(SellMedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AutoCompletionBinding<String> autocompletebinding = TextFields.bindAutoCompletion(userId,nameList);
        autocompletebinding.setPrefWidth(userId.getPrefWidth());
        autocompletebinding.setMaxWidth(userId.getMaxWidth());
        autocompletebinding.setVisibleRowCount(10);
        }
        else  if(authentication.getValue().equals("Employee")){
        ArrayList<String> nameList=new ArrayList<String>();
        nameList.removeAll(nameList);
        query="SELECT Employee_id,Name FROM Employee;";
        try {
            resultSet=connect2.connectDB(query);
            while(resultSet.next()){
            nameList.add("["+resultSet.getString("Employee_id")+"] "+resultSet.getString("Name"));
            }    
                } catch (SQLException ex) {
            //Logger.getLogger(SellMedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AutoCompletionBinding<String> autocompletebinding = TextFields.bindAutoCompletion(userId,nameList);
        autocompletebinding.setPrefWidth(userId.getPrefWidth());
        autocompletebinding.setMaxWidth(userId.getMaxWidth());
        autocompletebinding.setVisibleRowCount(10);
        }
        ///////////////////////////////////////////////////////////
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        authentication.getItems().addAll("Select Authentication","Owner","Employee");
        authentication.setValue("Select Authentication");
        Name.addEventFilter(KeyEvent.KEY_TYPED , Name_Validation(50));
        contactNumber.addEventFilter(KeyEvent.KEY_TYPED , contactNumber_Validation(11));
        address.addEventFilter(KeyEvent.KEY_TYPED , length_Validation(200));
        password.addEventFilter(KeyEvent.KEY_TYPED , length_Validation(10));
        confirmPassowrd.addEventFilter(KeyEvent.KEY_TYPED , length_Validation(10));
    }   

public EventHandler<KeyEvent> contactNumber_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[0-9]")){ 
                if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                    e.consume();
                }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                    e.consume(); 
                }
            }else{
                e.consume();
            }
        }
    };
}    
public EventHandler<KeyEvent> Name_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[A-Za-z. ]")){ 
            }else{
                e.consume();
            }
        }
    };
}
public EventHandler<KeyEvent> length_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
        }
    };
}    
    public void setAuthenticationLabel(String strin){
        authentication2=strin;
   }   
    
}
