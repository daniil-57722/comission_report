package sample.Controllers;

import com.sun.glass.ui.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DBHandler;
import sample.Main;

import java.io.IOException;
import java.sql.SQLException;

public class ParentInfoController {

    @FXML private MenuItem ChangeItem;
    @FXML private TextField fatherWorkPlace;
    @FXML private Button addBtn;
    @FXML private MenuItem addParentInfo;
    @FXML private MenuItem addStudent;
    @FXML private TextField childAmountField;
    @FXML private ComboBox<String> familyStatus;
    @FXML private TextField fatherAddressField;
    @FXML private TextField fatherNameField;
    @FXML private TextField fatherPhoneField;
    @FXML private TextField motherAddressField;
    @FXML private TextField motherNameField;
    @FXML private TextField motherPhoneField;
    @FXML private TextField motherWorkPlace;
    @FXML private TextField studentName;
    @FXML private TableView<ObservableList> tableView;
    @FXML private Button searchBtn;

    @FXML
    private void initialize(){
        ObservableList<String> family = FXCollections.observableArrayList();
        family.setAll("Полная", "Неполная");
        familyStatus.setItems(family);
        ChangeItem.setOnAction(event ->{
            studentName.getScene().getWindow().hide();
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
            studentName.getScene().getWindow().hide();
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
        searchBtn.setOnAction(event->{
            String studentFullName = studentName.getText().trim();
            String request = "SELECT id_entrant as id, fullname as ФИО, birthdate as `Дата рождения`, address as адрес FROM entrant_info WHERE fullname = '"+studentFullName+"'";
            try {
                Main.fill(request, tableView);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        addBtn.setOnAction(event ->{
            int childs = Integer.parseInt(childAmountField.getText().trim());
            String motherName = motherNameField.getText().trim();
            String motherAddress = motherAddressField.getText().trim();
            String motherPhone = motherPhoneField.getText().trim();
            String motherWork = motherWorkPlace.getText().trim();
            String fatherName = fatherNameField.getText().trim();
            String fatherAddress = fatherAddressField.getText().trim();
            String fatherPhone = fatherPhoneField.getText().trim();
            String fatherWork = fatherWorkPlace.getText().trim();
            String status = familyStatus.getValue();
            int idStudent = Integer.parseInt(String.valueOf(tableView.getSelectionModel().getSelectedItem().get(0)));
            DBHandler dbHandler = new DBHandler();
            dbHandler.add_parentInfo(childs, motherName, motherAddress, motherPhone, motherWork, fatherName, fatherAddress, fatherPhone, fatherWork, status, idStudent);
        });
    }
}
