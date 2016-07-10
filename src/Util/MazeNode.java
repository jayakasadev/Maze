package Util;

/**
 * Node representation of a maze location
 *
 * @author Jaya Kasa
 * @version 1.0
 */
public class MazeNode implements Comparable{
    private final int x;
    private final int y;
    private int g = 0;
    private double f = 0;
    private MazeNode parent = null;

    public MazeNode(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x value
     *
     * @return
     */
    public int getX(){ return x; }

    /**
     * Get the y value
     *
     * @return
     */
    public int getY(){ return y; }

    /**
     * On Visit, update the parent of the curret node to the node from which the current node was first visited.
     *
     * @param parent
     */
    public void setParent(MazeNode parent){
        this.parent = parent;
    }

    /**
     * Returns the parent node from which the current node was first visited
     *
     * @return parent node or null if node was not yet visited
     */
    public MazeNode getParent(){
        return parent;
    }

    /**
     * Method resets the parent to null if current node is deemed unnecessary
     */
    public void resetParent(){ parent = null; }

    /**
     * Method to set the G value for the node
     * @param g
     */
    public void setG(int g){
        this.g = g;
    }

    /**
     * Get the G value for the current node
     * @return
     */
    public int getG(){
        return g;
    }

    /**
     * Method to set the F value for the node
     * @param f
     */
    public void setF(double f){
        this.f = f + g;
    }

    /**
     * Get the F value for the current node
     * @return
     */
    public double getF(){
        return f;
    }

    @Override
    public String toString() {
        return "(" + (x+1) + ", " + (y+1) + ")";
    }

    @Override
    public int compareTo(Object o) {
        if(o == null){
            return -1;
        }
        else if(!(o instanceof MazeNode)){
            return -1;
        }
        MazeNode m = (MazeNode)o;
        if(m.getX() > x || m.getY() > y){
            return 1;
        }
        if(m.getX() == x && m.getY() == y){
            return 0;
        }
        return -1;
    }
}
