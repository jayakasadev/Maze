package Util;

/**
 * Node Used for BFS
 *
 * @author Jaya Kasa
 * @version 1.0
 */
public class TestNode implements Comparable{
    private Color color = Color.White;
    private int d = 0;
    private TestNode parent = null;
    private int x;
    private int y;

    public TestNode(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getD(){
        return d;
    }

    public TestNode getParent(){
        return parent;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void setDistance(int d){
        this.d = d;
    }

    public void setParent(TestNode parent){
        this.parent = parent;
    }

    @Override
    public int compareTo(Object o) {
        if(o == null){
            return -1;
        }
        else if(!(o instanceof TestNode)){
            return -1;
        }
        TestNode m = (TestNode)o;
        if(m.getX() > x || m.getY() > y){
            return 1;
        }
        if(m.getX() == x && m.getY() == y){
            return 0;
        }
        return -1;
    }

    public enum Color{
        White, Gray, Black
    }

    @Override
    public String toString() {
        /*
        if(color == Color.White){
            return "White";
        }
        if(color == Color.Gray){
            return "Gray";
        }
        return "Black";
        */
        return "(" + x +", " + y + ")";
    }
}
