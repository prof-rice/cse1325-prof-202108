public class TriBox extends Box {
    public TriBox(double length, double width, double height) {
        super(length, width, height);
    }
    @Override
    public double volume() {return 0.5 * length * width * height;}
    
    @Override
    public String toString() {
        return "Triangular box ("
            + length + " x " + width + " x " + height + ")";
    }
}
