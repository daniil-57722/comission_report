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
    ObservableList <String> data = FXCollections.observableArrayList();
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
    }
}
