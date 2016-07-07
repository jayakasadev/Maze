package Util;

/**
 * @author Jaya Kasa
 * @version 1.0
 */
class MazeReader {
    private static MazeReader mazeReader = null;
    private MazeReader(){}

    public static MazeReader getInstance(){
        if(mazeReader == null){
            synchronized (mazeReader){
                if(mazeReader == null){
                    mazeReader = new MazeReader();
                }
            }
        }
        return mazeReader;
    }

    /**
     * Method reads the maze from file and creates the data structure to hold the maze
     *
     * @param file
     */
    public void readFile(String file){
        //TODO implement way to read file into memory
    }
}
