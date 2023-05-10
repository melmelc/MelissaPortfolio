//MELISSA C
//C14200162 - SIB

package sample;

import java.sql.Date;
import java.sql.Time;

public class Jadwal2 {
    int idt;
    int idD;
    int idJ;
    String hari;
    Time jam_mulai_praktek;
    Time jam_selesai_praktek;
    Date tanggal_praktek;
    int kuotaP;

    public Jadwal2(int idt, int idD, int idJ, String hari, Time jam_mulai_praktek, Time jam_selesai_praktek, java.sql.Date tanggal_praktek, int kuotaP) {
        this.idt = idt;
        this.idD = idD;
        this.idJ = idJ;
        this.hari = hari;
        this.jam_mulai_praktek = jam_mulai_praktek;
        this.jam_selesai_praktek = jam_selesai_praktek;
        this.tanggal_praktek = tanggal_praktek;
        this.kuotaP = kuotaP;
    }

    public void setIdJ(int idJ) {
        this.idJ = idJ;
    }

    public int getIdt() {
        return idt;
    }

    public int getIdD() {
        return idD;
    }

    public int getIdJ() {
        return idJ;
    }

    public String getHari() {
        return hari;
    }

    public Time getJam_mulai_praktek() {
        return jam_mulai_praktek;
    }

    public Time getJam_selesai_praktek() {
        return jam_selesai_praktek;
    }

    public java.sql.Date getTanggal_praktek() {
        return tanggal_praktek;
    }

    public int getKuotaP() {
        return kuotaP;
    }
}
