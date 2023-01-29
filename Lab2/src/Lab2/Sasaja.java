package Lab2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * Klasė sąsajos su meniu atliekamiems veiksmams realizuoti.
 */
public class Sasaja extends JFrame {

    private final JLabel duomenuAntraste = new JLabel("Pradinių duomenų ir rezultatų lentelė:");
    private final JPanel duomenuPanel = new JPanel();
    private Container langoTurinys;
    private final JMenuBar meniuBaras = new JMenuBar();
    private final JTextArea informacija = new JTextArea(20, 80);
    private final JScrollPane zona = new JScrollPane(informacija);
    
    private JMenuItem rikiavimoMenu;
    private JMenuItem rikiavimoMenu2;
    private JMenuItem rikiavimoMenu3;
    private JMenuItem atrinkimoMenu;
    private JMenuItem atrinkimoMenu2;
    private final JLabel laikinasPranesimas = new JLabel("Čia bus pateikti pradiniai duomenys ir rezultatai.", SwingConstants.CENTER);
    
    private FilmuAtranka metodųKlasė = new FilmuAtranka();
    
    
    /**
     * Klasės konstruktorius - nustatymai ir meniu įdiegimas.
     */
    public Sasaja(){
        Locale.setDefault(Locale.US);
		Font f = new Font("Courier New", Font.PLAIN, 15);
		informacija.setFont(f);
		meniuIdiegimas();
                
                rikiavimoMenu.setEnabled(false);
                rikiavimoMenu2.setEnabled(false);
                rikiavimoMenu3.setEnabled(false);
                atrinkimoMenu.setEnabled(false);
                atrinkimoMenu2.setEnabled(false);
    }
    
