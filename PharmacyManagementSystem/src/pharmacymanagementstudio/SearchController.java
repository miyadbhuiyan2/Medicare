/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacymanagementstudio;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static javax.lang.model.type.TypeKind.NULL;
import org.omg.CORBA.portable.ValueFactory;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class SearchController implements Initializable {

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
    private JFXButton editRecordsBtn;
    @FXML
    private JFXButton profileBtn;
    @FXML
    private JFXButton logOutBtn;
    @FXML
    private JFXButton aboutBtn;
    @FXML
    private TextField searchBar;
    @FXML
    private JFXComboBox<String> searchFor;
    @FXML
    private JFXComboBox<String> searchBy;
    @FXML
    private Label authenticationLabel;
    /**
     * Initializes the controller class.
     */
    
    String authentication,search_by,search_for,query;
    ResultSet resultSet;
    ConnectDatabase connect2=new ConnectDatabase();
    HashMap<String, String> searchKey = new HashMap<String, String>();
     ObservableList<MedicineTable> data;
     ObservableList<BatchTable> batchdata;
     ObservableList<CustomerTable> customerdata;
     ObservableList<SupplierTable> supplierdata;
     ObservableList<OwnerTable> ownerdata;
     ObservableList<EmployeeTable> employeedata;
     ObservableList<PreOrderTable> preorderdata;
     ObservableList<ScheduleTable> scheduledata;
     
    @FXML
    private TableView<MedicineTable> medicineTable;
    @FXML
    private TableColumn<?, ?> medicineId1;
    @FXML
    private TableColumn<?, ?> supplierId1;
    @FXML
    private TableColumn<?, ?> batchNo1;
    @FXML
    private TableColumn<?, ?> quantity1;
    @FXML
    private TableColumn<?, ?> expireDate1;
    @FXML
    private TableColumn<?, ?> shelfNo1;
    @FXML
    private TableView<BatchTable> batchTable;
    @FXML
    private TableView<SupplierTable> supplierTable;
    @FXML
    private TableColumn<?, ?> supplierId2;
    @FXML
    private TableColumn<?, ?> suplierName2;
    @FXML
    private TableColumn<?, ?> contactNumber2;
    @FXML
    private TableColumn<?, ?> address2;
    @FXML
    private TableView<CustomerTable> customerTable;
    @FXML
    private TableColumn<?, ?> customerId21;
    @FXML
    private TableColumn<?, ?> customerName21;
    @FXML
    private TableColumn<?, ?> contactNumber21;
    @FXML
    private TableView<OwnerTable> ownerTable;
    @FXML
    private TableColumn<?, ?> ownerId211;
    @FXML
    private TableColumn<?, ?> name211;
    @FXML
    private TableColumn<?, ?> contactNumber211;
    @FXML
    private TableColumn<?, ?> address211;
    @FXML
    private TableView<EmployeeTable> employeeTable;
    @FXML
    private TableColumn<?, ?> employeeId2111;
    @FXML
    private TableColumn<?, ?> name2111;
    @FXML
    private TableColumn<?, ?> contactNumber2111;
    @FXML
    private TableColumn<?, ?> address2111;
    @FXML
    private TableView<PreOrderTable> preOrderTable;
    @FXML
    private TableColumn<?, ?> orderId21111;
    @FXML
    private TableColumn<?, ?> customerId21111;
    @FXML
    private TableColumn<?, ?> medicineId21111;
    @FXML
    private TableColumn<?, ?> medicineName21111;
    @FXML
    private TableColumn<?, ?> quantity21111;
    @FXML
    private TableColumn<?, ?> pending21111;
    @FXML
    private TableView<ScheduleTable> scheduleTable;
    @FXML
    private TableColumn<?, ?> scheduleId211111;
    @FXML
    private TableColumn<?, ?> customerId211111;
    @FXML
    private TableColumn<?, ?> medicineId211111;
    @FXML
    private TableColumn<?, ?> medicineName211111;
    @FXML
    private TableColumn<?, ?> quantity211111;
    @FXML
    private TableColumn<?, ?> date211111;
    @FXML
    private TableColumn<?, ?> pending211111;
    @FXML
    private TableColumn<?, ?> medicineId;
    @FXML
    private TableColumn<?, ?> medicineName;
    @FXML
    private TableColumn<?, ?> weight;
    @FXML
    private TableColumn<?, ?> unitPrice;
    @FXML
    private TableColumn<?, ?> quantity;
    @FXML
    private TableColumn<?, ?> companyName;
    @FXML
    private AnchorPane pane;
    @FXML
    private TableColumn<?, ?> genericName;
    @FXML
    private TableView<?> demoTable;
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
////////////////////////////////////////searchBychoiceBoxSelected/////////////////////////////////////////////////////////
@FXML          
public void searchBychoiceBoxSelected(ActionEvent event){
    if(!(searchBy.getValue().equalsIgnoreCase("Generic Name with Unit Price")))  
        searchBar.setPromptText("Search here");
    else
        searchBar.setPromptText("Example: Generic Name[Unit Price]");
}
////////////////////////////////////////choice/////////////////////////////////////////////////////////
@FXML          
public void choiceBoxSelected(ActionEvent event){
         
       searchBy.getItems().clear();
       search_for=searchFor.getValue();
       switch(search_for){
       
            case "Medicine":
                medicineTable.getItems().clear();
                medicineTable.setVisible(true);medicineTable.setManaged(true);
                demoTable.setVisible(false);demoTable.setManaged(false);                
                batchTable.setVisible(false);batchTable.setManaged(false);
                supplierTable.setVisible(false);supplierTable.setManaged(false);
                customerTable.setVisible(false);customerTable.setManaged(false);
                ownerTable.setVisible(false);ownerTable.setManaged(false);
                employeeTable.setVisible(false);employeeTable.setManaged(false);
                preOrderTable.setVisible(false);preOrderTable.setManaged(false);
                scheduleTable.setVisible(false);scheduleTable.setManaged(false);
                ////////////////////////////////////////////////////////////////
                medicineId.setVisible(true);
                medicineName.setVisible(true);
                genericName.setVisible(true);                
                weight.setVisible(true);
                unitPrice.setVisible(true);
                quantity.setVisible(true);
                companyName.setVisible(true);
               searchBy.getItems().addAll("Medicine ID","Medicine Name","Generic Name","Generic Name with Unit Price","Company Name");
               break;
            case "Batch":
                 batchTable.getItems().clear();
                medicineTable.setVisible(false);medicineTable.setManaged(false);
                demoTable.setVisible(false);demoTable.setManaged(false);                                
                batchTable.setVisible(true);batchTable.setManaged(true);
                supplierTable.setVisible(false);supplierTable.setManaged(false);
                customerTable.setVisible(false);customerTable.setManaged(false);
                ownerTable.setVisible(false);ownerTable.setManaged(false);
                employeeTable.setVisible(false);employeeTable.setManaged(false);
                preOrderTable.setVisible(false);preOrderTable.setManaged(false);
                scheduleTable.setVisible(false);scheduleTable.setManaged(false);
                ////////////////////////////////////////////////////////////////
               searchBy.getItems().addAll("Medicine ID","Batch No","Supplier ID");
               break;   
            case "Customer":
                 customerTable.getItems().clear();
                 medicineTable.setVisible(false);medicineTable.setManaged(false);
                demoTable.setVisible(false);demoTable.setManaged(false);                                 
                batchTable.setVisible(false);batchTable.setManaged(false);
                supplierTable.setVisible(false);supplierTable.setManaged(false);
                customerTable.setVisible(true);customerTable.setManaged(true);
                ownerTable.setVisible(false);ownerTable.setManaged(false);
                employeeTable.setVisible(false);employeeTable.setManaged(false);
                preOrderTable.setVisible(false);preOrderTable.setManaged(false);
                scheduleTable.setVisible(false);scheduleTable.setManaged(false);
                ////////////////////////////////////////////////////////////////
               searchBy.getItems().addAll("Customer ID","Customer Name","Contact Number");
               break;
            case "Supplier":
                 supplierTable.getItems().clear();
                  medicineTable.setVisible(false);medicineTable.setManaged(false);
                batchTable.setVisible(false);batchTable.setManaged(false);
                demoTable.setVisible(false);demoTable.setManaged(false);                
                supplierTable.setVisible(true);supplierTable.setManaged(true);
                customerTable.setVisible(false);customerTable.setManaged(false);
                ownerTable.setVisible(false);ownerTable.setManaged(false);
                employeeTable.setVisible(false);employeeTable.setManaged(false);
                preOrderTable.setVisible(false);preOrderTable.setManaged(false);
                scheduleTable.setVisible(false);scheduleTable.setManaged(false);
                ////////////////////////////////////////////////////////////////
               searchBy.getItems().addAll("Supplier ID","Supplier Name","Contact Number");
               break;
            case "Owner":
                 ownerTable.getItems().clear();
                  medicineTable.setVisible(false);medicineTable.setManaged(false);
                batchTable.setVisible(false);batchTable.setManaged(false);
                demoTable.setVisible(false);demoTable.setManaged(false);                
                supplierTable.setVisible(false);supplierTable.setManaged(false);
                customerTable.setVisible(false);customerTable.setManaged(false);
                ownerTable.setVisible(true);ownerTable.setManaged(true);
                employeeTable.setVisible(false);employeeTable.setManaged(false);
                preOrderTable.setVisible(false);preOrderTable.setManaged(false);
                scheduleTable.setVisible(false);scheduleTable.setManaged(false);
                ////////////////////////////////////////////////////////////////
               searchBy.getItems().addAll("Owner ID","Owner Name","Contact Number");
               break;
            case "Employee":
                 employeeTable.getItems().clear();
                  medicineTable.setVisible(false);medicineTable.setManaged(false);
                demoTable.setVisible(false);demoTable.setManaged(false);                
                batchTable.setVisible(false);batchTable.setManaged(false);
                supplierTable.setVisible(false);supplierTable.setManaged(false);
                customerTable.setVisible(false);customerTable.setManaged(false);
                ownerTable.setVisible(false);ownerTable.setManaged(false);
                employeeTable.setVisible(true);employeeTable.setManaged(true);
                preOrderTable.setVisible(false);preOrderTable.setManaged(false);
                scheduleTable.setVisible(false);scheduleTable.setManaged(false);
                ////////////////////////////////////////////////////////////////
               searchBy.getItems().addAll("Employee ID","Employee Name","Contact Number");
               break;
            case "Pre-Order":
                 preOrderTable.getItems().clear();
                 medicineTable.setVisible(false);medicineTable.setManaged(false);
                demoTable.setVisible(false);demoTable.setManaged(false);                
                batchTable.setVisible(false);batchTable.setManaged(false);
                supplierTable.setVisible(false);supplierTable.setManaged(false);
                customerTable.setVisible(false);customerTable.setManaged(false);
                ownerTable.setVisible(false);ownerTable.setManaged(false);
                employeeTable.setVisible(false);employeeTable.setManaged(false);
                preOrderTable.setVisible(true);preOrderTable.setManaged(true);
                scheduleTable.setVisible(false);scheduleTable.setManaged(false);
                ////////////////////////////////////////////////////////////////
               searchBy.getItems().addAll("Order ID","Medicine ID","Medicine Name","Customer ID");
               break;
            case "Schedule":
                 scheduleTable.getItems().clear();
                 medicineTable.setVisible(false);medicineTable.setManaged(false);
                demoTable.setVisible(false);demoTable.setManaged(false);                
                batchTable.setVisible(false);batchTable.setManaged(false);
                supplierTable.setVisible(false);supplierTable.setManaged(false);
                customerTable.setVisible(false);customerTable.setManaged(false);
                ownerTable.setVisible(false);ownerTable.setManaged(false);
                employeeTable.setVisible(false);employeeTable.setManaged(false);
                preOrderTable.setVisible(false);preOrderTable.setManaged(false);
                scheduleTable.setVisible(true);scheduleTable.setManaged(true);
                ////////////////////////////////////////////////////////////////
               searchBy.getItems().addAll("Schedule ID","Medicine ID","Medicine Name","Customer ID");
               break;        
       }       
}
////////////////////////////////////////Search/////////////////////////////////////////////////////////
    @FXML
    public void searchClicked(ActionEvent event){
  
       if(!(searchFor.getSelectionModel().isEmpty()||searchBy.getSelectionModel().isEmpty()||searchBar.getText().isEmpty())){
          search_for=searchFor.getValue();
       switch(search_for){
           
            case "Medicine":
                  medicineTable.getItems().clear();
                if(!(searchBy.getValue().equalsIgnoreCase("Generic Name with Unit Price")))  
                   query="SELECT * FROM Medicine where "+searchKey.get(searchBy.getValue())+" like '%"+searchBar.getText()+"%';";
                else
                   query="SELECT * FROM Medicine where GenericName like '%"+searchBar.getText().substring(0,searchBar.getText().indexOf("["))+"%' AND UnitPrice<="+Float.parseFloat(searchBar.getText().substring(searchBar.getText().indexOf("[")+1,searchBar.getText().indexOf("]")))+";";

                try {
                    resultSet=connect2.connectDB(query);
                    while(resultSet.next()){
                      data.add(new MedicineTable(resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("GenericName"),resultSet.getString("Weight"),resultSet.getString("UnitPrice")+"/=",resultSet.getString("Quantity"),resultSet.getString("CompanyName")));
                    }
                } catch (SQLException ex) {
                    //Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
                medicineTable.setItems(data);
                break;
                
            case "Batch":
                  batchTable.getItems().clear();
               query="SELECT  * FROM PROVIDE where "+searchKey.get(searchBy.getValue())+" like '%"+searchBar.getText()+"%';";
                try {
                    resultSet=connect2.connectDB(query);
                    while(resultSet.next()){
                      batchdata.add(new BatchTable(resultSet.getString("Medicine_id"),resultSet.getString("Supplier_id"),resultSet.getString("Batch_no"),resultSet.getString("Quantity"),resultSet.getString("ExpireDate"),resultSet.getString("Shelf_no")));
                    }
                } catch (SQLException ex) {
                    //Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
                batchTable.setItems(batchdata);
               break;   
            case "Customer":
                  customerTable.getItems().clear();
                query="Select * From Customer where "+searchKey.get(searchBy.getValue())+" like '%"+searchBar.getText()+"%';";
                 try {
                    resultSet=connect2.connectDB(query);
                    while(resultSet.next()){
                      customerdata.add(new CustomerTable(resultSet.getString("Customer_id"),resultSet.getString("Name"),resultSet.getString("ContactNumber")));
                    }
                } catch (SQLException ex) {
                   // Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
                customerTable.setItems(customerdata);
                break;
            case "Supplier":
                  supplierTable.getItems().clear();
                query="Select * From Supplier where "+searchKey.get(searchBy.getValue())+" like '%"+searchBar.getText()+"%';";
                 try {
                    resultSet=connect2.connectDB(query);
                    while(resultSet.next()){
                        supplierdata.add(new SupplierTable(resultSet.getString("Supplier_id"),resultSet.getString("SupplierName"),resultSet.getString("ContactNumber"),resultSet.getString("Address")));
                    }
                } catch (SQLException ex) {
                   // Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
                supplierTable.setItems(supplierdata);
                break;
            case "Owner":
                  ownerTable.getItems().clear();
                if(searchBy.getValue().contains("Contact Number"))
                    query="Select * From Owner where [Contact Number] like '%"+searchBar.getText()+"%';";
                else
                    query="Select * From Owner where "+searchKey.get(searchBy.getValue())+" like '%"+searchBar.getText()+"%';";
                try {
                    resultSet=connect2.connectDB(query);
                    while(resultSet.next()){
                      ownerdata.add(new OwnerTable(resultSet.getString("Owner_id"),resultSet.getString("Name"),resultSet.getString("Contact Number"),resultSet.getString("Address")));
                    }
                } catch (SQLException ex) {
                   // Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ownerTable.setItems(ownerdata);
                break;
                
            case "Employee":
                  employeeTable.getItems().clear();
                if(searchBy.getValue().contains("Contact Number"))
                    query="Select * From Employee where [Contact Number] like '%"+searchBar.getText()+"%';";
                else   
                    query="Select * From Employee where "+searchKey.get(searchBy.getValue())+" like '%"+searchBar.getText()+"%';";
                try {
                    resultSet=connect2.connectDB(query);
                    while(resultSet.next()){
                      employeedata.add(new EmployeeTable(resultSet.getString("Employee_id"),resultSet.getString("Name"),resultSet.getString("Contact Number"),resultSet.getString("Address")));
                    }
                } catch (SQLException ex) {
                   // Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
                employeeTable.setItems(employeedata);
               break;
               
            case "Pre-Order":
                  preOrderTable.getItems().clear();
                if(searchBy.getValue().equalsIgnoreCase("Medicine Name"))
                    query="SELECT  PreOrder.Medicine_id,Medicine.MedicineName,PreOrder.Order_id,PreOrder.Customer_id,PreOrder.Quantity,PreOrder.is_Pending\n" +
                    "FROM Medicine JOIN PreOrder ON Medicine.Medicine_id=PreOrder.Medicine_id where Medicine."+searchKey.get(searchBy.getValue())+" like '%"+searchBar.getText()+"%';";
                else
                    query="SELECT  PreOrder.Medicine_id,Medicine.MedicineName,PreOrder.Order_id,PreOrder.Customer_id,PreOrder.Quantity,PreOrder.is_Pending\n" +
                    "FROM Medicine JOIN PreOrder ON Medicine.Medicine_id=PreOrder.Medicine_id where PreOrder."+searchKey.get(searchBy.getValue())+" like '%"+searchBar.getText()+"%';";
                 try {
                    resultSet=connect2.connectDB(query);
                    while(resultSet.next()){
                      preorderdata.add(new PreOrderTable(resultSet.getString("Order_id"),resultSet.getString("Customer_id"),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Quantity"),resultSet.getString("is_Pending")));
                    }
                } catch (SQLException ex) {
                   // Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
                preOrderTable.setItems(preorderdata);
                break;
            case "Schedule":
                  scheduleTable.getItems().clear();
                if(searchBy.getValue().equalsIgnoreCase("Medicine Name"))
                    query="SELECT  SCHEDULE.Medicine_id,Medicine.MedicineName,SCHEDULE.Schedule_id,SCHEDULE.Customer_id,SCHEDULE.Quantity,SCHEDULE.Date,SCHEDULE.is_Pending\n" +
                    "FROM Medicine JOIN SCHEDULE ON Medicine.Medicine_id=SCHEDULE.Medicine_id where Medicine."+searchKey.get(searchBy.getValue())+" like '%"+searchBar.getText()+"%';";
                else
                    query="SELECT  SCHEDULE.Medicine_id,Medicine.MedicineName,SCHEDULE.Schedule_id,SCHEDULE.Customer_id,SCHEDULE.Quantity,SCHEDULE.Date,SCHEDULE.is_Pending\n" +
                    "FROM Medicine JOIN SCHEDULE ON Medicine.Medicine_id=SCHEDULE.Medicine_id where Schedule."+searchKey.get(searchBy.getValue())+" like '%"+searchBar.getText()+"%';";
                 try {
                    resultSet=connect2.connectDB(query);
                    while(resultSet.next()){
                      scheduledata.add(new ScheduleTable(resultSet.getString("Schedule_id"),resultSet.getString("Customer_id"),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Quantity"),resultSet.getString("Date"),resultSet.getString("is_Pending")));
                    }
                } catch (SQLException ex) {
                    //Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
                scheduleTable.setItems(scheduledata);
                break;
       }
       }
       else{
                   try{
                   Stage stage=(Stage) pane.getScene().getWindow();
                   Alert.AlertType type=Alert.AlertType.ERROR;
                   Alert alert=new Alert(type,"");
                   alert.initModality(Modality.APPLICATION_MODAL);
                   alert.initOwner(stage);
                   alert.getDialogPane().setContentText("Please fill all the fields first.");
                   alert.getDialogPane().setHeaderText("Failed");

                   Optional<ButtonType> result=alert.showAndWait();
                   if(result.get()==ButtonType.OK){}
                   }catch(Exception e){}
       }
}       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchFor.getItems().addAll("Medicine","Batch","Supplier","Customer","Owner","Employee","Pre-Order","Schedule");
       
        searchKey.put("Medicine ID", "Medicine_id");
        searchKey.put("Medicine Name", "MedicineName");
        searchKey.put("Generic Name", "GenericName");
        searchKey.put("Company Name", "CompanyName");
        searchKey.put("Batch No", "Batch_no");
        searchKey.put("Supplier ID", "Supplier_id");
        searchKey.put("Customer ID", "Customer_id");
        searchKey.put("Customer Name", "Name");
        searchKey.put("Contact Number", "ContactNumber");
        searchKey.put("Supplier Name", "SupplierName");
        searchKey.put("Owner ID", "Owner_id");
        searchKey.put("Owner Name", "Name");
        searchKey.put("Employee ID", "Employee_id");
        searchKey.put("Employee Name", "Name");
        searchKey.put("Order ID", "Order_id");
        searchKey.put("Schedule ID", "Schedule_id");
        
        demoTable.setVisible(true);demoTable.setManaged(true);
        medicineTable.setVisible(false);medicineTable.setManaged(false);
        batchTable.setVisible(false);batchTable.setManaged(false);
        supplierTable.setVisible(false);supplierTable.setManaged(false);
        customerTable.setVisible(false);customerTable.setManaged(false);
        ownerTable.setVisible(false);ownerTable.setManaged(false);
        employeeTable.setVisible(false);employeeTable.setManaged(false);
        preOrderTable.setVisible(false);preOrderTable.setManaged(false);
        scheduleTable.setVisible(false);scheduleTable.setManaged(false);
 
        data=FXCollections.observableArrayList();
        batchdata=FXCollections.observableArrayList();
        supplierdata=FXCollections.observableArrayList();
        customerdata=FXCollections.observableArrayList();
        ownerdata=FXCollections.observableArrayList();
        employeedata=FXCollections.observableArrayList();
        preorderdata=FXCollections.observableArrayList();
        scheduledata=FXCollections.observableArrayList();
        setCellTable();
    }  
    private void setCellTable(){
    medicineId.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
    medicineName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
    genericName.setCellValueFactory(new PropertyValueFactory<>("genericName"));
    weight.setCellValueFactory(new PropertyValueFactory<>("medicineWeight"));
    unitPrice.setCellValueFactory(new PropertyValueFactory<>("medicineUnitPrice"));
    quantity.setCellValueFactory(new PropertyValueFactory<>("medicineQuantty"));
    companyName.setCellValueFactory(new PropertyValueFactory<>("medicineCompany"));
    
    medicineId1.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
    supplierId1.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
    batchNo1.setCellValueFactory(new PropertyValueFactory<>("batchNo"));
    quantity1.setCellValueFactory(new PropertyValueFactory<>("batchQuantity"));
    expireDate1.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
    shelfNo1.setCellValueFactory(new PropertyValueFactory<>("shelfNo"));
    
    supplierId2.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
    suplierName2.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
    contactNumber2.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
    address2.setCellValueFactory(new PropertyValueFactory<>("address"));
    
    customerId21.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    customerName21.setCellValueFactory(new PropertyValueFactory<>("customerName"));
    contactNumber21.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
    
    ownerId211.setCellValueFactory(new PropertyValueFactory<>("ownerId"));
    name211.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
    contactNumber211.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
    address211.setCellValueFactory(new PropertyValueFactory<>("address"));
    
    employeeId2111.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
    name2111.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
    contactNumber2111.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
    address2111.setCellValueFactory(new PropertyValueFactory<>("address"));
    
    orderId21111.setCellValueFactory(new PropertyValueFactory<>("orderId"));
    customerId21111.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    medicineId21111.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
    medicineName21111.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
    quantity21111.setCellValueFactory(new PropertyValueFactory<>("orderQuantity"));
    pending21111.setCellValueFactory(new PropertyValueFactory<>("orderPending"));
    
    scheduleId211111.setCellValueFactory(new PropertyValueFactory<>("scheduleId"));
    customerId211111.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    medicineId211111.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
    medicineName211111.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
    quantity211111.setCellValueFactory(new PropertyValueFactory<>("scheduleQuantity"));    
    date211111.setCellValueFactory(new PropertyValueFactory<>("scheduleDate"));
    pending211111.setCellValueFactory(new PropertyValueFactory<>("schedulePending"));
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
    public class MedicineTable{
    private String medicineId;
    private String medicineName;
    private String genericName;
    private String medicineWeight;
    private String medicineUnitPrice;
    private String medicineQuantty;
    private String medicineCompany;
    
    public MedicineTable(String medicineId,String medicineName,String genericName,String medicineWeight,String medicineUnitPrice,String  medicineQuantity,String medicineCompany){
    this.medicineId=medicineId;
    this.medicineName=medicineName;
    this.genericName=genericName;
    this.medicineWeight=medicineWeight;
    this.medicineUnitPrice=medicineUnitPrice;
    this.medicineQuantty=medicineQuantity;
    this.medicineCompany=medicineCompany;        
    }
        /**
         * @return the medicineId
         */
        public String getMedicineId() {
            return medicineId;
        }

        /**
         * @param medicineId the medicineId to set
         */
        public void setMedicineId(String medicineId) {
            this.medicineId = medicineId;
        }

        /**
         * @return the medicineName
         */
        public String getMedicineName() {
            return medicineName;
        }

        /**
         * @param medicineName the medicineName to set
         */
        public void setMedicineName(String medicineName) {
            this.medicineName = medicineName;
        }

        /**
         * @return the medicineWeight
         */
        public String getMedicineWeight() {
            return medicineWeight;
        }

        /**
         * @param medicineWeight the medicineWeight to set
         */
        public void setMedicineWeight(String medicineWeight) {
            this.medicineWeight = medicineWeight;
        }

        /**
         * @return the medicineUnitPrice
         */
        public String getMedicineUnitPrice() {
            return medicineUnitPrice;
        }

        /**
         * @param medicineUnitPrice the medicineUnitPrice to set
         */
        public void setMedicineUnitPrice(String medicineUnitPrice) {
            this.medicineUnitPrice = medicineUnitPrice;
        }

        /**
         * @return the medicineQuantty
         */
        public String getMedicineQuantty() {
            return medicineQuantty;
        }

        /**
         * @param medicineQuantty the medicineQuantty to set
         */
        public void setMedicineQuantty(String medicineQuantty) {
            this.medicineQuantty = medicineQuantty;
        }

        /**
         * @return the medicineCompany
         */
        public String getMedicineCompany() {
            return medicineCompany;
        }

        /**
         * @param medicineCompany the medicineCompany to set
         */
        public void setMedicineCompany(String medicineCompany) {
            this.medicineCompany = medicineCompany;
        }
     
        /**
         * @return the genericName
         */
        public String getGenericName() {
            return genericName;
        }

        /**
         * @param genericName the genericName to set
         */
        public void setGenericName(String genericName) {
            this.genericName = genericName;
        }
    }     
public class BatchTable{
    private String medicineId;
    private String supplierId;
    private String batchNo;
    private String batchQuantity;
    private String expireDate;
    private String shelfNo;
   
    public BatchTable(String medicineId,String supplierId,String batchNo,String batchQuantity,String  expireDate,String shelfNo){
    this.medicineId=medicineId;
    this.supplierId=supplierId;
    this.batchNo=batchNo;
    this.batchQuantity=batchQuantity;
    this.expireDate=expireDate;
    this.shelfNo=shelfNo;        
    }

        /**
         * @return the medicineId
         */
        public String getMedicineId() {
            return medicineId;
        }

        /**
         * @param medicineId the medicineId to set
         */
        public void setMedicineId(String medicineId) {
            this.medicineId = medicineId;
        }

        /**
         * @return the supplierId
         */
        public String getSupplierId() {
            return supplierId;
        }

        /**
         * @param supplierId the supplierId to set
         */
        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

        /**
         * @return the batchNo
         */
        public String getBatchNo() {
            return batchNo;
        }

        /**
         * @param batchNo the batchNo to set
         */
        public void setBatchNo(String batchNo) {
            this.batchNo = batchNo;
        }

        /**
         * @return the batchQuantity
         */
        public String getBatchQuantity() {
            return batchQuantity;
        }

        /**
         * @param batchQuantity the batchQuantity to set
         */
        public void setBatchQuantity(String batchQuantity) {
            this.batchQuantity = batchQuantity;
        }

        /**
         * @return the expireDate
         */
        public String getExpireDate() {
            return expireDate;
        }

        /**
         * @param expireDate the expireDate to set
         */
        public void setExpireDate(String expireDate) {
            this.expireDate = expireDate;
        }

        /**
         * @return the shelfNo
         */
        public String getShelfNo() {
            return shelfNo;
        }

        /**
         * @param shelfNo the shelfNo to set
         */
        public void setShelfNo(String shelfNo) {
            this.shelfNo = shelfNo;
        }
    }
public class CustomerTable{
    private String customerId;
    private String customerName;
    private String contactNumber;
  
    
    public CustomerTable(String customerId,String customerName,String contactNumber){
    this.customerId=customerId;
    this.customerName=customerName;
    this.contactNumber=contactNumber;     
    }
    
        /**
         * @return the customerId
         */
        public String getCustomerId() {
            return customerId;
        }

        /**
         * @param customerId the customerId to set
         */
        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        /**
         * @return the customerName
         */
        public String getCustomerName() {
            return customerName;
        }

        /**
         * @param customerName the customerName to set
         */
        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        /**
         * @return the contactNumber
         */
        public String getContactNumber() {
            return contactNumber;
        }

        /**
         * @param contactNumber the contactNumber to set
         */
        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }
    }    
public class SupplierTable{
    private String supplierId;
    private String supplierName;
    private String contactNumber;
    private String address;
    
    public SupplierTable(String supplierId,String supplierName,String contactNumber,String address){
    this.supplierId=supplierId;
    this.supplierName=supplierName;
    this.contactNumber=contactNumber;
    this.address=address;     
    }

        /**
         * @return the supplierId
         */
        public String getSupplierId() {
            return supplierId;
        }

        /**
         * @param supplierId the supplierId to set
         */
        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

        /**
         * @return the supplierName
         */
        public String getSupplierName() {
            return supplierName;
        }

        /**
         * @param supplierName the supplierName to set
         */
        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        /**
         * @return the contactNumber
         */
        public String getContactNumber() {
            return contactNumber;
        }

        /**
         * @param contactNumber the contactNumber to set
         */
        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        /**
         * @return the address
         */
        public String getAddress() {
            return address;
        }

        /**
         * @param address the address to set
         */
        public void setAddress(String address) {
            this.address = address;
        }
    }
public class OwnerTable{
    private String ownerId;
    private String ownerName;
    private String contactNumber;
    private String address;
    
    public OwnerTable(String ownerId,String ownerName,String contactNumber,String address){
    this.ownerId=ownerId;
    this.ownerName=ownerName;
    this.contactNumber=contactNumber;
    this.address=address;
    }

        /**
         * @return the ownerId
         */
        public String getOwnerId() {
            return ownerId;
        }

        /**
         * @param ownerId the ownerId to set
         */
        public void setOwnerId(String ownerId) {
            this.ownerId = ownerId;
        }

        /**
         * @return the ownerName
         */
        public String getOwnerName() {
            return ownerName;
        }

        /**
         * @param ownerName the ownerName to set
         */
        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        /**
         * @return the contactNumber
         */
        public String getContactNumber() {
            return contactNumber;
        }

        /**
         * @param contactNumber the contactNumber to set
         */
        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        /**
         * @return the address
         */
        public String getAddress() {
            return address;
        }

        /**
         * @param address the address to set
         */
        public void setAddress(String address) {
            this.address = address;
        }
    }
public class EmployeeTable{
    private String employeeId;
    private String employeeName;
    private String contactNumber;
    private String address;
    
    public EmployeeTable(String employeeId,String employeeName,String contactNumber,String address){
    this.employeeId=employeeId;
    this.employeeName=employeeName;
    this.contactNumber=contactNumber;
    this.address=address;
    }

        /**
         * @return the employeeId
         */
        public String getEmployeeId() {
            return employeeId;
        }

        /**
         * @param employeeId the employeeId to set
         */
        public void setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
        }

        /**
         * @return the employeeName
         */
        public String getEmployeeName() {
            return employeeName;
        }

        /**
         * @param employeeName the employeeName to set
         */
        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        /**
         * @return the contactNumber
         */
        public String getContactNumber() {
            return contactNumber;
        }

        /**
         * @param contactNumber the contactNumber to set
         */
        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        /**
         * @return the address
         */
        public String getAddress() {
            return address;
        }

        /**
         * @param address the address to set
         */
        public void setAddress(String address) {
            this.address = address;
        }
    }
public class PreOrderTable{
    private String orderId;
    private String customerId;
    private String medicineId;
    private String medicineName;
    private String orderQuantity;
    private String orderPending;
    
    public PreOrderTable(String orderId,String customerId,String medicineId,String medicineName,String  orderQuantity,String orderPending){
    this.orderId=orderId;
    this.customerId=customerId;
    this.medicineId=medicineId;
    this.medicineName=medicineName;
    this.orderQuantity=orderQuantity;
    this.orderPending=orderPending;        
    }

        /**
         * @return the orderId
         */
        public String getOrderId() {
            return orderId;
        }

        /**
         * @param orderId the orderId to set
         */
        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        /**
         * @return the customerId
         */
        public String getCustomerId() {
            return customerId;
        }

        /**
         * @param customerId the customerId to set
         */
        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        /**
         * @return the medicineId
         */
        public String getMedicineId() {
            return medicineId;
        }

        /**
         * @param medicineId the medicineId to set
         */
        public void setMedicineId(String medicineId) {
            this.medicineId = medicineId;
        }

        /**
         * @return the medicineName
         */
        public String getMedicineName() {
            return medicineName;
        }

        /**
         * @param medicineName the medicineName to set
         */
        public void setMedicineName(String medicineName) {
            this.medicineName = medicineName;
        }

        /**
         * @return the orderQuantity
         */
        public String getOrderQuantity() {
            return orderQuantity;
        }

        /**
         * @param orderQuantity the orderQuantity to set
         */
        public void setOrderQuantity(String orderQuantity) {
            this.orderQuantity = orderQuantity;
        }

        /**
         * @return the orderPending
         */
        public String getOrderPending() {
            return orderPending;
        }

        /**
         * @param orderPending the orderPending to set
         */
        public void setOrderPending(String orderPending) {
            this.orderPending = orderPending;
        }
    }
public class ScheduleTable{
    private String scheduleId;
    private String customerId;
    private String medicineId;
    private String medicineName;
    private String scheduleQuantity;
    private String scheduleDate;
    private String schedulePending;
  
     
    public ScheduleTable(String scheduleId,String customerId,String medicineId,String  medicineName,String scheduleQuantity,String scheduleDate,String schedulePending){
    this.scheduleId=scheduleId;
    this.customerId=customerId;
    this.medicineId=medicineId;
    this.medicineName=medicineName;
    this.scheduleQuantity=scheduleQuantity;
    this.scheduleDate=scheduleDate;
    this.schedulePending=schedulePending;
    
    }

        /**
         * @return the scheduleId
         */
        public String getScheduleId() {
            return scheduleId;
        }

        /**
         * @param scheduleId the scheduleId to set
         */
        public void setScheduleId(String scheduleId) {
            this.scheduleId = scheduleId;
        }

        /**
         * @return the customerId
         */
        public String getCustomerId() {
            return customerId;
        }

        /**
         * @param customerId the customerId to set
         */
        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        /**
         * @return the medicineId
         */
        public String getMedicineId() {
            return medicineId;
        }

        /**
         * @param medicineId the medicineId to set
         */
        public void setMedicineId(String medicineId) {
            this.medicineId = medicineId;
        }

        /**
         * @return the medicineName
         */
        public String getMedicineName() {
            return medicineName;
        }

        /**
         * @param medicineName the medicineName to set
         */
        public void setMedicineName(String medicineName) {
            this.medicineName = medicineName;
        }

        /**
         * @return the scheduleQuantity
         */
        public String getScheduleQuantity() {
            return scheduleQuantity;
        }

        /**
         * @param scheduleQuantity the scheduleQuantity to set
         */
        public void setScheduleQuantity(String scheduleQuantity) {
            this.scheduleQuantity = scheduleQuantity;
        }

        /**
         * @return the scheduleDate
         */
        public String getScheduleDate() {
            return scheduleDate;
        }

        /**
         * @param scheduleDate the scheduleDate to set
         */
        public void setScheduleDate(String scheduleDate) {
            this.scheduleDate = scheduleDate;
        }

        /**
         * @return the schedulePending
         */
        public String getSchedulePending() {
            return schedulePending;
        }

        /**
         * @param schedulePending the schedulePending to set
         */
        public void setSchedulePending(String schedulePending) {
            this.schedulePending = schedulePending;
        }
    }
}
