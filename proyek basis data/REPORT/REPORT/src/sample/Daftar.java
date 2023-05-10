//MELISSA C
//C14200162 - SIB

package sample;

public class Daftar {
    String nama_dr;
    String nama_pasien;

    public Daftar(String dr, String pasien) {
        nama_dr = dr;
        nama_pasien = pasien;
    }

    public String getNama_dr() {
        return nama_dr;
    }

    public String getNama_pasien() {
        return nama_pasien;
    }

    public void setNama_pasien(String nama_pasien) {
        this.nama_pasien = nama_pasien;
    }
}