    /**
     * Suformuoja meniu, priskiria įvykius ir įdiegia jų veiksmus. 
     */
    private void meniuIdiegimas() {
		setJMenuBar(meniuBaras);
		JMenu mFailai = new JMenu("Failai");
		meniuBaras.add(mFailai);
		JMenu taxiSkai = new JMenu("Skaičiavimai");
		taxiSkai.setMnemonic('a');
		meniuBaras.add(taxiSkai);

		//  Grupė  "Failai" :
		JMenuItem miSkaityti = new JMenuItem("Skaityti duomenis");
		mFailai.add(miSkaityti);
		miSkaityti.addActionListener(this::veiksmaiSkaitantFailą);
		JMenuItem miBaigti = new JMenuItem("Baigti");
		miBaigti.setMnemonic('b');
		mFailai.add(miBaigti);
		miBaigti.addActionListener((ActionEvent e) -> System.exit(0));

		//	Grupė "Skaičiavimai"
                rikiavimoMenu = new JMenuItem("Rikiuoti pagal pelną"); 
                taxiSkai.add(rikiavimoMenu);
                rikiavimoMenu.addActionListener(new ActionListener() { 
                        public void actionPerformed(ActionEvent e) {
                                metodųKlasė.rikiuojaPagalPelną(); 
                                informacija.append("\nSurikiuotas sąrašas: \n\n");
                                informacija.append(metodųKlasė.toString());
			}
		});
                
                rikiavimoMenu2 = new JMenuItem("Rikiuoti pagal žanrą"); 
                taxiSkai.add(rikiavimoMenu2);
                rikiavimoMenu2.addActionListener(new ActionListener() { 
                        public void actionPerformed(ActionEvent e) {
                                metodųKlasė.rikiuojaPagalŽanrą(); 
                                informacija.append("\nSurikiuotas sąrašas: \n\n");
                                informacija.append(metodųKlasė.toString());
			}
		});
                
                rikiavimoMenu3 = new JMenuItem("Rikiuoti pagal metus ir pelną"); 
                taxiSkai.add(rikiavimoMenu3);
                rikiavimoMenu3.addActionListener(new ActionListener() { 
                        public void actionPerformed(ActionEvent e) {
                                metodųKlasė.rikiuojaPagalMetusIrPelną();
                                informacija.append("\nSurikiuotas sąrašas: \n\n");
                                informacija.append(metodųKlasė.toString());
			}
		});
                
                atrinkimoMenu = new JMenuItem("Atrinkti pagal markę");
		taxiSkai.add(atrinkimoMenu);
		atrinkimoMenu.addActionListener(this::atrenkaPagalŽanrą);
                
                atrinkimoMenu2 = new JMenuItem("Atrinkti pagal pelną");
		taxiSkai.add(atrinkimoMenu2);
		atrinkimoMenu2.addActionListener(this::atrenkaPagalPelną);
                

		duomenuPanel.setLayout(new BorderLayout());
		duomenuPanel.add(duomenuAntraste, BorderLayout.NORTH);
		duomenuPanel.add(zona, BorderLayout.CENTER);
                laikinasPranesimas.setFont(new Font("Arial", Font.PLAIN, 18));
		getContentPane().add(laikinasPranesimas);
                
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
    
    /**
     * Metodas yra kviečiamas vykdant meniu punktą "Skaityti duomenis"
     * @param e klasės ActionEvent objektas.
     */
    public void veiksmaiSkaitantFailą(ActionEvent e) {
        laikinasPranesimas.setVisible(false);
		JFileChooser fc = new JFileChooser(".");
		fc.setDialogTitle("Atidaryti failą skaitymui");
		fc.setApproveButtonText("Atidaryti");
		int rez = fc.showOpenDialog(Sasaja.this);
		if (rez == JFileChooser.APPROVE_OPTION) {
			if (!duomenuPanel.isShowing()) {
				langoTurinys = getContentPane();
				langoTurinys.setLayout(new FlowLayout());
				langoTurinys.add(duomenuPanel);
				validate();
			}
			File f1 = fc.getSelectedFile();
			String klaidosKodas = metodųKlasė.loadAndPrint(f1, informacija);
			if (klaidosKodas != null) {
				JOptionPane.showMessageDialog(Sasaja.this, klaidosKodas,
					"Skaitymas - rašymas", JOptionPane.INFORMATION_MESSAGE);
			}
//			didzRidosMenu.setEnabled(true);
			atrinkimoMenu.setEnabled(true);
                        atrinkimoMenu2.setEnabled(true);
                      rikiavimoMenu.setEnabled(true);
                      rikiavimoMenu2.setEnabled(true);
                      rikiavimoMenu3.setEnabled(true);
		} else if (rez == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(Sasaja.this,
					"Skaitymo atsisakyta (paspaustas ESC arba Cancel)",
					"Skaitymas - rašymas", JOptionPane.INFORMATION_MESSAGE);
		}
	} 
    
    /**
     * Atrenka filmus pagal nurodytą žanrą. Žanras įvedamas įvedimo lange (JOptionPane.showInputDialog)
     * @param e klasės ActionEvent objektas.
     */
    public void atrenkaPagalŽanrą(ActionEvent e) {
		String žanras = JOptionPane.showInputDialog(Sasaja.this, "Įveskite filmo žanrą",
				"Tekstas", JOptionPane.WARNING_MESSAGE);
		if (žanras == null) 
		{
			return;
		}
		informacija.append("\n\nAtrinkti " + žanras + " žanro filmai:" + System.lineSeparator() + "\n");
		metodųKlasė.atrinktiPagalŽanrą(žanras, informacija);
	} 
    
    /**
     * Atrenka filmus pagal nurodytą pelną. Pelnas įvedamas įvedimo lange (JOptionPane.showInputDialog)
     * @param e klasės ActionEvent objektas.
     */
    public void atrenkaPagalPelną(ActionEvent e) {
		int pelnas1 = Integer.parseInt(JOptionPane.showInputDialog(Sasaja.this, "Įveskite pirmąją pelno reikšmę",
				"Tekstas", JOptionPane.WARNING_MESSAGE));
                int pelnas2 = Integer.parseInt(JOptionPane.showInputDialog(Sasaja.this, "Įveskite antrąją pelno reikšmę",
				"Tekstas", JOptionPane.WARNING_MESSAGE));
		if (pelnas1 == 0 && pelnas2 == 0) 
		{
			return;
		}
		informacija.append("\n\nAtrinkti intervalo " + "[" + pelnas1 + "mln" + ":" + pelnas2 + "mln" + "]" 
                        + " pelno filmai:" + System.lineSeparator() + "\n");
		metodųKlasė.atrinktiPagalPelną(pelnas1, pelnas2, informacija);
	} 
    
    
    /**
     * Programos paleidimo taškas: sukuriamas JFrame klasės vaiko (SasajaSuMeniu) objektas, 
     *      nustatomas lango dydis ir vieta ir langas parodomas ekrane (metodas setVisible).
     * @param args argumentų masyvas.
     */
    public static void main(String[] args) {
        Sasaja frame = new Sasaja();
       frame.setTitle("Filmai");
       frame.setVisible(true);
       frame.setSize(800,500);
       frame.setLocationRelativeTo(null);
       ImageIcon image = new ImageIcon("filmfoto.png");
       frame.setIconImage(image.getImage());
    }
    
}
