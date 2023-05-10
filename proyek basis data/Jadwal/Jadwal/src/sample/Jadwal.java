//MELISSA C
//C14200162 - SIB

package sample;

import java.sql.Date;
import java.sql.Time;

public class Jadwal {
    String name;
    String status;
    String nama_hari;
    Time jam_mulai;
    Time jam_selesai;
    Date tanggal;
    Integer kuota;

    public Jadwal(String nama, String status, String nama_hari, Date date, Time jam1, Time jam2, Integer kuota) {
        name = nama;
        this.status = status;
        this.nama_hari = nama_hari;
        tanggal = date;
        jam_mulai = jam1;
        jam_selesai = jam2;
        this.kuota = kuota;
    }

    public String getNama_hari() {
        return nama_hari;
    }

    public void setNama_hari(String nama_hari) {
        this.nama_hari = nama_hari;
    }

    public Integer getKuota() {
        return kuota;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Time getJam_mulai() {
        return jam_mulai;
    }

    public void setJam_mulai(Time jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    public Time getJam_selesai() {
        return jam_selesai;
    }

    public void setJam_selesai(Time jam_selesai) {
        this.jam_selesai = jam_selesai;
    }

}
