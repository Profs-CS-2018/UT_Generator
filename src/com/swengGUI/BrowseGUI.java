package com.swengGUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class BrowseGUI {

    /**
     * Declaring GUI components below
     */
    private JPanel mainPanel;
    private JTextField textFieldSave;
    private JButton browseButton;
    private JTabbedPane tabbedPane;
    private JButton submitButton;
    private JList listFiles;
    private JButton saveButton;
    private JButton btnPreview;
    private JLabel fixedLabel;
    private JTextArea previewTextArea;
    private JTextField textField1;
    private JButton addFilesButton;
    private JLabel saveFile;
    JFileChooser fc = new JFileChooser();
    JFileChooser fc1 = new JFileChooser();

    public BrowseGUI() {
        DefaultListModel dm = new DefaultListModel();
        fixedLabel = new JLabel("Output Save Destination");
        fixedLabel.setLabelFor(textFieldSave);
        listFiles.setModel(dm);
        /**
         * Action Listener for the Browse Button
         * On being clicked the Browse button opens up a file browser which can be used to
         * select single or multiple files for testing.
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
                fc.setFileFilter(new FileNameExtensionFilter("CPP(.cpp)","cpp"));
                fc.setFileFilter(new FileNameExtensionFilter("C++(.cpp) and (.h)", "cpp", "h"));
                fc.setFileFilter(new FileNameExtensionFilter("C++(.cpp)(.h) and Text Files(.txt)", "cpp", "txt", "h"));

                /**
                 * The following code checks if the action of clicking the button takes place
                 * if it does then the user sees the textArea populated with the selected file
                 * names
                 */
                if (e.getSource() == browseButton) {
                    int returnVal = fc.showOpenDialog(mainPanel);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File[] files = fc.getSelectedFiles(); // the selected files from browse
                        if (files.length > 1) {
                            for (int i = 0; i < files.length; i++) {
                                dm.addElement(files[i].getAbsolutePath()); //add path into the JPanel

                            }
                        } else {
                            dm.addElement(files[0].getAbsolutePath());
                            //listFiles.setModel(dm);
                        }
                        //dm.addElement(fileNames);
                       // listFiles.setModel(dm);
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "Oops! Operation was cancelled.");
                    }
                }
            }
        });
        btnPreview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                /**
                 * The following code checks if the action of clicking the button takes place
                 * if it does then the user sees the textArea on the Preview tab changed to
                 * have the preview of the output file in it.
                 */
                if (e.getSource() == btnPreview) {
                    Object returnVal = listFiles.getSelectedValue();
                    if (returnVal != null) {
                        /**
                         * Selected file has its preview generated and a copy placed into
                         * the textArea on the Preview tab.
                         */

                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "No files selected for preview.");
                    }
                }
            }
        });
        /**
         * Action Listener for the file selected from the JList
         * Upon selection file should open in another window.
         */
        listFiles.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                try {
                    FileReader fr = new FileReader(listFiles.getSelectedValue().toString());
                    BufferedReader br = new BufferedReader(fr);
                    String sCurrentLine;
                    ArrayList<String> fileContent = new ArrayList<>();


                    while ((sCurrentLine = br.readLine()) != null) {
                        System.out.println(sCurrentLine);
                        fileContent.add(sCurrentLine);
                    }

                    //new window. modify later
                    JOptionPane.showMessageDialog(mainPanel, fileContent.get(0), "File Content", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e1) {
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
                        try {
                            FileWriter newFile = new FileWriter(file.getPath());
                            //fw.write(content);
                            //fw.flush();
                            //fw.close();
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(null, e1.getMessage());
                        }
                    }
                }
            }
        });


        /*
        Submit button begins the process of generating the makefile, test fixtures, etc.
        If the files could not be fully generated, a dialogue box pops up informing the user of the failure.
        It redirects to a tab containing the error information.

         */
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try { //begin the middle end attempt to parse and make files
                    throw new Exception();
                } catch (Exception e1) //catch any error which happens to have resulted in generation failure
                {
                    JOptionPane.showMessageDialog(null, "ERROR: See Error Information Tab " +
                            "for details");
                    //clicking OK redirects you to a tabbed pane labelled ERRORS
                    String errors = "Errors";

                    //check if ERRORS tab exists. Create if it does not.
                    if (tabbedPane.indexOfTab(errors) == -1) {
                        tabbedPane.add(errors, new JScrollPane(new JList<>()));
                    }

                    //populate the list of errors. make the text red?

                    //set ERROR tab as currently selected tab
                    tabbedPane.getModel().setSelectedIndex(tabbedPane.indexOfTab(errors));


                }
            }
        });
        addFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == addFilesButton)
                {
                    File addedFile = new File(textField1.getText());
                    boolean fileExists = addedFile.exists();
                    if(fileExists) {
                        dm.addElement(addedFile.getAbsolutePath());
                    }
                    else
                    {
                        int input = JOptionPane.showOptionDialog(null, "File Not Found", "Error Message", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
                        if(input == JOptionPane.OK_OPTION){
                            textField1.setText("");
                        }
                    }
                }
            }
        });

    }

    public static void main(String[] args) {
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
