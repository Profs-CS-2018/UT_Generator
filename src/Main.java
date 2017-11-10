import com.swengGUI.BrowseGUI;
import com.swengGUI.OutputGenerator;

import javax.swing.*;

/**
 * Main.java
 */
public class Main {

    public static void main(String[] args) {
        OutputGenerator outputGen = new OutputGenerator();
        BrowseGUI bg = new BrowseGUI();
        JFrame frame = new JFrame("Unit Test Generator Tool");
        /**
         * Changes the default theme of JFileChooser
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        frame.setContentPane(new BrowseGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); //this should center the app
    }
}
