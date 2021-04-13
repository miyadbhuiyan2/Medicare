/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacymanagementstudio;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
public class EditScheduleController implements Initializable {

    @FXML
    private JFXTextField scheduleId;
    @FXML
    private JFXButton fetchBtn;
    @FXML
    private JFXTextField customerId;
    @FXML
    private JFXTextField medicineId;
    @FXML
    private JFXTextField quantity;
    @FXML
    private JFXDatePicker dateToCall;
    @FXML
    private JFXButton backBtn;
    @FXML
    private JFXButton deleteBtn;

    /**
     * Initializes the controller class.
     */
    String authentication,query,pendingStatus;
      ResultSet resultSet;
      ConnectDatabase connect2=new ConnectDatabase();
     Date dateWithoutTime,dateFromPicker;
     LocalDate localDate;
    @FXML
    private JFXButton resetBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXRadioButton yes;
    @FXML
    private ToggleGroup pending;
    @FXML
    private JFXRadioButton no;
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
   
//////////////////////////////////////////////////////////////////////////////////////////////////    
public void deleteButtonClicked(ActionEvent event)
    {    try {   
        if(!(scheduleId.getText().isEmpty())){ 
        try{
         query="DELETE FROM Schedule where Schedule_id="+Integer.parseInt(scheduleId.getText());
     
             Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.WARNING;
             
            Alert alert=new Alert(type,"");
         
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setHeaderText("Are you sure want to delete this schedule?");
            alert.getDialogPane().setContentText("Note: This action can't be undone");
            
           ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("YES");
             ButtonType noButton=new ButtonType("NO",ButtonBar.ButtonData.NO);
              alert.getDialogPane().getButtonTypes().add(noButton);
            
            Optional<ButtonType> result=alert.showAndWait();
                if(result.get()==ButtonType.OK){
                 connect2.connectDBUpdate(query);
            
             Stage stage2=(Stage) pane.getScene().getWindow();
            Alert.AlertType type2=Alert.AlertType.INFORMATION;
             
            Alert alert2=new Alert(type2,"");
         
            alert2.initModality(Modality.APPLICATION_MODAL);
            alert2.initOwner(stage);
            alert2.getDialogPane().setContentText("The schedule has been deleted succefully");
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
            //Logger.getLogger(EditScheduleController.class.getName()).log(Level.SEVERE, null, ex);
            
      
    }
    
        }
    else{
         Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Schedule field must be filled");
            alert.getDialogPane().setHeaderText("Failed");
            
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
    }
       
     }catch (Exception ex){
         //ex.printStackTrace();
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////    
    public void fetchButtonClicked(ActionEvent event){  
        try {
             if(!(scheduleId.getText().isEmpty())){
            query="select Medicine.MedicineName, Medicine.Medicine_id,Customer.Name,Customer.Customer_id,Schedule.Date,Schedule.Quantity,Schedule.is_Pending\n" +
            "FROM ((Schedule INNER JOIN Medicine ON Schedule.Medicine_id = Medicine.Medicine_id)\n" +
            "INNER JOIN Customer ON Customer.Customer_id = Schedule.Customer_id) where Schedule_id="+Integer.parseInt(scheduleId.getText());
             resultSet=connect2.connectDB(query);
             if(resultSet.next()){
             customerId.setText("["+resultSet.getString("Customer_id")+"] "+resultSet.getString("Name"));
             quantity.setText(resultSet.getString("Quantity"));
             medicineId.setText("["+resultSet.getString("Medicine_id")+"] "+resultSet.getString("MedicineName"));
             dateToCall.setValue(LocalDate.parse(resultSet.getString("Date")));
              
              if(resultSet.getString("is_Pending").equalsIgnoreCase("YES")){
              yes.setSelected(true);
              pendingStatus="YES";
              }
              else if(resultSet.getString("is_Pending").equalsIgnoreCase("NO")){
              no.setSelected(true);
              pendingStatus="NO";
              }
             }
             else{
             Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("This Schedule ID doesn't exist in database");
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
            alert.getDialogPane().setContentText("Note: Schedule field must be filled");
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
            //Logger.getLogger(EditScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex){
        //ex.printStackTrace();
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////    
     public void resetButtonClicked(ActionEvent event) throws ParseException
    {
      scheduleId.setText("");
      customerId.setText("");
      quantity.setText("");
      medicineId.setText("");
      dateToCall.setValue(null);
      yes.setSelected(false);
      no.setSelected(false);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////     
public void updateButtonClicked(ActionEvent event)
    {
      try{
          if(!(scheduleId.getText().isEmpty()||customerId.getText().isEmpty()||quantity.getText().isEmpty()||medicineId.getText().isEmpty()||dateToCall.getValue()==null))
          {  localDate= dateToCall.getValue();
             dateFromPicker = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            if(Integer.parseInt(quantity.getText())>0)
            {
             try{
                  query="UPDATE Schedule set Date='"+dateToCall.getValue().toString()+"',Medicine_id="+Integer.parseInt(medicineId.getText().substring(1, medicineId.getText().indexOf("]")))+",Quantity="+Integer.parseInt(quantity.getText())+",Customer_id="+Integer.parseInt(customerId.getText().substring(1, customerId.getText().indexOf("]")))+",is_Pending='"+pendingStatus+"' where Schedule_id="+Integer.parseInt(scheduleId.getText());
          
                  connect2.connectDBUpdate(query);
                  
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.INFORMATION;
             
            Alert alert=new Alert(type,"");
         
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("The Schedule has been updated succefully");
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
                else if(ex.getErrorCode()==547)
                    alert.getDialogPane().setContentText("Medicine ID or Customer ID doesn't exist in database");
                else if(ex.getErrorCode()==2627)
                    alert.getDialogPane().setContentText("The schedule for this medicine added by this customer already exists");
                else
                    alert.getDialogPane().setContentText("Error occured.Try again.");// default message
                
                Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
               // System.out.println(ex.getErrorCode());
               // Logger.getLogger(EditScheduleController.class.getName()).log(Level.SEVERE, null, ex);
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
            alert.getDialogPane().setContentText("Constraints:\n1) Quantity must be greater than zero");
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
////////////////////////////////////////////////////////////////////////////////////////////////////
     @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
             dateWithoutTime = sdf.parse(sdf.format(new Date()));
        } catch (ParseException ex) {
            //Logger.getLogger(AddScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
//////////////////////////////////////////////////////////////////////////////////////////////////
        ArrayList<String> nameList=new ArrayList<String>();
        ArrayList<String> nameList2=new ArrayList<String>();
        try {
            query="SELECT Medicine_id,MedicineName FROM Medicine;";
            resultSet=connect2.connectDB(query);
            while(resultSet.next()){
            nameList.add("["+resultSet.getString("Medicine_id")+"] "+resultSet.getString("MedicineName"));
            }
            
            query="SELECT Customer_id,Name FROM Customer;";
            resultSet=connect2.connectDB(query);
            while(resultSet.next()){
            nameList2.add("["+resultSet.getString("Customer_id")+"] "+resultSet.getString("Name"));
            }  
                } catch (SQLException ex) {
            //Logger.getLogger(SellMedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AutoCompletionBinding<String> autocompletebinding = TextFields.bindAutoCompletion(medicineId,nameList);
        autocompletebinding.setPrefWidth(medicineId.getPrefWidth());
        autocompletebinding.setMaxWidth(medicineId.getMaxWidth());
        autocompletebinding.setVisibleRowCount(10);
        
        AutoCompletionBinding<String> autocompletebinding2 = TextFields.bindAutoCompletion(customerId,nameList2);
        autocompletebinding2.setPrefWidth(customerId.getPrefWidth());
        autocompletebinding2.setMaxWidth(customerId.getMaxWidth());
        autocompletebinding2.setVisibleRowCount(10);
  ///////////////////////////////////////////////////////////////////////////////////////////////////////
        quantity.addEventFilter(KeyEvent.KEY_TYPED , Number_Validation(9));
        scheduleId.addEventFilter(KeyEvent.KEY_TYPED , Number_Validation(10));
       
    }  
/////////////////////////////////////////////////////////////////////////////////////////////
     public void yesRadioClicked(ActionEvent event)
    {
        pendingStatus="YES";
    }
     public void noRadioClicked(ActionEvent event)
    {
        pendingStatus="NO";
    }
public EventHandler<KeyEvent> Number_Validation(final Integer max_Lengh) {
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
    public void setAuthenticationLabel(String strin){
        authentication=strin;
   } 
    
}
