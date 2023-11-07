package yazlab1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class yonetici_secme extends JFrame {

    private JPanel contentPane;
    private JPanel sidebarYonetici;
    private JTextField textField_YoneticiAd;
    private JTextField textField_YoneticiSifre;
    private JLabel lblNewLabel;
    private JLabel lblSoyad;
    private JLabel lblNumara;
    private JLabel lblNewLabelFoto;
    private Integer kisi_id; 

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                     yonetici_secme frame = new yonetici_secme();
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
    public yonetici_secme() {
    	 String dbURL = "jdbc:postgresql://localhost:5432/Db_yazlab1";
         String username = "postgres";
         String password = "12345678sk";
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1753, 1082);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(227, 208, 249));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Sidebar oluştur
        sidebarYonetici = new JPanel();
        sidebarYonetici.setBackground(new Color(160, 147, 219));
        sidebarYonetici.setBounds(0, 10, 216, 1035);// Kenar çubuğunun boyutunu ayarlayın
          contentPane.add(sidebarYonetici);
          sidebarYonetici.setLayout(null);
          
          JButton buttonResim = new JButton("Icon");
          Image img = new ImageIcon(yonetici.class.getResource("/img.png")).getImage();
          buttonResim.setIcon(new ImageIcon(img));
          sidebarYonetici.add(buttonResim);
          
          lblNewLabel = new JLabel("YÖNETİCİ");
          lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
          lblNewLabel.setForeground(new Color(227, 208, 249));
          lblNewLabel.setBounds(6, 172, 152, 31);
          sidebarYonetici.add(lblNewLabel);
          
          lblSoyad = new JLabel("PANELİ");
          lblSoyad.setFont(new Font("Tahoma", Font.PLAIN, 16));
          lblSoyad.setForeground(new Color(227, 208, 249));
          lblSoyad.setBounds(6, 216, 152, 31);
          sidebarYonetici.add(lblSoyad);
          
          
          
          lblNewLabelFoto= new JLabel("New label");
          Image img2 = new ImageIcon(this.getClass().getResource("/img.png")).getImage();
          lblNewLabelFoto .setIcon(new ImageIcon(img));
          lblNewLabelFoto.setBounds(29, 16, 95, 89);
          sidebarYonetici.add(lblNewLabelFoto);
          
          JButton btnNewButton = new JButton("Öğrenci ");
          btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 53));
          btnNewButton.setForeground(new Color(159, 85, 255));
          btnNewButton.setBounds(307, 391, 542, 252);
          contentPane.add(btnNewButton);
          btnNewButton.addActionListener(new ActionListener() {
          	public void actionPerformed(ActionEvent e) {
          		yonetici_secme.super.setVisible(false);
          		yonetici_ogrenciAnasayfa yonetici_ogrenciAnasayfa = new yonetici_ogrenciAnasayfa();
          		yonetici_ogrenciAnasayfa.setVisible(true);
          		
          		
          	}
          });
          
          JButton btnHocaGiri = new JButton("Hoca");
          btnHocaGiri.setFont(new Font("Dialog", Font.PLAIN, 53));
          btnHocaGiri.setForeground(new Color(159, 85, 255));
          btnHocaGiri.setBounds(1091, 391, 572, 260);
          contentPane.add(btnHocaGiri);
          btnHocaGiri.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		yonetici_secme.super.setVisible(false);
            		yonetici_hocaAnasayfa yonetici_hocaAnasayfa = new yonetici_hocaAnasayfa();
            		yonetici_hocaAnasayfa.setVisible(true);
            		
            	}
            });
          JButton btnGeri = new JButton("Geri");
          btnGeri.setFont(new Font("Tahoma", Font.PLAIN, 16));
          btnGeri.setForeground(SystemColor.textHighlight);
          btnGeri.setBounds(6, 976, 199, 29);
          sidebarYonetici.add(btnGeri);
          btnGeri.addActionListener(new ActionListener() {
          	public void actionPerformed(ActionEvent e) {
          		yonetici_secme.super.setVisible(false);
          		yonetici yonetici = new yonetici();
          		yonetici.setVisible(true);
          	
          	}
          });
       
       
        
 
        
       
    }
}