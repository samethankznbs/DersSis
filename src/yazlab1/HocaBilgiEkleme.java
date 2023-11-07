package yazlab1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HocaBilgiEkleme {
    public static void main(String[] args) {
    	String dbURL = "jdbc:postgresql://localhost:5432/Db_yazlab1";
        String username = "postgres";
        String password = "12345678sk";

        try (Connection connection = DriverManager.getConnection(dbURL, username, password)) {
            // Hoca_tablo ve ders_hoca_tablo'yu birleştirerek hoca_ad ve hoca_soyad değerlerini al
            String updateQuery = "UPDATE ders_hoca_tablo AS dh " +
                "SET hoca_ad = h.hoca_ad, hoca_soyad = h.hoca_soyad " +
                "FROM hoca_tablo AS h " +
                "WHERE dh.hoca_id = h.hoca_id";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
