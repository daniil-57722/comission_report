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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    @FXML private Button applyBtn;
    int id;

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
            String idStudent = String.valueOf(tableView.getSelectionModel().getSelectedItem().get(0));
            String[] parentInfo = collect();
            DBHandler dbHandler = new DBHandler();
            dbHandler.add_parentInfo(parentInfo[0], parentInfo[1], parentInfo[2], parentInfo[3], parentInfo[4],
                    parentInfo[5], parentInfo[6], parentInfo[7], parentInfo[8], parentInfo[9], idStudent);
        });
        applyBtn.setOnAction(event->{
            String[] parentInfo = collect();
            DBHandler dbHandler = new DBHandler();
            dbHandler.updateParentInfo(parentInfo[0], parentInfo[1], parentInfo[2], parentInfo[3], parentInfo[4],
                    parentInfo[5], parentInfo[6], parentInfo[7], parentInfo[8], parentInfo[9], id);
        });
    }


    public void initData(int i) {
        applyBtn.setVisible(true);
        id = i;
        DBHandler dbHandler = new DBHandler();
        ResultSet res = dbHandler.querry("SELECT * FROM comission.parents_info WHERE id_entrant = "+id);
        try {
            res.next();
            familyStatus.setValue(res.getString(2));
            childAmountField.setText(res.getString(3));
            motherNameField.setText(res.getString(4));
            motherAddressField.setText(res.getString(5));
            motherPhoneField.setText(res.getString(6));
            motherWorkPlace.setText(res.getString(7));
            fatherNameField.setText(res.getString(8));
            fatherAddressField.setText(res.getString(9));
            fatherPhoneField.setText(res.getString(10));
            fatherWorkPlace.setText(res.getString(11));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        res = dbHandler.querry("SELECT * FROM entrant_info WHERE id_entrant = '"+id+"'");
        try{
            res.next();
            studentName.setText(res.getString("fullname"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public String[] collect(){
        ArrayList<String> data = new ArrayList<>();
        data.add(childAmountField.getText().trim());
        data.add(motherNameField.getText().trim());
        data.add(motherAddressField.getText().trim());
        data.add(motherPhoneField.getText().trim());
        data.add(motherWorkPlace.getText().trim());
        data.add(fatherNameField.getText().trim());
        data.add(fatherAddressField.getText().trim());
        data.add(fatherPhoneField.getText().trim());
        data.add(fatherWorkPlace.getText().trim());
        data.add(familyStatus.getValue());
        return data.toArray(new String[data.size()]);
    }
}
