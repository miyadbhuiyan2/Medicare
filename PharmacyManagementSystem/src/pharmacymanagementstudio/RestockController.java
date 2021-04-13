/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacymanagementstudio;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import static java.lang.Integer.MAX_VALUE;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
public class RestockController implements Initializable {


    /**
     * Initializes the controller class.
     */
   String authentication,query;
      ResultSet resultSet;
       Date dateWithoutTime,dateFromPicker;
     LocalDate localDate;
 ConnectDatabase connect2=new ConnectDatabase();
    @FXML
    private JFXTextField medicineId;
    @FXML
    private JFXTextField batchNo;
    @FXML
    private JFXTextField quantity;
    @FXML
    private JFXDatePicker expireDate;
    @FXML
    private JFXTextField shelfNo;
    @FXML
    private JFXButton backBtn;
    @FXML
    private JFXButton resetBtn;
    @FXML
    private JFXButton addBtn;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField supplierId;
    @FXML
    public void backButtonClicked(ActionEvent event) throws IOException
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
     public void resetButtonClicked(ActionEvent event)
    {
      batchNo.setText("");
      quantity.setText("");
      shelfNo.setText("");
      medicineId.setText("");
      supplierId.setText("");
      expireDate.getEditor().clear();
    }
    @FXML
    public void addButtonClicked(ActionEvent event)
    {
      try{
          if(!(medicineId.getText().isEmpty()||batchNo.getText().isEmpty()||quantity.getText().isEmpty()||expireDate.getValue()==null||shelfNo.getText().isEmpty()||supplierId.getText().isEmpty()))
          {     localDate= expireDate.getValue();
                dateFromPicker = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
              if(!(Integer.parseInt(quantity.getText())<=0||dateFromPicker.compareTo(dateWithoutTime)<=0)){
               try{
   
                query="INSERT INTO Provide VALUES ("+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")))+",'"+batchNo.getText()+"',"+Integer.parseInt(quantity.getText())+",'"+expireDate.getValue().toString()+"','"+shelfNo.getText()+"',"+Integer.parseInt(supplierId.getText().substring(1, supplierId.getText().indexOf("]")))+")";

                connect2.connectDBUpdate(query);
             
                query="SELECT SUM (QUANTITY) as Total FROM PROVIDE WHERE Medicine_id="+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")));
                       
             resultSet=connect2.connectDB(query);
             resultSet.next();
             if(resultSet.getString("Total").equals(null))
                query="UPDATE Medicine Set Quantity="+0+" WHERE Medicine_id="+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")));
             else
                 query="UPDATE Medicine Set Quantity="+resultSet.getInt("Total")+" WHERE Medicine_id="+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")));

             connect2.connectDBUpdate(query);
             
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.INFORMATION;
             
            Alert alert=new Alert(type,"");
         
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("The medicine has been restocked succefully");
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
                if(ex.getErrorCode()==2627)
                     alert.getDialogPane().setContentText("The batch number for this medicine already exists");//Duplicate kew row
                else if(ex.getErrorCode()==547)
                      alert.getDialogPane().setContentText("The medicine ID or supplier ID doesn't exist in database");
                else
                    alert.getDialogPane().setContentText("Error occured.Try again.");// default message
                    //System.out.print(ex.getErrorCode());
                Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
               // Logger.getLogger(RestockController.class.getName()).log(Level.SEVERE, null, ex);
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
            alert.getDialogPane().setContentText("Constraints:\n1) Quantity must be greater than zero\n"
                    + "2)Expire Date must be greater than today");
            alert.getDialogPane().setHeaderText("Invalid Input");
            
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
            alert.getDialogPane().setContentText("Please fill all the fields with correct formats first");
            alert.getDialogPane().setHeaderText("Failed");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
          }
      }catch(Exception ex){
     // ex.printStackTrace();
      }
    }   
     @Override
   public void initialize(URL url, ResourceBundle rb) {
    ////////////////////////////////////////////////////////////////////////////////////////
        ArrayList<String> nameList=new ArrayList<String>();
        query="SELECT Medicine_id,MedicineName FROM Medicine;";
        try {
            resultSet=connect2.connectDB(query);
            while(resultSet.next()){
            nameList.add("["+resultSet.getString("Medicine_id")+"] "+resultSet.getString("MedicineName"));
            }    
                } catch (SQLException ex) {
            //Logger.getLogger(SellMedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AutoCompletionBinding<String> autocompletebinding = TextFields.bindAutoCompletion(medicineId,nameList);
        autocompletebinding.setPrefWidth(medicineId.getPrefWidth());
        autocompletebinding.setMaxWidth(medicineId.getMaxWidth());
        autocompletebinding.setVisibleRowCount(10);
    
        ArrayList<String> nameList2=new ArrayList<String>();
        query="SELECT Supplier_id,SupplierName FROM Supplier;";
        try {
            resultSet=connect2.connectDB(query);
            while(resultSet.next()){
            nameList2.add("["+resultSet.getString("Supplier_id")+"] "+resultSet.getString("SupplierName"));
            }    
                } catch (SQLException ex) {
            //Logger.getLogger(SellMedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AutoCompletionBinding<String> autocompletebinding2 = TextFields.bindAutoCompletion(supplierId,nameList2);
        autocompletebinding2.setPrefWidth(supplierId.getPrefWidth());
        autocompletebinding2.setMaxWidth(supplierId.getMaxWidth());
        autocompletebinding2.setVisibleRowCount(10);
        /////////////////////////////////////////////////////////
    /* add Event Filter to your TextFields **************************************************/
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
             dateWithoutTime = sdf.parse(sdf.format(new Date()));
        } catch (ParseException ex) {
            //Logger.getLogger(AddScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
          batchNo.addEventFilter(KeyEvent.KEY_TYPED , batchNo_Validation(20));
          quantity.addEventFilter(KeyEvent.KEY_TYPED , integer_Validation(9));
          shelfNo.addEventFilter(KeyEvent.KEY_TYPED , length_Validation(20));
}
/* Numeric Validation Limit the  characters to maxLengh AND to ONLY DigitS *************************************/
public EventHandler<KeyEvent> integer_Validation(final Integer max_Lengh) {
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
/*****************************************************************************************/

 /* Limit the  characters to maxLength *************************************/

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
/*****************************************************************************************/
 public EventHandler<KeyEvent> batchNo_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();  
             
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[A-Za-z]")||e.getCharacter().matches("[0-9]")){ 
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
  
     public void setAuthenticationLabel(String strin){
       
         authentication=strin;
   }
}
