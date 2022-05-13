package sample.Controllers;

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
import java.time.LocalDate;

public class Controller {
    @FXML private MenuItem addParentInfo;
    @FXML private TextField addressField;
    @FXML private DatePicker birthdateField;
    @FXML private TextField birthplace;
    @FXML private TextField certificateMark;
    @FXML private ComboBox<String> educationLvl;
    @FXML private DatePicker issueDate;
    @FXML private TextField issuer;
    @FXML private TextField phoneField;
    @FXML private TextField passportData;
    @FXML private ComboBox<String> profession;
    @FXML private TextField studentNameField;
    @FXML private Button addBtn;
    @FXML private TextField birthcertificate;
    @FXML private TextField certificate_id;
    @FXML private Button applyBtn;
    ObservableList <String> data = FXCollections.observableArrayList();
    int id;
    @FXML
    public void initialize(){
        data.setAll("1","2","3");
        educationLvl.setItems(data);
        data.setAll("2:3", "3:4");
        profession.setItems(data);
        addParentInfo.setOnAction(event ->{
            birthdateField.getScene().getWindow().hide();
            try {
            Parent root = FXMLLoader.load(Main.class.getResource("parentInfo.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        addBtn.setOnAction(event ->{
            String birthDateV = birthdateField.getValue().toString();
            String fullAddressV = addressField.getText();
            String phoneV = phoneField.getText().trim();
            String birthPlaceV = birthplace.getText().trim();
            String[] passDataV = passportData.getText().split(" ");
            String issuedateV = issueDate.getValue().toString();
            String issuerV = issuer.getText();
            String[] birthSertificateV = birthcertificate.getText().split(" ");
            String birthSeries = birthSertificateV[0];
            String birthNumber = birthSertificateV[1];
            String certificateNumber = certificate_id.getText().trim();
            float mark = Float.parseFloat(certificateMark.getText().trim());
            String edLvl = String.valueOf(educationLvl.getSelectionModel().getSelectedItem());
            String[] specialization = String.valueOf(profession.getSelectionModel().getSelectedItem()).split(":");
            DBHandler dbHandler = new DBHandler();
            dbHandler.addStudent(studentNameField.getText(), birthDateV, fullAddressV, phoneV,
                    birthPlaceV, passDataV[0], passDataV[1], issuedateV, issuerV, mark, edLvl, specialization[0], specialization[1], birthSeries, birthNumber, certificateNumber);
        });
        applyBtn.setOnAction(event->{
            String birthDateV = birthdateField.getValue().toString();
            String fullAddressV = addressField.getText();
            String phoneV = phoneField.getText().trim();
            String birthPlaceV = birthplace.getText().trim();
            String[] passDataV = passportData.getText().split(" ");
            String issuedateV = issueDate.getValue().toString();
            String issuerV = issuer.getText();
            String[] birthSertificateV = birthcertificate.getText().split(" ");
            String birthSeries = birthSertificateV[0];
            String birthNumber = birthSertificateV[1];
            String certificateNumber = certificate_id.getText().trim();
            float mark = Float.parseFloat(certificateMark.getText().trim());
            String edLvl = String.valueOf(educationLvl.getSelectionModel().getSelectedItem());
            String[] specialization = String.valueOf(profession.getSelectionModel().getSelectedItem()).split(":");
            DBHandler dbHandler = new DBHandler();
            dbHandler.updateStudent(id, studentNameField.getText(), birthDateV, fullAddressV, phoneV,
                    birthPlaceV, passDataV[0], passDataV[1], issuedateV, issuerV, mark, edLvl, specialization[0], specialization[1], birthSeries, birthNumber, certificateNumber);
        });
    }

    public void initData(int i) {
        applyBtn.setVisible(true);
        id = i;
        DBHandler dbHandler = new DBHandler();
        ResultSet res = dbHandler.querry("SELECT * FROM comission.entrant_info WHERE id_entrant = "+id);
        try {
            res.next();
            birthdateField.setValue(res.getObject(3, LocalDate.class));
            addressField.setText(res.getString(4));
            studentNameField.setText(res.getString(2));
            phoneField.setText(res.getString(5));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        res = dbHandler.querry("SELECT * FROM comission.personal_data WHERE id_entrant = "+id);
        try {
            res.next();
            birthplace.setText(res.getString(6));
            passportData.setText(res.getString(7)+" "+res.getString(8));
            issuer.setText(res.getString(9));
            issueDate.setValue(res.getObject(10, LocalDate.class));
            birthcertificate.setText(res.getString(11)+" "+res.getString(12));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        res = dbHandler.querry("SELECT * FROM comission.education WHERE id_entrant = "+id);
        try {
            res.next();
            certificateMark.setText(res.getString(4));
            certificate_id.setText(res.getString(3));
            profession.setValue(res.getString(5) + ":" + res.getString(6));
            educationLvl.setValue(res.getString(2));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
