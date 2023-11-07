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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.util.*;

public class ogrenci_transkript_arayuz extends JFrame {

    private JPanel contentPane;
    private JPanel sidebarTranskript;
    
    private JLabel lblNewLabel;
    private JLabel lblSoyad;
    private JLabel lblNumara;
    private JLabel lblNewLabelFoto;
    private JTextField textField;
   
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	  

                    ogrenci_transkript_arayuz frame = new ogrenci_transkript_arayuz();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public ogrenci_transkript_arayuz(int sifre1,String a,String b) {
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

        sidebarTranskript = new JPanel();
        sidebarTranskript.setBackground(new Color(160, 147, 219));
        sidebarTranskript.setBounds(0, 0, 216, 1046);
          contentPane.add(sidebarTranskript);
          sidebarTranskript.setLayout(null);
          

          
          lblNewLabel = new JLabel(a);
          lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
          lblNewLabel.setBackground(new Color(227, 208, 249));
          lblNewLabel.setForeground(new Color(227, 208, 249));
          lblNewLabel.setBounds(6, 150, 152, 31);
          sidebarTranskript.add(lblNewLabel);
          
          lblSoyad = new JLabel(b);
          lblSoyad.setFont(new Font("Tahoma", Font.PLAIN, 16));
          lblSoyad.setForeground(new Color(227, 208, 249));
          lblSoyad.setBounds(6, 208, 152, 31);
          sidebarTranskript.add(lblSoyad);
          
         
          
         lblNewLabelFoto= new JLabel("New label");
          Image img = new ImageIcon(this.getClass().getResource("/img.png")).getImage();
          lblNewLabelFoto .setIcon(new ImageIcon(img));
          lblNewLabelFoto.setBounds(29, 16, 95, 89);
          sidebarTranskript.add(lblNewLabelFoto);
          
          JLabel lblNewLabel_1 = new JLabel("       LÜTFEN TRANSKRİPTİNİZİ YÜKLEYİNİZ!!!!");
          lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 18));
          lblNewLabel_1.setForeground(new Color(149, 83, 246));
          lblNewLabel_1.setBounds(720, 401, 407, 29);
          contentPane.add(lblNewLabel_1);
          
          textField = new JTextField();
          textField.setForeground(new Color(149, 83, 246));
          textField.setBounds(736, 440, 407, 42);
          contentPane.add(textField);
          textField.setColumns(10);
          
          JButton btnNewButton = new JButton("yükle");
          btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
          btnNewButton.setForeground(new Color(149, 83, 246));
          btnNewButton.setBounds(844, 510, 179, 29);
          contentPane.add(btnNewButton);
          btnNewButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				String path=textField.getText();
    				PdfReaderDemo(path);
    				String metinString=txt_okuma();
    				String agno_okumeString=agno_okuma();
    				ArrayList<Ders>dersListesi=new ArrayList<>();
    				Dersler(metinString,dersListesi,sifre1,a,b);
    				for(int a=0;a<dersListesi.size();a++)
    		    	{
    		    		System.out.println("Ders Id :"+dersListesi.get(a).getDersId() +" Ders Adi :"+dersListesi.get(a).getDersAdi()+" Ders Akts :"+dersListesi.get(a).getakts()+" Ders Harf Notu :"+dersListesi.get(a).getharfNotu());
    		    	}
    				try {
    		            Connection connection = DriverManager.getConnection(dbURL,username, password);
    		           

    		            String sql = "INSERT INTO ogrenci_ders_tablo (ogrenci_id, ders_id, harf_not, ders_ad, ders_akts) VALUES (?, ?, ?, ?, ?)";
    		            PreparedStatement preparedStatement = connection.prepareStatement(sql);

    		            for (Ders ders : dersListesi) {
    		                preparedStatement.setInt(1, sifre1); // Öğrenci ID'sini ayarlayın
    		                preparedStatement.setString(2, ders.getDersId());
    		                preparedStatement.setString(3, ders.getharfNotu());
    		                preparedStatement.setString(4, ders.getDersAdi());
    		                preparedStatement.setString(5, ders.getakts());

    		                preparedStatement.executeUpdate();
    		            }

    		            preparedStatement.close();
    		            connection.close();
    		            System.out.println("Dersler veritabanına eklendi.");
    		        } catch (SQLException m) {
    		            m.printStackTrace();
    		        }
    				try {
    		            Connection connection = DriverManager.getConnection(dbURL,username, password);
    		           

    		            String query = "UPDATE ogrenci_tablo SET ogrenci_agno ="+agno_okumeString +" WHERE ogrenci_id="+sifre1;
    		            PreparedStatement preparedStatement = connection.prepareStatement(query);
    		            int rowsUpdated = preparedStatement.executeUpdate();

                        if (rowsUpdated > 0) {
                            System.out.println("Satır(lar) başarıyla güncellendi.");
                        } else {
                            System.out.println("Güncellenecek satır bulunamadı.");
                        }
    		            

    		           
    		          
    		        } catch (SQLException m) {
    		            m.printStackTrace();
    		        }
    				
    				ogrenci_transkript_arayuz.super.setVisible(false);
    				ogrenci_anasayfa anasayfa=new ogrenci_anasayfa(sifre1, a, b);
    				anasayfa.setVisible(true);
    				
    			}
    		});
          JButton btnGeri = new JButton("Geri");
          btnGeri.setFont(new Font("Tahoma", Font.PLAIN, 16));
          btnGeri.setForeground(new Color(144, 135, 255));
          btnGeri.setBounds(29, 984, 152, 29);
          btnGeri.addActionListener(new ActionListener() {
          	public void actionPerformed(ActionEvent e) {
          		ogrenci_transkript_arayuz.super.setVisible(false);
          		ogrenci_anasayfa ogrenci_anasayfa = new ogrenci_anasayfa(sifre1,a,b);
          		ogrenci_anasayfa.setVisible(true);
          	}
          });
          
          
          sidebarTranskript.add(btnGeri);
       
        
     
    
        
     
     
    
    }
    public static void PdfReaderDemo(String path)
    {
    	 PDDocument pd;
    	 BufferedWriter wr;
    	 try {
    	         File input = new File(path); 
    	         File output = new File("C:\\Users\\samethan\\Desktop\\SampleText.txt"); 
    	         pd = PDDocument.load(input);
    	         System.out.println(pd.getNumberOfPages());
    	         System.out.println(pd.isEncrypted());
    	         pd.save("CopyOfBill.pdf"); 
    	         PDFTextStripper stripper = new PDFTextStripper();
    	         stripper.setStartPage(1); 
    	         stripper.setEndPage(3); 
    	         wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
    	         stripper.writeText(pd, wr);
    	         if (pd != null) {
    	             pd.close();
    	         }
    	        
    	        wr.close();
    	 } catch (Exception e){
    	         e.printStackTrace();
    	        }
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }
    public static String txt_okuma()
    {
    	
    	BufferedReader br;
    	StringBuilder combinedText = null ;
    	try {
            br = new BufferedReader(new FileReader("C:\\Users\\samethan\\Desktop\\SampleText.txt"));
            String line;
            combinedText = new StringBuilder(); 
            boolean insideSection = false; 

            while ((line = br.readLine()) != null) {
                if (line.contains("Comment")) {
                    
                    insideSection = true;
                } else if (line.contains("DNO:")) {
                    
                    insideSection = false;
                } else if (insideSection) {
                    
                    combinedText.append(line);
                    String boslukString ="\n";
                    combinedText.append(boslukString);
                }
            }

            br.close();
            

            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    	return combinedText.toString();
    	
    	
    }
    public static String agno_okuma()
    {
    	String dosyaAdi = "C:\\Users\\samethan\\Desktop\\SampleText.txt";
    
    String anahtarKelime = "(Grading System) (4.0 Scale)";
    String sonrakiSatir = null;
    try {
        
        FileReader fileReader = new FileReader(dosyaAdi);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String satir;
        boolean anahtarKelimeBulundu = false;
        

        // Dosyayı satır satır oku
        while ((satir = bufferedReader.readLine()) != null) {
            if (anahtarKelimeBulundu) {
                // Anahtar kelime bulunduysa, hemen sonraki satırı al
                sonrakiSatir = satir;
                break;
            }

            if (satir.contains(anahtarKelime)) {
                // Anahtar kelimeyi bul
                anahtarKelimeBulundu = true;
            }
        }

        // Dosyayı kapat
        bufferedReader.close();

        if (sonrakiSatir != null) {
        	sonrakiSatir=sonrakiSatir.substring(0, 4);
          
        } else {
            System.out.println("Hedef satır bulunamadı.");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return sonrakiSatir;
    	
    }
    
    public static void Dersler(String degerString, ArrayList<Ders> dersListesi,int sifre,String a,String b) {

    	String satirlar[]=degerString.split("\n");

    	for (int i = 0; i < satirlar.length; i += 3) {
            
    		ogrenci_transkript_arayuz anaArayuz = new ogrenci_transkript_arayuz(sifre, a, b); // ogrenci_transkript_arayuz sınıfının bir örneği
    		ogrenci_transkript_arayuz.Ders ders = anaArayuz.new Ders(); // Ders sınıfının bir örneği

    		String idString= satirlar[i].substring(0, 6);
            String isimString = satirlar[i].substring(7);
            if(isimString.charAt(0)==32)
            {
            	isimString=isimString.substring(1);
            }
            ders.setDersId(idString);
            ders.setDersAdi(isimString);
            dersListesi.add(ders);
           
           
           
           
        }
    	int c=0;
    	
    	for (int i = 2; i < satirlar.length; i += 3) {
            
    	  String bolme[]=satirlar[i].split(" ");
    	  int m=0;
           
           for(int k=0;k<bolme[6].length();k++)
           {
        	  
        	   if(Character.isUpperCase(bolme[6].charAt(k)))
        	   {
        		   m=k-1;
        	   }
           }
           String harfNotu=bolme[6] .substring(m);
           if(harfNotu.contains("0"))
           {
        	   harfNotu=harfNotu.substring(1);
        	   
        	   
           }
           dersListesi.get(c).setHarfNotu(harfNotu);
           c++;
           
          
      
           
        }
    	c=0;
    	for (int i = 2; i < satirlar.length; i += 3) {
            
      	  String bolme[]=satirlar[i].split(" ");
      	 
             
            
         String akts=bolme[5];
          	 
        
        dersListesi.get(c).setakts(akts);
        c++;
             
          }
    	
		
	}
    class Ders {
        private String dersId;
        private String dersAdi;
        private String harfNotu;
        private String akts;
    public Ders() {
    	this.akts=null;
    	this.dersAdi=null;
    	this.dersId=null;
    	this.harfNotu=null;
    }
       
        public String getDersId() {
            return this.dersId;
        }
        public String getakts() {
            return this.akts;
        }

        public String getDersAdi() {
            return this.dersAdi;
        }

       

        public String getharfNotu() {
            return this.harfNotu;
        }
        
        public void setDersId(String dersId) {
            this.dersId = dersId;
        }
        public void setakts(String akts) {
            this.akts = akts;
        }


        public void setDersAdi(String dersAdi) {
            this.dersAdi = dersAdi;
        }

       

        public void setHarfNotu(String harfNotu) {
            this.harfNotu = harfNotu;
        }


        
    }
}
