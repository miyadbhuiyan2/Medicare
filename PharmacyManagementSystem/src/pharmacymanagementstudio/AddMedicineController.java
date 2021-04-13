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
import javafx.beans.value.ChangeListener;
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
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AddMedicineController implements Initializable {

    @FXML
    private JFXButton backBtn;
    @FXML
    private JFXTextField medicineName;
    @FXML
    private JFXTextField supplierId;
    @FXML
    private JFXTextField unitPrice;
    @FXML
    private JFXTextField batchNo;
    @FXML
    private JFXTextField quantity;
    @FXML
    private JFXDatePicker expireDate;
    @FXML
    private Text medicineId;

    /**
     * Initializes the controller class.
     */
    String authentication,query;
      ResultSet resultSet;
       Date dateWithoutTime,dateFromPicker;
     LocalDate localDate;
 ConnectDatabase connect2=new ConnectDatabase();
    @FXML
    private JFXTextField medicineWeight;
    @FXML
    private JFXTextField shelfNo;
    @FXML
    private JFXButton resetBtn;
    @FXML
    private JFXButton addBtn;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField companyName;
    @FXML
    private JFXTextField genericName;
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
      medicineName.setText("");
      companyName.setText("");
      medicineWeight.setText("");
      unitPrice.setText("");
       batchNo.setText("");
      quantity.setText("");
      shelfNo.setText("");
       supplierId.setText("");
      medicineId.setText("");
      genericName.setText("");
      expireDate.getEditor().clear();
    }
 
    @FXML
  public void addButtonClicked(ActionEvent event)
    {
      try{
          if(!(medicineName.getText().isEmpty()||unitPrice.getText().isEmpty()||batchNo.getText().isEmpty()||quantity.getText().isEmpty()||expireDate.getValue()==null||shelfNo.getText().isEmpty()||companyName.getText().isEmpty()||supplierId.getText().isEmpty()))
          {     localDate= expireDate.getValue();
                dateFromPicker = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
              if(!(Float.parseFloat(unitPrice.getText())<=0.0||Integer.parseInt(quantity.getText())<=0||dateFromPicker.compareTo(dateWithoutTime)<=0)){
               try{
                    query="INSERT INTO Medicine(MedicineName,UnitPrice,Weight,Quantity,CompanyName,GenericName) "
                            + "VALUES ('"+medicineName.getText()+"',"+Float.parseFloat(unitPrice.getText())+",'"+medicineWeight.getText()+"',"+0+",'"+companyName.getText()+"','"+genericName.getText()+"')";
                    connect2.connectDBUpdate(query);
                    
                    query="SELECT Medicine_id from Medicine where Medicine_id=(SELECT max(Medicine_id) FROM Medicine);";
                    resultSet=connect2.connectDB(query);
                    resultSet.next();
                    medicineId.setText(resultSet.getString("Medicine_id"));

                    query="INSERT INTO Provide VALUES ("+Integer.parseInt(resultSet.getString("Medicine_id"))+",'"+batchNo.getText()+"',"+Integer.parseInt(quantity.getText())+",'"+expireDate.getValue().toString()+"','"+shelfNo.getText()+"',"+Integer.parseInt(supplierId.getText().substring(1, supplierId.getText().indexOf("]")))+")";
                    connect2.connectDBUpdate(query);
      
                    query="SELECT SUM (QUANTITY) as Total FROM PROVIDE WHERE Medicine_id="+Integer.parseInt(medicineId.getText());
                       
                    resultSet=connect2.connectDB(query);
                    resultSet.next();
                    if(resultSet.getString("Total").equals(null))
                       query="UPDATE Medicine Set Quantity="+0+" WHERE Medicine_id="+Integer.parseInt(medicineId.getText());
                    else
                        query="UPDATE Medicine Set Quantity="+resultSet.getInt("Total")+" WHERE Medicine_id="+Integer.parseInt(medicineId.getText());

                    connect2.connectDBUpdate(query);

                   Stage stage=(Stage) pane.getScene().getWindow();
                   Alert.AlertType type=Alert.AlertType.INFORMATION;

                   Alert alert=new Alert(type,"");

                   alert.initModality(Modality.APPLICATION_MODAL);
                   alert.initOwner(stage);
                   alert.getDialogPane().setContentText("New Medicine has been added succefully");
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
                     {
                          if(ex.toString().contains("pk_UMNameWeightCNameConstraint")) 
                               alert.getDialogPane().setContentText("This medicine already exists");//Unique Constraint
                           else
                               alert.getDialogPane().setContentText("This batch number of this medicine already exists");//Unique Constraint
                        }
                else if(ex.getErrorCode()==547)
                      alert.getDialogPane().setContentText("The supplier ID doesn't exist in database."
                              + "So,the batch of this medicine couldn't be added."
                              + "Please restock the batch with existing supplier ID");
                else
                    alert.getDialogPane().setContentText("Error occured.Try again.");// default message
                    //System.out.print(ex.getErrorCode());
                Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
                //Logger.getLogger(AddMedicineController.class.getName()).log(Level.SEVERE, null, ex);
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
            alert.getDialogPane().setContentText("Constraints:\n1)Unit Price and Quantity must be greater than zero\n"
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
      //ex.printStackTrace();
      }
}    
    @Override
public void initialize(URL url, ResourceBundle rb) {
    /* add Event Filter to your TextFields **************************************************/
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
             dateWithoutTime = sdf.parse(sdf.format(new Date()));
        } catch (ParseException ex) {
            //Logger.getLogger(AddScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ////////////////////////////////////////////////////////////////////////////////////////
        ArrayList<String> nameList=new ArrayList<String>();
        query="SELECT Supplier_id,SupplierName FROM Supplier;";
        try {
            resultSet=connect2.connectDB(query);
            while(resultSet.next()){
            nameList.add("["+resultSet.getString("Supplier_id")+"] "+resultSet.getString("SupplierName"));
            }    
                } catch (SQLException ex) {
            //Logger.getLogger(SellMedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AutoCompletionBinding<String> autocompletebinding = TextFields.bindAutoCompletion(supplierId,nameList);
        autocompletebinding.setPrefWidth(supplierId.getPrefWidth());
        autocompletebinding.setMaxWidth(supplierId.getMaxWidth());
        autocompletebinding.setVisibleRowCount(10);
        /////////////////////////////////////////////////////////
         medicineName.addEventFilter(KeyEvent.KEY_TYPED , medicineName_Validation(50));
         genericName.addEventFilter(KeyEvent.KEY_TYPED , genericName_Validation(50));
         medicineWeight.addEventFilter(KeyEvent.KEY_TYPED , medicineWeight_Validation(20));
         unitPrice.addEventFilter(KeyEvent.KEY_TYPED , numericFloat_Validation(10));
         batchNo.addEventFilter(KeyEvent.KEY_TYPED , batchNo_Validation(20));
         quantity.addEventFilter(KeyEvent.KEY_TYPED , integer_Validation(9));
         shelfNo.addEventFilter(KeyEvent.KEY_TYPED , length_Validation(20));
         companyName.addEventFilter(KeyEvent.KEY_TYPED , Name_Validation(50));
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
public EventHandler<KeyEvent> Name_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[A-Za-z. ]")){ 
            }
            else{
                e.consume();
            }
        }
    };    
}  
 /* Letters Validation Limit the  characters to maxLengh AND to ONLY Letters ********/
public EventHandler<KeyEvent> medicineName_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[A-Za-z ]")||e.getCharacter().matches("[0-9./-]")||e.getCharacter().matches("[()]")){ 
            }
            else{
                e.consume();
            }
        }
    };
}
/*****************************************************************************************/
public EventHandler<KeyEvent> genericName_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[A-Za-z ]")||e.getCharacter().matches("[0-9.+/-]")||e.getCharacter().matches("[()]")){ 
            }
            else{
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
/* Numeric Float Validation Limit the  characters to maxLengh AND to ONLY DigitS *******************/
public EventHandler<KeyEvent> numericFloat_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();  
             
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[0-9.]")){ 
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
public EventHandler<KeyEvent> medicineWeight_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();  
             
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[0-9.]")||e.getCharacter().matches("[A-Za-z +/]")){ 
            }else{
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
