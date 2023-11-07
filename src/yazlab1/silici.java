package yazlab1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class silici {
    public static void main(String[] args) {
        String dbURL = "jdbc:postgresql://localhost:5432/Db_yazlab1";
        String username = "postgres";
        String password = "12345678sk";

        try (Connection connection = DriverManager.getConnection(dbURL, username, password)) {
            String deleteQuery = "DELETE FROM talep_ogrenci_tablo ";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                int rowsDeleted = preparedStatement.executeUpdate();
                
                if (rowsDeleted > 0) {
                    System.out.println(rowsDeleted + " satır silindi.");
                } else {
                    System.out.println("Silinecek satır bulunamadı.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
    }
    public void deneme2() {
    	String dbURL = "jdbc:postgresql://localhost:5432/Db_yazlab1";
        String username = "postgres";
        String password = "12345678sk";
        try (Connection connection = DriverManager.getConnection(dbURL, username, password)) {
            int ogrenci_id = 5; // Öğrenci ID'sini ayarlayın
            String selectQuery1 = "SELECT ders_id, ders_ad, hoca_id, hoca_ad, hoca_soyad FROM ders_hoca_tablo";
            String selectQuery2 = "SELECT ders_id FROM ogrenci_ders_tablo WHERE ogrenci_id = ?";
            String insertQuery = "INSERT INTO ogrenci_ders_secme_tablo (ogrenci_id, ders_id, ders_ad, hoca_id, hoca_ad, hoca_soyad) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement1 = connection.prepareStatement(selectQuery1);
            PreparedStatement preparedStatement2 = connection.prepareStatement(selectQuery2);
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

            preparedStatement2.setInt(1, ogrenci_id);
            ResultSet resultSet1 = preparedStatement1.executeQuery();

            List<String> eklenenDersler = new ArrayList<>();

            while (resultSet1.next()) {
                String ders_id = resultSet1.getString("ders_id");
                String ders_ad = resultSet1.getString("ders_ad");
                int hoca_id = resultSet1.getInt("hoca_id");
                String hoca_ad = resultSet1.getString("hoca_ad");
                String hoca_soyad = resultSet1.getString("hoca_soyad");

                preparedStatement2.setInt(1, ogrenci_id);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                boolean ogrencininDersi = false;
                while (resultSet2.next()) {
                    String ogrenciDersId = resultSet2.getString("ders_id");
                    if (ders_id.equals(ogrenciDersId)) {
                        ogrencininDersi = true;
                        break;
                    }
                }

                resultSet2.close();

                if (!ogrencininDersi && !eklenenDersler.contains(ders_id)) {
                    eklenenDersler.add(ders_id);

                    insertStatement.setInt(1, ogrenci_id);
                    insertStatement.setString(2, ders_id);
                    insertStatement.setString(3, ders_ad);
                    insertStatement.setInt(4, hoca_id);
                    insertStatement.setString(5, hoca_ad);
                    insertStatement.setString(6, hoca_soyad);

                    insertStatement.executeUpdate();
                }
            }

            preparedStatement1.close();
            preparedStatement2.close();
            insertStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    	
    	
    }
		
	
    
    public static void deneme()
    {String dbURL = "jdbc:postgresql://localhost:5432/Db_yazlab1";
    String username = "postgres";
    String password = "12345678sk";
    int ogrenci_id = 2; // Öğrenci ID'si

    try (Connection connection = DriverManager.getConnection(dbURL, username, password)) {
        String selectQuery1 = "SELECT ders_id, hoca_id, ders_ad, ders_kont, ders_akts, hoca_ad, hoca_soyad FROM ders_hoca_tablo";
        String selectQuery2 = "SELECT ders_id FROM ogrenci_ders_tablo WHERE ogrenci_id = ?";
        String insertQuery = "INSERT INTO ogrenci_secim_ders_tablo (ogrenci_id, hoca_id, ders_id, ders_ad, hoca_ad, hoca_soyad) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setInt(1, ogrenci_id); // Öğrenci ID'sini ayarla

        // Daha önce eklenen dersleri izlemek için bir liste oluşturun
        
        ArrayList<String> eklenenDersler = new ArrayList<>();

        ResultSet resultSet1 = connection.createStatement().executeQuery(selectQuery1);

        while (resultSet1.next()) {
            String ders_id1 = resultSet1.getString("ders_id");
            String ders_ad = resultSet1.getString("ders_ad");
            int hoca_id = resultSet1.getInt("hoca_id");
            String hoca_ad = resultSet1.getString("hoca_ad");
            String hoca_soyad = resultSet1.getString("hoca_soyad");

            boolean existsInTable2 = false;

            // Öğrencinin aldığı dersleri sorgula
            PreparedStatement preparedStatement2 = connection.prepareStatement(selectQuery2);
            preparedStatement2.setInt(1, ogrenci_id);
            ResultSet resultSet2 = preparedStatement2.executeQuery();

            while (resultSet2.next()) {
                String ders_id2 = resultSet2.getString("ders_id");

                if (ders_id1.equals(ders_id2)) {
                    existsInTable2 = true;
                    break;
                }
            }

            resultSet2.close();
            preparedStatement2.close();

            // Daha önce eklenmediyse ve daha önce eklenenler listesinde değilse, ekleyin ve listeye eklenenleri ekleyin
            if (!existsInTable2 && !eklenenDersler.contains(ders_id1)) {
                eklenenDersler.add(ders_id1);

                // Öğrenci ve hoca ID'lerini uygun şekilde ayarla
                preparedStatement.setInt(2, hoca_id);
                preparedStatement.setString(3, ders_id1);
                preparedStatement.setString(4, ders_ad);
                preparedStatement.setString(5, hoca_ad);
                preparedStatement.setString(6, hoca_soyad);
                preparedStatement.executeUpdate();
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    
    
    
    
}
