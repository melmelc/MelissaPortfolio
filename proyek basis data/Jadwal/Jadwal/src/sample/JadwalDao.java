//MELISSA C
//C14200162 - SIB

package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JadwalDao implements dao2<Jadwal, Integer> {
    private static final Logger LOGGER = Logger.getLogger(JadwalDao.class.getName());

    private final Optional<Connection> connection;
    private static JdbcConnection con;

    public JadwalDao() {
        this.connection = con.getConnection();
    }


    @Override
    public boolean checkBooking(int idj) {
        final boolean[] c = {false};
        String sql = "select id_jadwal from janji_konsultasi where id_jadwal = " + idj;
        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id_jadwal");
                    if (id != 0) c[0] = true;
                    LOGGER.log(Level.INFO, "Found {0} in database", id);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        return c[0];

    }

    @Override
    public ArrayList<String> getSpecialize() {
        ArrayList<String> spc = new ArrayList<>();
        String sql = "select nama_spesialisasi from spesialisasi";
        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    spc.add(resultSet.getString("nama_spesialisasi"));
                    LOGGER.log(Level.INFO, "Found {0} in database", spc);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        return spc;
    }

    @Override
    public int getMaxSchedId() {
        final Integer[] idjadwal = {0};
        String sql = "select max(id_jadwal) from jadwal_praktek";
        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    Integer id = resultSet.getInt("max");
                    idjadwal[0] = id;
                    LOGGER.log(Level.INFO, "Found {0} in database", id);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        return idjadwal[0];
    }

    @Override
    public int getPlaceId(String tempat) {
        final Integer[] idt = {0};
        String sql = "select jp.id_tempat from jadwal_praktek jp join tempat_praktek tp on jp.id_tempat = tp.id_tempat " +
                "where nama_tempat = '" + tempat + "'";
        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id_tempat");
                    idt[0] = id;
                    LOGGER.log(Level.INFO, "Found {0} in database", id);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        return idt[0];
    }

    @Override
    public int getDrIdfromList(String nama) {
        final int[] id2 = {0};
        String sql = "select id_dokter from dokter where nama_dokter ='" + nama + "'";
        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id_dokter");
                    id2[0] = id;
                    LOGGER.log(Level.INFO, "Found {0} in database", id);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        return id2[0];
    }


    @Override
    public int getSchedId(String date, int dr) {
        final int[] id2 = {0};
        String sql = "select id_jadwal from jadwal_praktek where text(tanggal_praktek)='" + date + "' and id_dokter = " + dr;
        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id_jadwal");
                    id2[0] = id;
                    LOGGER.log(Level.INFO, "Found {0} in database", id);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        return id2[0];
    }

    @Override
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
    public ArrayList<String> getNames(String tp, String ns) {
        ArrayList<String> drNames = new ArrayList<>();
        String sql = "select distinct nama_dokter from dokter d join jadwal_praktek jp on d.id_dokter = jp.id_dokter " +
                "join tempat_praktek tp on tp.id_tempat = jp.id_tempat " +
                "join spesialisasi s on s.id_spesialis = d.id_spesialis " +
                "where nama_spesialisasi = '" + ns + "' and nama_tempat = '" + tp + "' order by 1";
        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String name = resultSet.getString("nama_dokter");
                    drNames.add(name);
                    LOGGER.log(Level.INFO, "Found {0} in database", drNames);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        return drNames;
    }


    @Override
    public Collection<Jadwal> getAll(String tp) {
        Collection<Jadwal> drs = new ArrayList<>();
        String sql = "select nama_dokter, status_available, case when status_available = true then 'Available' " +
                "when status_available = false then 'Unavailable' end as STATUS, hari_praktek, tanggal_praktek, jam_mulai_praktek, jam_selesai_praktek, " +
                "kuota_pasien from dokter d join jadwal_praktek jp on d.id_dokter = jp.id_dokter " +
                "join tempat_praktek tp on tp.id_tempat = jp.id_tempat " +
                "where nama_tempat = '" + tp + "' order by 1";
        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String name = resultSet.getString("nama_dokter");
                    String stas = resultSet.getString("STATUS");
                    String d = resultSet.getString("hari_praktek");
                    Date tgl = resultSet.getDate("tanggal_praktek");
                    Time start = resultSet.getTime("jam_mulai_praktek");
                    Time end = resultSet.getTime("jam_selesai_praktek");
                    Integer q = resultSet.getInt("kuota_pasien");

                    Jadwal dr = new Jadwal(name, stas, d, tgl, start, end, q);

                    drs.add(dr);

                    LOGGER.log(Level.INFO, "Found {0} in database", drs);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        return drs;
    }

    @Override
    public Collection<Jadwal> getAll2(String tpt, String ns) {
        Collection<Jadwal> drs = new ArrayList<>();
        String sql = "select nama_dokter, status_available, case when status_available = true then 'Available' when status_available = false then 'Unavailable' end as STATUS, " +
                "hari_praktek, jam_mulai_praktek, jam_selesai_praktek, tanggal_praktek, kuota_pasien " +
                "from dokter d join jadwal_praktek jp on d.id_dokter = jp.id_dokter " +
                "join tempat_praktek tp on tp.id_tempat = jp.id_tempat " +
                "join spesialisasi s on s.id_spesialis = d.id_spesialis " +
                "where nama_spesialisasi = '" + ns + "' and nama_tempat = '" + tpt + "' order by 1";
        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String name = resultSet.getString("nama_dokter");
                    String stas = resultSet.getString("STATUS");
                    String d = resultSet.getString("hari_praktek");
                    Date tgl = resultSet.getDate("tanggal_praktek");
                    Time start = resultSet.getTime("jam_mulai_praktek");
                    Time end = resultSet.getTime("jam_selesai_praktek");
                    Integer q = resultSet.getInt("kuota_pasien");

                    Jadwal dr = new Jadwal(name, stas, d, tgl, start, end, q);

                    drs.add(dr);

                    LOGGER.log(Level.INFO, "Found {0} in database", drs);
                }

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        return drs;
    }

    @Override
    public Optional<Integer> add(Jadwal2 j) {
        String message = "Jadwal yang ditambahkan tidak boleh error";
        Jadwal2 nonNullCustomer = Objects.requireNonNull(j, message);
        String sql = "INSERT INTO "
                + "jadwal_praktek(id_tempat, id_dokter, id_jadwal, hari_praktek, jam_mulai_praktek, jam_selesai_praktek, tanggal_praktek, kuota_pasien) "
                + "VALUES(?, ?, ?,? ,?,?,?,?)";


        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement =
                         conn.prepareStatement(
                                 sql,
                                 Statement.RETURN_GENERATED_KEYS)) {

                statement.setInt(1, nonNullCustomer.getIdt());
                statement.setInt(2, nonNullCustomer.getIdD());
                statement.setInt(3, nonNullCustomer.getIdJ());
                statement.setString(4, nonNullCustomer.getHari());
                statement.setTime(5, nonNullCustomer.getJam_mulai_praktek());
                statement.setTime(6, nonNullCustomer.getJam_selesai_praktek());
                statement.setDate(7, nonNullCustomer.getTanggal_praktek());
                statement.setInt(8, nonNullCustomer.getKuotaP());

                int numberOfInsertedRows = statement.executeUpdate();

                // Retrieve the auto-generated id
                if (numberOfInsertedRows > 0) {
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            generatedId = Optional.of(resultSet.getInt(1));
                        }
                    }
                }

                LOGGER.log(
                        Level.INFO,
                        "{0} created successfully? {1}",
                        new Object[]{nonNullCustomer,
                                (numberOfInsertedRows > 0)});
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            return generatedId;
        });
    }

    @Override
    public void update(Jadwal2 a) {
        String message = "Jadwal yang di update tidak boleh kosong";
        Jadwal2 nonNullCustomer = Objects.requireNonNull(a, message);
        String sql = "UPDATE jadwal_praktek " +
                "SET id_dokter=?, id_tempat=?, id_jadwal=?, hari_praktek=?, jam_mulai_praktek=?, jam_selesai_praktek=?, tanggal_praktek=?, kuota_pasien=? " +
                "WHERE id_jadwal = ? ";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, nonNullCustomer.getIdD());
                statement.setInt(2, nonNullCustomer.getIdt());
                statement.setInt(3, nonNullCustomer.getIdJ());
                statement.setString(4, nonNullCustomer.getHari());
                statement.setTime(5, nonNullCustomer.getJam_mulai_praktek());
                statement.setTime(6, nonNullCustomer.getJam_selesai_praktek());
                statement.setDate(7, nonNullCustomer.getTanggal_praktek());
                statement.setInt(8, nonNullCustomer.getKuotaP());
                statement.setInt(9, nonNullCustomer.getIdJ());


                int numberOfUpdatedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the schedule updated successfully? {0}", numberOfUpdatedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void delete(int id_jadwal) {
        String sql = "DELETE from jadwal_praktek WHERE id_jadwal = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setInt(1, id_jadwal);

                int numberOfDeletedRows = statement.executeUpdate();

                LOGGER.log(Level.INFO, "Was the schedule deleted successfully? {0}",
                        numberOfDeletedRows > 0);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}
