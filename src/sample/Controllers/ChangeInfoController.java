package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class ChangeInfoController {
    @FXML
    private MenuItem ChangeItem;

    @FXML
    private Button acceptBtn;

    @FXML
    private Menu addItem;

    @FXML
    private MenuItem addParentInfo;

    @FXML
    private MenuItem addStudent;

    @FXML
    private Menu changeItem;

    @FXML
    private TextField nameField;

    @FXML
    private Menu searchItem;

    @FXML
    private TableView<?> table;

    @FXML
    private void initialize(){

        addStudent.setOnAction(event ->{
            table.getScene().getWindow().hide();
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
