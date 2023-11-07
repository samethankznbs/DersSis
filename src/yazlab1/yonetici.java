package yazlab1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class yonetici extends JFrame {

    private JPanel contentPane;
    private JPanel sidebarYonetici;
    private JTextField textField_yoneticiAd;
    private JTextField textField_yoneticiSifre;
    private JLabel lblNewLabelFoto;
    private Integer kisi_id; 

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    yonetici frame = new yonetici();
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
    public yonetici() {
    	 String dbURL = "jdbc:postgresql://localhost:5432/Db_yazlab1";
         String username = "postgres";
         String password = "12345678sk";
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1757, 1085);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(227, 208, 249));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Sidebar oluştur
        sidebarYonetici = new JPanel();
        sidebarYonetici.setBackground(new Color(160, 147, 219));
        sidebarYonetici.setBounds(0, 0, 215, 1048);// Kenar çubuğunun boyutunu ayarlayın
          contentPane.add(sidebarYonetici);
          sidebarYonetici.setLayout(null);
          
          lblNewLabelFoto= new JLabel("New label");
          Image img = new ImageIcon(this.getClass().getResource("/img.png")).getImage();
          lblNewLabelFoto .setIcon(new ImageIcon(img));
          lblNewLabelFoto.setBounds(50, 20, 95, 89);
          sidebarYonetici.add(lblNewLabelFoto);
       
        
        textField_yoneticiAd = new JTextField();
        textField_yoneticiAd.setForeground(new Color(149, 83, 246));
        textField_yoneticiAd.setBounds(912, 390, 321, 38);
        contentPane.add(textField_yoneticiAd);
        textField_yoneticiAd.setColumns(10);
        
        JLabel lblNewLabel_yoneticiAd = new JLabel("yönetici adı : ");
        lblNewLabel_yoneticiAd.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_yoneticiAd.setForeground(new Color(171, 77, 247));
        lblNewLabel_yoneticiAd.setBounds(765, 392, 122, 28);
        contentPane.add(lblNewLabel_yoneticiAd);
        
        JLabel lblNewLabel_yoneticiSifre= new JLabel("yönetici şifre :");
        lblNewLabel_yoneticiSifre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_yoneticiSifre.setForeground(new Color(171, 77, 247));
        lblNewLabel_yoneticiSifre.setBounds(765, 467, 148, 26);
        contentPane.add(lblNewLabel_yoneticiSifre);
        
        textField_yoneticiSifre = new JTextField();
        textField_yoneticiSifre.setForeground(new Color(149, 83, 246));
        textField_yoneticiSifre.setColumns(10);
        textField_yoneticiSifre.setBounds(912, 464, 321, 38);
        contentPane.add(textField_yoneticiSifre);
        JButton btnGeri = new JButton("Geri");
        btnGeri.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnGeri.setForeground(new Color(160, 147, 219));
        btnGeri.setBounds(10, 1009, 195, 29);
        btnGeri.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		yonetici.super.setVisible(false);
        		ders_sis ders_sis = new ders_sis();
        		ders_sis.setVisible(true);
        	}
        });
        
        
        sidebarYonetici.add(btnGeri);
        
        JButton btnNewButton = new JButton("giris");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(textField_yoneticiAd.getText().equals("yonetici") && textField_yoneticiSifre.getText().equals("123"))
        		{
        			yonetici.super.setVisible(false);
        		yonetici_secme yonetici_secme = new yonetici_secme();
        		yonetici_secme.setVisible(true);
        		}
        		else {
					System.out.println("giriş başarısız");
				}
        		
        	}
        });
        btnNewButton.setForeground(new Color(171, 77, 247));
        btnNewButton.setBounds(890, 552, 161, 45);
        contentPane.add(btnNewButton);
    }
}