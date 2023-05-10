//MELISSA C
//C14200162 - SIB

package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaftarDao implements dao3 {
    private static final Logger LOGGER = Logger.getLogger(DaftarDao.class.getName());

    private final Optional<Connection> connection;
    private static JdbcConnection con;

    public DaftarDao() {
        this.connection = con.getConnection();
    }

    public Collection<Daftar> getAll(String ntp, String tanggal) {
        Collection<Daftar> reports = new ArrayList<>();
        String sql = "select nama_dokter, nama_depan_pasien || ' ' || nama_belakang_pasien as nama from dokter d\n" +
                "join jadwal_praktek jp on d.id_dokter = jp.id_dokter " +
                "join tempat_praktek tp on tp.id_tempat = jp.id_tempat " +
                "join janji_konsultasi jk on jk.id_jadwal = jp.id_jadwal " +
                "join pasien p on jk.id_pasien = p.id_pasien " +
                "where nama_tempat = '" + ntp + "' and text(tanggal_praktek) = '" + tanggal + "'";
        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String name_dr = resultSet.getString("nama_dokter");
                    String nama_p = resultSet.getString("nama");

                    Daftar rep = new Daftar(name_dr, nama_p);

                    reports.add(rep);

                    LOGGER.log(Level.INFO, "Found {0} in database", reports);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        return reports;
    }

    public ArrayList<String> takePlaces() {
        ArrayList<String> places = new ArrayList<>();
        String sql = "select nama_tempat from tempat_praktek";
        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    places.add(resultSet.getString("nama_tempat"));
                    LOGGER.log(Level.INFO, "Found {0} in database", places);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        return places;
    }

    @Override
    public Collection<Daftar> getAll2(String ntp) {
        Collection<Daftar> reports = new ArrayList<>();
        String sql = "select nama_dokter, nama_depan_pasien || ' ' || nama_belakang_pasien as nama from dokter d\n" +
                "join jadwal_praktek jp on d.id_dokter = jp.id_dokter " +
                "join tempat_praktek tp on tp.id_tempat = jp.id_tempat " +
                "join janji_konsultasi jk on jk.id_jadwal = jp.id_jadwal " +
                "join pasien p on jk.id_pasien = p.id_pasien " +
                "where nama_tempat = '" + ntp + "'";
        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String name_dr = resultSet.getString("nama_dokter");
                    String nama_p = resultSet.getString("nama");

                    Daftar rep = new Daftar(name_dr, nama_p);

                    reports.add(rep);

                    LOGGER.log(Level.INFO, "Found {0} in database", reports);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        return reports;
    }
}
