import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import shapes.Shape;
import shapes.Line;
import shapes.Turtle;
import shapes.Polygon;

public class Canvas extends JPanel {
    private final static int border = 25;

    public Canvas() {
    
        // Create a turtle
        
        Turtle turtle = new Turtle();
        turtle.pen(Turtle.Pen.DOWN);
        
        for(int i=1; i<300; i += 1) {
            turtle.forward(i); 
            turtle.turn(61);
        }
        shapes.add(turtle);
        
        // Create a generic shape
        
        Shape shape = new Shape();
        shape.addLine(new Line(-355, -280, 355, -280));
        shape.addPoint(355, 280);
        shapes.add(shape);
        
        // Create a polygon
        
        Polygon polygon = new Polygon();
        polygon.addLine(new Line(-360, -285, 360, -285));
        polygon.addPoint(360, 285);
        polygon.addPoint(-360, 285);
        shapes.add(polygon);
    }

    public Dimension getPreferredSize() {
        return new Dimension(750,600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // Move origin to center
        Rectangle size = getBounds();
        g.translate(size.width / 2, size.height/2);
        
        // Paint all shapes in the ArrayList
        for(Shape shape : shapes)
            shape.paintComponent(g);
    }
    
    protected ArrayList<Shape> shapes = new ArrayList<>();
}
