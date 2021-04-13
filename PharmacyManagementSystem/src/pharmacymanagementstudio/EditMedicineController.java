/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacymanagementstudio;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
public class EditMedicineController implements Initializable {

    @FXML
    private JFXTextField medicineId;
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
    private JFXTextField shelfNo;
    @FXML
    private JFXButton backBtn;
    @FXML
    private JFXButton fetchBtn;

    /**
     * Initializes the controller class.
     */
    ActionEvent e;
      String authentication,query;
      ResultSet resultSet;
      ConnectDatabase connect2=new ConnectDatabase();
      Date dateWithoutTime,dateFromPicker;
     LocalDate localDate;
     boolean flag=true;
    @FXML
    private JFXTextField medicineWeight;
    @FXML
    private JFXButton resetBtn;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXComboBox<String> batchBox;
    @FXML
    private JFXTextField companyName;
    @FXML
    private JFXTextField genericName;
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
////////////////////////////////////////////update/////////////////////////////////////////////////////////     
    @FXML
    public void updateButtonClicked(ActionEvent event)
    {
      try{
          if(batchBox.getValue().equals("ALL")&&!(medicineName.getText().isEmpty()||medicineId.getText().isEmpty()||unitPrice.getText().isEmpty()||companyName.getText().isEmpty()))
          {  if(!(Float.parseFloat(unitPrice.getText())<=0.0))
            {
             try{
                  query="UPDATE Medicine set MedicineName='"+medicineName.getText()+"',UnitPrice="+Float.parseFloat(unitPrice.getText())+",Weight='"+medicineWeight.getText()+"',CompanyName='"+companyName.getText()+"',GenericName='"+genericName.getText()+"' where Medicine_id="+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")));
          
                  connect2.connectDBUpdate(query);
                  
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.INFORMATION;
             
            Alert alert=new Alert(type,"");
         
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("The medicine has been updated succefully");
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
                      alert.getDialogPane().setContentText("The supplier ID doesn't exist in database");
                else
                    alert.getDialogPane().setContentText("Error occured.Try again.");// default message
                
                Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
               // System.out.println(ex.getErrorCode());
               // Logger.getLogger(EditMedicineController.class.getName()).log(Level.SEVERE, null, ex);
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
            alert.getDialogPane().setContentText("Constraints:\nUnit Price must be greater than zero");
            alert.getDialogPane().setHeaderText("Invalid Input");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
            }
                             
          }
          else if(!(medicineId.getText().isEmpty()||unitPrice.getText().isEmpty()||companyName.getText().isEmpty()||batchNo.getText().isEmpty()||quantity.getText().isEmpty()||shelfNo.getText().isEmpty()||expireDate.getValue()==null||supplierId.getText().isEmpty()))
          {
            
             if(!(Integer.parseInt(quantity.getText())<=0||Float.parseFloat(unitPrice.getText())<=0.0))
            {
             try{
                  query="UPDATE Medicine set MedicineName='"+medicineName.getText()+"',UnitPrice="+Float.parseFloat(unitPrice.getText())+",Weight='"+medicineWeight.getText()+"',CompanyName='"+"',CompanyName='"+companyName.getText()+"',GenericName='"+genericName.getText()+"' where Medicine_id="+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")));
          
                  connect2.connectDBUpdate(query);
                  
                   query="UPDATE Provide set Batch_no='"+batchNo.getText()+"',Quantity="+Integer.parseInt(quantity.getText())+",ExpireDate='"+expireDate.getValue().toString()+"',Shelf_no='"+shelfNo.getText()+"',Supplier_id="+Integer.parseInt(supplierId.getText())+" where Medicine_id="+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")))+" AND Batch_no='"+batchBox.getValue()+"';";
          
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
                   alert.getDialogPane().setContentText("The medicine has been updated succefully");
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
                      alert.getDialogPane().setContentText("The supplier ID doesn't exist in database");
                else
                    alert.getDialogPane().setContentText("Error occured.Try again.");// default message
                
                Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
                //System.out.println(ex.getErrorCode());
                //Logger.getLogger(EditMedicineController.class.getName()).log(Level.SEVERE, null, ex);
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
            alert.getDialogPane().setContentText("Constraints:\nWeight,Unit Price and Quantity must be greater than zero");
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
            if(batchBox.getValue().equals("ALL")&&(medicineName.getText().isEmpty()||medicineId.getText().isEmpty()||medicineWeight.getText().isEmpty()||unitPrice.getText().isEmpty()||supplierId.getText().isEmpty()))
                alert.getDialogPane().setContentText("Please fill the fields:\n"
                        + "1)Medicine ID,\n"
                        + "2)Medicine Name,\n"
                        + "3)Weight,\n"
                        + "4)Unit Price,\n"
                        + "5)Supplier ID.\n");
            else
                 alert.getDialogPane().setContentText("Please fill all the fields with correct formats");
            alert.getDialogPane().setHeaderText("Failed");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
          }
      }catch(Exception ex){
      //ex.printStackTrace();
      }
}
////////////////////////////////////delete/////////////////////////////////////////////    
    @FXML
    public void deleteButtonClicked(ActionEvent event)
    {    try {   
        if(!(medicineId.getText().isEmpty())){ 
        try{
            if(batchBox.getValue().equals("ALL")){
                
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.WARNING;
             
            Alert alert=new Alert(type,"");
         
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setHeaderText("Are you sure want to delete this medicine?");
            alert.getDialogPane().setContentText("Note:It'll also delete all the Schedules and Pre-orders added for this medicine.And This action can't be undone");
            
           ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("YES");
             ButtonType noButton=new ButtonType("NO",ButtonBar.ButtonData.NO);
              alert.getDialogPane().getButtonTypes().add(noButton);
            
            Optional<ButtonType> result=alert.showAndWait();
                if(result.get()==ButtonType.OK){
                  query="DELETE s FROM Sell s INNER JOIN PROVIDE p ON p.Provide_id=s.Provide_id\n" +
                   "WHERE p.Medicine_id="+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")));
                  connect2.connectDBUpdate(query);
                  
                  query="DELETE FROM Provide WHERE Provide.Medicine_id="+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")));
                  connect2.connectDBUpdate(query);
                 
                  query="DELETE FROM Schedule where Medicine_id="+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")));
                  connect2.connectDBUpdate(query);
        
                  query="DELETE FROM PreOrder where Medicine_id="+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")));
                  connect2.connectDBUpdate(query);
                 
                  query="DELETE FROM Medicine where Medicine_id="+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")));  
                  connect2.connectDBUpdate(query);
                  
                    Stage stage2=(Stage) pane.getScene().getWindow();
                    Alert.AlertType type2=Alert.AlertType.INFORMATION;

                    Alert alert2=new Alert(type2,"");

                    alert2.initModality(Modality.APPLICATION_MODAL);
                    alert2.initOwner(stage);
                    alert2.getDialogPane().setContentText("The medicine has been deleted succefully");
                    alert2.getDialogPane().setHeaderText("Done");

                    Optional<ButtonType> result2=alert2.showAndWait();
                        if(result.get()==ButtonType.OK){}
                    }
                    else  if(result.get()==ButtonType.NO){

                      } 
            }
        else{
                Stage stage=(Stage) pane.getScene().getWindow();
                Alert.AlertType type=Alert.AlertType.WARNING;

                Alert alert=new Alert(type,"");

                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(stage);
                alert.getDialogPane().setHeaderText("Are you sure want to delete this batch of this medicine?");
                alert.getDialogPane().setContentText("Note:It'll also delete the selling record of this batch.This action can't be undone.");

               ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("YES");
                 ButtonType noButton=new ButtonType("NO",ButtonBar.ButtonData.NO);
                  alert.getDialogPane().getButtonTypes().add(noButton);

                Optional<ButtonType> result=alert.showAndWait();
                    if(result.get()==ButtonType.OK){
             query="DELETE s FROM Sell s INNER JOIN PROVIDE p ON p.Provide_id=s.Provide_id\n" +
                   "WHERE p.Batch_no='"+batchNo.getText()+"'";
            connect2.connectDBUpdate(query);
            
            query="DELETE FROM PROVIDE WHERE PROVIDE.Batch_no='"+batchNo.getText()+"'";
            connect2.connectDBUpdate(query);      

             query="SELECT SUM (QUANTITY) as Total FROM PROVIDE WHERE Medicine_id="+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")));         
             resultSet=connect2.connectDB(query);
             
             resultSet.next();
             if(resultSet.getString("Total").equals(null))
                query="UPDATE Medicine Set Quantity="+0+" WHERE Medicine_id="+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")));
             else
                 query="UPDATE Medicine Set Quantity="+resultSet.getInt("Total")+" WHERE Medicine_id="+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")));

             connect2.connectDBUpdate(query);
                  
             Stage stage2=(Stage) pane.getScene().getWindow();
            Alert.AlertType type2=Alert.AlertType.INFORMATION;
             
            Alert alert2=new Alert(type2,"");
         
            alert2.initModality(Modality.APPLICATION_MODAL);
            alert2.initOwner(stage);
            alert2.getDialogPane().setContentText("The medicine has been deleted succefully");
            alert2.getDialogPane().setHeaderText("Done");
            
            Optional<ButtonType> result2=alert2.showAndWait();
                if(result.get()==ButtonType.OK){}
            }
            else  if(result.get()==ButtonType.NO){
              
              } 
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
            //Logger.getLogger(EditMedicineController.class.getName()).log(Level.SEVERE, null, ex);  
    }
        }
    else{
         Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Note: Medicine field must be filled");
            alert.getDialogPane().setHeaderText("Failed");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
    }
       
     }catch (Exception ex){
         //ex.printStackTrace();
        }
    }    
////////////////////////////////////////choice/////////////////////////////////////////////////////////    
    @FXML
    public void choiceBoxSelected(ActionEvent event)
    {
        try {
            if(!(batchBox.getValue()==null))
            {
             if(!batchBox.getValue().equals("ALL")){
            query="Select Medicine.MedicineName,Medicine.Weight,Medicine.UnitPrice,Medicine.CompanyName,Provide.Supplier_id,Provide.Batch_no,"
                     + "Provide.Quantity,Provide.ExpireDate,Provide.Shelf_no from Medicine INNER JOIN PROVIDE ON Medicine.Medicine_id=PROVIDE.Medicine_id where Provide.Medicine_id="+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")))+" AND Batch_no='"+batchBox.getValue().toString()+"';";
            resultSet=connect2.connectDB(query);
            resultSet.next();
           batchNo.setText(batchBox.getValue());
           quantity.setText(resultSet.getString("Quantity"));
           shelfNo.setText(resultSet.getString("Shelf_no"));
           supplierId.setText(resultSet.getString("Supplier_id"));
            expireDate.setValue(LocalDate.parse(resultSet.getString("ExpireDate")));
            }
             else{
              batchNo.setText("");
           quantity.setText("");
           shelfNo.setText("");
            expireDate.setValue(null);
             }
            }
            } catch (SQLException ex) {
           // Logger.getLogger(EditMedicineController.class.getName()).log(Level.SEVERE, null, ex);
              }
              catch(Exception ex){
              
              }
        
        
}
///////////////////////////////////////////reset//////////////////////////////////////////////////////    
    @FXML
    public void resetButtonClicked(ActionEvent event) throws ParseException
    {  batchBox.getItems().clear();
      medicineId.setText("");  
      medicineWeight.setText("");
      supplierId.setText("");
      quantity.setText("");
       unitPrice.setText("");
      batchNo.setText("");
       shelfNo.setText("");
      medicineName.setText("");
       companyName.setText("");
             genericName.setText("");
       expireDate.setValue(null);
     
      flag=true;
}
////////////////////////////////////////FETCH/////////////////////////////////////////////////////////    
    @FXML
public void fetchButtonClicked(ActionEvent event)
{   
    try{    
         if(!(medicineId.getText().isEmpty())){
            batchBox.getItems().clear();
            medicineWeight.setText("");
            supplierId.setText("");
            quantity.setText("");
            unitPrice.setText("");
            batchNo.setText("");
            shelfNo.setText("");
            medicineName.setText("");
            companyName.setText("");
            genericName.setText("");
            expireDate.setValue(null);
            flag=true;
             query="Select Medicine.MedicineName,Medicine.Weight,Medicine.UnitPrice,Medicine.GenericName,Medicine.CompanyName,Provide.Supplier_id,Provide.Batch_no,"
                     + "Provide.Quantity,Provide.ExpireDate,Provide.Shelf_no from Medicine LEFT JOIN PROVIDE ON Medicine.Medicine_id=PROVIDE.Medicine_id where Medicine.Medicine_id="+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")))+";";
            resultSet=connect2.connectDB(query);
            while(resultSet.next()){
                if(flag){
                medicineName.setText(resultSet.getString("MedicineName"));
                genericName.setText(resultSet.getString("GenericName"));
                companyName.setText(resultSet.getString("CompanyName"));
                medicineWeight.setText(resultSet.getString("Weight"));
                unitPrice.setText(resultSet.getString("UnitPrice"));
                flag=false;
                batchBox.getItems().add("ALL");
                batchBox.setValue("ALL");
                }
            if(!resultSet.getString("Batch_no").isEmpty())    
                 batchBox.getItems().add(resultSet.getString("Batch_no"));
            }
        }
              else{
         Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Note: Medicine field must be filled");
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
            alert.getDialogPane().setHeaderText("Failed");
            alert.getDialogPane().setContentText("Error occured.Try again.");// default message    
                Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
           // Logger.getLogger(EditScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex){
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
        
          medicineName.addEventFilter(KeyEvent.KEY_TYPED , medicineName_Validation(50));
          genericName.addEventFilter(KeyEvent.KEY_TYPED , genericName_Validation(50));
          medicineWeight.addEventFilter(KeyEvent.KEY_TYPED , medicineWeight_Validation(20));
          unitPrice.addEventFilter(KeyEvent.KEY_TYPED , numericFloat_Validation(10));
          batchNo.addEventFilter(KeyEvent.KEY_TYPED , batchNo_Validation(20));
          quantity.addEventFilter(KeyEvent.KEY_TYPED , integer_Validation(9));
          shelfNo.addEventFilter(KeyEvent.KEY_TYPED , length_Validation(20));
          supplierId.addEventFilter(KeyEvent.KEY_TYPED , integer_Validation(10));
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
            }else{
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
            }
            else{
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
