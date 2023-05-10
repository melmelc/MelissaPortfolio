//MELISSA C
//C14200162 - SIB

package sample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public interface dao2 <T,I> {
    boolean checkBooking(int idj); /* method untuk mengecek apakah jadwal praktek
    memiliki janji konsul, jika iya maka akan mengembalikan nilai boolean true
    dan jadwal tidak akan bisa dihapus, berhubungan dengan method delete */

    ArrayList<String> getSpecialize();  //method untuk mengambil nama spesialisasi apa saja

    int getMaxSchedId(); /*method untuk mengambil id_jadwal terakhir di postgre,
    sehingga saat ditambahkan jadwal baru hanya perlu ditambah 1*/

    int getPlaceId(String tpt); //method untuk mengambil id tempat dari nama tempat yang dipilih di combo box
    int getDrIdfromList(String nama); //method untuk mengambil id dokter dari tableview maupun combo boc
    int getSchedId(String date, int dr); //method untuk mengambil id jadwal yang dipilih dari tableview
    ArrayList<String> takePlaces(); //method untuk mengambil nama-nama tempat praktek dari database dan ditampilkan di combo box tempat

    ArrayList<String> getNames(String tpt, String ns); /*method untuk mengambil nama-nama dokter
    sesuai tempat dan spesialisasi dan dimunculkan di combo box nama dokter*/

    Collection<T> getAll(String tpt); //method untuk mengambil semua nama dokter dari suatu tempat praktek dan ditampilkan di tableview
    Collection<T> getAll2(String tpt, String ns);  /*method untuk mengambil nama-nama dokter
    sesuai tempat dan spesialisasi dan dimunculkan di tableview */
    Optional<I> add(Jadwal2 j); //method untuk menambahkan jadwal baru
    void update(Jadwal2 t); //method untuk mengupdate jadwal yang sudah ada
    void delete(int id_jadwal); //method untuk menghapus jadwal
}
