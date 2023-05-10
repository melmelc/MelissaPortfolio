//MELISSA C
//C14200162 - SIB

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class Controller implements Initializable {
    @FXML
    private TableView daftar;

    @FXML
    private TableColumn<Daftar, String> namadr;

    @FXML
    private TableColumn<Daftar, String> namap;

    @FXML
    private ComboBox<String> NTP;

    @FXML
    private DatePicker searchDate;

    @FXML
    private Button search;

    private static final dao3<Daftar> d = new DaftarDao();
    private ObservableList<Daftar> reps = FXCollections.observableArrayList();
    LocalDate dateChosen;
    String tgl;
    String tp;

    public void tanggalCari(ActionEvent event) {
        dateChosen = searchDate.getValue();
        tgl = dateChosen.toString();
        if (tp != null && tgl != null) {
            reps.setAll(d.getAll(tp, tgl));
            daftar.setItems(reps);
        }
    }

    public void searchReports(ActionEvent event) {

    }

    public void show(String tp) {
        if (tp != null) {
            reps.setAll(d.getAll2(tp));
            daftar.setItems(reps);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList(d.takePlaces());
        NTP.setItems(list);
        namadr.setCellValueFactory(new PropertyValueFactory<Daftar, String>("nama_dr"));
        namap.setCellValueFactory(new PropertyValueFactory<Daftar, String>("nama_pasien"));
        NTP.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    tp = newValue;
                    show(tp);
                }
        );
    }
}
