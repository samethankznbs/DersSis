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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Font;

public class hoca_talep extends JFrame {

    private JPanel contentPane;
    private JPanel sidebarHoca;
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
    private String ders_Id;
    private String ders_Ad;
    private String ogrenci_Ad;
    private String ogrenci_Soyad;
    private String mesajString=null;
    private int ogrenci_id;
    private String ders_akts;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    hoca_talep frame = new hoca_talep();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @param soyad2 
     * @param soyad 
     * @param sifre1 
     */
    public hoca_talep(int sifre1, String ad, String soyad) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1756, 1084);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(227, 208, 249));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

   
        sidebarHoca = new JPanel();
        sidebarHoca.setBackground(new Color(160, 147, 219));
        sidebarHoca.setBounds(0, 0, 214, 1047);

          contentPane.add(sidebarHoca);
          sidebarHoca.setLayout(null);
          
          JButton buttonResim = new JButton("Icon");
          Image img = new ImageIcon(yonetici.class.getResource("/img.png")).getImage();
          buttonResim.setIcon(new ImageIcon(img));
          sidebarHoca.add(buttonResim);
          
        
          
          lblNewLabelFoto= new JLabel("New label");
          Image img2 = new ImageIcon(this.getClass().getResource("/img.png")).getImage();
          lblNewLabelFoto .setIcon(new ImageIcon(img));
          lblNewLabelFoto.setBounds(29, 16, 95, 89);
          sidebarHoca.add(lblNewLabelFoto);
          
          
          
          JButton btnNewButton_3 = new JButton("Gelen Kutusu");
          btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
          btnNewButton_3.setForeground(new Color(134, 130, 232));
          btnNewButton_3.setBounds(10, 144, 183, 29);
          btnNewButton_3.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				hoca_talep.super.setVisible(false);
  				mesajlarimHoca mesajlarimHoca = new mesajlarimHoca(sifre1,ad,soyad);
  				mesajlarimHoca.setVisible(true);
  						
  				
  			}
  		});

          sidebarHoca.add(btnNewButton_3);
          
          
          
          
          
          JButton btnGeri = new JButton("Geri");
          btnGeri.setFont(new Font("Tahoma", Font.PLAIN, 14));
          btnGeri.setForeground(new Color(134, 130, 232));
          btnGeri.setBounds(29, 992, 164, 29);
          btnGeri.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		hoca_talep.super.setVisible(false);
            		hoca_AnasayfaOgrenciList hoca_AnasayfaOgrenciList = new hoca_AnasayfaOgrenciList(sifre1, ad, soyad);
            		hoca_AnasayfaOgrenciList.setVisible(true);
            	}
            });
          
          sidebarHoca.add(btnGeri);
                  
          String dbURL = "jdbc:postgresql://localhost:5432/Db_yazlab1";
          String username = "postgres";
          String password = "12345678sk";
        
         tableModel = new DefaultTableModel();
         tableModel.addColumn("Ogrenci ID");
         tableModel.addColumn("Ders ID ");
         tableModel.addColumn("Ders Adı");
         tableModel.addColumn("Ogrenci Adı");
         tableModel.addColumn("Öğrenci Soyadı");
         tableModel.addColumn("Ders Akts");
         try {
             
             Connection connection = DriverManager.getConnection(dbURL, username, password);
             Statement statement = connection.createStatement();

             String query = "SELECT * FROM ogrenci_secim_ders_tablo WHERE hoca_id="+sifre1;
             ResultSet resultSet = statement.executeQuery(query);

             // Veritabanından verileri alarak tabloya ekle
             while (resultSet.next()) {
                 
                 Integer ogrenciId = resultSet.getInt("ogrenci_id");
                 String dersID = resultSet.getString("ders_id");
                 String dersAd = resultSet.getString("ders_ad");
                 String ogrenciAd = resultSet.getString("ogrenci_ad");
                 String ogrenciSoyad = resultSet.getString("ogrenci_soyad");
                 String dersaktString=resultSet.getString("ders_akts");
                 
                 

                 tableModel.addRow(new Object[]{ogrenciId, dersID, dersAd,ogrenciAd,ogrenciSoyad,dersaktString});
             }

             connection.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
         table = new JTable(tableModel);
         table.setBackground(new Color(227, 208, 249));
         JScrollPane scrollPane = new JScrollPane(table);
         scrollPane.setBackground(new Color(227, 208, 249));
         scrollPane.setBounds(224, 10, 1492, 519);
         contentPane.add(scrollPane);
         
         table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
             @Override
             public void valueChanged(ListSelectionEvent e) {
                 if (!e.getValueIsAdjusting()) {
                     int selectedRow = table.getSelectedRow();
                     if (selectedRow != -1) {
                         Object ogrenciIdObject = tableModel.getValueAt(selectedRow, 0);
                         Object dersIdObject = tableModel.getValueAt(selectedRow, 1);
                         Object dersAdObject =tableModel.getValueAt(selectedRow, 2);
                         Object ogrencAdObject =tableModel.getValueAt(selectedRow, 3);
                         Object ogrenciSoyAdObject =tableModel.getValueAt(selectedRow, 4);
                         Object dersaktsObject=tableModel.getValueAt(selectedRow, 5);

                         if (ogrenciIdObject instanceof Integer && dersIdObject instanceof String && dersAdObject instanceof String && ogrencAdObject instanceof String && ogrenciSoyAdObject instanceof String) {
                        	 ogrenci_id = (Integer) ogrenciIdObject;
                             ders_Id = (String) dersIdObject;
                             ders_Ad=(String) dersAdObject;
                             ogrenci_Ad=(String) ogrencAdObject;
                             ogrenci_Soyad=(String) ogrenciSoyAdObject;
                             ders_akts=(String)dersaktsObject;
                         } else {
                             // Uygun tür dönüşümü yapılamadı
                             // Hata işlemleri burada
                         }
                     }
                 }
             }

			
			
         });

        
         
         JLabel lblNewLabelGondermek = new JLabel("Göndermek İstediğiniz Mesajı Giriniz : ");
         lblNewLabelGondermek.setFont(new Font("Tahoma", Font.PLAIN, 16));
         lblNewLabelGondermek.setForeground(new Color(149, 75, 255));
         lblNewLabelGondermek.setBounds(290, 594, 585, 47);
         contentPane.add(lblNewLabelGondermek);
         
         JButton btnNewButton_2 = new JButton("Gönder");
         btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
         btnNewButton_2.setForeground(new Color(149, 75, 255));
         btnNewButton_2.setBounds(985, 715, 220, 56);
         contentPane.add(btnNewButton_2);
         btnNewButton_2.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				try {
 		            Connection connection = DriverManager.getConnection(dbURL,username, password);
 		           

 		            String sql = "INSERT INTO talep_hoca_tablo (gonderen_hoca_id, alici_ogrenci_id, ders_id, talep_mesaj,ders_ad,hoca_ad,hoca_soyad,ogrenci_ad,ogrenci_soyad,talep_durum,ders_akts) VALUES (?,?,?,?,?, ?, ?, ?,?,?,?)";
 		            PreparedStatement preparedStatement = connection.prepareStatement(sql);

 		          
 		                preparedStatement.setInt(1, sifre1); // Öğrenci ID'sini ayarlayın
 		                preparedStatement.setInt(2, ogrenci_id);
 		                preparedStatement.setString(3, ders_Id);
 		                preparedStatement.setString(4, mesajString);
 		                preparedStatement.setString(5, ders_Ad);
 		                preparedStatement.setString(6, ad);
 		                preparedStatement.setString(7, soyad);
 		                preparedStatement.setString(8, ogrenci_Ad);
 		                preparedStatement.setString(9, ogrenci_Soyad);
 		                preparedStatement.setInt(10, 1);
 		                preparedStatement.setString(11, ders_akts);
 		              

 		                preparedStatement.executeUpdate();
 		                preparedStatement.close();
 		                connection.close();
 		            System.out.println("Dersler veritabanına eklendi.");
 		        } catch (SQLException m) {
 		            m.printStackTrace();
 		        }
    				
    			}
    		});
         
         
      
         
         JTextArea textArea_1 = new JTextArea();
         textArea_1.setFont(new Font("Monospaced", Font.PLAIN, 12));
         textArea_1.setBounds(290, 651, 597, 216);
         contentPane.add(textArea_1);
         textArea_1.getDocument().addDocumentListener(new DocumentListener() {
     	    public void insertUpdate(DocumentEvent e) {
     	        mesajString = textArea_1.getText(); // Metni güncelle
     	    }

     	    @Override
     	    public void removeUpdate(DocumentEvent e) {
     	        mesajString = textArea_1.getText(); // Metni güncelle
     	    }

     	    @Override
     	    public void changedUpdate(DocumentEvent e) {
     	        mesajString = textArea_1.getText(); // Metni güncelle
     	    }
     	});
         
        

       
      
    }
}
