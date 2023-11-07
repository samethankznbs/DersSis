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

public class hoca extends JFrame {

    private JPanel contentPane;
    private JPanel sidebarHoca;
    private JTextField textField_HocaSoyad;
    private JTextField textField_HocaSifre;
    private JLabel lblNewLabelFoto;
    private JButton btnGeri;
    private JTextField textFieldAd;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    hoca frame = new hoca();
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
    public hoca() {
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
          Image img1 = new ImageIcon(this.getClass().getResource("/img.png")).getImage();
          lblNewLabelFoto .setIcon(new ImageIcon(img));
          lblNewLabelFoto.setBounds(46, 24, 95, 89);
          sidebarHoca.add(lblNewLabelFoto);
          
          btnGeri = new JButton("Geri");
          btnGeri.setFont(new Font("Tahoma", Font.PLAIN, 14));
          btnGeri.setForeground(new Color(179, 158, 255));
          btnGeri.setBounds(34, 995, 154, 29);
          btnGeri.addActionListener(new ActionListener() {
          	public void actionPerformed(ActionEvent e) {
          		hoca.super.setVisible(false);
          		ders_sis ders_sis = new ders_sis();
          		ders_sis.setVisible(true);
          	}
          });
          
          
          sidebarHoca.add(btnGeri);
        
        textField_HocaSoyad = new JTextField();
        textField_HocaSoyad.setBounds(867, 395, 366, 38);
        contentPane.add(textField_HocaSoyad);
        textField_HocaSoyad.setColumns(10);
        
        JLabel lblNewLabel_HocaSoyad = new JLabel(" hoca soyad : ");
        lblNewLabel_HocaSoyad.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_HocaSoyad.setForeground(new Color(171, 77, 247));
        lblNewLabel_HocaSoyad.setBounds(733, 405, 182, 28);
        contentPane.add(lblNewLabel_HocaSoyad);
        
        JLabel lblNewLabel_HocaSifre= new JLabel(" hoca şifre :");
        lblNewLabel_HocaSifre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_HocaSifre.setForeground(new Color(171, 77, 247));
        lblNewLabel_HocaSifre.setBounds(733, 484, 194, 37);
        contentPane.add(lblNewLabel_HocaSifre);
        
        textField_HocaSifre = new JTextField();
        textField_HocaSifre.setColumns(10);
        textField_HocaSifre.setBounds(867, 484, 366, 38);
        contentPane.add(textField_HocaSifre);
        
        JButton btnNewButton = new JButton("giris");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton.setForeground(new Color(171, 77, 247));
        btnNewButton.setBounds(892, 575, 171, 29);
        btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	        	    Connection connection = DriverManager.getConnection(dbURL, username, password);
	        	    String sql = "SELECT * FROM hoca_tablo WHERE hoca_id = ? AND hoca_ad = ? AND hoca_soyad = ?";
	        	    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        	        String ad=textFieldAd.getText();
	        	        String soyad=textField_HocaSoyad.getText();
	        	        String sifre=textField_HocaSifre.getText();
	        	        int sifre1 = Integer.parseInt(sifre);
	        	        System.out.println(sifre1);
	        	        System.out.println(ad);
	        	        System.out.println(soyad);
	        	    	
	        	    	
	        	    	preparedStatement.setString(2, ad);
	        	        preparedStatement.setString(3, soyad);
	        	        preparedStatement.setInt(1, sifre1);
	        	        ResultSet resultSet = preparedStatement.executeQuery();

	        	        if (resultSet.next()) {
	        	            
	        	            System.out.println("Giriş başarılı!");
	        	            hoca.super.setVisible(false);
	        				hoca_AnasayfaDersList hoca_anasayfa = new hoca_AnasayfaDersList(sifre1,ad,soyad);
	        				hoca_anasayfa.setVisible(true);
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
        
        contentPane.add(btnNewButton);
        
        JLabel lblNewLabel_HocaAd = new JLabel(" hoca adı : ");
        lblNewLabel_HocaAd.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_HocaAd.setForeground(new Color(171, 77, 247));
        lblNewLabel_HocaAd.setBounds(733, 317, 122, 16);
        contentPane.add(lblNewLabel_HocaAd);
        
        textFieldAd = new JTextField();
        textFieldAd.setColumns(10);
        textFieldAd.setBounds(867, 307, 366, 38);
        contentPane.add(textFieldAd);
    }
}