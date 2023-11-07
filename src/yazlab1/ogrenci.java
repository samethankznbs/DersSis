package yazlab1;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Identity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class ogrenci extends JFrame {

    private JPanel contentPane;
    private JPanel sidebarOgrenci;
    private JTextField textField_OgrenciAd;
    private JTextField textField_Ogrencisoyad;
    private JLabel lblNewLabel;
    private JLabel lblSoyad;
    private JLabel lblNumara;
    private JLabel lblNewLabelFoto;
    private JTextField textField_sifre;
    private Integer kisi_id;
	private JLabel sureLabel; 
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ogrenci frame = new ogrenci();
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
    public ogrenci() {
    	 String dbURL = "jdbc:postgresql://localhost:5432/Db_yazlab1";
         String username = "postgres";
         String password = "12345678sk";
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1755, 1083);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(227, 208, 249));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Sidebar oluştur
        sidebarOgrenci = new JPanel();
        sidebarOgrenci.setBackground(new Color(160, 147, 219));
        sidebarOgrenci.setBounds(0, 0, 215, 1046);// Kenar çubuğunun boyutunu ayarlayın
          contentPane.add(sidebarOgrenci);
          sidebarOgrenci.setLayout(null);
          
          JButton buttonResim = new JButton("Icon");
          Image img = new ImageIcon(yonetici.class.getResource("/img.png")).getImage();
          buttonResim.setIcon(new ImageIcon(img));
          sidebarOgrenci.add(buttonResim);
          
      
          
          lblNewLabelFoto= new JLabel("New label");
          Image img2 = new ImageIcon(this.getClass().getResource("/img.png")).getImage();
          lblNewLabelFoto .setIcon(new ImageIcon(img));
          lblNewLabelFoto.setBounds(57, 20, 95, 89);
          sidebarOgrenci.add(lblNewLabelFoto);
       
        
        textField_OgrenciAd = new JTextField();
        textField_OgrenciAd.setBounds(914, 350, 340, 38);
        contentPane.add( textField_OgrenciAd);
        textField_OgrenciAd.setColumns(10);
        
        JLabel lblNewLabel_OgrenciAd = new JLabel("öğrenci adı : ");
        lblNewLabel_OgrenciAd.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_OgrenciAd.setForeground(new Color(171, 77, 247));
        lblNewLabel_OgrenciAd.setBounds(739, 360, 122, 28);
        contentPane.add(lblNewLabel_OgrenciAd);
        
        JLabel lblNewLabel_OgrenciSifre= new JLabel("öğrenci şifre :");
        lblNewLabel_OgrenciSifre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_OgrenciSifre.setForeground(new Color(171, 77, 247));
        lblNewLabel_OgrenciSifre.setBounds(739, 509, 122, 30);
        contentPane.add(lblNewLabel_OgrenciSifre);
        
        textField_Ogrencisoyad = new JTextField();
        textField_Ogrencisoyad.setColumns(10);
        textField_Ogrencisoyad.setBounds(912, 428, 342, 38);
        contentPane.add(textField_Ogrencisoyad);
        
        
        JButton btnNewButton = new JButton("giris");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton.setForeground(new Color(171, 77, 247));
        btnNewButton.setBounds(855, 578, 200, 29);
        contentPane.add(btnNewButton);
        
        
        
        textField_sifre = new JTextField();
        textField_sifre.setColumns(10);
        textField_sifre.setBounds(912, 501, 342, 38);
        contentPane.add(textField_sifre);
        
        
        JLabel lblNewLabel_OgrenciSifre_1 = new JLabel("öğrenci soyadı :");
        lblNewLabel_OgrenciSifre_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_OgrenciSifre_1.setForeground(new Color(171, 77, 247));
        lblNewLabel_OgrenciSifre_1.setBounds(739, 438, 122, 28);
        contentPane.add(lblNewLabel_OgrenciSifre_1);
    	btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
		        	    Connection connection = DriverManager.getConnection(dbURL, username, password);
		        	    String sql = "SELECT * FROM ogrenci_tablo WHERE ogrenci_id = ? AND ogrenci_ad = ? AND ogrenci_soyad = ?";
		        	    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		        	        String ad=textField_OgrenciAd.getText();
		        	        String soyad=textField_Ogrencisoyad.getText();
		        	        String sifre=textField_sifre.getText();
		        	        int sifre1 = Integer.parseInt(sifre);
		        	        System.out.println(sifre1);
		        	    	
		        	    	
		        	    	preparedStatement.setString(2, ad);
		        	        preparedStatement.setString(3, soyad);
		        	        preparedStatement.setInt(1, sifre1);
		        	        ResultSet resultSet = preparedStatement.executeQuery();

		        	        if (resultSet.next()) {
		        	            
		        	            System.out.println("Giriş başarılı!");
		        	            ogrenci.super.setVisible(false);
		        				ogrenci_anasayfa ogrenci_anasayfa = new ogrenci_anasayfa(sifre1,ad,soyad);
		        				ogrenci_anasayfa.setVisible(true);
		        	        } else {
		        	            // Sonuç bulunamadı, giriş başarısız
		        	            System.out.println("Giriş başarısız!");
		        	        }
		        	    } catch (SQLException a) {
		        	        a.printStackTrace();
		        	    }
		        	    
		        	    
		        	} catch (SQLException c) {
		        	    c.printStackTrace();
		        	}
				
			}
		});
    	JButton btnGeri = new JButton("Geri");
    	btnGeri.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnGeri.setForeground(new Color(179, 158, 255));
        btnGeri.setBounds(29, 991, 160, 29);
        btnGeri.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ogrenci.super.setVisible(false);
        		ders_sis ders_sis = new ders_sis();
        		ders_sis.setVisible(true);
        	}
        });
        
        
        sidebarOgrenci.add(btnGeri);
        
        
    }

}