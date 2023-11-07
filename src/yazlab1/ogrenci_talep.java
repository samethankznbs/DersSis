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

import yazlab1.ogrenci_transkript_arayuz.Ders;

import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class ogrenci_talep extends JFrame {

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
    private int hoca_Id;
    private String ders_Id;
    private String ders_Ad;
    private String hoca_Ad;
    private String hoca_Soyad;
    private String mesajString=null;
    private String ders_akts;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ogrenci_talep frame = new ogrenci_talep();
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
     */
    public ogrenci_talep(int sifre1,String ad,String soyad) {
    	 String dbURL = "jdbc:postgresql://localhost:5432/Db_yazlab1";
         String username = "postgres";
         String password = "12345678sk";
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1752, 1084);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(227, 208, 249));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
       

      
        sidebarOgrenci = new JPanel();
        sidebarOgrenci.setBackground(new Color(160, 147, 219));
        sidebarOgrenci.setBounds(0, 0, 216, 1047);
      
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
          
          
        
         tableModel = new DefaultTableModel();
         tableModel.addColumn("Hoca ID");
         tableModel.addColumn("Ders ID ");
         tableModel.addColumn("Ders Adı");
         tableModel.addColumn("Hoca Adı");
         tableModel.addColumn("Hoca Soyadı");
         tableModel.addColumn("Ders Akts");
         try {
             
             Connection connection = DriverManager.getConnection(dbURL, username, password);
             Statement statement = connection.createStatement();

             String query = "SELECT * FROM ogrenci_secim_ders_tablo WHERE ogrenci_id="+sifre1;
             ResultSet resultSet = statement.executeQuery(query);

             // Veritabanından verileri alarak tabloya ekle
             while (resultSet.next()) {
                 
                 Integer hocaId = resultSet.getInt("hoca_id");
                 String dersID = resultSet.getString("ders_id");
                 String dersAd = resultSet.getString("ders_ad");
                 String hocaAd = resultSet.getString("hoca_ad");
                 String hocaSoyad = resultSet.getString("hoca_soyad");
                 String dersaktString=resultSet.getString("ders_akts");
                 

                 tableModel.addRow(new Object[]{hocaId, dersID, dersAd,hocaAd,hocaSoyad,dersaktString});
             }

             connection.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
        

         table = new JTable(tableModel);
         table.setBackground(new Color(227, 208, 249));
         JScrollPane scrollPane = new JScrollPane(table);
         scrollPane.setBackground(new Color(227, 208, 249));
         scrollPane.setBounds(226, 10, 1502, 531);
         contentPane.add(scrollPane);
        
         table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
             @Override
             public void valueChanged(ListSelectionEvent e) {
                 if (!e.getValueIsAdjusting()) {
                     int selectedRow = table.getSelectedRow();
                     if (selectedRow != -1) {
                         Object hocaIdObject = tableModel.getValueAt(selectedRow, 0);
                         Object dersIdObject = tableModel.getValueAt(selectedRow, 1);
                         Object dersAdObject =tableModel.getValueAt(selectedRow, 2);
                         Object hocaAdObject =tableModel.getValueAt(selectedRow, 3);
                         Object hocaSoyAdObject =tableModel.getValueAt(selectedRow, 4);
                         Object dersaktsObject =tableModel.getValueAt(selectedRow, 5);

                         if (hocaIdObject instanceof Integer && dersIdObject instanceof String && dersAdObject instanceof String && hocaAdObject instanceof String && hocaSoyAdObject instanceof String && dersaktsObject instanceof String) {
                        	 hoca_Id = (Integer) hocaIdObject;
                             ders_Id = (String) dersIdObject;
                             ders_Ad=(String) dersAdObject;
                             hoca_Ad=(String) hocaAdObject;
                             hoca_Soyad=(String) hocaSoyAdObject;
                             ders_akts=(String) dersaktsObject;
                             
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
         lblNewLabelGondermek.setBounds(284, 631, 303, 29);
         contentPane.add(lblNewLabelGondermek);
         
         JButton btnNewButton_2 = new JButton("Gönder");
         btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
         btnNewButton_2.setForeground(new Color(149, 75, 255));
         btnNewButton_2.setBounds(923, 748, 207, 29);
         contentPane.add(btnNewButton_2);
         btnNewButton_2.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				try {
		            Connection connection = DriverManager.getConnection(dbURL,username, password);
		           

		            String sql = "INSERT INTO talep_ogrenci_tablo (ogrenci_gonderen_id, hoca_alici_id, ders_id, talep_mesaj,ders_ad,hoca_ad,hoca_soyad,ogrenci_ad,ogrenci_soyad,talep_durum,ders_akts) VALUES (?,?,?,?,?, ?, ?, ?,?,?,?)";
		            PreparedStatement preparedStatement = connection.prepareStatement(sql);

		          
		                preparedStatement.setInt(1, sifre1); // Öğrenci ID'sini ayarlayın
		                preparedStatement.setInt(2, hoca_Id);
		                preparedStatement.setString(3, ders_Id);
		                System.out.println(mesajString);
		                preparedStatement.setString(4, mesajString);
		                preparedStatement.setString(5, ders_Ad);
		                preparedStatement.setString(6, hoca_Ad);
		                preparedStatement.setString(7, hoca_Soyad);
		                preparedStatement.setString(8, ad);
		                preparedStatement.setString(9, soyad);
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
         textArea_1.setBounds(284, 677, 521, 205);
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
         JButton btnNewButton_3 = new JButton("Gelen Kutusu");
         btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
         btnNewButton_3.setForeground(new Color(134, 130, 232));
         btnNewButton_3.setBounds(29, 163, 154, 29);
         btnNewButton_3.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				ogrenci_talep.super.setVisible(false);
 				mesajlarimOgrenci mesajlarımOgrenci = new mesajlarimOgrenci(sifre1,ad,soyad);
 				mesajlarımOgrenci.setVisible(true);
 						
 				
 			}
 		});

         sidebarOgrenci.add(btnNewButton_3);
         JButton btnGeri = new JButton("Geri");
         btnGeri.setFont(new Font("Tahoma", Font.PLAIN, 16));
         btnGeri.setForeground(new Color(134, 130, 232));
         btnGeri.setBounds(27, 995, 156, 29);
         btnGeri.addActionListener(new ActionListener() {
           	public void actionPerformed(ActionEvent e) {
           		ogrenci_talep.super.setVisible(false);
           		ogrenci_anasayfa ogrenci_anasayfa = new ogrenci_anasayfa(sifre1,ad,soyad);
           		ogrenci_anasayfa.setVisible(true);
           	}
           });
         
         sidebarOgrenci.add(btnGeri);
       
       

       
      
    }
}