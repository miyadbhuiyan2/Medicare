/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacymanagementstudio;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AddEmloyeeAdminController implements Initializable {
String authentication1,query,signedInUser;
 ResultSet resultSet;
 ConnectDatabase connect2=new ConnectDatabase();
    @FXML
    private JFXButton addBtn;

    /**
     * Initializes the controller class.
     */
    ChoiceBox<?> authentication;
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
    private JFXButton resetBtn;
    @FXML
    private ChoiceBox<String> authenticationSelect;
    @FXML
    private JFXButton backBtn;
    @FXML
    private AnchorPane addEmployeeAdminPane;
    @FXML
    private Text userId;
    @FXML
    public void backButtonClicked(ActionEvent event) throws IOException
    {
       FXMLLoader loader=new FXMLLoader(getClass().getResource("Admin_addNew.fxml"));
        Parent adminmenuParent=(Parent)loader.load();
        
        Admin_addNewController amc=loader.getController();
        amc.setAuthenticationLabel(authentication1);
        
        Scene adminmenuScene = new Scene(adminmenuParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminmenuScene);
        window.show();
    }
    @FXML
    public void resetButtonClicked(ActionEvent event)
    {   userId.setText("");
       Name.setText("");
       contactNumber.setText("");
       address.setText("");
       password.setText("");
       confirmPassowrd.setText("");
        authenticationSelect.setValue("Select Authentication");
    }
    @FXML
    public void addButtonClicked(ActionEvent event)
    {
       try{
        if(!(Name.getText().isEmpty()||contactNumber.getText().isEmpty()||address.getText().isEmpty()||password.getText().isEmpty()|| confirmPassowrd.getText().isEmpty()||authenticationSelect.getValue().equals("Select Authentication")))
        {
          if(password.getText().equals(confirmPassowrd.getText())){
              try {
 ///////////////////////////////Owner////////////////////////////////////////////////////////////////// 
            if(authenticationSelect.getValue().equals("Owner")){
         
            query="INSERT INTO Owner VALUES ('"+Name.getText()+"','"+password.getText()+"','"+contactNumber.getText()+"','"+address.getText()+"')";
            
            connect2.connectDBUpdate(query);
             
            query="SELECT Owner_id from Owner where Owner_id=(SELECT max(Owner_id) FROM Owner);";
            resultSet=connect2.connectDB(query);
            resultSet.next();
            userId.setText(resultSet.getString("Owner_id"));
               
            Stage stage=(Stage) addEmployeeAdminPane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.INFORMATION;
             
            Alert alert=new Alert(type,"");
         
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Data has been added succefully");
            alert.getDialogPane().setHeaderText("Done");
            
            Optional<ButtonType> result=alert.showAndWait();
                if(result.get()==ButtonType.OK){}
              
            }
  ///////////////////////////////Employee///////////////////////////////////////////////////////////////               
        else if(authenticationSelect.getValue().equals("Employee")){           
            query="INSERT INTO Employee VALUES ('"+Name.getText()+"','"+password.getText()+"','"+contactNumber.getText()+"','"+address.getText()+"')";
          
            connect2.connectDBUpdate(query);
                
            query="SELECT Employee_id from Employee where Employee_id=(SELECT max(Employee_id) FROM Employee);";
            resultSet=connect2.connectDB(query);
            resultSet.next();
            userId.setText(resultSet.getString("Employee_id"));
            
            Stage stage=(Stage) addEmployeeAdminPane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.INFORMATION;
             
            Alert alert=new Alert(type,"");
         
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Data has been added succefully");
            alert.getDialogPane().setHeaderText("Done");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
          
        }
        }catch (SQLException ex) {
            
            Stage stage=(Stage) addEmployeeAdminPane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR; 
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Data has been added succefully");
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
            Stage stage=(Stage) addEmployeeAdminPane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("You have entered wrong confirm password");
            alert.getDialogPane().setHeaderText("Failed");
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
        }
        
        }
        else{
         Stage stage=(Stage) addEmployeeAdminPane.getScene().getWindow();
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        authenticationSelect.getItems().addAll("Select Authentication","Owner","Employee");
        authenticationSelect.setValue("Select Authentication");
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
       
        authentication1=strin;
   }
}
