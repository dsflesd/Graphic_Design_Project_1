import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BrickLayout {

    private ArrayList<Brick> bricks;
    private int[][] grid;
    private int currentBrick =0;

    public BrickLayout(String inputFile) {
        ArrayList<String> fileData = getFileData(inputFile);
       bricks = new ArrayList<Brick>();
        for (String line : fileData) {
            String[] points = line.split(",");
            int start = Integer.parseInt(points[0]);
            int end = Integer.parseInt(points[1]);
            Brick b = new Brick(start, end);
            bricks.add(b);
        }
        grid = new int[30][40];
    }
    public int[][] getGrid() {
        return grid;
    }

    public void dropOneBrick() {
        Brick current=bricks.get(currentBrick);
        int startCol=current.getStart();
        int endCol=current.getEnd();
        int landingRow=29;
        for (int row=0;row<30;row++){
            boolean collision=false;
            for (int cols=startCol;cols<=endCol;cols++){
                if (cols <40&& grid[row][cols] == 1) {
                    collision=true;
                    break;
                }
            }
            if (collision){
                landingRow=row-1;
                break;
            }
        }
        if (landingRow>=0) {
            for (int c=startCol;c<=endCol;c++){
                if(c<40){
                    grid[landingRow][c] = 1;
                }
            }
        }
        currentBrick++;
    }
    public  ArrayList<String> getFileData(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }
        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        return fileData;
    }
}