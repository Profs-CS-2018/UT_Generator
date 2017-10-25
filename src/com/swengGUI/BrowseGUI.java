package com.swengGUI;

import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;

public class BrowseGUI {

    /**
     * Declaring GUI components below
     */
    private JPanel mainPanel;
    private JTextField textFieldSave;
    private JButton browseButton;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JButton submitButton;
    private JList listFiles;
    private JButton saveButton;
    private JButton btnPreview;
    private JLabel saveFile;
    JFileChooser fc = new JFileChooser();
    JFileChooser fc1 = new JFileChooser();

    public BrowseGUI() {
        DefaultListModel dm = new DefaultListModel();
        /**
         * Action Listener for the Browse Button
         */

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * Enables multiple selection.
                 */
                fc.setMultiSelectionEnabled(true);
                /**
                 *The following line of code can be used to open the file search in a particular directory
                 * */
                fc.setCurrentDirectory(new File("C:\\Users\\aanch\\Desktop\\Fall 2017"));
                /**
                 * The following code adds filter to the file extensions.
                 */

                fc.setFileFilter(new FileNameExtensionFilter("Text Files(.txt)", "txt"));
                fc.setFileFilter(new FileNameExtensionFilter("Java(.java)", "java"));
                fc.setFileFilter(new FileNameExtensionFilter("C++(.cpp)", "cpp"));
                /**
                 * The following code checks if the action of clicking the button takes place
                 * if it does then the user sees the textArea populated with the selected file
                 * names
                 */
                if (e.getSource() == browseButton) {
                    int returnVal = fc.showOpenDialog(mainPanel);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File[] files = fc.getSelectedFiles();
                        if (files.length > 1) {
                            for (int i = 0; i < files.length; i++) {
                                dm.addElement(files[i].getAbsolutePath());
                            }
                        } else {
                            dm.addElement(files[0].getAbsolutePath());
                            //listFiles.setModel(dm);
                        }
                        //dm.addElement(fileNames);
                        listFiles.setModel(dm);
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "Oops! Operation was cancelled.");
                    }
                }
            }
        });

        listFiles.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                /**
                 * JOptionPane implemented below is just for testing purposes.
                 */
                JOptionPane.showMessageDialog(mainPanel, listFiles.getSelectedValue());
                System.out.println(listFiles.getSelectedValue().toString());
                try {
                    FileReader fr = new FileReader(listFiles.getSelectedValue().toString());
                    BufferedReader br = new BufferedReader(fr);
                    String sCurrentLine;
                    /**
                     * trying to create a new Panel here.
                     */
                    JPanel fileContent = new JPanel();
                    JTextArea textArea = new JTextArea();
                    fileContent.add(textArea);
                    /**
                     * Lines below again for testing
                     */
                    textArea.setText("Hello!!!!");
                    while((sCurrentLine = br.readLine())!= null){
                        System.out.println(sCurrentLine);
                    }

                }
                catch (Exception e1)
                {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fc1.setCurrentDirectory(fc.getCurrentDirectory());

                if (e.getSource() == saveButton) {
                    int returnVal = fc1.showSaveDialog(mainPanel);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc1.getSelectedFile();
                        textFieldSave.setText(file.getAbsolutePath());
                        try{
                            FileWriter newFile = new FileWriter(file.getPath());
                            //fw.write(content);
                            //fw.flush();
                            //fw.close();
                        }
                        catch(Exception e1){
                            JOptionPane.showMessageDialog(null, e1.getMessage());
                        }
                        /**
                         * if the user wants to display only file name then the following line of code can be used
                         * textField.setText(file.getName());
                         */
                    }
                }
            }
        });
    }
    public static void main (String[] args)
    {
        JFrame frame = new JFrame("Unit Test Generator Tool");
        /**
         * Changes the default theme of JFileChooser
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        frame.setContentPane(new BrowseGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); //this should center the app
    }


}
