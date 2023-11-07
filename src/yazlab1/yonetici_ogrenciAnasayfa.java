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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Image;

import javax.sound.midi.Soundbank;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class yonetici_ogrenciAnasayfa extends JFrame {

    private JPanel contentPane;
    private JPanel sidebarYonetici;
  
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
    private JButton btnkaydet1;
    private JTextField textField_rastgelesayisi;
    private int rastgele_ogrenci_id;
    private int rastgele_sayisi;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    yonetici_ogrenciAnasayfa frame = new yonetici_ogrenciAnasayfa();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public yonetici_ogrenciAnasayfa() {
    	 String dbURL = "jdbc:postgresql://localhost:5432/Db_yazlab1";
         String username = "postgres";
         String password = "12345678sk";
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1755, 1082);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(227, 208, 249));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Sidebar oluştur
        sidebarYonetici = new JPanel();
        sidebarYonetici.setBackground(new Color(160, 147, 219));
        sidebarYonetici.setBounds(0, 0, 214, 1045);
        // Kenar çubuğunun boyutunu ayarlayın
          contentPane.add(sidebarYonetici);
          sidebarYonetici.setLayout(null);
          
          JButton buttonResim = new JButton("Icon");
          Image img = new ImageIcon(yonetici.class.getResource("/img.png")).getImage();
          buttonResim.setIcon(new ImageIcon(img));
          sidebarYonetici.add(buttonResim);
          
          lblNewLabel = new JLabel("YÖNETİCİ");
          lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
          lblNewLabel.setForeground(new Color(227, 208, 249));
          lblNewLabel.setBounds(6, 133, 152, 31);
          sidebarYonetici.add(lblNewLabel);
          
          lblSoyad = new JLabel("PANELİ");
          lblSoyad.setFont(new Font("Tahoma", Font.PLAIN, 16));
          lblSoyad.setForeground(new Color(227, 208, 249));
          lblSoyad.setBounds(6, 174, 152, 31);
          sidebarYonetici.add(lblSoyad);
          
       
          
          lblNewLabelFoto= new JLabel("New label");
          Image img2 = new ImageIcon(this.getClass().getResource("/img.png")).getImage();
          lblNewLabelFoto .setIcon(new ImageIcon(img));
          lblNewLabelFoto.setBounds(29, 16, 95, 89);
          sidebarYonetici.add(lblNewLabelFoto);
          
         
       
        
         tableModel = new DefaultTableModel();
         tableModel.addColumn("Öğrenci ID");
         tableModel.addColumn("Öğrenci Ad ");
         tableModel.addColumn("Öğrenci Soyad");
         tableModel.addColumn("Öğrenci Durum");
         tableModel.addColumn("Öğrenci Agno");
         
         try {
             Connection connection = DriverManager.getConnection(dbURL, username, password);

             // Sorgu ile verileri al
             String query = "SELECT * FROM ogrenci_tablo";
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
             while (resultSet.next()) {
                 int ogrenciId = resultSet.getInt("ogrenci_id");
                 String ogrenciAd = resultSet.getString("ogrenci_ad");
                 String ogrenciSoyad = resultSet.getString("ogrenci_soyad");
                 Boolean ogrenciDurum = resultSet.getBoolean("ogrenci_durum");
                 String ogrenciAgno = resultSet.getString("ogrenci_agno");

                 tableModel.addRow(new Object[]{ogrenciId, ogrenciAd, ogrenciSoyad, ogrenciDurum, ogrenciAgno});
             }
             resultSet.close();
             statement.close();
             connection.close();
         }
         catch (Exception e) {
			e.printStackTrace();
		}
         
         JButton btnGeri = new JButton("Geri");
         btnGeri.setFont(new Font("Tahoma", Font.PLAIN, 16));
         btnGeri.setForeground(new Color(144, 135, 255));
         btnGeri.setBounds(6, 1006, 198, 29);
         btnGeri.addActionListener(new ActionListener() {
           	public void actionPerformed(ActionEvent e) {
           		yonetici_ogrenciAnasayfa.super.setVisible(false);
           		yonetici_secme yonetici_secme = new yonetici_secme();
           		yonetici_secme.setVisible(true);
           	}
           });
         sidebarYonetici.add(btnGeri);
         table = new JTable(tableModel);
         table.setBackground(new Color(227, 208, 249));
         JScrollPane scrollPane = new JScrollPane(table);
         scrollPane.setBackground(new Color(227, 208, 249));
         scrollPane.setBounds(224, 10, 1471, 559);
         contentPane.add(scrollPane);
         
         JButton btnNewButtonSil = new JButton("Sil");
         btnNewButtonSil.setFont(new Font("Tahoma", Font.PLAIN, 18));
         btnNewButtonSil.setForeground(new Color(142, 122, 192));
         btnNewButtonSil.setBounds(282, 674, 315, 47);
         btnNewButtonSil.addActionListener(new ActionListener() {
     	    @Override
     	    public void actionPerformed(ActionEvent e) {
     	        int selectedRow = table.getSelectedRow(); // Seçili satırın indeksi
     	        if (selectedRow >= 0) { // Eğer bir satır seçilmişse
     	        	 try {
     	                Connection connection = DriverManager.getConnection(dbURL, username, password);

     	                String deleteQuery = "DELETE FROM ogrenci_ders_tablo WHERE ogrenci_id = "+tableModel.getValueAt(selectedRow, 0);
     	                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
     	                

     	                int rowsAffected = preparedStatement.executeUpdate();

     	                if (rowsAffected > 0) {
     	                    System.out.println("Veri silindi.");
     	                } else {
     	                    System.out.println("Silme başarısız oldu. Belirtilen ID bulunamadı.");
     	                }

     	                preparedStatement.close();
     	                connection.close();
     	            } catch (Exception a) {
     	                a.printStackTrace();
     	            }
     	        	 try {
      	                Connection connection = DriverManager.getConnection(dbURL, username, password);

      	                String deleteQuery = "DELETE FROM ogrenci_ilgialani_tablo WHERE ogrenci_id = "+tableModel.getValueAt(selectedRow, 0);
      	                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
      	                

      	                int rowsAffected = preparedStatement.executeUpdate();

      	                if (rowsAffected > 0) {
      	                    System.out.println("Veri silindi.");
      	                } else {
      	                    System.out.println("Silme başarısız oldu. Belirtilen ID bulunamadı.");
      	                }

      	                preparedStatement.close();
      	                connection.close();
      	            } catch (Exception a) {
      	                a.printStackTrace();
      	            }
     	        	 try {
      	                Connection connection = DriverManager.getConnection(dbURL, username, password);

      	                String deleteQuery = "DELETE FROM ogrenci_secim_ders_tablo WHERE ogrenci_id = "+tableModel.getValueAt(selectedRow, 0);
      	                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
      	                

      	                int rowsAffected = preparedStatement.executeUpdate();

      	                if (rowsAffected > 0) {
      	                    System.out.println("Veri silindi.");
      	                } else {
      	                    System.out.println("Silme başarısız oldu. Belirtilen ID bulunamadı.");
      	                }

      	                preparedStatement.close();
      	                connection.close();
      	            } catch (Exception a) {
      	                a.printStackTrace();
      	            }
     	        	 
     	        	 try {
      	                Connection connection = DriverManager.getConnection(dbURL, username, password);

      	                String deleteQuery = "DELETE FROM talep_ogrenci_tablo WHERE ogrenci_gonderen_id = "+tableModel.getValueAt(selectedRow, 0);
      	                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
      	                

      	                int rowsAffected = preparedStatement.executeUpdate();

      	                if (rowsAffected > 0) {
      	                    System.out.println("Veri silindi.");
      	                } else {
      	                    System.out.println("Silme başarısız oldu. Belirtilen ID bulunamadı.");
      	                }

      	                preparedStatement.close();
      	                connection.close();
      	            } catch (Exception a) {
      	                a.printStackTrace();
      	            }
     	        	 try {
      	                Connection connection = DriverManager.getConnection(dbURL, username, password);

      	                String deleteQuery = "DELETE FROM talep_hoca_tablo WHERE alici_ogrenci_id = "+tableModel.getValueAt(selectedRow, 0);
      	                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
      	                

      	                int rowsAffected = preparedStatement.executeUpdate();

      	                if (rowsAffected > 0) {
      	                    System.out.println("Veri silindi.");
      	                } else {
      	                    System.out.println("Silme başarısız oldu. Belirtilen ID bulunamadı.");
      	                }

      	                preparedStatement.close();
      	                connection.close();
      	            } catch (Exception a) {
      	                a.printStackTrace();
      	            }
     	        	try {
      	                Connection connection = DriverManager.getConnection(dbURL, username, password);

      	                String deleteQuery = "DELETE FROM ogrenci_tablo WHERE ogrenci_id = "+tableModel.getValueAt(selectedRow, 0);
      	                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
      	                

      	                int rowsAffected = preparedStatement.executeUpdate();

      	                if (rowsAffected > 0) {
      	                    System.out.println("Veri silindi.");
      	                } else {
      	                    System.out.println("Silme başarısız oldu. Belirtilen ID bulunamadı.");
      	                }

      	                preparedStatement.close();
      	                connection.close();
      	            } catch (Exception a) {
      	                a.printStackTrace();
      	            }
     	            tableModel.removeRow(selectedRow); // Seçilen satırı kaldır
     	        } else {
     	            // Kullanıcı bir satır seçmemişse uyarı verebilirsiniz.
     	           JOptionPane.showMessageDialog(null, "Lütfen bir satır seçin.");
     	        }
     	    }
     	});
         
         
         
         contentPane.add(btnNewButtonSil);
         
         
         
         
         
         
         
         
         
         
         
         JButton btnDuzenle = new JButton("Düzenle");
         btnDuzenle.setFont(new Font("Tahoma", Font.PLAIN, 18));
         btnDuzenle.setForeground(new Color(142, 122, 192));
         btnDuzenle.setBounds(282, 742, 315, 47);
         btnDuzenle.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		 int selectedRow = table.getSelectedRow(); // Seçili satırın indeksi
                 int selectedColumn = table.getSelectedColumn(); // Seçili sütunun indeksi
                
                 if (selectedRow >= 0 && selectedColumn >= 0) { // Eğer bir satır ve sütun seçilmişse
                	 System.out.println(selectedColumn);
                	
                	 if(selectedColumn==1)
                	 {
                		 String bString = JOptionPane.showInputDialog("Yeni Bilgiyi Giriniz.");
                		 int aString= (Integer) tableModel.getValueAt(selectedRow,0);
                		 String cString= (String) tableModel.getValueAt(selectedRow,1);
                		
                		 
                		 tableModel.setValueAt(bString, selectedRow, selectedColumn);
                		 try {
             	            Connection connection = DriverManager.getConnection(dbURL, username, password);
             	           
             	            String updateQuery = "UPDATE ogrenci_tablo SET ogrenci_ad = ? WHERE ogrenci_id = ? AND ogrenci_ad = ?";
             	            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
             	            preparedStatement.setString(1, bString);
             	            preparedStatement.setInt(2, aString);
             	            preparedStatement.setString(3, cString);
             	            
             	      

             	            int rowsAffected = preparedStatement.executeUpdate();
             	           System.out.println(aString+"yeni ;     "+bString+"eski ;  "+cString);

             	            if (rowsAffected > 0) {
             	                System.out.println("Veri güncellendi. 1");
             	            } else {
             	                System.out.println("Güncelleme başarısız oldu.");
             	            }

             	            preparedStatement.close();
             	            connection.close();
             	        } catch (Exception et) {
             	            et.printStackTrace();
             	        }
                		 
                		 try {
             	            Connection connection = DriverManager.getConnection(dbURL, username, password);

             	            String updateQuery = "UPDATE ogrenci_secim_ders_tablo SET ogrenci_ad = ? WHERE ogrenci_id =?";
             	            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
             	           preparedStatement.setString(1, bString);
            	            preparedStatement.setInt(2, aString);
             	      

             	            int rowsAffected = preparedStatement.executeUpdate();

             	            if (rowsAffected > 0) {
             	                System.out.println("Veri güncellendi.2");
             	            } else {
             	                System.out.println("Güncelleme başarısız oldu.");
             	            }

             	            preparedStatement.close();
             	            connection.close();
             	        } catch (Exception ek) {
             	            ek.printStackTrace();
             	        }
                		 try {
             	            Connection connection = DriverManager.getConnection(dbURL, username, password);

             	            String updateQuery = "UPDATE talep_ogrenci_tablo SET ogrenci_ad = ? WHERE ogrenci_gonderen_id =?";
             	            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
             	           preparedStatement.setString(1, bString);
            	            preparedStatement.setInt(2, aString);

             	            int rowsAffected = preparedStatement.executeUpdate();

             	            if (rowsAffected > 0) {
             	                System.out.println("Veri güncellendi.3");
             	            } else {
             	                System.out.println("Güncelleme başarısız oldu.");
             	            }

             	            preparedStatement.close();
             	            connection.close();
             	        } catch (Exception ec) {
             	            ec.printStackTrace();
             	        }
                		 try {
             	            Connection connection = DriverManager.getConnection(dbURL, username, password);

             	            String updateQuery = "UPDATE talep_hoca_tablo SET ogrenci_ad = ? WHERE alici_ogrenci_id =?";
             	            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
             	           preparedStatement.setString(1, bString);
            	            preparedStatement.setInt(2, aString);

             	            int rowsAffected = preparedStatement.executeUpdate();

             	            if (rowsAffected > 0) {
             	                System.out.println("Veri güncellendi.4");
             	            } else {
             	                System.out.println("Güncelleme başarısız oldu.");
             	            }
             	           
             	            preparedStatement.close();
             	            connection.close();
             	        } catch (Exception em) {
             	            em.printStackTrace();
             	        }
                		 
                     
                     
                  
                		 
                 }
                	 else if(selectedColumn==2)
                	 {
                		 String bString = JOptionPane.showInputDialog("Yeni Bilgiyi Giriniz.");
                		 int aString= (Integer) tableModel.getValueAt(selectedRow,0);
                		
                		
                		 
                		 tableModel.setValueAt(bString, selectedRow, selectedColumn);
                		 try {
             	            Connection connection = DriverManager.getConnection(dbURL, username, password);
             	           
             	            String updateQuery = "UPDATE ogrenci_tablo SET ogrenci_soyad = ? WHERE ogrenci_id = ?";
             	            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
             	            preparedStatement.setString(1, bString);
             	            preparedStatement.setInt(2, aString);
             	           
             	            
             	      

             	            int rowsAffected = preparedStatement.executeUpdate();
             	           System.out.println(aString+"yeni ;     "+bString);

             	            if (rowsAffected > 0) {
             	                System.out.println("Veri güncellendi. 1");
             	            } else {
             	                System.out.println("Güncelleme başarısız oldu.");
             	            }

             	            preparedStatement.close();
             	            connection.close();
             	        } catch (Exception et) {
             	            et.printStackTrace();
             	        }
                		 
                		 try {
             	            Connection connection = DriverManager.getConnection(dbURL, username, password);

             	            String updateQuery = "UPDATE ogrenci_secim_ders_tablo SET ogrenci_soyad = ? WHERE ogrenci_id =?";
             	            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
             	           preparedStatement.setString(1, bString);
            	            preparedStatement.setInt(2, aString);
             	      

             	            int rowsAffected = preparedStatement.executeUpdate();

             	            if (rowsAffected > 0) {
             	                System.out.println("Veri güncellendi.2");
             	            } else {
             	                System.out.println("Güncelleme başarısız oldu.");
             	            }

             	            preparedStatement.close();
             	            connection.close();
             	        } catch (Exception ek) {
             	            ek.printStackTrace();
             	        }
                		 try {
             	            Connection connection = DriverManager.getConnection(dbURL, username, password);

             	            String updateQuery = "UPDATE talep_ogrenci_tablo SET ogrenci_soyad = ? WHERE ogrenci_gonderen_id =?";
             	            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
             	           preparedStatement.setString(1, bString);
            	            preparedStatement.setInt(2, aString);

             	            int rowsAffected = preparedStatement.executeUpdate();

             	            if (rowsAffected > 0) {
             	                System.out.println("Veri güncellendi.3");
             	            } else {
             	                System.out.println("Güncelleme başarısız oldu.");
             	            }

             	            preparedStatement.close();
             	            connection.close();
             	        } catch (Exception ec) {
             	            ec.printStackTrace();
             	        }
                		 try {
             	            Connection connection = DriverManager.getConnection(dbURL, username, password);

             	            String updateQuery = "UPDATE talep_hoca_tablo SET ogrenci_soyad = ? WHERE alici_ogrenci_id =?";
             	            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
             	           preparedStatement.setString(1, bString);
            	            preparedStatement.setInt(2, aString);

             	            int rowsAffected = preparedStatement.executeUpdate();

             	            if (rowsAffected > 0) {
             	                System.out.println("Veri güncellendi.4");
             	            } else {
             	                System.out.println("Güncelleme başarısız oldu.");
             	            }
             	           
             	            preparedStatement.close();
             	            connection.close();
             	        } catch (Exception em) {
             	            em.printStackTrace();
             	        }
                		 
                     
                     
                  
                	
                	 }
                	 else {
                     // Kullanıcı bir hücre seçmemişse uyarı verebilirsiniz.
                     JOptionPane.showMessageDialog(null, "Lütfen bir hücre seçin.");
                 }
         	}
         	}
         });
         
       contentPane.add(btnDuzenle);
       
       JButton btnekle = new JButton("Ekle");
       btnekle.setFont(new Font("Tahoma", Font.PLAIN, 18));
       btnekle.setForeground(new Color(142, 122, 192));
       btnekle.setBounds(282, 813, 315, 52);
       btnekle.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tableModel.addRow(new Object[]{});
        		 String yeniDeger = JOptionPane.showInputDialog("Öğrenci idsini giriniz :");
                 int sonSatirIndeksi = table.getRowCount() - 1;
                 int ogrenciID = Integer.parseInt(yeniDeger);
        		 tableModel.setValueAt(ogrenciID, sonSatirIndeksi, 0);
        		 String yeniDeger1 = JOptionPane.showInputDialog("Öğrenci Adını giriniz :");
        		 tableModel.setValueAt(yeniDeger1, sonSatirIndeksi, 1);
        		 String yeniDeger2 = JOptionPane.showInputDialog("Öğrenci Soyadını giriniz :");
        		 tableModel.setValueAt(yeniDeger2, sonSatirIndeksi, 2);
        		 tableModel.setValueAt(true, sonSatirIndeksi, 3);
        		 JButton btnKaydet = new JButton("Kaydet");
        	       btnKaydet.setForeground(new Color(142, 122, 192));
        	       btnKaydet.setFont(new Font("Tahoma", Font.PLAIN, 18));
        	       btnKaydet.setBounds(282, 890, 315, 52);
        	       contentPane.add(btnKaydet);
        	      
        	     btnKaydet.addActionListener(new ActionListener() {
        	        	public void actionPerformed(ActionEvent e) {
        	        		try {
        	                    Connection connection = DriverManager.getConnection(dbURL, username, password);

        	                    String insertQuery = "INSERT INTO ogrenci_tablo (ogrenci_id,ogrenci_ad, ogrenci_soyad,ogrenci_durum) VALUES (?,?,?, ?)";
        	                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        	                    preparedStatement.setInt(1, ogrenciID); 
        	                    preparedStatement.setString(2, yeniDeger1); 
        	                    preparedStatement.setString(3, yeniDeger2); 
        	                    preparedStatement.setBoolean(4, true); 

        	                    int rowsAffected = preparedStatement.executeUpdate();

        	                    if (rowsAffected > 0) {
        	                        System.out.println("Veri eklendi.");
        	                    } else {
        	                        System.out.println("Ekleme başarısız oldu.");
        	                    }

        	                    preparedStatement.close();
        	                    connection.close();
        	                } catch (Exception a) {
        	                    a.printStackTrace();
        	                }
        	        		try (Connection connection = DriverManager.getConnection(dbURL, username, password)) {
        	                    if (connection != null) {
        	                        System.out.println("Veritabanına bağlandı!");

        	                       
        	                        List<IlgiAlani> ilgiAlanlari = new ArrayList<>(); // Ilgi alanlari nesnelerini tutan liste

        	                        // Hoca ID'leri ve İlgi Alanı bilgileri veritabanından çekilir.
        	                       
        	                        String ilgialaniQuery = "SELECT ilgialan_id, ilgialan_ad FROM ilgialani_tablo";

        	                        try (PreparedStatement ilgialaniStatement = connection.prepareStatement(ilgialaniQuery))
        	                             {
        	                           
        	                            ResultSet ilgialaniResult = ilgialaniStatement.executeQuery();

        	                          

        	                            while (ilgialaniResult.next()) {
        	                                int ilgialaniID = ilgialaniResult.getInt("ilgialan_id");
        	                                String ilgialaniAd = ilgialaniResult.getString("ilgialan_ad");
        	                                IlgiAlani ilgiAlani = new IlgiAlani(ilgialaniID, ilgialaniAd);
        	                                ilgiAlanlari.add(ilgiAlani);
        	                            }
        	                        }
        	                        

        	                        Random random = new Random();
        	                        String insertQuery = "INSERT INTO ogrenci_ilgialani_tablo ( ogrenci_id, ilgialani_id,ilgialani_ad) VALUES (?, ?, ?)";
        	                        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
        	                           
        	                                // İlgili hoca için rastgele sayıda ilgi alanı seç
        	                                int ilgialaniSayisi = random.nextInt(ilgiAlanlari.size()) + 1;

        	                                for (int i = 0; i < ilgialaniSayisi; i++) {
        	                                    IlgiAlani ilgiAlani = ilgiAlanlari.get(i);
        	                                    insertStatement.setInt(2, ilgiAlani.getId());
        	                                    insertStatement.setInt(1, ogrenciID);
        	                                    insertStatement.setString(3, ilgiAlani.getAd());
        	                                    insertStatement.executeUpdate();
        	                                }
        	                            
        	                        }

        	                        System.out.println("ogrenciye rastgele sayıda ilgi alanı eklendi ve ilgi alanı adları da eklendi.");
        	                    }
        	                } catch (SQLException a) {
        	                    System.err.println("Veritabanı hatası: " + a.getMessage());
        	                }
        	        		
        	        		
        	        		 
        	        		
        	        		 
        	        	}
        	        });
        		 
        		
        		 
        	}
        });
       contentPane.add(btnekle);
       
       JButton btnNewButtonsatsgele = new JButton("Rastgele Ders ");
       btnNewButtonsatsgele.setFont(new Font("Tahoma", Font.PLAIN, 18));
       btnNewButtonsatsgele.setForeground(new Color(142, 122, 192));
       btnNewButtonsatsgele.setBounds(677, 674, 315, 47);
       btnNewButtonsatsgele.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       	 String rastgele=textField_rastgelesayisi.getText();
         rastgele_sayisi=Integer.parseInt(rastgele);
         int satir=table.getSelectedRow();
         rastgele_ogrenci_id=(int) tableModel.getValueAt(satir,0);
         System.out.println(rastgele_ogrenci_id+"  "+rastgele_sayisi);
         int ogrenciId = rastgele_ogrenci_id;
         int desiredCount = rastgele_sayisi;

         
        

         try {
             Connection connection = DriverManager.getConnection(dbURL, username, password);
             connection.setAutoCommit(false);

             // Öğrencinin mevcut derslerini al
             ArrayList<ders> existingDersler = new ArrayList<>();
            
             String query = "SELECT hoca_id, hoca_ad, hoca_soyad, ders_id, ders_ad, ders_akts FROM ogrenci_secim_ders_tablo WHERE ogrenci_id = ?";
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             preparedStatement.setInt(1, ogrenciId);
             ResultSet resultSet = preparedStatement.executeQuery();
             while (resultSet.next()) {
                 int hocaId = resultSet.getInt("hoca_id");
                 String hocaAd = resultSet.getString("hoca_ad");
                 String hocaSoyad = resultSet.getString("hoca_soyad");
                 String dersId = resultSet.getString("ders_id");
                 String dersAd = resultSet.getString("ders_ad");
                 String dersAkts = resultSet.getString("ders_akts");
                 ders ders=new ders(hocaId, hocaAd, hocaSoyad, dersId, dersAd, dersAkts);
                 existingDersler.add(ders);
                 
                 
             }
             HashSet<String> uniqueDersAdlari = new HashSet<>();
             Iterator<ders> iterator = existingDersler.iterator();
             while (iterator.hasNext()) {
                 ders ders = iterator.next();
                 if (!uniqueDersAdlari.add(ders.getDersAd())) {
                     iterator.remove(); 
                 }
             }
             
             Collections.shuffle(existingDersler);
             
             ArrayList<ders> selecteDers = new ArrayList<>();
            for(int a=0;a<desiredCount;a++)
            {
            	selecteDers.add(existingDersler.get(a));
            }
            for(int a=0;a<desiredCount;a++)
            {
            	System.out.println(selecteDers.get(a).getDersAd());
            }
            


             // ...

             
            
         
             


             // Seçilen dersleri kaldır ve ekleyin
             String removeQuery = "DELETE FROM ogrenci_secim_ders_tablo WHERE ogrenci_id = ? AND ders_id = ?";
             String insertQuery = "INSERT INTO ogrenci_ders_tablo (ogrenci_id, ders_id,harf_not,ders_ad, ders_akts, hoca_id, hoca_ad, hoca_soyad) VALUES (?,?, ?, ?, ?, ?, ?, ?)";

             
                 for (ders ders : selecteDers) {
                    
                         // Seçilen dersi kaldır
                         PreparedStatement removeStatement = connection.prepareStatement(removeQuery);
                         removeStatement.setInt(1, ogrenciId);
                         removeStatement.setString(2, ders.getDersId());
                         removeStatement.executeUpdate();
                         System.out.println("silindi");

                         // Seçilen dersi ekleyin
                         PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                         insertStatement.setInt(1, ogrenciId);
                         insertStatement.setString(2, ders.getDersId());
                         insertStatement.setString(3, null);
                         insertStatement.setString(4, ders.getDersAd());
                         insertStatement.setString(5, ders.getDersAkts());
                         insertStatement.setInt(6, ders.getHocaId());
                         insertStatement.setString(7, ders.getHocaAd());
                         insertStatement.setString(8, ders.getHocaSoyad());
                         insertStatement.executeUpdate();
                         System.out.println("yüklendi");
                     
                 }
             

             connection.commit();
             connection.close();
         } catch (SQLException a) {
             a.printStackTrace();
         }
        		 
        		 
       		 
       		
       		 
       	}
       });
       contentPane.add(btnNewButtonsatsgele);
       
       
       textField_rastgelesayisi = new JTextField();
       textField_rastgelesayisi.setBounds(1105, 674, 64, 41);
       contentPane.add(textField_rastgelesayisi);
       textField_rastgelesayisi.setColumns(10);
       
      
       
       
      
         
         

       
      
    }
    public static ArrayList<String> getRandomUniqueDersler(ArrayList<ders> existingDersler, ArrayList<String> allDersler, int desiredCount) {
        HashSet<String> selectedDersler = new HashSet<>();
        Collections.shuffle(allDersler);

        for (String dersId : allDersler) {
            boolean isUnique = true;

            for (ders ders : existingDersler) {
                if (ders.getDersId().equals(dersId)) {
                    isUnique = false;
                    break;
                }
            }

            if (isUnique) {
                selectedDersler.add(dersId);

                if (selectedDersler.size() >= desiredCount) {
                    break;  // İstenilen sayıya ulaşıldığında döngüyü sonlandır
                }
            }
        }

        return new ArrayList<>(selectedDersler);
    }
}
class ders {
    private int hocaId;
    private String hocaAd;
    private String hocaSoyad;
    private String dersId;
    private String dersAd;
    private String dersAkts;

    public ders(int hocaId, String hocaAd, String hocaSoyad, String dersId, String dersAd, String dersAkts) {
        this.hocaId = hocaId;
        this.hocaAd = hocaAd;
        this.hocaSoyad = hocaSoyad;
        this.dersId = dersId;
        this.dersAd = dersAd;
        this.dersAkts = dersAkts;
    }

    public int getHocaId() {
        return hocaId;
    }

    public String getHocaAd() {
        return hocaAd;
    }

    public String getHocaSoyad() {
        return hocaSoyad;
    }

    public String getDersId() {
        return dersId;
    }

    public String getDersAd() {
        return dersAd;
    }

    public String getDersAkts() {
        return dersAkts;
    }
}
class IlgiAlani {
    private int id;
    private String ad;

    public IlgiAlani(int id, String ad) {
        this.id = id;
        this.ad = ad;
    }

    public int getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }
}

