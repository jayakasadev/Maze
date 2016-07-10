package Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Jaya Kasa
 * @version 1.0
 */
public class MazeReader {
    private MazeReader(){}

    /**
     * Method reads the maze from file and creates the data structure to hold the maze
     *
     * @param file
     */
    public static MazeNode[][] readFile(String file) throws IOException {
        List<Integer> cols = new LinkedList<>();
        int rows = 0;
        Path path = FileSystems.getDefault().getPath("data", file);
        //calculating number of rows and columns
        try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String s = "";
            while((s = reader.readLine()) != null){
                rows++;
                cols.add(s.length());
            }
        } catch (java.io.IOException e) {
            System.err.println("Problem reading file " + file + "\nCause: " + e.getCause());
            throw e;
        }

        //System.out.println(rows);
        //System.out.println(cols);

        MazeNode[][] maze = new MazeNode[rows][];
        int countx = 0;
        int county = 0;
        try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            int c = -1;
            MazeNode[] temp = new MazeNode[cols.get(countx)];
            while((c = reader.read()) != -1){
                //System.out.print((char)c);
                if(c == '.'){
                    temp[county] = new MazeNode(countx, county);
                    //System.out.print(temp[county] + " ");
                    county++;
                    //System.out.print(true + " ");
                }
                else if(c == '#'){
                    temp[county] = null;
                    //System.out.print(temp[county] + " ");
                    county++;
                    //System.out.print(false + " ");
                }
                else if(c == '\n'){
                    //System.out.println();
                    //System.out.println(county);
                    //System.out.print(county);
                    county = 0;
                    maze[countx] = temp;
                    temp = new MazeNode[cols.get(countx)];
                    countx++;
                }
            }
            maze[countx] = temp;
        } catch (java.io.IOException e) {
            System.err.println("Problem reading file " + file + "\nCause: " + e.getCause());
            return null;
        }

        /*
        for(int a = 0; a < maze.length; a++){
            for(int b = 0; b < maze[a].length; b++){
                System.out.print(maze[a][b]);
                //System.out.print(b + " ");
            }
            System.out.println();
        }
        */
        return maze;
    }
}
