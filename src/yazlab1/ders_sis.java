package yazlab1;

import java.awt.Color;
import javax.swing.Timer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.LineBreakMeasurer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import java.awt.SystemColor;


public class ders_sis extends JFrame {

	private JPanel contentPane;
	private Integer kisi_id; 
	



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ders_sis frame = new ders_sis();
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
	public ders_sis() {
		 String dbURL = "jdbc:postgresql://localhost:5432/Db_yazlab1";
         String username = "postgres";
         String password = "12345678sk";
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1753, 1082);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(227, 208, 249));
		contentPane.setBackground(new Color(227, 208, 249));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		; 
		
		
	    
		

		

		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("             DERS SİSTEMİ");
		lblNewLabel.setBounds(471, 119, 861, 103);
		lblNewLabel.setForeground(new Color(160, 147, 219));
		lblNewLabel.setBackground(UIManager.getColor("RadioButton.light"));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 57));
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Yönetici Giriş");
		btnNewButton.setBounds(126, 403, 356, 238);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.setToolTipText("");
		btnNewButton.setForeground(new Color(193, 118, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ders_sis.super.setVisible(false);
				yonetici yonetici=new yonetici();
				yonetici.setVisible(true);
				
			}
		});
		btnNewButton.setBackground(UIManager.getColor("Button.foreground")); // Yönetici Giriş butonunun arka plan rengini mavi yapar
         contentPane.add(btnNewButton);
		
		JButton btnNewButton1 = new JButton("Öğrenci Giriş");
		btnNewButton1.setToolTipText("");
		btnNewButton1.setForeground(new Color(193, 118, 255));
		btnNewButton1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton1.setBackground(UIManager.getColor("Button.select"));
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ders_sis.super.setVisible(false);
				ogrenci ogrenci=new ogrenci();
			      ogrenci.setVisible(true);
			}
		});
		btnNewButton1.setBounds(691, 403, 380, 238);
		contentPane.add(btnNewButton1);
		
		JButton btnNewButton2 = new JButton("Hoca Giriş");
		btnNewButton2.setToolTipText("");
		btnNewButton2.setForeground(new Color(193, 118, 255));
		btnNewButton2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton2.setBackground(UIManager.getColor("Button.select"));
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ders_sis.super.setVisible(false);
				hoca hoca=new hoca();
			      hoca.setVisible(true);
			}
		});
		
		
		btnNewButton2.setBounds(1275, 403, 356, 238);
		contentPane.add(btnNewButton2);
		
	
	}
}