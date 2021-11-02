import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

class Reversi extends JFrame {
    public Reversi() {
        super("Reversi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        
        // Menu Bar
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        
        JMenuItem setText = new JMenuItem("Set Text");
        setText.addActionListener(event -> onSetTextClick());

        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(event -> onQuitClick());

        file.add(setText);
        file.add(quit);
        menubar.add(file);
        setJMenuBar(menubar);
        
        // Tool Bar
        JToolBar toolbar = new JToolBar();
        JButton reverse = new JButton(new ImageIcon("reverse.png", "Reverse"));
        reverse.setToolTipText("Reverse the String");
        reverse.addActionListener(event -> onReverseClick());
        
        toolbar.add(reverse);
        getContentPane().add(toolbar, BorderLayout.PAGE_START);
        
        // Data Area
        getContentPane().add(data, BorderLayout.CENTER);
        
        // Status Area
        status = new JLabel("Icon by Gregor Cresnar of The Noun Project");
        getContentPane().add(status, BorderLayout.PAGE_END);
        
        setVisible(true);
    }
    
    public void  onSetTextClick() {
        String newText = JOptionPane.showInputDialog(this, "Enter new text");
        if(newText != null) data.setText(newText);
    }
/*    public void onReverseClick() {
        String s = "";
        for(char c : data.getText().toCharArray()) s = c + s;
        data.setText(s);
    }
*/    // Or, more concisely and efficiently,
    public void onReverseClick() {
        StringBuffer sb = new StringBuffer(data.getText());
        data.setText(sb.reverse().toString());
    }

    public void onQuitClick() {
        dispose();
    }
    public static void main(String[] args) {
        new Reversi();
    }
    JLabel data = new JLabel("Hello, World");
    JLabel status;
}
