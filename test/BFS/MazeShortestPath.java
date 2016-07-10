package BFS;

import Util.MazeNode;
import Util.MazeReader;
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
    TestNode[][] maze = null;

    MazeShortestPath(TestNode[][] maze){
        this.maze = maze;
    }

    void solve(){
        int x = maze.length-1;
        int y = maze[x].length-1;
        if(maze[x][y] == null){
            System.err.println("Maze is unsolvable. The Goal Position is a barrier, which is an illegal goal position.");
        }
        BFS(maze, maze[0][0], maze[x][y]);
        Stack<TestNode> path = new Stack<>();
        TestNode node = maze[x][y];
        System.out.println("Calculated Shortest path takes " + node.getD() + " steps.");
        while(node.compareTo(maze[0][0]) != 0){
            //System.out.println(node.getD());
            path.add(node);
            node = node.getParent();
            //System.out.println(node.getX() != 0 && node.getY() != 0);
        }
        path.add(node);

        System.out.print("[ ");
        while(!path.empty()){
            System.out.print(path.pop() + " ");
        }
        System.out.println(" ]");
    }

    private void BFS(TestNode[][] maze, TestNode start, TestNode finish){
        start.setColor(TestNode.Color.Gray);
        Queue<TestNode> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()){
            TestNode curr = queue.remove();
            TestNode[] neighbors = neighbors(curr);
            for(TestNode t : neighbors){
                if(t != null && t.getColor() == TestNode.Color.White){
                    t.setColor(TestNode.Color.Gray);
                    t.setDistance(curr.getD()+1);
                    t.setParent(curr);
                    queue.add(t);
                }
            }
            curr.setColor(TestNode.Color.Black);
        }

    }

    private TestNode[] neighbors(TestNode curr){
        //calculate the neighbors
        int x = curr.getX();
        int y = curr.getY();
        TestNode[] neighbors = new TestNode[4];
        if(x-1 > 0){
            neighbors[0] = maze[x-1][y];
            if(curr != null && neighbors[0] != null && (curr.getParent() != null && neighbors[0].compareTo(curr.getParent()) == 0)){
                neighbors[0] = null;
            }
        }
        if(x+1 < maze.length){
            neighbors[1] = maze[x+1][y];
            if(curr != null && neighbors[1] != null && (curr.getParent() != null && neighbors[1].compareTo(curr.getParent()) == 0)){
                neighbors[1] = null;
            }
        }
        if(y-1 > 0){
            neighbors[2] = maze[x][y-1];
            if(curr != null && neighbors[2] != null && (curr.getParent() != null && neighbors[2].compareTo(curr.getParent()) == 0)){
                neighbors[2] = null;
            }
        }
        if(y+1 < maze[x].length){
            neighbors[3] = maze[x][y+1];
            if(curr != null && neighbors[3] != null && (curr.getParent() != null && neighbors[3].compareTo(curr.getParent()) == 0)){
                neighbors[3] = null;
            }
        }

        return neighbors;
    }

    public static void main(String ... args){
        System.out.println("Solving smallmaze.txt");
        new MazeShortestPath(TestReader.readFile("smallmaze")).solve();
        System.out.println("Solving multipath.txt");
        new MazeShortestPath(TestReader.readFile("multipath")).solve();
        System.out.println("Solving maze_No_1.txt");
        new MazeShortestPath(TestReader.readFile("maze_No_1.txt")).solve();
        System.out.println("Solving maze_No_2.txt");
        new MazeShortestPath(TestReader.readFile("maze_No_2.txt")).solve();
    }
}