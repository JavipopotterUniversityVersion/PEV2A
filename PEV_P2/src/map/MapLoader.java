package map;

import java.io.*;

public class MapLoader {

    public static int[][] load(String path) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(path));

        String line;
        int rows = 0;
        int cols = 0;

        while((line = br.readLine()) != null){

            if(line.contains("DIMENSIONES")){
                String[] parts = line.split("=");
                String[] dims = parts[1].trim().split(" ");
                rows = Integer.parseInt(dims[0]);
                cols = Integer.parseInt(dims[1]);
                break;
            }

        }

        int[][] map = new int[rows][cols];

        for(int i=0;i<rows;i++){

            String[] values = br.readLine().trim().split(" ");

            for(int j=0;j<cols;j++){
                map[i][j] = Integer.parseInt(values[j]);
            }

        }

        br.close();

        return map;
    }

}