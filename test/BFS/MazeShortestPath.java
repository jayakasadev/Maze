package BFS;

import Util.TestNode;
import Util.TestReader;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Class used to find the shortest path from the start to the finish position in the maze using BFS
 *
 * @author Jaya Kasa
 * @version 1.0
 */
public class MazeShortestPath {
    private int finishx = 0;
    private int finishy = 0;
    private int startx = 0;
    private int starty = 0;
    TestNode[][] maze = null;

    MazeShortestPath(TestNode[][] maze, int finishx, int finishy, int startx, int starty){
        this.maze = maze;
        this.startx = --startx;
        this.starty = --starty;
        this.finishx = --finishx;
        this.finishy = --finishy;
    }


    MazeShortestPath(TestNode[][] maze, int finishx, int finishy){
        this.maze = maze;
        this.finishx = --finishx;
        this.finishy = --finishy;
    }

    MazeShortestPath(TestNode[][] maze){
        this.maze = maze;
        this.finishx = maze.length-1;
        this.finishy = maze[finishx].length-1;
    }

    void solve(){
        int x = finishx;
        int y = finishy;
        if(maze[x][y] == null){
            System.err.println("Maze is unsolvable. The Goal Position is a barrier, which is an illegal goal position.");
        }
        //System.out.println(maze[startx][starty]);
        //System.out.println(maze[x][y]);
        BFS(maze, maze[startx][starty]);
        Stack<TestNode> path = new Stack<>();
        TestNode node = maze[x][y];
        //System.out.println(node);
        //System.out.println(node.compareTo(maze[startx][starty]) != 0);
        System.out.println("Calculated Shortest path takes " + node.getD() + " steps.");
        while(node.compareTo(maze[startx][starty]) != 0){
            //System.out.println(node.getD());
            path.add(node);
            node = node.getParent();
            //System.out.println("Parent" + node);
            //System.out.println(node.getX() != 0 && node.getY() != 0);
        }
        path.add(node);

        System.out.print("[ ");
        while(!path.empty()){
            System.out.print(path.pop() + " ");
        }
        System.out.println(" ]");
    }

    private void BFS(TestNode[][] maze, TestNode start){
        start.setColor(TestNode.Color.Gray);
        Queue<TestNode> queue = new LinkedList<>();
        queue.add(start);
        TestNode curr = null;
        while(!queue.isEmpty()){
            curr = queue.remove();
            //System.out.println(curr + " parent is " + curr.getParent());
            TestNode[] neighbors = neighbors(curr);
            int count = 0;
            for(TestNode t : neighbors){
                //System.out.println(count++ + " "  + t);
                if(t != null && t.getColor() == TestNode.Color.White){
                    t.setColor(TestNode.Color.Gray);
                    t.setDistance(curr.getD()+1);
                    t.setParent(curr);
                    queue.add(t);
                }
            }
            curr.setColor(TestNode.Color.Black);
        }
        System.out.println("Last visited node was" + curr);

    }

    private TestNode[] neighbors(TestNode curr){
        //calculate the neighbors
        int x = curr.getX();
        int y = curr.getY();
        TestNode[] neighbors = new TestNode[4];
        if(x-1 > 0){
            //System.out.println("A");
            neighbors[0] = maze[x-1][y];
        }
        if(x+1 < maze.length){
            //System.out.println("B");
            neighbors[1] = maze[x+1][y];
        }
        if(y-1 > 0){
            //System.out.println("C");
            neighbors[2] = maze[x][y-1];
        }
        if(y+1 < maze[x].length){
            //System.out.println("D");
            neighbors[3] = maze[x][y+1];
        }

        return neighbors;
    }

    public static void main(String ... args){
        /*
        System.out.println("Solving smallmaze.txt");
        new MazeShortestPath(TestReader.readFile("smallmaze")).solve();
        System.out.println("Solving multipath.txt");
        new MazeShortestPath(TestReader.readFile("multipath")).solve();

        System.out.println("Solving maze_No_1.txt");
        new MazeShortestPath(TestReader.readFile("maze_No_1.txt")).solve();
        System.out.println("Solving maze_No_2.txt");
        new MazeShortestPath(TestReader.readFile("maze_No_2.txt")).solve();
        */
        /*
        System.out.println("\n\nSolving for custom start postions");
        System.out.println("Solving smallmaze.txt");
        new MazeShortestPath(TestReader.readFile("smallmaze"), 3, 3, 2, 1).solve();
        System.out.println("Solving multipath.txt");
        new MazeShortestPath(TestReader.readFile("multipath"), 3, 4, 2, 1).solve();
        */
        System.out.println("Solving maze_No_1.txt");
        new MazeShortestPath(TestReader.readFile("maze_No_1.txt"), 78, 47, 1, 1).solve();
        System.out.println("Solving maze_No_2.txt");
        new MazeShortestPath(TestReader.readFile("maze_No_2.txt"), 101, 101, 1, 53).solve();
        /*
        System.out.println("\n\nSolving for custom finish postions");
        System.out.println("Solving smallmaze.txt");
        new MazeShortestPath(TestReader.readFile("smallmaze"), 3 , 1).solve();
        System.out.println("Solving multipath.txt");
        new MazeShortestPath(TestReader.readFile("multipath"), 3 , 1).solve();
        System.out.println("Solving maze_No_1.txt");
        new MazeShortestPath(TestReader.readFile("maze_No_1.txt"), 92, 87).solve();
        System.out.println("Solving maze_No_2.txt");
        new MazeShortestPath(TestReader.readFile("maze_No_2.txt"), 92, 87).solve();
        */
    }
}
