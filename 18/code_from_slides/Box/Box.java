public class Box {
    public Box(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;  
    }
    public double volume() {return length * width * height;}
    
    @Override
    public String toString() {
        return "Rectangular box ("
            + length + " x " + width + " x " + height + ")";
    }
    
    protected double length;
    protected double width;
    protected double height;
}
