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
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class ControllerJadwal implements Initializable {
    @FXML
    private TableView<Jadwal> listJadwal;

    @FXML
    private TableColumn<Jadwal, String> namaDr;

    @FXML
    private TableColumn<Jadwal, String> status;

    @FXML
    private TableColumn<Jadwal, String> day;

    @FXML
    private TableColumn<Jadwal, Date> date;

    @FXML
    private TableColumn<Jadwal, Time> start;

    @FXML
    private TableColumn<Jadwal, Time> end;

    @FXML
    private TableColumn<Jadwal, Integer> kuota;

    @FXML
    private ComboBox<String> spc;

    @FXML
    private ComboBox<String> tpt;

    @FXML
    private ComboBox<String> name;

    @FXML
    private DatePicker setDate;

    @FXML
    private ComboBox<String> jam1;

    @FXML
    private ComboBox<String> jam2;

    @FXML
    private ComboBox<String> menit1;
    @FXML
    private ComboBox<String> menit2;

    @FXML
    private Spinner<Integer> kp;

    ArrayList<String> h = new ArrayList<>();
    ArrayList<String> m = new ArrayList<>();
    String namaDrSelected;
    String t1;
    String t2;
    String j1;
    String j2;
    String m1;
    String m2;
    String tempat;
    String namadr;
    String tanggalP;
    LocalDate dateChosen;
    String tgl;
    int idJ;
    int idJUpd;
    int index = -1;
    boolean isBooked = false;
    java.sql.Date tgl2;
    int k1;
    String namaspc;

    private static final dao2<Jadwal, Integer> dr = new JadwalDao();
    private ObservableList<Jadwal> dokters = FXCollections.observableArrayList();

    public void selectDate(ActionEvent event) {
        if (setDate.getValue() != null) {
            dateChosen = setDate.getValue();

            tgl = dateChosen.toString();
            System.out.println(tgl);
            setTimes();
        }
    }
    //method untuk mencari nama hari
    public String cekHari() {
        DayOfWeek namaHari = dateChosen.getDayOfWeek();
        String hari2 = "";
        if (namaHari.toString().equals("MONDAY")) hari2 = "Senin";
        if (namaHari.toString().equals("TUESDAY")) hari2 = "Selasa";
        if (namaHari.toString().equals("WEDNESDAY")) hari2 = "Rabu";
        if (namaHari.toString().equals("THURSDAY")) hari2 = "Kamis";
        if (namaHari.toString().equals("FRIDAY")) hari2 = "Jumat";
        if (namaHari.toString().equals("SATURDAY")) hari2 = "Sabtu";

        return hari2;
    }

    public void setTimes() {
        String[] hours = {"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"};
        String[] mins = {"00", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"};
        h.addAll(Arrays.asList(hours));
        m.addAll(Arrays.asList(mins));
        ObservableList<String> list = FXCollections.observableArrayList(h);
        ObservableList<String> list2 = FXCollections.observableArrayList(m);
        jam1.setItems(list);
        jam2.setItems(list);
        menit1.setItems(list2);
        menit2.setItems(list2);
    }

    public void saveJadwal(ActionEvent event) {
        System.out.println(dr.getMaxSchedId());
        tgl2 = java.sql.Date.valueOf(setDate.getValue());
        idJ = dr.getMaxSchedId() + 1;
        int idDr2 = dr.getDrIdfromList(namaDrSelected);
        int idtpt2 = dr.getPlaceId(tempat);
        t1 = j1 + ":" + m1 + ":00";
        t2 = j2 + ":" + m2 + ":00";
        String d = cekHari();
        Time jamM = Time.valueOf(t1);
        Time jamS = Time.valueOf(t2);
        int kp2 = kp.getValue();
        Jadwal2 baru = new Jadwal2(idtpt2, idDr2, idJ, d, jamM, jamS, tgl2, kp2);
        dr.add(baru);
        viewAllRecord();
        reset();
    }

    public void deleteJadwal(ActionEvent event) {
        isBooked = dr.checkBooking(idJUpd);
        if (!isBooked) {
            dr.delete(idJUpd);
        } else System.out.println("ERROR, JADWAL PRAKTEK ADA JANJI KONSULTASI !!");
        viewAllRecord();
        reset();

    }

    public void updateJadwal(ActionEvent event) {
        tgl2 = java.sql.Date.valueOf(setDate.getValue());
        int idDr2 = dr.getDrIdfromList(namadr);
        int idtpt2 = dr.getPlaceId(tempat);
        t1 = j1 + ":" + m1 + ":00";
        t2 = j2 + ":" + m2 + ":00";
        String d = cekHari();
        Time jamM = Time.valueOf(t1);
        Time jamS = Time.valueOf(t2);
        int kp2 = kp.getValue();

        Jadwal2 baru = new Jadwal2(idtpt2, idDr2, idJUpd, d, jamM, jamS, tgl2, kp2);
        dr.update(baru);
        viewAllRecord();
        reset();

    }

    //method untuk mengambil data dari tableview
    public void getSelected(MouseEvent mouseEvent) {
        index = listJadwal.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        //mengambil data dari kolom yang diklik
        namadr = namaDr.getCellData(index);
        tanggalP = date.getCellData(index).toString();
        k1 = kuota.getCellData(index);
        idJUpd = dr.getSchedId(tanggalP, dr.getDrIdfromList(namadr));


        name.setValue(namadr);
        setDate.setValue(LocalDate.parse(tanggalP));
        jam1.setValue(start.getCellData(index).toString().substring(0, 2));
        menit1.setValue(start.getCellData(index).toString().substring(3, 5));
        jam2.setValue(end.getCellData(index).toString().substring(0, 2));
        menit2.setValue(end.getCellData(index).toString().substring(3, 5));
        if (k1 >= 5) {
            SpinnerValueFactory<Integer> s2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 20, k1);
            kp.setValueFactory(s2);
        }
    }

    void qtyReset() {
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 20, 5);
        kp.setValueFactory(spinnerValueFactory);
    }

    public void reset() {
        jam1.setValue("00");
        jam2.setValue("00");
        menit1.setValue("00");
        menit2.setValue("00");
        name.setValue(null);
        setDate.setValue(null);
        qtyReset();


    }

    void viewAllRecord() {
        dokters.setAll(dr.getAll2(tempat, namaspc));
        listJadwal.setItems(dokters);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList(dr.takePlaces());
        tpt.setItems(list);

        qtyReset();
        namaDr.setCellValueFactory(new PropertyValueFactory<>("name"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        date.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        start.setCellValueFactory(new PropertyValueFactory<>("jam_mulai"));
        end.setCellValueFactory(new PropertyValueFactory<>("jam_selesai"));
        day.setCellValueFactory(new PropertyValueFactory<>("nama_hari"));
        kuota.setCellValueFactory(new PropertyValueFactory<Jadwal, Integer>("kuota"));
        getSelectedCB();
    }

    //method untuk menangkap pilihan dari combo box
    public void getSelectedCB() {
        tpt.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    if (tpt.getValue() == null) tpt.setValue(null);
                    else {
                        tempat = newValue;
                        if (tempat != null) {
                            dokters.setAll(dr.getAll(tempat));
                            listJadwal.setItems(dokters);
                            ObservableList<String> listS = FXCollections.observableArrayList(dr.getSpecialize());
                            spc.setItems(listS);


                        }

                    }
                }
        );
        spc.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    namaspc = newValue;
                    if (namaspc != null) {
                        dokters.setAll(dr.getAll2(tempat, namaspc));
                        listJadwal.setItems(dokters);

                        ObservableList<String> list = FXCollections.observableArrayList(dr.getNames(tempat, namaspc));
                        name.setItems(list);
                    }
                }
        );

        name.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    if (name.getValue() == null) name.setValue(null);
                    else namaDrSelected = newValue;
                }
        );
        jam1.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    if (jam1.getValue() == null && start.getCellData(index).toString() != null)
                        jam1.setValue(start.getCellData(index).toString().substring(0, 2));
                    else if (jam1.getValue() != null) j1 = newValue;
                }
        );
        jam2.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    if (jam2.getValue() == null && end.getCellData(index).toString() != null)
                        jam2.setValue(end.getCellData(index).toString().substring(0, 2));
                    else if (jam2.getValue() != null) j2 = newValue;
                }
        );
        menit1.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    if (menit1.getValue() == null && start.getCellData(index).toString() != null)
                        menit1.setValue(start.getCellData(index).toString().substring(3, 5));
                    else if (menit1.getValue() != null) m1 = newValue;
                }
        );
        menit2.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    if (menit2.getValue() == null && end.getCellData(index).toString() != null)
                        menit2.setValue(end.getCellData(index).toString().substring(3, 5));
                    else if (menit2.getValue() != null) m2 = newValue;
                }
        );
    }




}
