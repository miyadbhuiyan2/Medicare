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
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
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

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class HistoryController implements Initializable {

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
    private JFXButton logOutBtn;
    @FXML
    private JFXButton aboutBtn;
    @FXML
    private JFXButton clearHistoryBtn;
    @FXML
    private Label authenticationLabel;

    /**
     * Initializes the controller class.
     */
     String authentication,search_by,query;
     ResultSet resultSet;
     boolean flag,cartFlag;
      ConnectDatabase connect2=new ConnectDatabase();
      ObservableList<HistoryTable> historydata;
      HashMap<String, String> searchKey = new HashMap<String, String>();
    @FXML
    private TableColumn<?, ?> timeColumn;
    @FXML
    private TableColumn<?, ?> medicineIdColumn;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> batchColumn;
    @FXML
    private TableColumn<?, ?> quantityColumn;
    @FXML
    private TableColumn<?, ?> sellerIdColumn;
    @FXML
    private TableView<HistoryTable> history;
    @FXML
    private AnchorPane pane;
    @FXML
    private TextField searchBar;
    @FXML
    private JFXComboBox<String> searchBy;
    @FXML
    private JFXButton searchBtn1;
    @FXML
    private TableColumn<?, ?> totalCostColumn;
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
   @FXML
    public void clearButtonClicked(ActionEvent event)
    {
        try {
            if(authentication.contains("OWNER"))
            {    
            query="TRUNCATE TABLE Sell";
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.WARNING;
             
            Alert alert=new Alert(type,"");
         
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setHeaderText("Are you sure want to delete the history?");
            alert.getDialogPane().setContentText("Note: This action can't be undone");
            
           ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("YES");
             ButtonType noButton=new ButtonType("NO",ButtonBar.ButtonData.NO);
              alert.getDialogPane().getButtonTypes().add(noButton);
            
            Optional<ButtonType> result=alert.showAndWait();
                if(result.get()==ButtonType.OK){
                    connect2.connectDBUpdate(query);
                    history.getItems().clear();
                    Stage stage2=(Stage) pane.getScene().getWindow();
                    Alert.AlertType type2=Alert.AlertType.INFORMATION;

                    Alert alert2=new Alert(type2,"");

                    alert2.initModality(Modality.APPLICATION_MODAL);
                    alert2.initOwner(stage);
                    alert2.getDialogPane().setContentText("The History has been deleted succefully");
                    alert2.getDialogPane().setHeaderText("Done");

                    Optional<ButtonType> result2=alert2.showAndWait();
                        if(result.get()==ButtonType.OK){}
            }
            else  if(result.get()==ButtonType.NO){
              
              } 
        }
        else{
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("Only Owner can clear history");
            alert.getDialogPane().setHeaderText("Access Denied");
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
            }    
        } catch (SQLException ex) {
           // Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
           // Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch (Exception ex) {
           // Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    @FXML
public void clearFilterClicked(ActionEvent event){
        searchBar.setText("");
        searchBy.getSelectionModel().clearSelection();
        history.getItems().clear();
        historydata=FXCollections.observableArrayList();
        query="select Sell.Time,Sell.TotalCost,Provide.Medicine_id,Medicine.MedicineName,Provide.Batch_no,Sell.Quantity,\n" +
              "Sell.Employee_id,Sell.Owner_id from Provide inner join Medicine on Provide.Medicine_id = Medicine.Medicine_id inner join Sell on\n" +
              "Provide.Provide_id =Sell.Provide_id order by Sell.Time desc;"; 
        try {
                resultSet=connect2.connectDB(query);
                while(resultSet.next()){
                   if(!(resultSet.getString("Employee_id")==null))
                        historydata.add(new HistoryTable(resultSet.getString("Time").replace(' ','\n'),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Batch_no"),resultSet.getString("Quantity"),resultSet.getString("TotalCost"),resultSet.getString("Employee_id")+"[EMPLOYEE]"));
                    else
                        historydata.add(new HistoryTable(resultSet.getString("Time").replace(' ','\n'),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Batch_no"),resultSet.getString("Quantity"),resultSet.getString("TotalCost"),resultSet.getString("Owner_id")+"[OWNER]"));
                }    
            } catch (SQLException ex) {
                  //Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        history.setItems(historydata);
}
    ////////////////////////////////////////Search/////////////////////////////////////////////////////////
    @FXML
    public void searchClicked(ActionEvent event){
    if(!(searchBy.getSelectionModel().isEmpty()||searchBar.getText().isEmpty())){
          String search_for=searchBy.getValue();
       switch(search_for){
           
            case "Time":
                history.getItems().clear();
                query="select Sell.Time,Sell.TotalCost,Provide.Medicine_id,Medicine.MedicineName,Provide.Batch_no,Sell.Quantity,\n" +
                "Sell.Employee_id,Sell.Owner_id from Provide inner join Medicine on Provide.Medicine_id = Medicine.Medicine_id inner join Sell on\n" +
                "Provide.Provide_id =Sell.Provide_id where CONVERT(VARCHAR, Sell.Time, 21) LIKE '%"+searchBar.getText()+"%';";
                try {
                    resultSet=connect2.connectDB(query);
                    while(resultSet.next()){
                    if(!(resultSet.getString("Employee_id")==null))
                        historydata.add(new HistoryTable(resultSet.getString("Time").replace(' ','\n'),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Batch_no"),resultSet.getString("Quantity"),resultSet.getString("TotalCost"),resultSet.getString("Employee_id")+"[EMPLOYEE]"));
                    else
                        historydata.add(new HistoryTable(resultSet.getString("Time").replace(' ','\n'),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Batch_no"),resultSet.getString("Quantity"),resultSet.getString("TotalCost"),resultSet.getString("Owner_id")+"[OWNER]"));
                    }
                }
                 catch (SQLException ex) {
                   // Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
                history.setItems(historydata);
                break;
                
            case "Medicine ID":
                  history.getItems().clear();
                query="select Sell.Time,Sell.TotalCost,Provide.Medicine_id,Medicine.MedicineName,Provide.Batch_no,Sell.Quantity,\n" +
                "Sell.Employee_id,Sell.Owner_id from Provide inner join Medicine on Provide.Medicine_id = Medicine.Medicine_id inner join Sell on\n" +
                "Provide.Provide_id =Sell.Provide_id where Provide.Medicine_id like '%"+searchBar.getText()+"%';";
                try {
                    resultSet=connect2.connectDB(query);
                    while(resultSet.next()){
                    if(!(resultSet.getString("Employee_id")==null))
                        historydata.add(new HistoryTable(resultSet.getString("Time").replace(' ','\n'),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Batch_no"),resultSet.getString("Quantity"),resultSet.getString("TotalCost"),resultSet.getString("Employee_id")+"[EMPLOYEE]"));
                    else
                        historydata.add(new HistoryTable(resultSet.getString("Time").replace(' ','\n'),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Batch_no"),resultSet.getString("Quantity"),resultSet.getString("TotalCost"),resultSet.getString("Owner_id")+"[OWNER]"));
                    }
                }
                 catch (SQLException ex) {
                   // Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
                history.setItems(historydata);
               break;   
            case "Medicine Name":
                  history.getItems().clear();
                  query="select Sell.Time,Sell.TotalCost,Provide.Medicine_id,Medicine.MedicineName,Provide.Batch_no,Sell.Quantity,\n" +
                  "Sell.Employee_id,Sell.Owner_id from Provide inner join Medicine on Provide.Medicine_id = Medicine.Medicine_id inner join Sell on\n" +
                  "Provide.Provide_id =Sell.Provide_id where Medicine.MedicineName like '%"+searchBar.getText()+"%';";                
                  try {
                    resultSet=connect2.connectDB(query);
                    while(resultSet.next()){
                    if(!(resultSet.getString("Employee_id")==null))
                        historydata.add(new HistoryTable(resultSet.getString("Time").replace(' ','\n'),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Batch_no"),resultSet.getString("Quantity"),resultSet.getString("TotalCost"),resultSet.getString("Employee_id")+"[EMPLOYEE]"));
                    else
                        historydata.add(new HistoryTable(resultSet.getString("Time").replace(' ','\n'),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Batch_no"),resultSet.getString("Quantity"),resultSet.getString("TotalCost"),resultSet.getString("Owner_id")+"[OWNER]"));
                    }
                }
                 catch (SQLException ex) {
                   // Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
                history.setItems(historydata);
                break;
            case "Batch No":
                  history.getItems().clear();
                query="select Sell.Time,Sell.TotalCost,Provide.Medicine_id,Medicine.MedicineName,Provide.Batch_no,Sell.Quantity,\n" +
                  "Sell.Employee_id,Sell.Owner_id from Provide inner join Medicine on Provide.Medicine_id = Medicine.Medicine_id inner join Sell on\n" +
                  "Provide.Provide_id =Sell.Provide_id where Provide.Batch_no like '%"+searchBar.getText()+"%';";
                try {
                    resultSet=connect2.connectDB(query);
                    while(resultSet.next()){
                    if(!(resultSet.getString("Employee_id")==null))
                        historydata.add(new HistoryTable(resultSet.getString("Time").replace(' ','\n'),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Batch_no"),resultSet.getString("Quantity"),resultSet.getString("TotalCost"),resultSet.getString("Employee_id")+"[EMPLOYEE]"));
                    else
                        historydata.add(new HistoryTable(resultSet.getString("Time").replace(' ','\n'),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Batch_no"),resultSet.getString("Quantity"),resultSet.getString("TotalCost"),resultSet.getString("Owner_id")+"[OWNER]"));
                    }
                }
                 catch (SQLException ex) {
                   // Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
                history.setItems(historydata);
                break;
            case "Owner ID":
                  history.getItems().clear();
                query="select Sell.Time,Sell.TotalCost,Provide.Medicine_id,Medicine.MedicineName,Provide.Batch_no,Sell.Quantity,\n" +
                  "Sell.Employee_id,Sell.Owner_id from Provide inner join Medicine on Provide.Medicine_id = Medicine.Medicine_id inner join Sell on\n" +
                  "Provide.Provide_id =Sell.Provide_id where Sell.Owner_id like '%"+searchBar.getText()+"%';";                 
                try {
                    resultSet=connect2.connectDB(query);
                    while(resultSet.next()){
                    if(!(resultSet.getString("Employee_id")==null))
                        historydata.add(new HistoryTable(resultSet.getString("Time").replace(' ','\n'),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Batch_no"),resultSet.getString("Quantity"),resultSet.getString("TotalCost"),resultSet.getString("Employee_id")+"[EMPLOYEE]"));
                    else
                        historydata.add(new HistoryTable(resultSet.getString("Time").replace(' ','\n'),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Batch_no"),resultSet.getString("Quantity"),resultSet.getString("TotalCost"),resultSet.getString("Owner_id")+"[OWNER]"));
                    }
                }
                 catch (SQLException ex) {
                   // Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
                history.setItems(historydata);
                break;
                
            case "Employee ID":
                history.getItems().clear();
                query="select Sell.Time,Sell.TotalCost,Provide.Medicine_id,Medicine.MedicineName,Provide.Batch_no,Sell.Quantity,\n" +
                  "Sell.Employee_id,Sell.Owner_id from Provide inner join Medicine on Provide.Medicine_id = Medicine.Medicine_id inner join Sell on\n" +
                  "Provide.Provide_id =Sell.Provide_id where Sell.Employee_id like '%"+searchBar.getText()+"%';";                 
                try {
                    resultSet=connect2.connectDB(query);
                    while(resultSet.next()){
                    if(!(resultSet.getString("Employee_id")==null))
                        historydata.add(new HistoryTable(resultSet.getString("Time").replace(' ','\n'),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Batch_no"),resultSet.getString("Quantity"),resultSet.getString("TotalCost"),resultSet.getString("Employee_id")+"[EMPLOYEE]"));
                    else
                        historydata.add(new HistoryTable(resultSet.getString("Time").replace(' ','\n'),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Batch_no"),resultSet.getString("Quantity"),resultSet.getString("TotalCost"),resultSet.getString("Owner_id")+"[OWNER]"));
                    }
                }
                 catch (SQLException ex) {
                    //Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
                history.setItems(historydata);
               break;
    }
  }
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        searchBy.getItems().addAll("Time","Medicine ID","Medicine Name","Batch No","Owner ID","Employee ID");
     
        historydata=FXCollections.observableArrayList();
        query="select Sell.Time,Sell.TotalCost,Provide.Medicine_id,Medicine.MedicineName,Provide.Batch_no,Sell.Quantity,\n" +
              "Sell.Employee_id,Sell.Owner_id from Provide inner join Medicine on Provide.Medicine_id = Medicine.Medicine_id inner join Sell on\n" +
              "Provide.Provide_id =Sell.Provide_id order by Sell.Time desc;"; 
        try {
                resultSet=connect2.connectDB(query);
                while(resultSet.next()){
                   if(!(resultSet.getString("Employee_id")==null))
                        historydata.add(new HistoryTable(resultSet.getString("Time").replace(' ','\n'),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Batch_no"),resultSet.getString("Quantity"),resultSet.getString("TotalCost"),resultSet.getString("Employee_id")+"[EMPLOYEE]"));
                    else
                        historydata.add(new HistoryTable(resultSet.getString("Time").replace(' ','\n'),resultSet.getString("Medicine_id"),resultSet.getString("MedicineName"),resultSet.getString("Batch_no"),resultSet.getString("Quantity"),resultSet.getString("TotalCost"),resultSet.getString("Owner_id")+"[OWNER]"));
                }    
            } catch (SQLException ex) {
                  //Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        history.setItems(historydata);
        
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        medicineIdColumn.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        batchColumn.setCellValueFactory(new PropertyValueFactory<>("batchNo"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalCostColumn.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        sellerIdColumn.setCellValueFactory(new PropertyValueFactory<>("sellerId"));
    } 

public class HistoryTable{
    private String time;
    private String medicineId;
    private String medicineName;
    private String batchNo;
    private String quantity;
    private String totalCost;
    private String sellerId;
    
    public HistoryTable(String time,String medicineId,String medicineName,String batchNo,String  quantity,String  totalCost,String sellerId){
    this.time=time;
    this.medicineId=medicineId;
    this.medicineName=medicineName;
    this.batchNo=batchNo;
    this.quantity=quantity;
    this.totalCost=totalCost;
    this.sellerId=sellerId;        
    }
    
        /**
         * @return the time
         */
        public String getTime() {
            return time;
        }

        /**
         * @param time the time to set
         */
        public void setTime(String time) {
            this.time = time;
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
         * @return the quantity
         */
        public String getQuantity() {
            return quantity;
        }

        /**
         * @param quantity the quantity to set
         */
        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        /**
         * @return the sellerId
         */
        public String getSellerId() {
            return sellerId;
        }

        /**
         * @param sellerId the sellerId to set
         */
        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        /**
         * @return the totalCost
         */
        public String getTotalCost() {
            return totalCost;
        }

        /**
         * @param totalCost the totalCost to set
         */
        public void setTotalCost(String totalCost) {
            this.totalCost = totalCost;
        }
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
