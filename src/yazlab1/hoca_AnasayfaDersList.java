package yazlab1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ListModel;

public class hoca_AnasayfaDersList extends JFrame {

    private JPanel contentPane;
    private JPanel sidebarHoca;
  
    private JLabel lblNewLabel;
    private JLabel lblSoyad;
    private JLabel lblNumara;
    private JLabel lblNewLabelFoto;
    private JButton btnNewButton;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnNewButton_1;
    private JLabel lblAgno;
    private JButton btnOgrenciListesineGec;
    private JButton btnGelenKutusu;
    private JButton btnGeri;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JScrollPane scrollPane2_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	hoca_AnasayfaDersList frame = new hoca_AnasayfaDersList();
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
    public hoca_AnasayfaDersList(int sifre1, String ad, String soyad) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1753, 1082);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(227, 208, 249));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        

   
        sidebarHoca = new JPanel();
        sidebarHoca.setBackground(new Color(160, 147, 219));
        sidebarHoca.setBounds(0, 0, 215, 1044);

          contentPane.add(sidebarHoca);
          sidebarHoca.setLayout(null);
          
          JButton buttonResim = new JButton("Icon");
          Image img = new ImageIcon(yonetici.class.getResource("/img.png")).getImage();
          buttonResim.setIcon(new ImageIcon(img));
          sidebarHoca.add(buttonResim);
          
          lblNewLabel = new JLabel(ad);
          lblNewLabel.setBackground(new Color(227, 208, 249));
          lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
          lblNewLabel.setForeground(new Color(227, 208, 249));
          lblNewLabel.setBounds(6, 131, 180, 31);
          sidebarHoca.add(lblNewLabel);
          
          lblSoyad = new JLabel(soyad);
          lblSoyad.setFont(new Font("Tahoma", Font.PLAIN, 16));
          lblSoyad.setForeground(new Color(227, 208, 249));
          lblSoyad.setBounds(6, 172, 180, 31);
          sidebarHoca.add(lblSoyad);
          
       
          
          lblNewLabelFoto= new JLabel("New label");
          Image img2 = new ImageIcon(this.getClass().getResource("/img.png")).getImage();
          lblNewLabelFoto .setIcon(new ImageIcon(img));
          lblNewLabelFoto.setBounds(29, 16, 95, 89);
          sidebarHoca.add(lblNewLabelFoto);
          
          btnOgrenciListesineGec = new JButton("Ögrenci Listesi");
          btnOgrenciListesineGec.setFont(new Font("Tahoma", Font.PLAIN, 16));
          btnOgrenciListesineGec.setForeground(SystemColor.textHighlight);
          btnOgrenciListesineGec.setBounds(16, 290, 170, 29);
          btnOgrenciListesineGec.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  			hoca_AnasayfaDersList.super.setVisible(false);
  			hoca_AnasayfaOgrenciList hoca_AnasayfaOgrenciList =new hoca_AnasayfaOgrenciList(sifre1,ad,soyad);
  			hoca_AnasayfaOgrenciList.setVisible(true);
  				
  			}
  		});
          
          sidebarHoca.add(btnOgrenciListesineGec);
          
          btnGelenKutusu = new JButton("Gelen Kutusu");
          btnGelenKutusu.setFont(new Font("Tahoma", Font.PLAIN, 16));
          btnGelenKutusu.setForeground(SystemColor.textHighlight);
          btnGelenKutusu.setBounds(16, 349, 170, 29);
          btnGelenKutusu.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    			hoca_AnasayfaDersList.super.setVisible(false);
    			mesajlarimHoca mesajlarimHoca = new mesajlarimHoca(sifre1,ad,soyad);
    			mesajlarimHoca.setVisible(true);
    				
    			}
    		});
          
          sidebarHoca.add(btnGelenKutusu);
          
          btnGeri = new JButton("Geri");
          btnGeri.setFont(new Font("Tahoma", Font.PLAIN, 16));
          btnGeri.setForeground(SystemColor.textHighlight);
          btnGeri.setBounds(16, 991, 164, 29);
          btnGeri.addActionListener(new ActionListener() {
          	public void actionPerformed(ActionEvent e) {
          		hoca_AnasayfaDersList.super.setVisible(false);
          		hoca hoca = new hoca();
          		hoca.setVisible(true);
          	}
          });
          sidebarHoca.add(btnGeri);
          
         
       
        
         tableModel = new DefaultTableModel();
         
         tableModel.addColumn("Ders ID ");
         tableModel.addColumn("Ders Adı");
         tableModel.addColumn("Ders Kontenjan");
         tableModel.addColumn("Ders AKTS");

         table = new JTable(tableModel);
         table.setBackground(new Color(227, 208, 249));
         JScrollPane scrollPane = new JScrollPane(table);
         scrollPane.setBackground(new Color(227, 208, 249));
         scrollPane.setBounds(242, 20, 1462, 633);
         contentPane.add(scrollPane);
         String dbURL = "jdbc:postgresql://localhost:5432/Db_yazlab1";
         String username = "postgres";
         String password = "12345678sk";
         try {
             
             Connection connection = DriverManager.getConnection(dbURL, username, password);
             Statement statement = connection.createStatement();

             String query = "SELECT * FROM ders_hoca_tablo WHERE hoca_id="+sifre1;
             ResultSet resultSet = statement.executeQuery(query);

             // Veritabanından verileri alarak tabloya ekle
             while (resultSet.next()) {
                 
                 String dersID = resultSet.getString("ders_id");
                 String dersAd = resultSet.getString("ders_ad");
                 int derskont = resultSet.getInt("ders_kont");
                 String dersAKTS = resultSet.getString("ders_akts");

                 tableModel.addRow(new Object[]{dersID, dersAd, derskont, dersAKTS});
             }

             connection.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
         ArrayList<String> ilgiAlaniads = new ArrayList<>();
         try (Connection connection = DriverManager.getConnection(dbURL, username, password)) {
             if (connection != null) {
                 System.out.println("Veritabanına bağlandı!");

                  // 

                 
                 String query = "SELECT ilgialani_ad FROM hoca_ilgialani_tablo WHERE hoca_id = ?";
                 try (PreparedStatement statement = connection.prepareStatement(query)) {
                     statement.setInt(1, sifre1);
                     ResultSet resultSet = statement.executeQuery();

                     while (resultSet.next()) {
                         String ilgiAlaniad = resultSet.getString("ilgialani_ad");
                         ilgiAlaniads.add(ilgiAlaniad);
                     }
                 }

                 System.out.println("Hocanın ilgi alanları: " + ilgiAlaniads);
             }
         } catch (SQLException e) {
             System.err.println("Veritabanı hatası: " + e.getMessage());
         }
         DefaultListModel<String> model = new DefaultListModel<>();
         for (String ilgiAlaniAd : ilgiAlaniads) {
             model.addElement(ilgiAlaniAd);
         }
         
         JList<String> ilgiAlanlariList = new JList<>(model);
         scrollPane2_1 = new JScrollPane(ilgiAlanlariList);
         scrollPane2_1.setBounds(100, 207, 105, 72);
         sidebarHoca.add(scrollPane2_1);
       
       
      
         JLabel lblIlgıAlanı = new JLabel("İLGİ ALANI");
         lblIlgıAlanı.setFont(new Font("Tahoma", Font.PLAIN, 16));
         lblIlgıAlanı.setForeground(new Color(227, 208, 249));
         lblIlgıAlanı.setBounds(6, 213, 180, 31);
         sidebarHoca.add(lblIlgıAlanı);
         
         
         
         
         
         

         JButton btnTalep = new JButton("Talep Oluştur");
         btnTalep.setFont(new Font("Tahoma", Font.PLAIN, 16));
         btnTalep.setForeground(SystemColor.textHighlight);
         btnTalep.setBounds(16, 408, 170, 29);
         btnTalep.addActionListener(new ActionListener() {
           	public void actionPerformed(ActionEvent e) {
           		hoca_AnasayfaDersList.super.setVisible(false);
           		hoca_talep hoca_talep = new hoca_talep(sifre1,ad,soyad);
           		hoca_talep.setVisible(true);
           	}
           });
         sidebarHoca.add(btnTalep);
         
         JLabel lblKriterDersler = new JLabel("KRİTER DERSLER");
         lblKriterDersler.setForeground(new Color(227, 208, 249));
         lblKriterDersler.setFont(new Font("Tahoma", Font.PLAIN, 16));
         lblKriterDersler.setBounds(6, 459, 180, 31);
         sidebarHoca.add(lblKriterDersler);
         ArrayList<String> kriterdersads = new ArrayList<>();
         try (Connection connection = DriverManager.getConnection(dbURL, username, password)) {
             if (connection != null) {
                 System.out.println("Veritabanına bağlandı!");

                  // 

                 
                 String query = "SELECT kriter_ders_ad FROM kriter_ders_tablo WHERE kriter_hoca_id = ?";
                 try (PreparedStatement statement = connection.prepareStatement(query)) {
                     statement.setInt(1, sifre1);
                     ResultSet resultSet = statement.executeQuery();

                     while (resultSet.next()) {
                         String kriterdersad = resultSet.getString("kriter_ders_ad");
                         kriterdersads.add(kriterdersad);
                     }
                 }

                 System.out.println("Hocanın ilgi alanları: " + kriterdersads);
             }
         } catch (SQLException e) {
             System.err.println("Veritabanı hatası: " + e.getMessage());
         }
         DefaultListModel<String> model1 = new DefaultListModel<>();
         for (String kriterdersAd : kriterdersads) {
             model1.addElement(kriterdersAd);
         }

         // JList oluşturun ve DefaultListModel ile doldurun
         JList<String> kriterdersList = new JList<>(model1);

         // JScrollPane oluşturun ve JList'i içine yerleştirin
         JScrollPane scrollPane2 = new JScrollPane(kriterdersList);
         scrollPane2.setBounds(16, 501, 170, 134);
         sidebarHoca.add(scrollPane2);
         
        
         
       
      
    }
}