package yazlab1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
    	 String dbURL = "jdbc:postgresql://localhost:5432/Db_yazlab1";
         String username = "postgres";
         String password = "12345678sk";

        try (Connection connection = DriverManager.getConnection(dbURL, username, password)) {
            if (connection != null) {
                System.out.println("Veritabanına bağlandı!");

                List<Integer> hocaIDs = new ArrayList<>(); // Hoca ID'lerini tutan liste
                List<dersim> dersler = new ArrayList<>(); // Ilgi alanlari nesnelerini tutan liste

                // Hoca ID'leri ve İlgi Alanı bilgileri veritabanından çekilir.
                String hocaQuery = "SELECT hoca_id FROM hoca_tablo";
                String ilgialaniQuery = "SELECT ders_id, ders_ad FROM ders_tablo";

                try (PreparedStatement hocaStatement = connection.prepareStatement(hocaQuery);
                     PreparedStatement ilgialaniStatement = connection.prepareStatement(ilgialaniQuery)) {
                    ResultSet hocaResult = hocaStatement.executeQuery();
                    ResultSet dersResult = ilgialaniStatement.executeQuery();

                    while (hocaResult.next()) {
                        hocaIDs.add(hocaResult.getInt("hoca_id"));
                    }

                    while (dersResult.next()) {
                        String dersID = dersResult.getString("ders_id");
                        String dersAd = dersResult.getString("ders_ad");
                        dersim ders = new dersim(dersID, dersAd);
                        dersler.add(ders);
                    }
                }
                System.out.println(hocaIDs);

                Random random = new Random();
                String insertQuery = "INSERT INTO kriter_ders_tablo ( kriter_hoca_id,kriter_ders_id,kriter_ders_ad) VALUES (?, ?, ?)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                    for (int hocaID : hocaIDs) {
                    	Collections.shuffle(dersler);
                        // İlgili hoca için rastgele sayıda ilgi alanı seç
                        int ilgialaniSayisi = random.nextInt(5)+1;
                        System.out.println(ilgialaniSayisi);

                        for (int i = 0; i < ilgialaniSayisi; i++) {
                            dersim ders = dersler.get(i);
                            insertStatement.setString(2, ders.getId());
                            insertStatement.setInt(1, hocaID);
                            insertStatement.setString(3, ders.getAd());
                            insertStatement.executeUpdate();
                        }
                    }
                }

                System.out.println("Hocalara rastgele dersler eklendi ve ilgi alanı adları da eklendi.");
            }
        } catch (SQLException e) {
            System.err.println("Veritabanı hatası: " + e.getMessage());
        }
    }
}

class dersim {
    private String id;
    private String ad;

    public dersim(String id, String ad) {
        this.id = id;
        this.ad = ad;
    }

    public String getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }
}

