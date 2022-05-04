package sample.Controllers;

import com.sun.glass.ui.Menu;
import com.sun.glass.ui.MenuBar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class ParentInfoController {
    @FXML
    private MenuItem ChangeItem;

    @FXML
    private MenuItem addParentInfo;

    @FXML
    private MenuItem addStudent;

    @FXML
    private TextField address;

    @FXML
    private TextField address1;

    @FXML
    private TextField birthplace;

    @FXML
    private TextField birthplace1;

    @FXML
    private ComboBox<?> familyStatus;

    @FXML
    private TextField phone;

    @FXML
    private TextField phone1;

    @FXML
    private TextField studentNameField;

    @FXML
    private TextField studentNameField1;

    @FXML
    private void initialize(){
        ChangeItem.setOnAction(event ->{
            studentNameField.getScene().getWindow().hide();
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("changeInfo.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        addStudent.setOnAction(event ->{
            studentNameField.getScene().getWindow().hide();
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("sample.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e){
                e.printStackTrace();
            }
        });
        addParentInfo.setOnAction(event ->{
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("ParentInfoController.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
