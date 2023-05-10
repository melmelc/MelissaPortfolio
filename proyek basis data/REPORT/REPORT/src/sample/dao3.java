//MELISSA C
//C14200162 - SIB

package sample;

import java.util.ArrayList;
import java.util.Collection;

public interface dao3<T> {
    ArrayList<String> takePlaces(); //method untuk mengambil nama tempat dan ditampilkan di combobox
    Collection<Daftar> getAll2 (String ntp); //method untuk menampilkan daftar dokter dan pasiennya di tableview
    Collection<Daftar> getAll(String ntp, String tanggal);
    //method untuk menampilkan daftar dokter dan pasiennya di tableview berdasarkan tanggal
}
