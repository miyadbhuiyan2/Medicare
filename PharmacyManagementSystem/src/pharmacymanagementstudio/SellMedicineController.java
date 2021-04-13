/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacymanagementstudio;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import impl.org.controlsfx.skin.AutoCompletePopup;
import java.io.IOException;
import static java.lang.Integer.MAX_VALUE;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
public class SellMedicineController implements Initializable {

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
    private JFXButton editRecordBtn;
    @FXML
    private JFXButton profileBtn;
    @FXML
    private JFXButton logOutBtn;
    @FXML
    private JFXButton aboutBtn;
    @FXML
    private Label authenticationLabel;
    @FXML
    private JFXButton searchBtn1;
    @FXML
    private TableView<CartTable> searchTable;

    /**
     * Initializes the controller class.
     */
    int range;
    String authentication,query;
     ResultSet resultSet;
     boolean flag,cartFlag;
      ConnectDatabase connect2=new ConnectDatabase();
      ObservableList<CartTable> cartdata;
    @FXML
    private Label medicineID;
    @FXML
    private Label weight;
    @FXML
    private Label unitPrice;
    @FXML
    private Label expireDate;
    @FXML
    private Label shelfNo;
    @FXML
    private Spinner<Integer> quantity;
    @FXML
    private JFXButton addToCart;
    @FXML
    private TableColumn<?, ?> idColumn;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> batchColumn;
    @FXML
    private TableColumn<?, ?> shelfColumn;
    @FXML
    private TableColumn<?, ?> quantityColumn;
    @FXML
    private TableColumn<?, ?> unitPriceColumn;
    @FXML
    private TableColumn<?, ?> totalPriceColumn;
    @FXML
    private TextField searchBar;
    @FXML
    private JFXComboBox<String> batchBox;
    @FXML
    private AnchorPane pane;
    @FXML
    private Text grandTotal;
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
////////////////////////////////////////CheckOut///////////////////////////////////////////////////////
@FXML
public void checkOutButtonClicked(ActionEvent event)
{
    try{
       if(searchTable.getItems().size()<=0){
        Stage stage=(Stage) pane.getScene().getWindow();
        Alert.AlertType type=Alert.AlertType.ERROR;
        Alert alert=new Alert(type,"");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.getDialogPane().setContentText("The table of cart is empty");
        alert.getDialogPane().setHeaderText("Failed");
        Optional<ButtonType> result=alert.showAndWait();
        if(result.get()==ButtonType.OK){}
        }
        else{    
        Stage stage=(Stage) pane.getScene().getWindow();
        Alert.AlertType type=Alert.AlertType.WARNING;
        Alert alert=new Alert(type,"");         
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.getDialogPane().setHeaderText("Are you sure want to proceed?");            
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("YES");
        ButtonType noButton=new ButtonType("NO",ButtonBar.ButtonData.NO);
        alert.getDialogPane().getButtonTypes().add(noButton);
        Optional<ButtonType> result=alert.showAndWait();
    
        if(result.get()==ButtonType.OK){
            CartTable ct;
            
            for(int i=0;i<searchTable.getItems().size();i++)
            {
                ct=searchTable.getItems().get(i);
                /////////////////////////////////////////////////////////////////////////////////////
                query="Update Provide set Quantity=Quantity-"+ct.quantity+" where Medicine_id="+ct.medicineId+" AND Batch_no='"+ct.batchNo+"';";
                connect2.connectDBUpdate(query);
                ///////////////////////////////////////////////////////////////////////////////////
                query="SELECT SUM (QUANTITY) as Total FROM PROVIDE WHERE Medicine_id="+ct.medicineId;        
                resultSet=connect2.connectDB(query);
                resultSet.next();
                if(resultSet.getString("Total").equals(null))
                    query="UPDATE Medicine Set Quantity="+0+" WHERE Medicine_id="+ct.medicineId;
                else
                    query="UPDATE Medicine Set Quantity="+resultSet.getInt("Total")+" WHERE Medicine_id="+ct.medicineId;
                connect2.connectDBUpdate(query);
                ///////////////////////////////////////////////////////////////////////////////////
                query="SELECT Provide_id FROM PROVIDE WHERE Medicine_id="+ct.medicineId+" AND Batch_no='"+ct.batchNo+"';";        
                resultSet=connect2.connectDB(query);
                resultSet.next();
                ///////////////////////////////////////////////////////////////////////////////////
                if(authentication.contains("OWNER")){
                    query="INSERT INTO Sell(Time,Owner_id,Provide_id,Quantity,TotalCost) "
                      + "VALUES(GETDATE(),"+Integer.parseInt(authentication.substring(authentication.indexOf("(")+1,authentication.indexOf(")")))+","+resultSet.getInt("Provide_id")+","+ct.quantity+","+ct.totalPrice+");";
                }
                else{
                    query="INSERT INTO Sell(Time,Employee_id,Provide_id,Quantity,TotalCost) "
                      + "VALUES(GETDATE(),"+Integer.parseInt(authentication.substring(authentication.indexOf("(")+1,authentication.indexOf(")")))+","+resultSet.getInt("Provide_id")+","+ct.quantity+","+ct.totalPrice+");";
                }
                connect2.connectDBUpdate(query);
            }  
            
            Stage stage2=(Stage) pane.getScene().getWindow();
            Alert.AlertType type2=Alert.AlertType.INFORMATION;
            Alert alert2=new Alert(type2,"");
            alert2.initModality(Modality.APPLICATION_MODAL);
            alert2.initOwner(stage);
            alert2.getDialogPane().setContentText("The checkout has been done succefully");
            alert2.getDialogPane().setHeaderText("Done");
            Optional<ButtonType> result2=alert2.showAndWait();
            if(result.get()==ButtonType.OK){}
            searchBar.setText("");
            batchBox.getItems().clear();
            medicineID.setText("");
            weight.setText("");
            unitPrice.setText("");
            quantity.getEditor().clear();
            expireDate.setText("");
            shelfNo.setText("");
            grandTotal.setText("");
            searchTable.getItems().clear();
    }
    else  if(result.get()==ButtonType.NO){} 
    }    
    }catch(SQLException ex){
        //ex.printStackTrace();
    }
    catch(Exception ex){
    //ex.printStackTrace();
    } 
}
////////////////////////////////////////Delete///////////////////////////////////////////////////////
@FXML
public void deleteButtonClicked(ActionEvent event)
{ 
     searchTable.getItems().removeAll(searchTable.getSelectionModel().getSelectedItem());
     grandTotalCalculator();
}
////////////////////////////////////////AddToCart///////////////////////////////////////////////////////
@FXML
public void addToCartButtonClicked(ActionEvent event)
{ 
    try {
        if(!(batchBox.getSelectionModel().isEmpty())){

            if(checkCart(Integer.parseInt(medicineID.getText()),batchBox.getValue())){
                if(spinnerValidation(Integer.parseInt(quantity.getEditor().getText())))
                {
                cartdata.add(new CartTable(Integer.parseInt(medicineID.getText()),resultSet.getString("MedicineName"),batchBox.getValue(),shelfNo.getText(),Integer.parseInt(quantity.getEditor().getText()),Float.parseFloat(unitPrice.getText().substring(0, unitPrice.getText().indexOf("/"))),Float.parseFloat(unitPrice.getText().substring(0, unitPrice.getText().indexOf("/")))*Integer.parseInt(quantity.getEditor().getText())));
                searchTable.setItems(cartdata);
                grandTotalCalculator();
                }
            }
            else{
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("This batch of this medicine has already been added to the cart.");
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
        alert.getDialogPane().setContentText("Please select a Batch.");
        alert.getDialogPane().setHeaderText("Failed");
        Optional<ButtonType> result=alert.showAndWait();
        if(result.get()==ButtonType.OK){}
        }
    } catch (SQLException ex) {
       // Logger.getLogger(SellMedicineController.class.getName()).log(Level.SEVERE, null, ex);
    }catch (NumberFormatException ex) {
            Stage stage=(Stage) pane.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.ERROR;
            Alert alert=new Alert(type,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setContentText("You have entered an invalid input at quantity field");
            alert.getDialogPane().setHeaderText("Invalid Input");
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get()==ButtonType.OK){}
       // Logger.getLogger(SellMedicineController.class.getName()).log(Level.SEVERE, null, ex);
    }catch (Exception ex) {
        //Logger.getLogger(SellMedicineController.class.getName()).log(Level.SEVERE, null, ex);
    }
}        
////////////////////////////////////////Search/////////////////////////////////////////////////////////    
@FXML
public void searchMedicineButtonClicked(ActionEvent event)
{   
    try{    
         if(!(searchBar.getText().isEmpty())){
            batchBox.getItems().clear();
            medicineID.setText("");
            weight.setText("");
            unitPrice.setText("");
            quantity.getEditor().clear();
            expireDate.setText("");
            shelfNo.setText("");
            flag=true;
             query="Select Medicine.Medicine_id,Medicine.CompanyName,Medicine.MedicineName,Medicine.Weight,Medicine.UnitPrice,Provide.Batch_no,"
                     + "Provide.Quantity,Provide.Provide_id,Provide.ExpireDate,Provide.Shelf_no from Medicine JOIN PROVIDE ON Medicine.Medicine_id=PROVIDE.Medicine_id"
                     + " where Medicine.Medicine_id="+Integer.parseInt(searchBar.getText().substring(1, searchBar.getText().indexOf("]")))
                     + " AND Provide.Quantity>0 AND Provide.ExpireDate>GETDATE() ;";
            resultSet=connect2.connectDB(query);
            while(resultSet.next()){
                if(flag){
                medicineID.setText(resultSet.getString("Medicine_id"));
                weight.setText(resultSet.getString("Weight"));
                unitPrice.setText(resultSet.getString("UnitPrice")+"/=");
                flag=false;
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
            alert.getDialogPane().setContentText("Note: Search Bar must be filled");
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
            //Logger.getLogger(SellMedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex){
        //ex.printStackTrace();
        }
}
////////////////////////////////////////choice/////////////////////////////////////////////////////////    
@FXML
public void choiceBatchBoxSelected(ActionEvent event)
{
        try {
            if(!(batchBox.getValue()==null))
            {
            query="Select Medicine.MedicineName,Medicine.Weight,Medicine.UnitPrice,Medicine.CompanyName,Provide.Supplier_id,Provide.Batch_no,"
                     + "Provide.Quantity,Provide.ExpireDate,Provide.Shelf_no from Medicine INNER JOIN PROVIDE ON Medicine.Medicine_id=PROVIDE.Medicine_id where Provide.Medicine_id="+Integer.parseInt(medicineID.getText())+" AND Batch_no='"+batchBox.getValue().toString()+"';";
            resultSet=connect2.connectDB(query);
            resultSet.next();
            SpinnerValueFactory<Integer> svf=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,resultSet.getInt("Quantity"),resultSet.getInt("Quantity"));     
            quantity.setValueFactory(svf);
            range=resultSet.getInt("Quantity");
            shelfNo.setText(resultSet.getString("Shelf_no"));
            expireDate.setText(resultSet.getString("ExpireDate"));
            }
            } catch (SQLException ex) {
            //Logger.getLogger(SellMedicineController.class.getName()).log(Level.SEVERE, null, ex);
              }
              catch(Exception ex){
              }    
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
    @Override
public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<String> nameList=new ArrayList<String>();
        query="SELECT distinct PROVIDE.Medicine_id,Medicine.MedicineName,Medicine.Weight,Medicine.CompanyName \n" +
        "FROM Medicine JOIN PROVIDE ON Medicine.Medicine_id=PROVIDE.Medicine_id where PROVIDE.ExpireDate>GETDATE() AND PROVIDE.Quantity>0;";
        try {
            resultSet=connect2.connectDB(query);
            while(resultSet.next()){
            nameList.add("["+resultSet.getString("Medicine_id")+"] "+resultSet.getString("MedicineName"));
            }    
                } catch (SQLException ex) {
            //Logger.getLogger(SellMedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AutoCompletionBinding<String> autocompletebinding = TextFields.bindAutoCompletion(searchBar,nameList);
        autocompletebinding.setPrefWidth(980.8);
        autocompletebinding.setMaxWidth(MAX_VALUE);
        autocompletebinding.setVisibleRowCount(10);
        
        cartdata=FXCollections.observableArrayList();
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        batchColumn.setCellValueFactory(new PropertyValueFactory<>("batchNo"));
        shelfColumn.setCellValueFactory(new PropertyValueFactory<>("shelfNo"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    } 

 public void grandTotalCalculator(){
     CartTable ct;
     float gt = 0;
     for(int i=0;i<searchTable.getItems().size();i++)
    {
    ct=searchTable.getItems().get(i);
    gt+=ct.totalPrice;
    } 
     grandTotal.setText(Float.toString(gt));
 }
public boolean checkCart(int id,String batch){
     CartTable ct;
     
     for(int i=0;i<searchTable.getItems().size();i++)
    {
    ct=searchTable.getItems().get(i);
    if(ct.medicineId==id&&ct.batchNo.equals(batch))
        return false;
    } 

 return true;
 }
    public class CartTable{
    private int medicineId;
    private String medicineName;
    private String batchNo;
    private String shelfNo;
    private int quantity;
    private float unitPrice;
    private float totalPrice;
    
    public CartTable(int medicineId,String medicineName,String batchNo,String shelfNo,int quantity,float unitPrice,float totalPrice){
    this.medicineId=medicineId;
    this.medicineName=medicineName;
    this.batchNo=batchNo;
    this.shelfNo=shelfNo;
    this.quantity=quantity;
    this.unitPrice=unitPrice; 
    this.totalPrice=totalPrice;
    }

        /**
         * @return the medicineId
         */
        public int getMedicineId() {
            return medicineId;
        }

        /**
         * @param medicineId the medicineId to set
         */
        public void setMedicineId(int medicineId) {
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

        /**
         * @return the quantity
         */
        public int getQuantity() {
            return quantity;
        }

        /**
         * @param quantity the quantity to set
         */
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        /**
         * @return the unitPrice
         */
        public float getUnitPrice() {
            return unitPrice;
        }

        /**
         * @param unitPrice the unitPrice to set
         */
        public void setUnitPrice(float unitPrice) {
            this.unitPrice = unitPrice;
        }

        /**
         * @return the totalPrice
         */
        public float getTotalPrice() {
            return totalPrice;
        }

        /**
         * @param totalPrice the totalPrice to set
         */
        public void setTotalPrice(float totalPrice) {
            this.totalPrice = totalPrice;
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////    
public boolean spinnerValidation(int value){
    
    if(value<=0){
        try{
                Stage stage=(Stage) pane.getScene().getWindow();
                Alert.AlertType type=Alert.AlertType.ERROR;
                Alert alert=new Alert(type,"");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(stage);
                alert.getDialogPane().setContentText("Note: Quantity must be greater than zero");
                alert.getDialogPane().setHeaderText("Failed");
                Optional<ButtonType> result=alert.showAndWait();
                if(result.get()==ButtonType.OK){}
        }catch(Exception e){}    
        return false;
    }
    else if(value>range){
        try{
                Stage stage=(Stage) pane.getScene().getWindow();
                Alert.AlertType type=Alert.AlertType.ERROR;
                Alert alert=new Alert(type,"");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(stage);
                alert.getDialogPane().setContentText("Note: Quantity can't be greater than "+range);
                alert.getDialogPane().setHeaderText("Failed");
                Optional<ButtonType> result=alert.showAndWait();
                if(result.get()==ButtonType.OK){}
        }catch(Exception e){}
        return false;
    }
    return true;
}
///////////////////////////////////////////////////////////////////////////////////////////////
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