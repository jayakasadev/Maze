package Maze;

import Util.MazeNode;
import Util.MazeReader;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Class finds a path from the start position to the goal position in the maze using AStar search.
 *
 * @author Jaya Kasa
 * @version 1.0
 */
public class Maze {
    private MazeNode[][] maze = null;
    private int startx = 0;
    private int starty = 0;
    private int finishx = 0;
    private int finishy = 0;
    private Deque<MazeNode> open = new LinkedList<>();
    private Stack<MazeNode> close = new Stack<>();

    Maze(String filename){
        maze = MazeReader.readFile(filename);
        finishx = maze.length-1;
        finishy = maze[finishx].length-1;
    }

    Maze(String filename, int finishx, int finishy){
        maze = MazeReader.readFile(filename);

        finishx--;
        finishy--;

        int maxx = maze.length-1;
        int maxy = maze[maxx].length-1;

        //System.out.println(finishx + " " + maxx);
        //System.out.println(finishy + " " + maxy);

        if(finishx >= 0 && finishx <= maxx){
            this.finishx = finishx;
        }
        else{
            throw new IllegalArgumentException("Goal x Position is out of bounds of maze.");
        }

        if(finishy >= 0 && finishy <= maxy){
            this.finishy = finishy;
        }
        else{
            throw new IllegalArgumentException("Goal y Position is out of bounds of maze.");
        }
    }

    /**
     * Method to solve the maze using AStar Algorithm
     */
    void solve(){
        open.add(maze[startx][starty]);
        while(!open.isEmpty()){
            MazeNode curr = open.pop();
            int x = curr.getX();
            int y = curr.getY();
            close.add(curr);
            if(x == finishx && y == finishy){
                return;
            }
            MazeNode[] neighbors = getNeighbors(curr);
            for(MazeNode n : neighbors){
                if(n == null){
                    continue;
                }
                if(!open.contains(n)){
                    n.setParent(curr);
                    n.setG(curr.getG() + 1);
                    n.setF(h(n.getX(), n.getY()));
                    open.add(n);
                }
                else{
                    int g = curr.getG() + 1;
                    double h = h(n.getX(), n.getY());
                    if(n.getF() < h+g){
                        n.setParent(curr);
                        n.setG(g);
                        n.setF(h);
                    }
                }
            }
        }
    }

    /**
     * Method to print the path solution for the maze from the given starting positions
     *
     * @param x
     * @param y
     */
    void printPath(int x, int y){
        this.startx = --x;
        this.starty = --y;
        if(maze[x][y] == null){
            throw new IllegalArgumentException("Illegal Start Position");
        }
        solve();
        Stack<MazeNode> path = new Stack<>();
        MazeNode curr = close.pop();
        while(curr.compareTo(maze[startx][starty]) != 0){
            path.add(curr);
            curr = curr.getParent();
        }
        path.add(curr);
        System.out.println("Path for current maze took " + (path.size()-1) + " moves.");
        System.out.print("[ ");
        while(!path.empty()){
            System.out.print(path.pop() + " ");
        }
        System.out.println("]");
    }

    /**
     * Method to print the path solution for the maze from the given starting positions
     */
    void printPath(){
        if(maze[finishx][finishy] == null){
            System.err.println("Goal Position is an obstable! No legal path to goal exists!");
            return;
        }
        solve();
        Stack<MazeNode> path = new Stack<>();
        MazeNode curr = close.pop();
        while(curr.compareTo(maze[startx][starty]) != 0){
            path.add(curr);
            curr = curr.getParent();
        }
        path.add(curr);
        System.out.println("Path for current maze took " + (path.size()-1) + " moves.");
        System.out.print("[ ");
        while(!path.empty()){
            System.out.print(path.pop() + " ");
        }
        System.out.println("]");
    }

    /**
     * Method gets the current nodes neighbors and excludes the parents and any nodes already in the path
     * @param curr
     * @return
     */
    private MazeNode[] getNeighbors(MazeNode curr){
        //calculate the neighbors
        int x = curr.getX();
        int y = curr.getY();
        MazeNode[] neighbors = new MazeNode[4];
        if(x-1 > 0){
            neighbors[0] = maze[x-1][y];
            if(curr != null && neighbors[0] != null && (curr.getParent() != null && neighbors[0].compareTo(curr.getParent()) == 0) || close.contains(neighbors[0])){
                neighbors[0] = null;
            }
        }
        if(x+1 < maze.length){
            neighbors[1] = maze[x+1][y];
            if(curr != null && neighbors[1] != null && (curr.getParent() != null && neighbors[1].compareTo(curr.getParent()) == 0) || close.contains(neighbors[1])){
                neighbors[1] = null;
            }
        }
        if(y-1 > 0){
            neighbors[2] = maze[x][y-1];
            if(curr != null && neighbors[2] != null && (curr.getParent() != null && neighbors[2].compareTo(curr.getParent()) == 0) || close.contains(neighbors[2])){
                neighbors[2] = null;
            }
        }
        if(y+1 < maze[x].length){
            neighbors[3] = maze[x][y+1];
            if(curr != null && neighbors[3] != null && (curr.getParent() != null && neighbors[3].compareTo(curr.getParent()) == 0) || close.contains(neighbors[3])){
                neighbors[3] = null;
            }
        }

        return neighbors;
    }

    /**
     * Hueristic Evaluation function
     *
     * @param x
     * @param y
     */
    private double h(int x, int y){
        //consider merging the h and g methods into this one
        return Math.sqrt((finishx - x)*(finishx - x) + (finishy - y)*(finishy - y));
    }

    public static void main(String ... args){
        System.out.println("Solving multipath.txt");
        new Maze("multipath").printPath();
        System.out.println("Solving smallmaze.txt");
        new Maze("smallmaze").printPath();
        System.out.println("Solving maze_No_1.txt");
        new Maze("maze_No_1.txt").printPath();
        System.out.println("Solving maze_No_2.txt");
        new Maze("maze_No_2.txt").printPath();

        System.out.println("\n\nSolving Maze with custom start locations");

        System.out.println("Solving multipath.txt");
        new Maze("multipath").printPath(2, 1);
        System.out.println("Solving smallmaze.txt");
        new Maze("smallmaze").printPath(2, 1);
        System.out.println("Solving maze_No_1.txt");
        new Maze("maze_No_1.txt").printPath(10, 17);
        System.out.println("Solving maze_No_2.txt");
        new Maze("maze_No_2.txt").printPath(10, 17);

        System.out.println("\n\nSolving Maze with custom finish locations");

        System.out.println("Solving multipath.txt");
        new Maze("multipath", 3, 1).printPath();
        System.out.println("Solving smallmaze.txt");
        new Maze("smallmaze", 3 ,1).printPath();
        System.out.println("Solving maze_No_1.txt");
        new Maze("maze_No_1.txt", 92, 87).printPath();
        System.out.println("Solving maze_No_2.txt");
        new Maze("maze_No_2.txt", 92, 87).printPath();
    }
}
