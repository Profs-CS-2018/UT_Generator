package com.swengGUI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class BrowseGUI {

    /**
     * Declaring GUI components below
     */
    public JPanel mainPanel;
    public JTextField textFieldSave;
    public JButton browseButton;
    public JTabbedPane tabbedPane;
    public JButton submitButton;
    public JList listFiles;
    public JButton saveButton;
    public JButton btnPreview;
    public JLabel fixedLabel;
    public JTextArea previewTextArea;
    public JLabel saveFile;
    public JTextField txtFldDirNm;
    public JButton addFilesButton;
    public JCheckBox allFilesCheckBox;
    public JCheckBox makeFileCheckBox;
    public JCheckBox testFixtureCheckBox;
    public JCheckBox unitTestCheckBox;
    private JTextField txtFldFileNm;
    JFileChooser fc = new JFileChooser(); // for Browse button
    JFileChooser fc1 = new JFileChooser();// for Save button
    JFileChooser fc2 = new JFileChooser();// for Add Files button( case : not file name is entered)
    JFileChooser fc3 = new JFileChooser(); // for Add Files button( case : file name is entered and we want the browser to open with the file selected)


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
                fc.setCurrentDirectory(new File("C:\\Users\\aanch\\Desktop\\Fall 2017\\SE-WI"));
                /**
                 * The following code adds filter to the file extensions.
                 */

                //for searching the files, filters which files appear in the box
                fc.setFileFilter(new FileNameExtensionFilter("Text Files(.txt)", "txt"));
                fc.setFileFilter(new FileNameExtensionFilter("Java(.java)", "java"));
                fc.setFileFilter(new FileNameExtensionFilter("C++(.cpp)", "cpp"));
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

            }
        });

        //This connects to the textArea1 box we were talking about. You may have to add a textArea into the
        // GUI form in order for this to take effect
        previewTextArea.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //toggles between read only and editable
                if(SwingUtilities.isRightMouseButton(e)){
                    previewTextArea.setEditable(true);
                }
                if(SwingUtilities.isLeftMouseButton(e)){
                    previewTextArea.setEditable(false);
                    Scanner sc = new Scanner(previewTextArea.getText());
                    ArrayList<String> txt = new ArrayList<>();
                    //int count = 0;
                    //String[] nest = textArea1.split("\n");

                    for(String str : previewTextArea.getText().split("\n")){
                        //System.out.println(str);
                        txt.add(str);
                        //count++;
                    }

                    //System.out.println(txt);

                    //Detects if the textArea has duplicate, but doesn't state which files were duplicates
                    Set<String> set = new HashSet<String>(txt);
                    if(set.size() < txt.size()){
                        //System.out.println("ERROR");
                        JOptionPane.showMessageDialog(null,
                                "WARNING: Multiple files of the same name have been selected",
                                "Double File Exception",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            //These are needed for the MouseListener, otherwise gives errors
            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

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
                        catch(Exception e1) {
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
                }
                catch (Exception e1) //catch any error which happens to have resulted in generation failure
                {
                    JOptionPane.showMessageDialog(null, "ERROR: See Error Information Tab " +
                            "for details");
                    //clicking OK redirects you to a tabbed pane labelled ERRORS
                    String errors = "Errors";

                    //check if ERRORS tab exists. Create if it does not.
                    if(tabbedPane.indexOfTab(errors) == -1) {
                        tabbedPane.add(errors, new JScrollPane(new JList<>()));
                    }

                        //populate the list of errors. make the text red?

                        //set ERROR tab as currently selected tab
                    tabbedPane.getModel().setSelectedIndex(tabbedPane.indexOfTab(errors));



                }
            }
        });
        /**
         * @Aanchal Chaturvedi
         * The add file button works based on the two text fields
         *  1. File name
         *  2. Directory name
         * Entering a directory name is MUST. (otherwise it would be like creating something similar to Browse)
         * If user does not add directory name or adds an incorrect directory name Error pops up saying they have to enter a valid directory name.
         * Once the user enters a valid directory name, they can either enter a file name or not
         * Case 1: user enters a file name
         * File chooser opens with the selected file in the open field.
         * Upon hitting approve the code checks of the duplicate of file selected already exists in Jfile.
         * Yes duplicate exists: error pops up saying “duplicate file exits”
         * Duplicate in the Jlist gets highlighted
         * Case 2: user does not enter a file name
         * File chooser opens with selected directory. User chooses a file from the directory.
         * Upon hitting open the code checks for duplicate and does the same as previously mentioned
         */
        addFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileNm = txtFldFileNm.getText();
                String dirNm = txtFldDirNm.getText();
                String directoryName = dirNm.replace("\\", "\\\\");
                File directory = new File(directoryName);
                if(e.getSource() == addFilesButton)
                {
                    /**
                     * Checks if directory does not exists or the directory text field is empty
                     */
                    if(dirNm.isEmpty() || !directory.exists())
                   {
                       JOptionPane.showMessageDialog(null, "Please enter valid directory name");
                   }
                    /**
                     * In case directory entered exists user can either
                     * 1. Enter a file name
                     * 2. Not enter a file name
                     *
                     */
                   else {
                       String absolutePath = dirNm + "\\" + fileNm;
                        /**
                         * Case 1: user does not enter file name
                         *
                         */
                       if (fileNm.isEmpty()){
                           fc2.setCurrentDirectory(new File(directoryName));
                           System.out.println(directoryName);
                           int returnVal = fc2.showOpenDialog(mainPanel);
                            if(returnVal == JFileChooser.APPROVE_OPTION)
                            {
                                if(dm.isEmpty())
                                {
                                    dm.addElement(fc2.getSelectedFile().getAbsolutePath());
                                }
                                else
                                {
                                    System.out.println(dm.contains(fc2.getSelectedFile().getAbsolutePath()));
                                    if(dm.contains(fc2.getSelectedFile().getAbsolutePath())) {
                                        JOptionPane.showMessageDialog(null, "Duplicate exists");
                                        listFiles.setSelectedIndex(dm.indexOf(fc2.getSelectedFile().getAbsolutePath()));
                                    }
                                    else {
                                        dm.addElement(fc2.getSelectedFile().getAbsolutePath());
                                    }
                                }

                            }
                       }
                       /**
                        * Case 2: user enters file name
                        */
                       else {
                           fc3.setSelectedFile(new File(absolutePath));
                           int returnVal1 = fc3.showOpenDialog(mainPanel);
                           if(returnVal1 == JFileChooser.APPROVE_OPTION)
                           {
                               if(dm.isEmpty())
                               {
                                   dm.addElement(fc3.getSelectedFile().getAbsolutePath());
                               }
                               else
                               {
                                   System.out.println(dm.contains(fc3.getSelectedFile().getAbsolutePath()));
                                   if(dm.contains(fc3.getSelectedFile().getAbsolutePath())) {
                                       JOptionPane.showMessageDialog(null, "Duplicate exists");
                                       listFiles.setSelectedIndex(dm.indexOf(fc3.getSelectedFile().getAbsolutePath()));
                                   }
                                   else {
                                       dm.addElement(fc3.getSelectedFile().getAbsolutePath());
                                   }
                               }
                           }
                       }
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
