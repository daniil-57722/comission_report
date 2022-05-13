package sample.Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.DBHandler;
import sample.Main;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeInfoController {
    @FXML private MenuItem ChangeItem;
    @FXML private Menu addItem;
    @FXML private MenuItem addParentInfo;
    @FXML private MenuItem addStudent;
    @FXML private Menu changeItem;
    @FXML private Button deleteBtn;
    @FXML private TableView<ObservableList> eduTable;
    @FXML private Tab education;
    @FXML private Tab entrantInfo;
    @FXML private TableView<ObservableList> entrantTable;
    @FXML private TextField nameField;
    @FXML private Tab parentInfo;
    @FXML private TableView<ObservableList> parentsTable;
    @FXML private Tab personalData;
    @FXML private TableView<ObservableList> personalTable;
    @FXML private Button searchBtn;
    @FXML private Menu searchItem;
    @FXML
    private void initialize(){
        searchBtn.setOnAction(event->{
            String[] fullname = nameField.getText().split(" ");
            System.out.println(fullname[0]+" "+fullname[1]+" "+fullname[2]);
            try {
                DBHandler dbHandler = new DBHandler();
                ResultSet resultSet = dbHandler.querry("SELECT id_entrant FROM entrant_info WHERE fullname = '" + nameField.getText().trim()+"'");
                resultSet.next();
                int id = resultSet.getInt(1);
                Main.fill("SELECT * FROM personal_data WHERE lastname='" + fullname[0] + "' AND firstname = '"
                        + fullname[1] + "' AND patronymic= '" + fullname[2]+"'", personalTable);
                Main.fill("SELECT * FROM entrant_info WHERE fullname= '"+nameField.getText().trim()+"'", entrantTable);
                Main.fill("SELECT * FROM parents_info WHERE id_entrant='"+id+"'", parentsTable);
                Main.fill("SELECT * FROM education WHERE id_entrant='"+id+"'", eduTable);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        deleteBtn.setOnAction(event->{
            String id;
            if(entrantInfo.isSelected()){
                id = String.valueOf(entrantTable.getSelectionModel().getSelectedItem().get(0));
                entrantTable.getItems().remove(entrantTable.getSelectionModel().getSelectedItem());
            } else if(parentInfo.isSelected()){
                id = String.valueOf(parentsTable.getSelectionModel().getSelectedItem().get(0));
                parentsTable.getItems().remove(parentsTable.getSelectionModel().getSelectedItem());
            } else if(education.isSelected()){
                id = String.valueOf(eduTable.getSelectionModel().getSelectedItem().get(0));
                eduTable.getItems().remove(eduTable.getSelectionModel().getSelectedItem());
            } else{
                id = String.valueOf(personalTable.getSelectionModel().getSelectedItem().get(0));
                personalTable.getItems().remove(personalTable.getSelectionModel().getSelectedItem());
            }
            DBHandler dbHandler = new DBHandler();
            dbHandler.delete("DELETE FROM entrant_info WHERE id_entrant = " + id);
        });
        addStudent.setOnAction(event ->{
            deleteBtn.getScene().getWindow().hide();
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
        entrantTable.setOnMouseClicked(event->{
            if(event.getClickCount() == 2){
                ObservableList<String> selected = entrantTable.getSelectionModel().getSelectedItem();
                changeEntrantInfo(selected);
            }
        });
        parentsTable.setOnMouseClicked(event->{
            if(event.getClickCount()==2){
            try {
                ObservableList<String> selected = parentsTable.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("parentInfo.fxml"));
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setScene(new Scene(loader.load()));
                ParentInfoController controller = loader.getController();
                controller.initData(Integer.parseInt(selected.get(0)));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        });
        eduTable.setOnMouseClicked(event->{
            if(event.getClickCount()==2){
                ObservableList<String> selected = entrantTable.getSelectionModel().getSelectedItem();
                changeEntrantInfo(selected);
            }
        });
        personalTable.setOnMouseClicked(event->{
            ObservableList<String> selected = entrantTable.getSelectionModel().getSelectedItem();
            changeEntrantInfo(selected);
        });
    }
    public void changeEntrantInfo(ObservableList<String> selected){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("sample.fxml"));
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(new Scene(loader.load()));
            Controller controller = loader.getController();
            controller.initData(Integer.parseInt(selected.get(0)));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
