package yazlab1;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.DefaultListModel;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class ogrenci_anasayfa extends JFrame {

    private JPanel contentPane;
    private JPanel sidebarOgrenci;
    private JTextField textField_OgrenciAd;
    private JTextField textField_OgrenciSifre;
    private JLabel lblNewLabel;
    private JLabel lblSoyad;
    private JLabel lblNumara;
    private JLabel lblNewLabelFoto;
    private JButton btnNewButton;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnNewButton_1;
    private JLabel lblAgno;
    private Integer kisi_id; 
    private String agno;
    private Integer sure;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ogrenci_anasayfa frame = new ogrenci_anasayfa();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @param sifre1 
     * @param soyad 
     * @param ad 
     */
    public ogrenci_anasayfa(int sifre1, String ad, String soyad) {
    	 String dbURL = "jdbc:postgresql://localhost:5432/Db_yazlab1";
         String username = "postgres";
         String password = "12345678sk";
         try {
             
             Connection connection = DriverManager.getConnection(dbURL, username, password);
             Statement statement = connection.createStatement();

             String query = "SELECT * FROM ogrenci_tablo WHERE ogrenci_id="+sifre1;
             ResultSet resultSet = statement.executeQuery(query);

             // Veritabanından verileri alarak tabloya ekle
             while (resultSet.next()) {
                 
                 agno = resultSet.getString("ogrenci_agno");
              }

             connection.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
         
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1755, 1082);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(227, 208, 249));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        


        // Sidebar oluştur
        sidebarOgrenci = new JPanel();
        sidebarOgrenci.setBackground(new Color(160, 147, 219));
        sidebarOgrenci.setBounds(0, 0, 216, 1035);// Kenar çubuğunun boyutunu ayarlayın
          contentPane.add(sidebarOgrenci);
          sidebarOgrenci.setLayout(null);
          
          JButton buttonResim = new JButton("Icon");
          Image img = new ImageIcon(yonetici.class.getResource("/img.png")).getImage();
          buttonResim.setIcon(new ImageIcon(img));
          sidebarOgrenci.add(buttonResim);
          
          lblNewLabel = new JLabel(ad);
          lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
          lblNewLabel.setForeground(new Color(227, 208, 249));
          lblNewLabel.setBounds(6, 146, 152, 31);
          sidebarOgrenci.add(lblNewLabel);
          
          lblSoyad = new JLabel(soyad);
          lblSoyad.setFont(new Font("Tahoma", Font.PLAIN, 16));
          lblSoyad.setForeground(new Color(227, 208, 249));
          lblSoyad.setBounds(6, 187, 152, 31);
          sidebarOgrenci.add(lblSoyad);
          String idString=String.valueOf(sifre1);
          
          lblNumara = new JLabel(idString);
          lblNumara.setFont(new Font("Tahoma", Font.PLAIN, 16));
          lblNumara.setForeground(new Color(227, 208, 249));
          lblNumara.setBounds(6, 228, 152, 31);
          sidebarOgrenci.add(lblNumara);
          
          lblAgno = new JLabel(agno);
          lblAgno.setFont(new Font("Tahoma", Font.PLAIN, 16));
          lblAgno.setBackground(Color.white);
          lblAgno.setForeground(new Color(227, 208, 249));
          lblAgno.setBounds(6, 269, 152, 31);
          sidebarOgrenci.add(lblAgno);
          
          
          lblNewLabelFoto= new JLabel("New label");
          Image img2 = new ImageIcon(this.getClass().getResource("/img.png")).getImage();
          lblNewLabelFoto .setIcon(new ImageIcon(img));
          lblNewLabelFoto.setBounds(29, 16, 95, 89);
          sidebarOgrenci.add(lblNewLabelFoto);
          
          btnNewButton = new JButton("Transkript Yükle");
          btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
          btnNewButton.setBackground(new Color(255, 255, 255));
          btnNewButton.setForeground(new Color(128, 128, 255));
          btnNewButton.setBounds(6, 373, 200, 29);
          sidebarOgrenci.add(btnNewButton);
          btnNewButton.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				ogrenci_anasayfa.super.setVisible(false);
  				ogrenci_transkript_arayuz ogrenci_transkript_arayuz = new ogrenci_transkript_arayuz(sifre1, ad, soyad);
  				ogrenci_transkript_arayuz.setVisible(true);
  				
  			}
  		});
          
          btnNewButton_1 = new JButton("Talep Oluştur");
          btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
          btnNewButton_1.setBackground(new Color(255, 255, 255));
          btnNewButton_1.setForeground(new Color(128, 128, 255));
          btnNewButton_1.setBounds(6, 412, 200, 29);
          sidebarOgrenci.add(btnNewButton_1);
          
        
          btnNewButton_1.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
      				ogrenci_anasayfa.super.setVisible(false);
      				ogrenci_talep ogrenci_talep = new ogrenci_talep(sifre1,ad,soyad) ;
      				
      				ogrenci_talep.setVisible(true);
      				
      			}
      		});
          
        
         tableModel = new DefaultTableModel();
         
         tableModel.addColumn("Ders ID ");
         tableModel.addColumn("Harf Notu");
         tableModel.addColumn("Ders Adı");
         tableModel.addColumn("Ders AKTS");
         tableModel.addColumn("Hoca ID");
         tableModel.addColumn("Hoca Ad");
         tableModel.addColumn("Hoca Soyad");
         try {
             
             Connection connection = DriverManager.getConnection(dbURL, username, password);
             Statement statement = connection.createStatement();

             String query = "SELECT * FROM ogrenci_ders_tablo WHERE ogrenci_id="+sifre1;
             ResultSet resultSet = statement.executeQuery(query);

             // Veritabanından verileri alarak tabloya ekle
             while (resultSet.next()) {
                 
                 String dersID = resultSet.getString("ders_id");
                 String harfNot = resultSet.getString("harf_not");
                 String dersAd = resultSet.getString("ders_ad");
                 int dersAKTS = resultSet.getInt("ders_akts");
                 int hoca_id=resultSet.getInt("hoca_id");
                 String hoca_adString=resultSet.getString("hoca_ad");
                 String hoca_soyadString=resultSet.getString("hoca_soyad");

                 tableModel.addRow(new Object[]{dersID, harfNot, dersAd, dersAKTS,hoca_id,hoca_adString,hoca_soyadString});
             }

             connection.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
         table = new JTable(tableModel);
         table.setBackground(new Color(227, 208, 249));
         JScrollPane scrollPane = new JScrollPane(table);
         scrollPane.setBackground(new Color(227, 208, 249));
         scrollPane.setBounds(222, 10, 1487, 746);
         contentPane.add(scrollPane);

         int ogrenciid = sifre1; // Öğrenci ID'sini ayarlayın

        
        
         try (Connection connection1 = DriverManager.getConnection(dbURL, username, password)) {
             String selectQuery = "SELECT COUNT(*) FROM ogrenci_ders_tablo WHERE ogrenci_id = ?";
             PreparedStatement preparedStatement = connection1.prepareStatement(selectQuery);
             preparedStatement.setInt(1, ogrenciid);

             ResultSet resultSet = preparedStatement.executeQuery();

             if (resultSet.next()) {
                 int rowCount = resultSet.getInt(1);
                 if (rowCount == 0) {
                     System.out.println("Öğrencinin hiçbir satıra sahip olmadığı görünüyor.");
                     
                 } else {
                	 try (Connection connection = DriverManager.getConnection(dbURL, username, password)) {
                		    int ogrenci_id = sifre1; // Öğrenci ID'sini ayarlayın
                		    String selectQuery1 = "SELECT ders_id, hoca_id, ders_ad, ders_kont, ders_akts, hoca_ad, hoca_soyad FROM ders_hoca_tablo";
                		    String selectQuery2 = "SELECT ders_id FROM ogrenci_ders_tablo WHERE ogrenci_id = ?";
                		    String insertQuery = "INSERT INTO ogrenci_secim_ders_tablo (ogrenci_id, hoca_id, ders_id, ders_ad, hoca_ad, hoca_soyad,ogrenci_ad,ogrenci_soyad,ders_akts) VALUES (?,?,?,?, ?, ?, ?, ?, ?)";

                		    PreparedStatement preparedStatement1 = connection.prepareStatement(selectQuery1);
                		    PreparedStatement preparedStatement2 = connection.prepareStatement(selectQuery2);
                		    PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

                		    preparedStatement2.setInt(1, ogrenci_id);
                		    ResultSet resultSet1 = preparedStatement1.executeQuery();

                		    ArrayList<String> eklenenDersler = new ArrayList<>();
                		    HashSet<String> ogrencininDersleri = new HashSet<>();

                		    while (resultSet1.next()) {
                		        String ders_id = resultSet1.getString("ders_id");

                		        // Eğer bu ders zaten öğrencinin dersleri arasındaysa atla
                		        if (ogrencininDersleri.contains(ders_id)) {
                		            continue;
                		        }

                		        String ders_ad = resultSet1.getString("ders_ad");
                		        int hoca_id = resultSet1.getInt("hoca_id");
                		        String hoca_ad = resultSet1.getString("hoca_ad");
                		        String hoca_soyad = resultSet1.getString("hoca_soyad");
                		        String ders_akts=resultSet1.getString("ders_akts");

                		        preparedStatement2.setInt(1, ogrenci_id);
                		        ResultSet resultSet2 = preparedStatement2.executeQuery();

                		        boolean ogrencininDersi = false;
                		        while (resultSet2.next()) {
                		            String ogrenciDersId = resultSet2.getString("ders_id");
                		            ogrencininDersleri.add(ogrenciDersId); // Öğrencinin derslerini set'e ekleyin

                		            if (ders_id.equals(ogrenciDersId)) {
                		                ogrencininDersi = true;
                		                break;
                		            }
                		        }

                		        resultSet2.close();

                		        if (!ogrencininDersi) {
                		            // Veritabanı tablosunda aynı satırın var olup olmadığını burada kontrol edebilirsiniz
                		            if (!isRowExistInDatabase(ogrenci_id, ders_id, hoca_id,connection)) {
                		                eklenenDersler.add(ders_id);

                		                insertStatement.setInt(1, ogrenci_id);
                		                insertStatement.setInt(2, hoca_id);
                		                insertStatement.setString(3, ders_id);
                		                insertStatement.setString(4, ders_ad);
                		                insertStatement.setString(5, hoca_ad);
                		                insertStatement.setString(6, hoca_soyad);
                		                insertStatement.setString(7, ad);
                		                insertStatement.setString(8, soyad);
                		                insertStatement.setString(9, ders_akts);
                		                insertStatement.executeUpdate();
                		            }
                		        }
                		    }

                		    preparedStatement1.close();
                		    preparedStatement2.close();
                		    insertStatement.close();

                		} catch (SQLException e) {
                		    e.printStackTrace();
                		}
                    
                 }
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         JButton btnGeri = new JButton("Geri");
         btnGeri.setFont(new Font("Tahoma", Font.PLAIN, 16));
         btnGeri.setForeground(new Color(160, 147, 219));
         btnGeri.setBackground(new Color(255, 255, 255));
         btnGeri.setBounds(6, 983, 200, 29);
         btnGeri.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		ogrenci_anasayfa.super.setVisible(false);
         		ogrenci ogrenci = new ogrenci();
         		ogrenci.setVisible(true);
         	}
         });
        ArrayList<String> ilgiAlaniads = new ArrayList<>();
         sidebarOgrenci.add(btnGeri);
         try (Connection connection = DriverManager.getConnection(dbURL, username, password)) {
             if (connection != null) {
                 System.out.println("Veritabanına bağlandı!");

                  // 

                 // Öğrencinin ilgi alanı ID'lerini çek
                 String query = "SELECT ilgialani_ad FROM ogrenci_ilgialani_tablo WHERE ogrenci_id = ?";
                 try (PreparedStatement statement = connection.prepareStatement(query)) {
                     statement.setInt(1, sifre1);
                     ResultSet resultSet = statement.executeQuery();

                     while (resultSet.next()) {
                         String ilgiAlaniad = resultSet.getString("ilgialani_ad");
                         ilgiAlaniads.add(ilgiAlaniad);
                     }
                 }

                 System.out.println("Öğrencinin ilgi alanları: " + ilgiAlaniads);
             }
         } catch (SQLException e) {
             System.err.println("Veritabanı hatası: " + e.getMessage());
         }
         
         JLabel lblIlgıAlanı = new JLabel("İlgi Alanları");
         lblIlgıAlanı.setFont(new Font("Tahoma", Font.PLAIN, 16));
         lblIlgıAlanı.setForeground(new Color(227, 208, 249));
         lblIlgıAlanı.setBounds(6, 310, 152, 31);
         sidebarOgrenci.add(lblIlgıAlanı);
         
         
        
         
         
         DefaultListModel<String> model = new DefaultListModel<>();
         for (String ilgiAlaniAd : ilgiAlaniads) {
             model.addElement(ilgiAlaniAd);
         }

         // JList oluşturun ve DefaultListModel ile doldurun
         JList<String> ilgiAlanlariList = new JList<>(model);

         // JScrollPane oluşturun ve JList'i içine yerleştirin
         JScrollPane scrollPane1 = new JScrollPane(ilgiAlanlariList);
         scrollPane1.setBounds(101, 294, 105, 72);
         sidebarOgrenci.add(scrollPane1);
        
         
         

        
        
         
         
        	 
         
        
         
         

       
      
    }
    private static boolean isRowExistInDatabase(int ogrenci_id, String ders_id,int hoca_id ,Connection connection) throws SQLException {
        String selectQuery = "SELECT COUNT(*) FROM ogrenci_secim_ders_tablo WHERE ogrenci_id = ? AND ders_id = ? AND hoca_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, ogrenci_id);
        preparedStatement.setString(2, ders_id);
        preparedStatement.setInt(3, hoca_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int rowCount = resultSet.getInt(1);
        preparedStatement.close();
        return rowCount > 0;
    }
}