/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacymanagementstudio;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Asus
 */
public class FXMLDocumentController implements Initializable {
    public String str2;
    ResultSet resultSet;
    String query,userIdText,passwordText;
    ConnectMSSQL connect=new ConnectMSSQL();
     ConnectDatabase connect2=new ConnectDatabase();
    @FXML
    private JFXButton signInBtn;
    @FXML
    private JFXTextField userId;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXCheckBox signAsAdmin;
    @FXML
    private Label message;
    @FXML
        public void ok_click()
        {
         //connect.connectDB();
        }
       public void signInButtonClicked(ActionEvent event) throws IOException, SQLException
    {      
       
        try {
           userIdText=userId.getText();
           passwordText=password.getText();
           if(userIdText.equals("")||passwordText.equals(""))
           {
               if(userIdText.equals("")&&passwordText.equals(""))
               {
                   message.setText("Please Fill the inputs");
               }
               else if(userIdText.equals(""))
               {
                   message.setText("Please enter User ID ");
               }
               else
               {
                   message.setText("Please enter Password");
               }
           }
           else{
           
        if(signAsAdmin.isSelected()){
            query="select Password from Owner where Owner_id="+userIdText;
            resultSet=connect2.connectDB(query);
            
            if(resultSet.next()){
               
                if(resultSet.getString("Password").equals(passwordText)){
                 
                  
                    Stage window2=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window2.close();
                    
                   FXMLLoader loader=new FXMLLoader(getClass().getResource("Admin_menu.fxml"));
                    Parent root=(Parent)loader.load();
                    
                    Admin_menuController amc=loader.getController();
                    amc.setAuthenticationLabel("OWNER UserID:("+userIdText+")Password:["+passwordText+"]");
                    
                    Stage stage=new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                }
                else{
                     message.setText("Incorrect Password");
                }
            }
            else{
                message.setText("User ID isn't found");
            }
        }
        else{
            query="select Password from Employee where Employee_id="+userIdText;
            resultSet=connect2.connectDB(query);
            
            if(resultSet.next()){
                
                if(resultSet.getString("Password").equals(passwordText)){
                  
                   Stage window2=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window2.close();
                    
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("Admin_menu.fxml"));
                    Parent root=(Parent)loader.load();
                    
                    Admin_menuController amc=loader.getController();
                    amc.setAuthenticationLabel("EMPLOYEE UserID:("+userIdText+")Password:["+passwordText+"]");
                    
                    Stage stage=new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                   
                    
                }
                else{
                      message.setText("Incorrect Password");
                }
            }
            else{
                message.setText("User ID isn't found");
            }

        }
    }            
        } catch (SQLException ex) {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
