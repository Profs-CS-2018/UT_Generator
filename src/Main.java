import com.swengGUI.BrowseGUI;
import javax.swing.*;

/**
 * Created by Tom Soistmann on 11/10/2017.
 */
public class Main {

    public static void main(String[] args) {
        BrowseGUI bg = new BrowseGUI();
        JFrame frame = new JFrame("Unit Test Generator Tool");
        OutputGenerator output_gen = new OutputGenerator();
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
