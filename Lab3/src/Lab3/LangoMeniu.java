package Lab3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ResourceBundle;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import laborai.gui.MyException;
import laborai.gui.swing.KsSwing;

public class LangoMeniu extends JMenuBar implements ActionListener {

    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("Lab3.Pranešimai");

    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenuItem jMenuItem11;
    private JMenuItem jMenuItem12;
    private JMenuItem jMenuItem13;
    private JMenuItem jMenuItem21;
    private final Langas lab3Panel;

    public LangoMeniu(Langas lab3Panel) {
        this.lab3Panel = lab3Panel;
        initComponents();
    }

    private void initComponents() {
        // Sukuriama meniu juosta
        jMenu1 = new JMenu(MESSAGES.getString("menu1"));
        super.add(jMenu1);
        jMenuItem11 = new JMenuItem(MESSAGES.getString("menuItem11"));
        jMenuItem11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        jMenuItem12 = new JMenuItem(MESSAGES.getString("menuItem12"));
        jMenuItem12.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        jMenuItem13 = new JMenuItem(MESSAGES.getString("menuItem13"));
        jMenuItem13.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));

        jMenu1.add(jMenuItem11);
        jMenu1.add(jMenuItem12);
        jMenu1.addSeparator();
        jMenu1.add(jMenuItem13);

        jMenu2 = new JMenu(MESSAGES.getString("menu2"));
        super.add(jMenu2);
        jMenuItem21 = new JMenuItem(MESSAGES.getString("menuItem21"));
        jMenuItem21.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.SHIFT_MASK));
        jMenu2.add(jMenuItem21);

        jMenuItem11.addActionListener(this);
        jMenuItem12.addActionListener(this);
        jMenuItem13.addActionListener(this);
        jMenuItem21.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            Object command = ae.getSource();
            if (command.equals(jMenuItem11)) {
                fileChooseMenu();
            } else if (command.equals(jMenuItem12)) {
                KsSwing.ounerr(lab3Panel.getTaOutput(), MESSAGES.getString("msg1"));
            } else if (command.equals(jMenuItem13)) {
                System.exit(0);
            } else if (command.equals(jMenuItem21)) {
               JOptionPane.showOptionDialog(lab3Panel,
                        MESSAGES.getString("author"),
                        MESSAGES.getString("menuItem21"),
                        JOptionPane.OK_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        new String[]{"OK"},
                        null);
            }
        } catch (MyException e) {
            KsSwing.ounerr(lab3Panel.getTaOutput(), e.getMessage());
        } catch (Exception e) {
            KsSwing.ounerr(lab3Panel.getTaOutput(), MESSAGES.getString("msg9"));
            e.printStackTrace(System.out);
        }
    }

    private void fileChooseMenu() throws MyException {
        JFileChooser fc = new JFileChooser(".");

        // Nuimamas "all Files" filtras
        // fc.setAcceptAllFileFilterUsed(false);
        // Papildoma mūsų sukurtais filtrais
        fc.addChoosableFileFilter(new MyFilter());
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            lab3Panel.treeGeneration(file.getAbsolutePath());
        }
    }

    /**
     * Privati klasė, aprašanti failų pasirinkimo dialogo filtrus
     */
    private class MyFilter extends javax.swing.filechooser.FileFilter {

        @Override
        public boolean accept(File file) {
            String filename = file.getName();
            // Rodomos tik direktorijos ir txt failai
            return file.isDirectory() || filename.endsWith(".txt");
        }

        @Override
        public String getDescription() {
            return "*.txt";
        }
    }

}
