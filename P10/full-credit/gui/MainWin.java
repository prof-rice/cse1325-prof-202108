package gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import javax.swing.JToolBar;
import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
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

import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import store.Store;
import store.Order;
import store.Product;
import store.Donut;
import store.Frosting;
import store.Filling;
import store.Java;
import store.Darkness;
import store.Shot;
import store.Person;
import store.Customer;
import store.Server;

public class MainWin extends JFrame {
    private final String NAME = "JADE";
    private final String EXT = "jade";
    private final String VERSION = "0.5";
    private final String FILE_VERSION = "1.2";
    private final String MAGIC_COOKIE = "ʝąɗ玉";

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    
    protected enum Display {PRODUCTS, PEOPLE, ORDERS};
    
    public MainWin(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();
        
        JMenu     mFile     = new JMenu("File");
        JMenuItem mNew      = new JMenuItem("New");
        JMenuItem mOpen     = new JMenuItem("Open");
        JMenuItem mSave     = new JMenuItem("Save");
        JMenuItem mSaveAs   = new JMenuItem("Save As");
        JMenuItem mQuit     = new JMenuItem("Quit");
        JMenu     mCreate   = new JMenu("Create");
        JMenuItem mOrder    = new JMenuItem("Order");
        JMenuItem mJava     = new JMenuItem("Java");
        JMenuItem mDonut    = new JMenuItem("Donut");
        JMenuItem mCustomer = new JMenuItem("Customer");
        JMenuItem mServer   = new JMenuItem("Server");
        JMenu     mView     = new JMenu("View");
        JMenuItem mOrders   = new JMenuItem("Orders");
        JMenuItem mProducts = new JMenuItem("Products");
        JMenuItem mPeople   = new JMenuItem("People");
        JMenu     mHelp     = new JMenu("Help");
        JMenuItem mAbout    = new JMenuItem("About");
        
        mNew     .addActionListener(event -> onNewClick());
        mOpen    .addActionListener(event -> onOpenClick());
        mSave    .addActionListener(event -> onSaveClick());
        mSaveAs  .addActionListener(event -> onSaveAsClick());
        mQuit    .addActionListener(event -> onQuitClick());
        mOrder   .addActionListener(event -> onCreateOrderClick());
        mJava    .addActionListener(event -> onCreateJavaClick());
        mDonut   .addActionListener(event -> onCreateDonutClick());
        mCustomer.addActionListener(event -> onCreateCustomerClick());
        mServer  .addActionListener(event -> onCreateServerClick());
        mProducts.addActionListener(event -> updateDisplay(Display.PRODUCTS));
        mPeople  .addActionListener(event -> updateDisplay(Display.PEOPLE));
        mOrders  .addActionListener(event -> updateDisplay(Display.ORDERS));
        mAbout   .addActionListener(event -> onAboutClick());

        
        mFile  .add(mNew);
        mFile  .add(mOpen);
        mFile  .add(mSave);
        mFile  .add(mSaveAs);
        mFile  .add(mQuit);
        mCreate.add(mJava);
        mCreate.add(mDonut);
        mCreate.add(mCustomer);
        mCreate.add(mServer);
        mView  .add(mProducts);
        mView  .add(mPeople);
        mHelp  .add(mAbout);
        
        menubar.add(mFile);
        menubar.add(mCreate);
        menubar.add(mView);
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
        
        // Create the buttons using the icons provided
        bOrder  = new JButton(new ImageIcon("gui/resources/new_order.png"));
          bOrder.setActionCommand("Create new Order");
          bOrder.setToolTipText("Place an Order");
          toolbar.add(bOrder);
          bOrder.addActionListener(event -> onCreateOrderClick());

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

        bCustomer = new JButton(new ImageIcon("gui/resources/new_customer.png"));
          bCustomer.setActionCommand("Create new customer");
          bCustomer.setToolTipText("Create a new customer");
          toolbar.add(bCustomer);
          bCustomer.addActionListener(event -> onCreateCustomerClick());

        bServer = new JButton(new ImageIcon("gui/resources/new_server.png"));
          bServer.setActionCommand("Create new server");
          bServer.setToolTipText("Create a new server");
          toolbar.add(bServer);
          bServer.addActionListener(event -> onCreateServerClick());

        toolbar.add(Box.createHorizontalStrut(25));
        
        bListOrders = new JButton(new ImageIcon("gui/resources/list_orders.png"));
          bListOrders.setActionCommand("List all orders");
          bListOrders.setToolTipText("List all orders");
          toolbar.add(bListOrders);
          bListOrders.addActionListener(event -> updateDisplay(Display.ORDERS));

        bListProducts = new JButton(new ImageIcon("gui/resources/list_products.png"));
          bListProducts.setActionCommand("List all products");
          bListProducts.setToolTipText("List all products");
          toolbar.add(bListProducts);
          bListProducts.addActionListener(event -> updateDisplay(Display.PRODUCTS));

        bListPeople = new JButton(new ImageIcon("gui/resources/list_people.png"));
          bListPeople.setActionCommand("List people");
          bListPeople.setToolTipText("List people");
          toolbar.add(bListPeople);
          bListPeople.addActionListener(event -> updateDisplay(Display.PEOPLE));

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
        updateDisplay(Display.PRODUCTS);
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
                    throw new RuntimeException(
                        "Incompatible " + NAME + " file format: " + fileVersion);
                
                // Try to read the new store
                // If it fails, old store remains
                // If it succeeds, replace old store with new store
                
                Store newStore = new Store(br);
                store = newStore;
                filename = fname;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                    this, 
                    "Unable to open " + filename + '\n' + e, "Failed",
                    JOptionPane.ERROR_MESSAGE); 
            }
            updateDisplay(Display.PRODUCTS);
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
            if(!fname.getAbsolutePath().endsWith("." + EXT))
                fname = new File(fname.getAbsolutePath() + "." + EXT);
            filename = fname; // Success - use new filename!
            onSaveClick();
        }
    }

    protected void onQuitClick() {
        dispose();
    }
    
    protected void onCreateOrderClick() {  // Place an order
        try {
            ArrayList<Customer> customers = new ArrayList<>();
            ArrayList<Server>   servers = new ArrayList<>();
        
            for(Object p : store.getPeople()) {
                if(p instanceof Customer) customers.add((Customer) p);
                if(p instanceof Server)   servers.add((Server) p);
            }

            // Customer
            JLabel lCustomer = new JLabel("<HTML>Customer</HTML>");
            JComboBox<Object> dCustomer = new JComboBox<>(customers.toArray());
   
            // Server
            JLabel lServer = new JLabel("<HTML><BR/>Server</HTML>");
            JComboBox<Object> dServer = new JComboBox<>(servers.toArray());
        
            Object[] objects = {
                lCustomer, dCustomer,
                lServer, dServer
            };
        
            int button = JOptionPane.showConfirmDialog(
                this,
                objects,
                "Begin Order",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            if(button != JOptionPane.OK_OPTION) return;

            // Create the Order object
            Order newOrder = new Order((Customer) dCustomer.getSelectedItem(), 
                                       (Server)   dServer  .getSelectedItem());
       
            // Quantity and Product
            JLabel lProducts = new JLabel("<HTML>" 
                                        + newOrder.toString() 
                         + "<BR/><BR/><BR/><BR/><BR/><BR/><BR/><BR/></HTML>");
        
            JSpinner sQuantity = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
            JComboBox<Object> dProducts = new JComboBox<>(store.getProducts());
            JButton bAdd = new JButton("Add");
            bAdd.addActionListener(event -> {
                newOrder.addProduct((Integer) sQuantity.getValue(), 
                                    (Product) dProducts.getSelectedItem());
                lProducts.setText("<HTML>" + newOrder.toString() 
                                                     .replaceAll("<",  "&lt;")
                                                     .replaceAll(">",  "&gt;")
                                                     .replaceAll("\n", "<br/>")
                               + "</HTML>");
            });
        
            JPanel productPanel = new JPanel(); // Default FlowLayout is fine here
            productPanel.add(bAdd);
            productPanel.add(sQuantity);
            productPanel.add(dProducts);

            // Add components to a dialog

            Object[] orderObjects = {
                lProducts,
                productPanel,
            };
            
            int selection = JOptionPane.showConfirmDialog(
                this,
                orderObjects,
                "Add Products to Order",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if(selection != JOptionPane.OK_OPTION) return;

            store.addOrder(newOrder);
            updateDisplay(Display.ORDERS);
        } catch(Exception e) {
            msg.setText("Failed to create new Order: " + e.getMessage());
        }
    }

    protected void onCreateJavaClick() {  // Create a new Java product
        try {
            // Name of Java
            JLabel lName = new JLabel("Name");
            JTextField dName = new JTextField(20);

            // Price
            JLabel lPrice = new JLabel("<HTML><BR/>Price</HTML>");
            JSpinner dPrice = new JSpinner(new SpinnerNumberModel(3.95, 0.0, 99.99, 0.05));

            // Cost
            JLabel lCost = new JLabel("<HTML><BR/>Cost</HTML>");
            JSpinner dCost = new JSpinner(new SpinnerNumberModel(0.95, 0.0, 99.99, 0.05));

            // Darkness
            JLabel lDarkness = new JLabel("<HTML><BR/>Darkness</HTML>");
            JComboBox<Object> dDarkness = new JComboBox<>(Darkness.values());
            
            // Provide spacing to the Shots area
            JLabel sShots = new JLabel("<HTML><BR/></HTML>");
            
            // Shots
            JPanel shotPanel = new JPanel();
            shotPanel.setLayout(new BoxLayout(shotPanel, BoxLayout.PAGE_AXIS));
            
            // This provides a scroll bar when a lot of shots are added
            JScrollPane scrollingShotPanel = new JScrollPane(shotPanel);
            scrollingShotPanel.setPreferredSize(new Dimension(200,120));
            
            // Prepopulate with 3 shot comboboxes
            shotPanel.add(new JComboBox<Shot>(Shot.values()));
            shotPanel.add(new JComboBox<Shot>(Shot.values()));
            shotPanel.add(new JComboBox<Shot>(Shot.values()));
            
            // This button adds another combobox each time it's pressed
            JButton addShot = new JButton("Add a Shot");
            addShot.addActionListener(event -> {
                shotPanel.add(new JComboBox<Shot>(Shot.values()));
            });
            
            // Add components to a dialog

            Object[] objects = {
                lName,     dName,
                lPrice,    dPrice,
                lCost,     dCost,
                lDarkness, dDarkness,
                sShots,    
                addShot,    scrollingShotPanel,
            };
            
            int button = JOptionPane.showConfirmDialog(
                this,
                objects,
                "New Java",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if(button != JOptionPane.OK_OPTION) return;

            String name = dName.getText();
            double price = (double) dPrice.getValue();
            double cost = (double) dCost.getValue();
            Darkness darkness = (Darkness) dDarkness.getSelectedItem();
            Java java = new Java(name, price, cost, darkness);
            
            for(Object o : shotPanel.getComponents()) {
                if(!(o instanceof JComboBox)) continue; // verify cast will work, then
                @SuppressWarnings("unchecked")          // skip unchecked cast warning
                    JComboBox<Object> cb = (JComboBox<Object>) o;
                Shot shot = (Shot) cb.getSelectedItem();
                if(shot != Shot.None) java.addShot(shot);
            }
            store.addProduct(java);
            updateDisplay(Display.PRODUCTS);
        } catch(Exception e) {
            msg.setText("Failed to create new Java: " + e.getMessage());
        }
    }
    
    protected void onCreateDonutClick() {  // Create a new Java product
        try {
            // Name of Donut
            JLabel lName = new JLabel("Name");
            JTextField dName = new JTextField(20);

            // Price
            JLabel lPrice = new JLabel("<HTML><BR/>Price</HTML>");
            JSpinner dPrice = new JSpinner(new SpinnerNumberModel(1.95, 0.0, 99.99, 0.05));

            // Cost
            JLabel lCost = new JLabel("<HTML><BR/>Cost</HTML>");
            JSpinner dCost = new JSpinner(new SpinnerNumberModel(0.55, 0.0, 99.99, 0.05));

            // Frosting
            JLabel lFrosting = new JLabel("<HTML><BR/>Frosting</HTML>");
            JComboBox<Object> dFrosting = new JComboBox<>(Frosting.values());

            // Filling
            JLabel lFilling = new JLabel("<HTML><BR/>Filling</HTML>");
            JComboBox<Object> dFilling = new JComboBox<>(Filling.values());

            // Sprinkles
            String[] options = {"No Sprinkles", "Sprinkles"};
            JLabel lSprinkles = new JLabel("<HTML><BR/>Sprinkles</HTML>");
            JComboBox<Object> dSprinkles = new JComboBox<>(options);

            Object[] objects = {
                lName,      dName,
                lPrice,     dPrice,
                lCost,      dCost,
                lFrosting,  dFrosting,
                lFilling,   dFilling,
                lSprinkles, dSprinkles,
            };
            
            int button = JOptionPane.showConfirmDialog(
                this,
                objects,
                "New Donut",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if(button == JOptionPane.OK_OPTION) {
                String name = dName.getText();
                double price = (double) dPrice.getValue();
                double cost = (double) dCost.getValue();
                Frosting frosting = (Frosting) dFrosting.getSelectedItem();
                Filling filling = (Filling) dFilling.getSelectedItem();
                boolean sprinkles = (dSprinkles.getSelectedItem() == "Sprinkles");
                store.addProduct(new Donut(name, price, cost, 
                                           frosting, filling, sprinkles));
            }
            updateDisplay(Display.PRODUCTS);
        } catch(Exception e) {
            msg.setText("Failed to create new Donut: " + e);
        }
    }
                            
    protected void onCreateCustomerClick() {
        // Server Name
        JLabel lName = new JLabel("Name");
        JTextField dName = new JTextField(20);

        // Social Security Number
        JLabel lPhone = new JLabel(
            "<HTML><BR/>Phone  <SMALL>(Example: 123-456-7890)</SMALL></HTML>");
        JTextField dPhone = new JTextField(20);

        Object[] objects = {
            lName,  dName,
            lPhone,   dPhone,
        };

        final Pattern phoneFormat = Pattern.compile("^(\\d{3})-(\\d{3})-(\\d{4})$");
 
        while(true) {
            try {
                int button = JOptionPane.showConfirmDialog(
                    this,
                    objects,
                    "New Customer",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
            
                if(button == JOptionPane.OK_OPTION) {
                    String name = dName.getText();
                    String phone = dPhone.getText();
                    if(!phoneFormat.matcher(phone).find())
                        throw new IllegalArgumentException("Invalid Phone: " + phone);
                    store.addPerson(new Customer(name, phone));
                    updateDisplay(Display.PEOPLE);
                }
                break;
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    protected void onCreateServerClick() {
        // Customer Name
        JLabel lName = new JLabel("Name");
        JTextField dName = new JTextField(20);

        // Phone Number
        JLabel lPhone = new JLabel(
            "<HTML><BR/>Phone  <SMALL>(Example: 123-456-7890)</SMALL></HTML>");
        JTextField dPhone = new JTextField(20);

        // Social Security Number
        JLabel lSSN = new JLabel(
            "<HTML><BR/>SSN  <SMALL>(Example: 123-45-6789)</SMALL></HTML>");
        JTextField dSSN = new JTextField(20);

        Object[] objects = {
            lName,  dName,
            lPhone, dPhone,
            lSSN,   dSSN,
        };

        final Pattern phoneFormat = Pattern.compile("^(\\d{3})-(\\d{3})-(\\d{4})$");
        final Pattern ssnFormat   = Pattern.compile("^(\\d{3})-(\\d{2})-(\\d{4})$");
 
        while(true) {
            try {
                int button = JOptionPane.showConfirmDialog(
                    this,
                    objects,
                    "New Server",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
            
                if(button == JOptionPane.OK_OPTION) {
                    String name = dName.getText();
                    String phone = dPhone.getText();
                    if(!phoneFormat.matcher(phone).find())
                        throw new IllegalArgumentException("Invalid Phone: " + phone);
                    String ssn = dSSN.getText();
                    if(!ssnFormat.matcher(ssn).find())
                        throw new IllegalArgumentException("Invalid SSN: " + ssn);
                    store.addPerson(new Server(name, phone, ssn));
                    updateDisplay(Display.PEOPLE);
                }
                break;
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
         
    protected void onAboutClick() {                 // Display About dialog
        JDialog about = new JDialog();
        about.getContentPane().setLayout(
            new BoxLayout(about.getContentPane(), BoxLayout.PAGE_AXIS));
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
          + "<p>Server Icon by corpus delicti via the Noun Project</p>"
          + "<p><font size=-2>https://thenounproject.com/term/waiter/937157</p>"
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
    private void updateDisplay(Display display) {
        String s = "ERROR: Invalid display request: " + display;
        if(display == Display.PRODUCTS) s = store.toString();
        if(display == Display.PEOPLE)  s = store.peopleToString();
        if(display == Display.ORDERS)  s = store.ordersToString();
        data.setText("<html>" + s.replaceAll("<","&lt;")
                                 .replaceAll(">", "&gt;")
                                 .replaceAll("\n", "<br/>")
                              + "</html>");
    }
    
    private Store store;
    private File filename;
    
    private JLabel data;                    // Display of output in main window
    private JLabel msg;                     // Status message display
    private JButton bOrder;                 // Button to place an order
    private JButton bJava;                  // Button to create a new Java product
    private JButton bDonut;                 // Button to create a new Donut product
    private JButton bCustomer;              // Button to create a new Customer
    private JButton bServer;                // Button to create a new Server
    private JButton bListOrders;            // Button to list orders
    private JButton bListProducts;          // Button to list products
    private JButton bListPeople;            // Button to list people
}
