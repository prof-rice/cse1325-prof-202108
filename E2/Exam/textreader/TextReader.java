import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class TextReader extends JFrame {
    public TextReader() {
        super("Text Reader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200,100);
        JButton button = new JButton("Read Text");
        button.addActionListener(event -> onFileOpenClick());
        add(button);
        setVisible(true);
    }
    public void onFileOpenClick() {
        final JFileChooser fc = new JFileChooser("text.txt");
        FileFilter txtFiles = new FileNameExtensionFilter("Text files", "txt");
        fc.addChoosableFileFilter(txtFiles);
        fc.setFileFilter(txtFiles);
        
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File filename = fc.getSelectedFile();
            try (BufferedReader in = new BufferedReader(new FileReader("text.txt"))) {
                JComboBox<String> cbOptions = new JComboBox<>();
                String s;
                while((s = in.readLine()) != null) cbOptions.addItem(s);
                JOptionPane.showMessageDialog(this, cbOptions);
                JOptionPane.showMessageDialog(this, "<html>You selected<br/>" 
                    + cbOptions.getSelectedItem() + "</html>");
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void main(String[] args) {
        new TextReader();
    }
}
        
