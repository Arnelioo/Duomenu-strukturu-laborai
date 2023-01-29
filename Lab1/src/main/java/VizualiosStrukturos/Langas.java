package VizualiosStrukturos;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * Klasė sąsajos su meniu atliekamiems veiksmams realizuoti.
 */
public class Langas extends JFrame{
    
    private final JLabel duomenuAntraste = new JLabel("Pradinių duomenų ir rezultatų lentelė:");
    private final JPanel duomenuPanel = new JPanel();
    private Container langoTurinys;
    private final JMenuBar meniuBaras = new JMenuBar();
    private final JTextArea informacija = new JTextArea(20, 80);
    private final JScrollPane zona = new JScrollPane(informacija);
    
    private JMenuItem didzRidosMenu;
    private JMenuItem rikiavimoMenu;
    private JMenuItem atrinkimoMenu;
    private final JLabel laikinasPranesimas = new JLabel("Čia bus pateikti pradiniai duomenys ir rezultatai.", SwingConstants.CENTER);
    
    private TaxiSkaičiavimai metodųKlasė = new TaxiSkaičiavimai();
    
    /**
     * Klasės konstruktorius - nustatymai ir meniu įdiegimas.
     */
    public Langas(){
        Locale.setDefault(Locale.US);
		Font f = new Font("Courier New", Font.PLAIN, 15);
		informacija.setFont(f);
		meniuIdiegimas();
                
                didzRidosMenu.setEnabled(false);
                rikiavimoMenu.setEnabled(false);
                atrinkimoMenu.setEnabled(false);
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
                didzRidosMenu = new JMenuItem("Surasti daugiausiai eksploatuotą automobilį");
                taxiSkai.add(didzRidosMenu);
                didzRidosMenu.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            metodųKlasė.didžiausiaRida();
                            informacija.append("\nDaugiausiai eksploatuotas automobilis: \n\n");
                            informacija.append(metodųKlasė.didžiausiaRida().toString());
                        }
                 });

                atrinkimoMenu = new JMenuItem("Atrinkti pagal markę");
		taxiSkai.add(atrinkimoMenu);
		atrinkimoMenu.addActionListener(this::atrenkaPagalMarke);
                
                rikiavimoMenu = new JMenuItem("Rikiuoti pagal ridą"); 
                taxiSkai.add(rikiavimoMenu);
                rikiavimoMenu.addActionListener(new ActionListener() { 
                        public void actionPerformed(ActionEvent e) {
                                metodųKlasė.rikiuojaPagalRidą(); 
                                informacija.append("\nSurikiuotas sąrašas: \n\n");
                                informacija.append(metodųKlasė.toString());
			}
		});

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
		int rez = fc.showOpenDialog(Langas.this);
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
				JOptionPane.showMessageDialog(Langas.this, klaidosKodas,
					"Skaitymas - rašymas", JOptionPane.INFORMATION_MESSAGE);
			}
			didzRidosMenu.setEnabled(true);
			atrinkimoMenu.setEnabled(true);
                        rikiavimoMenu.setEnabled(true);
		} else if (rez == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(Langas.this,
					"Skaitymo atsisakyta (paspaustas ESC arba Cancel)",
					"Skaitymas - rašymas", JOptionPane.INFORMATION_MESSAGE);
		}
	} 
    
    /**
     * Atrenka automobilius pagal pasirinktą jų markę. Markė įvedama įvedimo lange (JOptionPane.showInputDialog)
     * @param e klasės ActionEvent objektas.
     */
    public void atrenkaPagalMarke(ActionEvent e) {
		String markė = JOptionPane.showInputDialog(Langas.this, "Įveskite markę",
				"Tekstas", JOptionPane.WARNING_MESSAGE);
		if (markė == null) 
		{
			return;
		}
		informacija.append("\n\nAtrinkti " + markė + " markės automobiliai:" + System.lineSeparator() + "\n");
		metodųKlasė.atrinktiPagalMarkę(markė, informacija);
	} 
    
    /**
     * Programos paleidimo taškas: sukuriamas JFrame klasės vaiko (SasajaSuMeniu) objektas, 
     *      nustatomas lango dydis ir vieta ir langas parodomas ekrane (metodas setVisible).
     * @param args argumentų masyvas.
     */
    public static void main(String[] args) {
       Langas frame = new Langas();
       frame.setTitle("Taxi");
       frame.setVisible(true);
       frame.setSize(800,500);
       frame.setLocationRelativeTo(null);
       ImageIcon image = new ImageIcon("TaxiLogo.png");
       frame.setIconImage(image.getImage());
       

       
    }
    
}
