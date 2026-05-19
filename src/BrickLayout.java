import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BrickLayout {

    private static ArrayList<Brick> bricks;
    private int[][] grid;
    int n = bricks.size();
    public void fillGrid(){
        int[] grids=new int[n];
        for (int i = 0; i < n; i++) {
            grids[i] = 1;
            for (int x = 0; x < i; x++) {
                if (bricks.get(i).getStart() <= bricks.get(x).getEnd() && bricks.get(i).getEnd() >= bricks.get(x).getStart()) {
                    grids[i] = Math.max(grids[i], grids[x] + 1);
                }
            }

        }
        }


    public BrickLayout(String inputFile) {
        ArrayList<String> fileData = getFileData(inputFile);
        ArrayList<Brick> bricks = new ArrayList<Brick>();
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
        fillGrid();

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