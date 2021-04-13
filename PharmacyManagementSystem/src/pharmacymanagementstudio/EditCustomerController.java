
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacymanagementstudio;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.ButtonType;
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
public class EditCustomerController implements Initializable {

    @FXML
    private JFXTextField customerId;
    @FXML
    private JFXButton fetchBtn;
    @FXML
    private JFXTextField customerName;
    @FXML
    private JFXTextField contactNumber;
    @FXML
    private JFXButton backBtn;
    @FXML
    private JFXButton deleteBtn;

    /**
     * Initializes the controller class.
     */
    String authentication,query;
      ResultSet resultSet;
 ConnectDatabase connect2=new ConnectDatabase();
    @FXML
    private JFXButton resetBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private AnchorPane pane;
    @FXML
    public void backButtonClicked(ActionEvent event) throws IOException
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
 public void resetButtonClicked(ActionEvent event)
    {
      customerId.setText("");
      customerName.setText("");
      contactNumber.setText("");
    }
    public void deleteButtonClicked(ActionEvent event)
    { 
        if(!(customerId.getText().isEmpty())){ 
        
        try {   
             Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.WARNING;
             
            Alert alert=new Alert(type,"");
         
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setHeaderText("Are you sure want to delete this customer?");
            alert.getDialogPane().setContentText("Note: This will also delete all the schedules and preorders added for this customer.And This action can't be undone.");
            
           ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("YES");
             ButtonType noButton=new ButtonType("NO",ButtonBar.ButtonData.NO);
              alert.getDialogPane().getButtonTypes().add(noButton);
            
            Optional<ButtonType> result=alert.showAndWait();
                if(result.get()==ButtonType.OK){
                 query="DELETE FROM Schedule where Customer_id="+Integer.parseInt(customerId.getText().substring(1, customerId.getText().indexOf("]")));   
                 connect2.connectDBUpdate(query);   
                    
                 query="DELETE FROM PreOrder where Customer_id="+Integer.parseInt(customerId.getText().substring(1, customerId.getText().indexOf("]")));   
                 connect2.connectDBUpdate(query);   
                    
                 query="DELETE FROM Customer where Customer_id="+Integer.parseInt(customerId.getText().substring(1, customerId.getText().indexOf("]")));   
                 connect2.connectDBUpdate(query);
            
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
        }catch (Exception ex){
        
        }
      
    }
    else{
         Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Customer field must be filled");
            alert.getDialogPane().setHeaderText("Failed");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
    }
    }
    public void fetchButtonClicked(ActionEvent event)
    { 
        try {  
        if(!(customerId.getText().isEmpty())){ 
        
            query="SELECT * FROM Customer where Customer_id="+Integer.parseInt(customerId.getText().substring(1, customerId.getText().indexOf("]")));
            resultSet=connect2.connectDB(query);
            resultSet.next();
             customerName.setText(resultSet.getString("Name"));
             contactNumber.setText(resultSet.getString("ContactNumber"));
    }
    else{
         Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Customer field must be filled");
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
            //Logger.getLogger(EditScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception ex){
        
        } 
    }
 
    public void updateButtonClicked(ActionEvent event)
    {
      try{
          if(!(customerName.getText().isEmpty()||contactNumber.getText().isEmpty()||customerId.getText().isEmpty()))
          {
              try{
             query="UPDATE Customer SET Name='"+customerName.getText()+"',ContactNumber='"+contactNumber.getText()+"' where Customer_id="+Integer.parseInt(customerId.getText().substring(1, customerId.getText().indexOf("]")));
          
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
                //Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
   
                //Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);   
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
      }catch(Exception ex){}
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                ///////////////////////////////////////////////////////////////////
        ArrayList<String> nameList2=new ArrayList<String>();
        try {
            query="SELECT Customer_id,Name FROM Customer;";
            resultSet=connect2.connectDB(query);
            while(resultSet.next()){
            nameList2.add("["+resultSet.getString("Customer_id")+"] "+resultSet.getString("Name"));
            }  
                } catch (SQLException ex) {
            //Logger.getLogger(SellMedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        AutoCompletionBinding<String> autocompletebinding2 = TextFields.bindAutoCompletion(customerId,nameList2);
        autocompletebinding2.setPrefWidth(customerId.getPrefWidth());
        autocompletebinding2.setMaxWidth(customerId.getMaxWidth());
        autocompletebinding2.setVisibleRowCount(10);
  ///////////////////////////////////////////////////////////////////////////////////////////////////////

        customerName.addEventFilter(KeyEvent.KEY_TYPED , Name_Validation(50));
        contactNumber.addEventFilter(KeyEvent.KEY_TYPED , contactNumber_Validation(11));
       
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
      
    public void setAuthenticationLabel(String strin){
        authentication=strin;
   } 
}
