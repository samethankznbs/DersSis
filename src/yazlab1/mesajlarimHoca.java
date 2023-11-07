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

public class mesajlarimHoca extends JFrame {

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
    private JButton btnDersList;
    private JButton btnOgrenciList;
    private JButton btnGeri;
    private int ogrenci_id;
    private String ders_id;
    private String ders_ad;
    private String ogrenci_ad;
    private String ogrenci_soyad;
    private int talep_durum;

    private String hoca_ad;
    private String ders_akts;
    

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    mesajlarimHoca frame = new mesajlarimHoca();
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
    public mesajlarimHoca(int sifre1, String ad, String soyad) {
    	String dbURL = "jdbc:postgresql://localhost:5432/Db_yazlab1";
        String username = "postgres";
        String password = "12345678sk";
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1754, 1086);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(227, 208, 249));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

    
        sidebarOgrenci = new JPanel();
        sidebarOgrenci.setBackground(new Color(160, 147, 219));
        sidebarOgrenci.setBounds(0, 0, 214, 1049);
    
          contentPane.add(sidebarOgrenci);
          sidebarOgrenci.setLayout(null);
          
          JButton buttonResim = new JButton("Icon");
          Image img = new ImageIcon(yonetici.class.getResource("/img.png")).getImage();
          buttonResim.setIcon(new ImageIcon(img));
          sidebarOgrenci.add(buttonResim);
          
        
          
          lblNewLabelFoto= new JLabel("New label");
          Image img2 = new ImageIcon(this.getClass().getResource("/img.png")).getImage();
          lblNewLabelFoto .setIcon(new ImageIcon(img));
          lblNewLabelFoto.setBounds(29, 16, 95, 89);
          sidebarOgrenci.add(lblNewLabelFoto);
          
          btnDersList = new JButton("Ders Listesi");
          btnDersList.setFont(new Font("Tahoma", Font.PLAIN, 14));
          btnDersList.setForeground(SystemColor.textInactiveText);
          btnDersList.setBounds(17, 144, 168, 29);
          
          
          
          btnDersList.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
            	   mesajlarimHoca.super.setVisible(false);
            	   hoca_AnasayfaDersList hoca_AnasayfaDersList = new hoca_AnasayfaDersList(sifre1,ad,soyad);
            	   hoca_AnasayfaDersList.setVisible(true);
              }
          });
          
          sidebarOgrenci.add(btnDersList);
          
          btnOgrenciList = new JButton("Öğrenci Listesi");
          btnOgrenciList.setFont(new Font("Tahoma", Font.PLAIN, 14));
          btnOgrenciList.setForeground(SystemColor.textInactiveText);
          btnOgrenciList.setBounds(17, 185, 168, 29);
          
          btnOgrenciList.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
            	  
           mesajlarimHoca.super.setVisible(false);
           hoca_AnasayfaOgrenciList hoca_AnasayfaOgrenciList = new hoca_AnasayfaOgrenciList(sifre1, ad, soyad);
           hoca_AnasayfaOgrenciList.setVisible(true);
              }
          });
          
          
          
          
          
          sidebarOgrenci.add(btnOgrenciList);
          
          btnGeri = new JButton("Geri");
          btnGeri.setFont(new Font("Tahoma", Font.PLAIN, 14));
          btnGeri.setForeground(SystemColor.textInactiveText);
          btnGeri.setBounds(29, 990, 156, 29);
          btnGeri.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		mesajlarimHoca.super.setVisible(false);
            		hoca_AnasayfaDersList hoca_AnasayfaDersList = new hoca_AnasayfaDersList(sifre1, ad, soyad);
            		hoca_AnasayfaDersList.setVisible(true);
            	}
            });
          sidebarOgrenci.add(btnGeri);
          

        
         tableModel = new DefaultTableModel();
         tableModel.addColumn("Öğrenci ID");
         tableModel.addColumn("Ders ID ");
         tableModel.addColumn("Ders Adı");
         tableModel.addColumn("Öğrenci Adı");
         tableModel.addColumn("Öğrenci Soyadı");
         tableModel.addColumn("Talep Mesajı");
         tableModel.addColumn("Talep Durum");
         tableModel.addColumn("Ders AKTS");

         table = new JTable(tableModel);
         table.setBackground(new Color(227, 208, 249));
         JScrollPane scrollPane = new JScrollPane(table);
         scrollPane.setBackground(new Color(227, 208, 249));
         scrollPane.setBounds(224, 10, 1480, 534);
         contentPane.add(scrollPane);
         try {
             
             Connection connection = DriverManager.getConnection(dbURL, username, password);
             Statement statement = connection.createStatement();

             String query = "SELECT * FROM talep_ogrenci_tablo WHERE hoca_alici_id="+sifre1;
             ResultSet resultSet = statement.executeQuery(query);

             // Veritabanından verileri alarak tabloya ekle
             while (resultSet.next()) {
                 
                 
                 int ogrenciId = resultSet.getInt("ogrenci_gonderen_id");
                 String dersIdString =resultSet.getString("ders_id");
                 String dersadString =resultSet.getString("ders_ad");
                 String ogrenciadString =resultSet.getString("ogrenci_ad");
                 String ogrencisoyadString =resultSet.getString("ogrenci_soyad");
                 String mesajString =resultSet.getString("talep_mesaj");
                 int durumString =resultSet.getInt("talep_durum");
                 String ders_aktString=resultSet.getString("ders_akts");
                 
                 System.out.println(mesajString);
                 
                 

                 tableModel.addRow(new Object[]{ogrenciId, dersIdString, dersadString, ogrenciadString,ogrencisoyadString,mesajString,durumString,ders_aktString});
             }

             connection.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
         
        
        ////// 
         
         ListSelectionModel selectionModel = table.getSelectionModel();
         selectionModel.addListSelectionListener(new ListSelectionListener() {
             public void valueChanged(ListSelectionEvent e) {
                 if (!e.getValueIsAdjusting()) {
                     int selectedRow = table.getSelectedRow();
                     if (selectedRow != -1) {
                         Object ogreciIdObject = tableModel.getValueAt(selectedRow, 0);
                         Object dersIdObject = tableModel.getValueAt(selectedRow, 1);
                         Object dersAdObject =tableModel.getValueAt(selectedRow, 2);
                         Object ogrenciAdObject =tableModel.getValueAt(selectedRow, 3);
                         Object ogrenciSoyAdObject =tableModel.getValueAt(selectedRow, 4);
                         Object talepObject = tableModel.getValueAt(selectedRow, 6);
                         Object dersaktsObject=tableModel.getValueAt(selectedRow, 7);

                         if ( ogreciIdObject instanceof Integer && dersIdObject instanceof String && dersAdObject instanceof String && ogrenciAdObject instanceof String && ogrenciSoyAdObject instanceof String) {
                        	 ogrenci_id = (Integer) ogreciIdObject;
                             ders_id = (String) dersIdObject;
                             ders_ad=(String) dersAdObject;
                             ogrenci_ad=(String) ogrenciAdObject;
                             ogrenci_soyad=(String) ogrenciSoyAdObject;
                             ders_akts=(String)dersaktsObject;
                             talep_durum=(int)talepObject;
                             
                             
                         } else {
                             // Uygun tür dönüşümü yapılamadı
                             // Hata işlemleri burada
                         }
                     }
                 
                 
                 
                 }
             }
         });
         
         
         
         
         JButton btnNewButtonKabul = new JButton("Kabul Ediyorum");
         btnNewButtonKabul.setFont(new Font("Tahoma", Font.PLAIN, 16));
         btnNewButtonKabul.setForeground(new Color(172, 162, 249));
         btnNewButtonKabul.setBounds(286, 571, 240, 29);
         
         btnNewButtonKabul.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 if(talep_durum==1)
            	 {
            		 try {
                         
                         Connection connection = DriverManager.getConnection(dbURL, username, password);
                         Statement statement = connection.createStatement();

                         String query = "UPDATE talep_ogrenci_tablo SET talep_durum = 2 WHERE hoca_alici_id = ? AND ders_id = ? AND ogrenci_gonderen_id = ?";
                         PreparedStatement preparedStatement = connection.prepareStatement(query);

                         System.out.println(sifre1 +" "+ ders_id+ " "+ogrenci_id);
                         // Parametre değerlerini belirle
                         preparedStatement.setInt(1, sifre1);
                         preparedStatement.setString(2, ders_id);
                         preparedStatement.setInt(3, ogrenci_id);
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
                         preparedStatement.setInt(1, ogrenci_id);
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
             		    preparedStatement.setInt(1, ogrenci_id);
             		    preparedStatement.setString(2, ders_id);
             		    preparedStatement.setString(3, null);
             		    preparedStatement.setString(4, ders_ad);
             		    preparedStatement.setString(5, ders_akts);
             		    preparedStatement.setInt(6, sifre1);
             		    preparedStatement.setString(7, ad);
             		    preparedStatement.setString(8, soyad);
             		    
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
            	 }else {
					System.out.println("Ders için cevap gönderilmis");
				}
            	 
            	 
            	 

            	 
                
             }
         });
         contentPane.add(btnNewButtonKabul);
         
         
    
         
         JButton btnRed = new JButton("Red Ediyorum ");
         btnRed.setFont(new Font("Tahoma", Font.PLAIN, 16));
         btnRed.setForeground(new Color(174, 162, 255));
         btnRed.setBounds(571, 571, 263, 29);
         btnRed.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 if(talep_durum==1)
            	 {
            		 try {
                         
                         Connection connection = DriverManager.getConnection(dbURL, username, password);
                         Statement statement = connection.createStatement();

                         String query = "UPDATE talep_ogrenci_tablo SET talep_durum = 3 WHERE hoca_alici_id = ? AND ders_id = ? AND ogrenci_gonderen_id = ?";
                         PreparedStatement preparedStatement = connection.prepareStatement(query);

                         System.out.println(sifre1 +" "+ ders_id+ " "+ogrenci_id);
                         // Parametre değerlerini belirle
                         preparedStatement.setInt(1, sifre1);
                         preparedStatement.setString(2, ders_id);
                         preparedStatement.setInt(3, ogrenci_id);
                         int rowsUpdated = preparedStatement.executeUpdate();

                         if (rowsUpdated > 0) {
                             System.out.println("Satır(lar) başarıyla güncellendi.");
                         } else {
                             System.out.println("Güncellenecek satır bulunamadı.");
                         }

                        
                     } catch (Exception a) {
                         a.printStackTrace();
                     }
            	 }
            	 else {
					System.out.println("Ders için cevap gönderilmis");
				}
            
             
                
                
             }
         });
         contentPane.add(btnRed);
         
         JLabel lblNewLabel_mesaj = new JLabel("Öğrenciden gelen mesaj:");
         lblNewLabel_mesaj.setFont(new Font("Tahoma", Font.PLAIN, 16));
         lblNewLabel_mesaj.setForeground(new Color(162, 149, 255));
         lblNewLabel_mesaj.setBounds(297, 629, 277, 36);
         contentPane.add(lblNewLabel_mesaj);
         
      
         
         JTextArea textAreaMesajıSec = new JTextArea();
         textAreaMesajıSec.setFont(new Font("Monospaced", Font.PLAIN, 12));
         textAreaMesajıSec.setBounds(286, 674, 516, 232);
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
                         Object mesaj = tableModel.getValueAt(selectedRow1, 5);
                        
                         
                         
                         // Verileri textAreaMesajıSec alanına yazdırın
                         textAreaMesajıSec.setText((String) mesaj);
                     }
                 }
             }
         });
      
         
      
         
         

       
      
    }
}