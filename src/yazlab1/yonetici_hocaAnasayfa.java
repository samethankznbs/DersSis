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
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class yonetici_hocaAnasayfa extends JFrame {

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

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    yonetici_hocaAnasayfa frame = new yonetici_hocaAnasayfa();
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
    public yonetici_hocaAnasayfa() {
    	 String dbURL = "jdbc:postgresql://localhost:5432/Db_yazlab1";
         String username = "postgres";
         String password = "12345678sk";
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1752, 1083);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(227, 208, 249));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Sidebar oluştur
        sidebarYonetici = new JPanel();
        sidebarYonetici.setBackground(new Color(160, 147, 219));
        sidebarYonetici.setBounds(0, 0, 215, 1046);
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
          lblNewLabel.setBounds(6, 144, 152, 31);
          sidebarYonetici.add(lblNewLabel);
          
          lblSoyad = new JLabel("PANELİ");
          lblSoyad.setFont(new Font("Tahoma", Font.PLAIN, 16));
          lblSoyad.setForeground(new Color(227, 208, 249));
          lblSoyad.setBounds(6, 206, 152, 31);
          sidebarYonetici.add(lblSoyad);
          
      
          
          lblNewLabelFoto= new JLabel("New label");
          Image img2 = new ImageIcon(this.getClass().getResource("/img.png")).getImage();
          lblNewLabelFoto .setIcon(new ImageIcon(img));
          lblNewLabelFoto.setBounds(29, 16, 95, 89);
          sidebarYonetici.add(lblNewLabelFoto);
          
         
       
        
         tableModel = new DefaultTableModel();
         tableModel.addColumn("Hoca ID");
         tableModel.addColumn("Hoca AD ");
         tableModel.addColumn("Hoca SOYAD");
         
         try {
             Connection connection = DriverManager.getConnection(dbURL, username, password);

             // Sorgu ile verileri al
             String query = "SELECT * FROM hoca_tablo";
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
             while (resultSet.next()) {
                 int hocaId = resultSet.getInt("hoca_id");
                 String hocaad = resultSet.getString("hoca_ad");
                 String hocasoyad = resultSet.getString("hoca_soyad");
               

                 tableModel.addRow(new Object[]{hocaId,hocaad, hocasoyad});
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
         btnGeri.setBounds(27, 993, 162, 29);
         btnGeri.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		yonetici_hocaAnasayfa.super.setVisible(false);
         		yonetici_secme yonetici_secme = new yonetici_secme();
         		yonetici_secme.setVisible(true);
         	}
         });
         
         sidebarYonetici.add(btnGeri);
         
         
         
         table = new JTable(tableModel);
         table.setBackground(new Color(227, 208, 249));
         JScrollPane scrollPane = new JScrollPane(table);
         scrollPane.setBackground(new Color(227, 208, 249));
         scrollPane.setBounds(225, 10, 1486, 486);
         contentPane.add(scrollPane);
         JButton btnNewButtonSil = new JButton("Sil");
         btnNewButtonSil.setFont(new Font("Tahoma", Font.PLAIN, 18));
         btnNewButtonSil.setForeground(new Color(142, 122, 192));
         btnNewButtonSil.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		 
              	  
              	        int selectedRow = table.getSelectedRow(); // Seçili satırın indeksi
              	        if (selectedRow >= 0) { // Eğer bir satır seçilmişse
              	            tableModel.removeRow(selectedRow); // Seçilen satırı kaldır
              	        } else {
              	            // Kullanıcı bir satır seçmemişse uyarı verebilirsiniz.
              	           JOptionPane.showMessageDialog(null, "Lütfen bir satır seçin.");
              	        }
              	    
              	
         	}
         });
         btnNewButtonSil.setBounds(276, 559, 375, 29);
         contentPane.add(btnNewButtonSil);
         
         JButton btnDuzenle = new JButton("Düzenle");
         btnDuzenle.setFont(new Font("Tahoma", Font.PLAIN, 18));
         btnDuzenle.setForeground(new Color(142, 122, 192));
         btnDuzenle.setBounds(276, 623, 375, 29);
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
               		
               		
               		 
               		 tableModel.setValueAt(bString, selectedRow, selectedColumn);
               		 try {
            	            Connection connection = DriverManager.getConnection(dbURL, username, password);
            	           
            	            String updateQuery = "UPDATE hoca_tablo SET hoca_ad = ? WHERE hoca_id = ? ";
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
         	           
         	            String updateQuery = "UPDATE ders_hoca_tablo SET hoca_ad = ? WHERE hoca_id = ?";
         	            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
         	            preparedStatement.setString(1, bString);
         	            preparedStatement.setInt(2, aString);
         	            
         	            
         	      

         	            int rowsAffected = preparedStatement.executeUpdate();
         	           System.out.println(aString+"yeni ;     "+bString+"eski ;  ");

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

            	            String updateQuery = "UPDATE ogrenci_secim_ders_tablo SET hoca_ad = ? WHERE hoca_id =?";
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

            	            String updateQuery = "UPDATE talep_ogrenci_tablo SET hoca_ad = ? WHERE hoca_alici_id =?";
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

            	            String updateQuery = "UPDATE talep_hoca_tablo SET hoca_ad = ? WHERE gonderen_hoca_id =?";
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
               		 try {
         	            Connection connection = DriverManager.getConnection(dbURL, username, password);

         	            String updateQuery = "UPDATE ogrenci_ders_tablo SET hoca_ad = ? WHERE hoca_id =?";
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
                    
                    
                 
               		 
                }
               	 else if(selectedColumn==2)
               	 {
               		 String bString = JOptionPane.showInputDialog("Yeni Bilgiyi Giriniz.");
               		 int aString= (Integer) tableModel.getValueAt(selectedRow,0);
               		
               		
               		 
               		 tableModel.setValueAt(bString, selectedRow, selectedColumn);
               		 try {
            	            Connection connection = DriverManager.getConnection(dbURL, username, password);
            	           
            	            String updateQuery = "UPDATE hoca_tablo SET hoca_soyad = ? WHERE hoca_id = ? ";
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
         	           
         	            String updateQuery = "UPDATE ders_hoca_tablo SET hoca_soyad = ? WHERE hoca_id = ?";
         	            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
         	            preparedStatement.setString(1, bString);
         	            preparedStatement.setInt(2, aString);
         	            
         	            
         	      

         	            int rowsAffected = preparedStatement.executeUpdate();
         	           System.out.println(aString+"yeni ;     "+bString+"eski ;  ");

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

            	            String updateQuery = "UPDATE ogrenci_secim_ders_tablo SET hoca_soyad = ? WHERE hoca_id =?";
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

            	            String updateQuery = "UPDATE talep_ogrenci_tablo SET hoca_soyad = ? WHERE hoca_alici_id =?";
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

            	            String updateQuery = "UPDATE talep_hoca_tablo SET hoca_soyad = ? WHERE gonderen_hoca_id =?";
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
               		 try {
         	            Connection connection = DriverManager.getConnection(dbURL, username, password);

         	            String updateQuery = "UPDATE ogrenci_ders_tablo SET hoca_soyad = ? WHERE hoca_id =?";
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
                    
                    
                 
               		 
                }
               	 else {
                    // Kullanıcı bir hücre seçmemişse uyarı verebilirsiniz.
                    JOptionPane.showMessageDialog(null, "Lütfen bir hücre seçin.");
                }
        	}
        	
               	
          	}
          });
         
         
         
         
         contentPane.add(btnDuzenle);
        

       
      
    }
}