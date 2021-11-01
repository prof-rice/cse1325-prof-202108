package gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import javax.swing.JToolBar;
import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import store.Store;
import store.Product;
import store.Donut;
import store.Frosting;
import store.Filling;
import store.Java;
import store.Darkness;
import store.Shot;

public class MainWin extends JFrame {
    private final String NAME = "JADE";
    private final String EXT = "jade";
    private final String VERSION = "0.3";
    private final String FILE_VERSION = "1.0";
    private final String MAGIC_COOKIE = "ʝąɗ玉";

    // Thrown if the Cancel button on a dialog is clicked
    protected class CancelDialogException extends Exception {
        public CancelDialogException() {
            super("Dialog canceled");
        }
    }

    public MainWin(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();
        
        JMenu     mFile    = new JMenu("File");
        JMenuItem mNew     = new JMenuItem("New");
        JMenuItem mOpen    = new JMenuItem("Open");
        JMenuItem mSave    = new JMenuItem("Save");
        JMenuItem mSaveAs  = new JMenuItem("Save As");
        JMenuItem mQuit    = new JMenuItem("Quit");
        JMenu     mCreate  = new JMenu("Create");
        JMenuItem mJava    = new JMenuItem("Java");
        JMenuItem mDonut   = new JMenuItem("Donut");
        JMenu     mHelp    = new JMenu("Help");
        JMenuItem mAbout   = new JMenuItem("About");
        
        mNew   .addActionListener(event -> onNewClick());
        mOpen  .addActionListener(event -> onOpenClick());
        mSave  .addActionListener(event -> onSaveClick());
        mSaveAs.addActionListener(event -> onSaveAsClick());
        mQuit  .addActionListener(event -> onQuitClick());
        mJava  .addActionListener(event -> onCreateJavaClick());
        mDonut .addActionListener(event -> onCreateDonutClick());
        mAbout .addActionListener(event -> onAboutClick());

        
        mFile  .add(mNew);
        mFile  .add(mOpen);
        mFile  .add(mSave);
        mFile  .add(mSaveAs);
        mFile  .add(mQuit);
        mCreate.add(mJava);
        mCreate.add(mDonut);
        mHelp  .add(mAbout);
        
        menubar.add(mFile);
        menubar.add(mCreate);
        menubar.add(mHelp);
        
        setJMenuBar(menubar);
        
        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R
        // Add a toolbar to the PAGE_START region below the menu
        JToolBar toolbar = new JToolBar("JADE Controls");

       JButton bNew = new JButton(new ImageIcon("gui/resources/file_new.png"));
          bNew.setActionCommand("Create new JADE store");
          bNew.setToolTipText("Create new JADE store");
          toolbar.add(bNew);
          bNew.addActionListener(event -> onNewClick());

        JButton bOpen = new JButton(new ImageIcon("gui/resources/file_open.png"));
          bOpen.setActionCommand("Open JADE file");
          bOpen.setToolTipText("Open JADE file");
          toolbar.add(bOpen);
          bOpen.addActionListener(event -> onOpenClick());

        JButton bSave = new JButton(new ImageIcon("gui/resources/file_save.png"));
          bSave.setActionCommand("Save JADE file");
          bSave.setToolTipText("Save JADE file");
          toolbar.add(bSave);
          bSave.addActionListener(event -> onSaveClick());

        JButton bSaveAs = new JButton(new ImageIcon("gui/resources/file_save_as.png"));
          bSaveAs.setActionCommand("Save JADE as new file");
          bSaveAs.setToolTipText("Save JADE as new file");
          toolbar.add(bSaveAs);
          bSaveAs.addActionListener(event -> onSaveAsClick());

        toolbar.add(Box.createHorizontalStrut(25));
        
        // Create the 3 buttons using the icons provided
        bJava  = new JButton(new ImageIcon("gui/resources/new_java.png"));
          bJava.setActionCommand("Create new Java");
          bJava.setToolTipText("Create a new coffee selection");
          toolbar.add(bJava);
          bJava.addActionListener(event -> onCreateJavaClick());

        bDonut = new JButton(new ImageIcon("gui/resources/new_donut.png"));
          bDonut.setActionCommand("Create new donut");
          bDonut.setToolTipText("Create a new donut selection");
          toolbar.add(bDonut);
          bDonut.addActionListener(event -> onCreateDonutClick());

        toolbar.add(Box.createHorizontalStrut(25));
        
        JButton bAbout = new JButton(new ImageIcon("gui/resources/about.png"));
          bAbout.setActionCommand("About JADE Manager");
          bAbout.setToolTipText("About JADE Manager");
          toolbar.add(bAbout);
          bAbout.addActionListener(event -> onAboutClick());

        getContentPane().add(toolbar, BorderLayout.PAGE_START);
        
        
        // /////////////////////////// ////////////////////////////////////////////
        // D A T A   D I S P L A Y
        // Provide a text entry box to show output
        data = new JLabel();
        data.setFont(new Font("SansSerif", Font.BOLD, 12));
        data.setVerticalAlignment(JLabel.TOP);
        add(data, BorderLayout.CENTER);

        // S T A T U S   B A R   D I S P L A Y ////////////////////////////////////
        // Provide a status bar for game messages
        msg = new JLabel();
        add(msg, BorderLayout.PAGE_END);
        
        // Create a default store
        onNewClick();
        
        // Make everything in the JFrame visible
        setVisible(true);        
    }
    
