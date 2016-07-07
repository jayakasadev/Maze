package Util;

@Deprecated
/**
 * Node representation of a maze location
 *
 * @author Jaya Kasa
 * @version 1.0
 */
public class MazeNode {
    private short x;
    private short y;
    private MazeNode parent = null;

    public MazeNode(short x, short y){
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x value
     *
     * @return
     */
    public short getX(){ return x; }

    /**
     * Get the y value
     *
     * @return
     */
    public short getY(){ return y; }

    /**
     * On Visit, update the parent of the curret node to the node from which the current node was first visited.
     *
     * @param parent
     */
    public void onVisit(MazeNode parent){
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
}
