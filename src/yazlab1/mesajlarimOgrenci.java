package yazlab1;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Font;

public class mesajlarimOgrenci extends JFrame {

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
    private int ogrenci_id;
    private String ders_id;
    private String ders_ad;
    private String ogrenci_ad;
    private String ogrenci_soyad;
    private int hocaId;
    private int talep_durum;

    
    private String hoca_ad;
    private String hoca_soyad;
    private String ders_akts;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    mesajlarimOgrenci frame = new mesajlarimOgrenci();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @param soyad 
     * @param ad 
     * @param sifre1 
     */
    public mesajlarimOgrenci(int sifre1, String ad, String soyad) {
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1752, 1086);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(227, 208, 249));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

    
        sidebarOgrenci = new JPanel();
        sidebarOgrenci.setBackground(new Color(160, 147, 219));
        sidebarOgrenci.setBounds(0, 0, 215, 1039);
    
          contentPane.add(sidebarOgrenci);
          sidebarOgrenci.setLayout(null);
          
          JButton buttonResim = new JButton("Icon");
          Image img = new ImageIcon(yonetici.class.getResource("/img.png")).getImage();
          buttonResim.setIcon(new ImageIcon(img));
          sidebarOgrenci.add(buttonResim);
          
        
          
          lblNewLabelFoto= new JLabel("New label");
          Image img2 = new ImageIcon(this.getClass().getResource("/img.png")).getImage();
          lblNewLabelFoto .setIcon(new ImageIcon(img));
          lblNewLabelFoto.setBounds(53, 10, 95, 89);
          sidebarOgrenci.add(lblNewLabelFoto);
          

          tableModel = new DefaultTableModel();
          tableModel.addColumn("Hoca ID");
          tableModel.addColumn("Ders ID ");
          tableModel.addColumn("Ders Adı");
          tableModel.addColumn("Hoca Adı");
          tableModel.addColumn("Hoca Soyadı");
          tableModel.addColumn("Talep Mesajı");
          tableModel.addColumn("Talep Durum");
          tableModel.addColumn("Ders AKTS");


          table = new JTable(tableModel);
          table.setBackground(new Color(227, 208, 249));
          JScrollPane scrollPane = new JScrollPane(table);
          scrollPane.setBackground(new Color(227, 208, 249));
          scrollPane.setBounds(233, 24, 1481, 477);
          contentPane.add(scrollPane);
         JButton btnGeri = new JButton("Geri");
         btnGeri.setFont(new Font("Tahoma", Font.PLAIN, 16));
         btnGeri.setForeground(new Color(172, 162, 249));
         btnGeri.setBounds(10, 985, 195, 29);
         btnGeri.addActionListener(new ActionListener() {
           	public void actionPerformed(ActionEvent e) {
           		mesajlarimOgrenci.super.setVisible(false);
           		ogrenci_anasayfa ogrenci_anasayfa = new ogrenci_anasayfa(sifre1,ad,soyad);
           		ogrenci_anasayfa.setVisible(true);
           	}
           });
         sidebarOgrenci.add(btnGeri);
         
         String dbURL = "jdbc:postgresql://localhost:5432/Db_yazlab1";
         String username = "postgres";
         String password = "12345678sk";
        ////// 
         	try {
             
             Connection connection = DriverManager.getConnection(dbURL, username, password);
             Statement statement = connection.createStatement();

             String query = "SELECT * FROM talep_hoca_tablo WHERE alici_ogrenci_id="+sifre1;
             ResultSet resultSet = statement.executeQuery(query);

             // Veritabanından verileri alarak tabloya ekle
             while (resultSet.next()) {
                 
                 
                 hocaId = resultSet.getInt("gonderen_hoca_id");
                 ders_id =resultSet.getString("ders_id");
                 ders_ad =resultSet.getString("ders_ad");
                 hoca_ad =resultSet.getString("hoca_ad");
                 hoca_soyad =resultSet.getString("hoca_soyad");
                 String mesajString =resultSet.getString("talep_mesaj");
                 talep_durum =resultSet.getInt("talep_durum");
                 ders_akts=resultSet.getString("ders_akts");

                 
                 System.out.println(mesajString);
                 
                 

                 tableModel.addRow(new Object[]{hocaId, ders_id, ders_ad, hoca_ad,hoca_soyad,mesajString,talep_durum,ders_akts});
             }

             connection.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
         
         ListSelectionModel selectionModel = table.getSelectionModel();
         selectionModel.addListSelectionListener(new ListSelectionListener() {
             public void valueChanged(ListSelectionEvent e) {
                 if (!e.getValueIsAdjusting()) {
                     int selectedRow = table.getSelectedRow();
                     // Seçilen satırın indisini alın ve bu indis üzerinde işlem yapabilirsiniz.
                 }
             }
         });
         
         
         
         
         JButton btnNewButtonKabul = new JButton("Kabul Ediyorum");
         btnNewButtonKabul.setFont(new Font("Tahoma", Font.PLAIN, 16));
         btnNewButtonKabul.setForeground(new Color(172, 162, 249));
         btnNewButtonKabul.setBounds(288, 549, 382, 52);
         
         btnNewButtonKabul.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 if (talep_durum==1) {
            		 try {
                         
                         Connection connection = DriverManager.getConnection(dbURL, username, password);
                         Statement statement = connection.createStatement();

                         String query = "UPDATE talep_hoca_tablo SET talep_durum = 2 WHERE gonderen_hoca_id = ? AND ders_id = ? AND alici_ogrenci_id = ?";
                         PreparedStatement preparedStatement = connection.prepareStatement(query);

                        
                         // Parametre değerlerini belirle
                         preparedStatement.setInt(1, hocaId);
                         preparedStatement.setString(2, ders_id);
                         preparedStatement.setInt(3, sifre1);
                         int rowsUpdated = preparedStatement.executeUpdate();

                         if (rowsUpdated > 0) {
                             System.out.println("Satır(lar) başarıyla güncellendi.");
                         } else {
                             System.out.println("Güncellenecek satır bulunamadı.");
                         }

                        
                     } catch (Exception a) {
                         a.printStackTrace();
                     }
                     try {
                         
                         Connection connection = DriverManager.getConnection(dbURL, username, password);
                         Statement statement = connection.createStatement();

                         String query = "DELETE FROM ogrenci_secim_ders_tablo WHERE ogrenci_id = ? AND ders_id = ? ";
                         PreparedStatement preparedStatement = connection.prepareStatement(query);

                       
                         // Parametre değerlerini belirle
                         preparedStatement.setInt(1, sifre1);
                         preparedStatement.setString(2, ders_id);
                         
                         int rowsUpdated = preparedStatement.executeUpdate();

                         if (rowsUpdated > 0) {
                             System.out.println("Satır(lar) başarıyla güncellendi.");
                         } else {
                             System.out.println("Güncellenecek satır bulunamadı.");
                         }

                        
                     } catch (Exception a) {
                         a.printStackTrace();
                     }
                     try {
             		    Connection connection = DriverManager.getConnection(dbURL, username, password);
             		    
             		    String query = "INSERT INTO ogrenci_ders_tablo (ogrenci_id, ders_id, harf_not, ders_ad, ders_akts, hoca_id, hoca_ad, hoca_soyad) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
             		    PreparedStatement preparedStatement = connection.prepareStatement(query);
             		    
             		    // Parametre değerlerini belirle
             		    preparedStatement.setInt(1, sifre1);
             		    preparedStatement.setString(2, ders_id);
             		    preparedStatement.setString(3, null);
             		    preparedStatement.setString(4, ders_ad);
             		    preparedStatement.setString(5, ders_akts);
             		    preparedStatement.setInt(6, hocaId);
             		    preparedStatement.setString(7, hoca_ad);
             		    preparedStatement.setString(8, hoca_soyad);
             		    
             		    int rowsInserted = preparedStatement.executeUpdate();

             		    if (rowsInserted > 0) {
             		        System.out.println("Satır başarıyla eklendi.");
             		    } else {
             		        System.out.println("Satır eklenemedi.");
             		    }
             		    
             		    connection.close(); // Bağlantıyı kapatmayı unutmayın.
             		} catch (Exception b) {
             		    b.printStackTrace();
             		}
				}
            	 else {
					System.out.println("Derse Cevap verilmiş");
				}
            	
             }
         });
         contentPane.add(btnNewButtonKabul);
         JLabel lblNewLabel_mesaj = new JLabel("Hocanızdan gelen mesaj:");
         lblNewLabel_mesaj.setFont(new Font("Tahoma", Font.PLAIN, 16));
         lblNewLabel_mesaj.setForeground(new Color(162, 149, 255));
         lblNewLabel_mesaj.setBounds(288, 691, 278, 16);
         contentPane.add(lblNewLabel_mesaj);
         
      
         
         JTextArea textAreaMesajıSec = new JTextArea();
         textAreaMesajıSec.setBounds(288, 735, 488, 225);
         contentPane.add(textAreaMesajıSec);
         
         ListSelectionModel selectionModel1 = table.getSelectionModel();
         selectionModel1.addListSelectionListener(new ListSelectionListener() {
             public void valueChanged(ListSelectionEvent e) {
                 if (!e.getValueIsAdjusting()) {
                     int selectedRow = table.getSelectedRow();
                     if (selectedRow >= 0) {
                         // Seçilen satırın indisini alın
                         int selectedRow1 = table.getSelectedRow();
                         
                         // Seçilen satırın verilerini alın
                         Object mesajObject = tableModel.getValueAt(selectedRow1, 5);
                         
                         
                         // Verileri textAreaMesajıSec alanına yazdırın
                         textAreaMesajıSec.setText((String) mesajObject);
                     }
                 }
             }
         });
      
         
      
         
   
       
      
    }
}