    // Listeners
    
    protected void onNewClick() {
        String storeName = "JADE";
        if(store != null) {
            storeName = JOptionPane.showInputDialog(this, "New store name?");
        }
        if(storeName != null) {
            store = new Store(storeName);
            filename = new File("Untitled.jade");
        }
        updateDisplay();
    }

    protected void onOpenClick() {
        final JFileChooser fc = new JFileChooser(filename);
        FileFilter jadeFiles = new FileNameExtensionFilter(NAME + " files", EXT);
        fc.addChoosableFileFilter(jadeFiles);
        fc.setFileFilter(jadeFiles);
        
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fname = fc.getSelectedFile();
             
            try (BufferedReader br = new BufferedReader(new FileReader(fname))) {
                String magicCookie = br.readLine();
                if(!magicCookie.equals(MAGIC_COOKIE)) 
                    throw new RuntimeException("Not a " + NAME + " file");
                String fileVersion = br.readLine();
                if(!fileVersion.equals(FILE_VERSION)) 
                    throw new RuntimeException("Incompatible " + NAME + " file format");
                
                // Try to read the new store
                // If it fails, old store remains
                // If it succeeds, replace old store with new store
                
                Store newStore = new Store(br);
                store = newStore;
                filename = fname;
            } catch (Exception e) {
                e.printStackTrace(System.err);
                JOptionPane.showMessageDialog(
                    this, 
                    "Unable to open " + filename + '\n' + e, "Failed",
                    JOptionPane.ERROR_MESSAGE); 
            }
            updateDisplay();
        }
    }
    protected void onSaveClick() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(MAGIC_COOKIE + '\n');
            bw.write(FILE_VERSION + '\n');
            store.save(bw);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this, 
                "Unable to save " + filename + '\n' + e, "Failed",
                JOptionPane.ERROR_MESSAGE); 
        }
    }

    protected void onSaveAsClick() {
        final JFileChooser fc = new JFileChooser(filename);
        FileFilter jadeFiles = new FileNameExtensionFilter(NAME + " files", EXT);
        fc.addChoosableFileFilter(jadeFiles);
        fc.setFileFilter(jadeFiles);
        
        int result = fc.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fname = fc.getSelectedFile();
            if(!filename.getAbsolutePath().endsWith("." + EXT))
                filename = new File(filename.getAbsolutePath() + "." + EXT);
            onSaveClick();
        }
    }

    protected void onQuitClick() {
        dispose();
    }

    protected void onCreateJavaClick() {  // Create a new Java product
        try {
            String name = getString("Java name?");
            double price = getDouble("Price?");
            double cost = getDouble("Cost?");
            Darkness darkness = (Darkness) selectFromArray("Darkness?", Darkness.values());
            Java java = new Java(name, price, cost, darkness);
            while(true) {
                Shot shot = (Shot) selectFromArray("Shot?", Shot.values());
                if(shot.equals(Shot.None)) break;
                java.addShot(shot);
            }
            store.addProduct(java);
            updateDisplay();
        } catch (CancelDialogException e) { // ignore a Cancel
        } catch(Exception e) {
            msg.setText("Failed to create new Java: " + e.getMessage());
        }
    }
    
    protected void onCreateDonutClick() {  // Create a new Java product
        try {
            String name = getString("Donut name?");
            double price = getDouble("Price?");
            double cost = getDouble("Cost?");
            Frosting frosting = (Frosting) selectFromArray("Select a Frosting", Frosting.values());
            Filling filling = (Filling) selectFromArray("Select a Filling", Filling.values());
            String[] options = {"No Sprinkles", "Sprinkles"};
            boolean sprinkles = (JOptionPane.showOptionDialog(this, "Sprinkles?", "", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]) == 1);
            store.addProduct(new Donut(name, price, cost, frosting, filling, sprinkles));
            updateDisplay();
        } catch (CancelDialogException e) { // ignore a Cancel
        } catch(Exception e) {
            msg.setText("Failed to create new Donut: " + e);
        }
    }
            
    protected void onAboutClick() {                 // Display About dialog
        JDialog about = new JDialog();
        about.getContentPane().setLayout(new BoxLayout(about.getContentPane(), BoxLayout.PAGE_AXIS));
        about.setTitle("Java and Donut Express");
        about.setSize(640,600);
        
        try {
            BufferedImage myPicture = ImageIO.read(new File("gui/resources/logo.png"));
            JLabel logo = new JLabel(new ImageIcon(myPicture));
            logo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            about.add(logo);
        } catch(IOException e) {
        }
        
        JLabel title = new JLabel("<html>"
          + "<p><font size=+3>Java and Donut Express</font></p>"
          + "</html>");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        about.add(title);

        JLabel copyright = new JLabel("<html>"
          + "<p>Version 0.2</p>"
          + "<p>Copyright 2021 by George F. Rice</p>"
          + "<p>Licensed under Gnu GPL 3.0</p>"
          + "<br/>"
          + "</html>");
        copyright.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        about.add(copyright);
                    
       JLabel artists = new JLabel("<html>"
          + "<p>JADE Logo by SaxDeux, licensed under CC BY-SA 3.0</p>"
          + "<p><font size=-2>https://commons.wikimedia.org/wiki/File:Logo_JADE.png</p>"
          + "<p>Flat Coffee Cup Icon by superawesomevectors, licensed under CC BY-SA 3.0</p>"
          + "<p><font size=-2>http://fav.me/dbf6otc</p>"
          + "<p><font size=-2>https://creativecommons.org/licenses/by-sa/3.0/us/</p>"
          + "<p>Donut Icon by Hazmat2 via Hyju, public domain</p>"
          + "<p><font size=-2>https://en.wikipedia.org/wiki/File:Simpsons_Donut.svg</p>"
          + "<p>Help Icon by Vector Stall via the Flat Icon license</p>"
          + "<p><font size=-2>https://www.flaticon.com/premium-icon/question-mark_3444393</p>"
          + "<p>File icons made by Freepik</p>"
          + "<p><font size=-2>https://www.freepik.com</p>"          
          + "<br/>"
          + "</html>");
        artists.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        about.add(artists);

        JButton ok = new JButton("OK");
        ok.setAlignmentX(JButton.CENTER_ALIGNMENT);
        ok.addActionListener(event -> about.setVisible(false));
        about.add(ok);
        
        about.pack();
        about.setVisible(true);
     }

    public static void main(String[] args) {
        MainWin myApp = new MainWin("JADE");
        myApp.setVisible(true);
    }

    // Utilities
    
    private String getString(String prompt) throws CancelDialogException {
        String newPrompt = prompt;
        while(true) {
            try {
                newPrompt = JOptionPane.showInputDialog(this, newPrompt);
                if(newPrompt == null || newPrompt.length() == 0) 
                    throw new CancelDialogException();
                break;
            } catch (CancelDialogException e) {
                throw e;  // Rethrow to signal Cancel was clicked
            } catch (Exception e) {
                newPrompt = "ERROR: Invalid string '" + newPrompt + "\n" + prompt;
            }
        }
        return newPrompt;
    }
    
    private double getDouble(String prompt) throws CancelDialogException {
        String newPrompt = prompt;
        double result = 0.0;
        while(true) {
            try {
                newPrompt = JOptionPane.showInputDialog(this, newPrompt);
                if(newPrompt == null) throw new CancelDialogException();
                result = Double.parseDouble(newPrompt);
                break;
            } catch (CancelDialogException e) {
                throw e;  // Rethrow to signal Cancel was clicked
            } catch (Exception e) {
                newPrompt = "ERROR: Invalid double '" + newPrompt + "\n" + prompt;
            }
        }
        return result;
    }
    
    private Object selectFromArray(String prompt, Object[] options) throws CancelDialogException {
        JComboBox<Object> comboEnum = new JComboBox<>();
        comboEnum.setModel(new DefaultComboBoxModel<Object>(options));
        int button = JOptionPane.showConfirmDialog(this, comboEnum, prompt, 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(button == JOptionPane.CANCEL_OPTION) throw new CancelDialogException();
        return comboEnum.getSelectedItem();
    }
    
    private void updateDisplay() {
        data.setText("<html>" + store.toString()
                                     .replaceAll("<","&lt;")
                                     .replaceAll(">", "&gt;")
                                     .replaceAll("\n", "<br/>")
                              + "</html>");
    }

    
    private Store store;
    private File filename;
    
    private JLabel data;                    // Display of output in main window
    private JLabel msg;                     // Status message display
    private JButton bJava;                  // Button to select 1 stick
    private JButton bDonut;                 // Button to select 2 sticks
}
