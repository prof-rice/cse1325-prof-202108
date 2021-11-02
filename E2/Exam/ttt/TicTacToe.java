import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class TicTacToe extends JFrame {
    public TicTacToe () {
        super("Tic Tac Toe");
        add(new Canvas());
        setSize(90,90);
        setVisible(true);
    }
    public static void main(String[] args) {
        new TicTacToe();
    }
}

class Canvas extends JPanel {
    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics.create();
        g.setColor(Color.RED);
        g.drawLine(0, 30, 90, 30);
        g.drawLine(0, 60, 90, 60);
        g.drawLine(30, 0, 30, 90);
        g.drawLine(60, 0, 60, 90);
        g.drawString("X", 40, 50);
    }
}
    
