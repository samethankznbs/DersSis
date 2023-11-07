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

public class hoca_AnasayfaOgrenciList extends JFrame {

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
    private String ogrenciadString;
    private String ogrencisoyadString;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	hoca_AnasayfaOgrenciList frame = new hoca_AnasayfaOgrenciList();
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
    public hoca_AnasayfaOgrenciList(int sifre1, String ad, String soyad) {
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

   
        sidebarHoca = new JPanel();
        sidebarHoca.setBackground(new Color(160, 147, 219));
        sidebarHoca.setBounds(0, 0, 199, 1045);

          contentPane.add(sidebarHoca);
          sidebarHoca.setLayout(null);
          
          JButton buttonResim = new JButton("Icon");
          Image img = new ImageIcon(yonetici.class.getResource("/img.png")).getImage();
          buttonResim.setIcon(new ImageIcon(img));
          sidebarHoca.add(buttonResim);
          
          lblNewLabel = new JLabel(ad);
          lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
          lblNewLabel.setForeground(Color.WHITE);
          lblNewLabel.setBounds(6, 118, 152, 31);
          sidebarHoca.add(lblNewLabel);
          
          lblSoyad = new JLabel(soyad);
          lblSoyad.setFont(new Font("Tahoma", Font.PLAIN, 14));
          lblSoyad.setForeground(Color.WHITE);
          lblSoyad.setBounds(6, 159, 152, 31);
          sidebarHoca.add(lblSoyad);
          
         
       
          
          lblNewLabelFoto= new JLabel("New label");
          Image img2 = new ImageIcon(this.getClass().getResource("/img.png")).getImage();
          lblNewLabelFoto .setIcon(new ImageIcon(img));
          lblNewLabelFoto.setBounds(29, 16, 95, 89);
          sidebarHoca.add(lblNewLabelFoto);
          
          JButton btnDersListesineGec = new JButton("Ders Listesi");
          btnDersListesineGec.setFont(new Font("Tahoma", Font.PLAIN, 12));
          btnDersListesineGec.setForeground(SystemColor.textHighlight);
          btnDersListesineGec.setBounds(17, 275, 158, 29);
          btnDersListesineGec.addActionListener(new ActionListener() {
       	 
       	    public void actionPerformed(ActionEvent e) {
       	    	
       	    	hoca_AnasayfaOgrenciList.super.setVisible(false);
       	    	hoca_AnasayfaDersList hoca_AnasayfaDersList = new hoca_AnasayfaDersList(sifre1, ad, soyad);
       	    	hoca_AnasayfaDersList.setVisible(true);
       	    	
       	      
       	    }
       	});
          sidebarHoca.add(btnDersListesineGec);
          
          JButton btnGelenKutusu = new JButton("Gelen Kutusu");
          btnGelenKutusu.setFont(new Font("Tahoma", Font.PLAIN, 12));
          btnGelenKutusu.setForeground(SystemColor.textHighlight);
          btnGelenKutusu.setBounds(17, 326, 158, 29);
          btnGelenKutusu.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  			hoca_AnasayfaOgrenciList.super.setVisible(false);
  			mesajlarimHoca mesajlarimHoca = new mesajlarimHoca(sifre1, ad, soyad);
  			mesajlarimHoca.setVisible(true);
  				
  			}
  		});
          
          
          sidebarHoca.add(btnGelenKutusu);
          
          JButton btnGeri = new JButton("Geri");
          btnGeri.setFont(new Font("Tahoma", Font.PLAIN, 12));
          btnGeri.setForeground(SystemColor.textHighlight);
          btnGeri.setBounds(29, 990, 146, 29);
          btnGeri.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		hoca_AnasayfaOgrenciList.super.setVisible(false);
            		hoca hoca = new hoca();
            		hoca.setVisible(true);
            	}
            });
          sidebarHoca.add(btnGeri);
          
         
       
        
         tableModel = new DefaultTableModel();
         tableModel.addColumn("Öğrenci ID");
         tableModel.addColumn("Ders ID ");
         tableModel.addColumn("Ders Adı");
         tableModel.addColumn("Ders Akts");
         tableModel.addColumn("Öğrenci Ad");
         tableModel.addColumn("Öğrenci Soyad");

         table = new JTable(tableModel);
         table.setBackground(new Color(227, 208, 249));
         JScrollPane scrollPane = new JScrollPane(table);
         scrollPane.setBackground(new Color(227, 208, 249));
         scrollPane.setBounds(215, 28, 1516, 587);
         contentPane.add(scrollPane);
         
         try {
        	    Connection connection = DriverManager.getConnection(dbURL, username, password);
        	    Statement statement = connection.createStatement();

        	    String query = "SELECT * FROM ogrenci_ders_tablo WHERE hoca_id=" + sifre1;
        	    ResultSet resultSet = statement.executeQuery(query);

        	    while (resultSet.next()) {
        	        int ogrenciid = resultSet.getInt("ogrenci_id");
        	        String dersID = resultSet.getString("ders_id");
        	        String dersAd = resultSet.getString("ders_ad");
        	        String dersaktString = resultSet.getString("ders_akts");

        	        Connection connection1 = DriverManager.getConnection(dbURL, username, password);
        	        Statement statement1 = connection1.createStatement();

        	        String query1 = "SELECT * FROM ogrenci_tablo WHERE ogrenci_id=" + ogrenciid;
        	        ResultSet resultSet1 = statement1.executeQuery(query1);
        	        while (resultSet1.next()) {
        	            String ogrenciadString = resultSet1.getString("ogrenci_ad");
        	            String ogrencisoyadString = resultSet1.getString("ogrenci_soyad");
        	            tableModel.addRow(new Object[]{ogrenciid, dersID, dersAd, dersaktString, ogrenciadString, ogrencisoyadString});
        	        }
        	        connection1.close();
        	        resultSet1.close();
        	    }

        	    connection.close();
        	    resultSet.close();
        	} catch (Exception e) {
        	    e.printStackTrace();
        	}
        
        
        

       
      

         
         
         
         
         
         
       
      
    }
}