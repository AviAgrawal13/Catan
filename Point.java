public class Point {

    public int x; 
    public int y; 

    public Point(int x, int y) { 
        this.x = x; 
        this.y = y; 
    } 

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String toString() {
        return "(" + this.x + " " + this.y + ")";
    }

    public boolean equals(Object a){
        Point p = (Point) a;
        return this.x == p.getX() && this.y == p.getY();
    }

    
}
