
package wif3003.assignment;

/**
 * 
 * A model class for Point object
 */

public class Point {
    
    private double x;
    private double y;
    private boolean connected;  

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        connected = false;
    }
    
    public void setPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public boolean isConnected() {
        return connected;
    }
    
    public boolean connect() {
        connected = true;
        return connected;
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
