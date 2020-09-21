package layer_presentation.Controller;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import layer_presentation.util.Descriptions.Description;
import layer_presentation.util.ModifiedMenuItems.SetDTO;

import javax.persistence.Table;
import java.util.*;

public class ControllerUpdateSets implements ControllerDescription{

ObservableList<SetDTO> games = FXCollections.observableArrayList();
Description description;

    @FXML
    private TableView<SetDTO> table;
    @FXML
    private TableColumn<SetDTO , String> player1;
    @FXML
    private TableColumn<SetDTO,String> player2;
    @FXML
    private Button add;
    @FXML
    private Button remove;
    @FXML
    private Button done;
    @FXML
    void initialize(){
        player1.setCellValueFactory(new PropertyValueFactory<SetDTO, String>("p1Score"));
        player2.setCellValueFactory(new PropertyValueFactory<SetDTO, String>("p2Score"));
        player1.setOnEditCommit(this::editCell1);
        player2.setOnEditCommit(this::editCell2);
        player1.setCellFactory(TextFieldTableCell.forTableColumn());
        player2.setCellFactory(TextFieldTableCell.forTableColumn());
        add.setOnAction(event -> {
        table.getItems().add(SetDTO.builder().p1Score("0").p2Score("0").build());
        });
        remove.setOnAction(event -> {
            table.getItems().remove(table.getItems().size()-1);
        });

    }
    private void editCell1(TableColumn.CellEditEvent edited){
        SetDTO selectedItem= table.getSelectionModel().getSelectedItem();
        String aux = edited.getNewValue().toString().replaceAll("\\D+","");
        if(aux.isBlank())aux = "0";
        selectedItem.setP1Score(aux);
    }
    private void editCell2(TableColumn.CellEditEvent edited){
        SetDTO selectedItem= table.getSelectionModel().getSelectedItem();
        String aux = edited.getNewValue().toString().replaceAll("\\D+","");
        if(aux.isBlank())aux = "0";
        selectedItem.setP2Score(aux);
    }


    @Override
    public void loadValues(Description description) {
        this.description = description;
    games.addAll(description.getData2());
    table.setItems(games);
        done.setOnAction(event -> {
            this.description.getData2().clear();
            this.description.getData2().addAll(table.getItems());
            Stage stage = (Stage) done.getScene().getWindow();
            // do what you have to do
            stage.close();
        });
    }

}
