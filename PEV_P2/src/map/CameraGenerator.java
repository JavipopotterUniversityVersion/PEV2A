package map;

import java.util.*;
import model.*;

public class CameraGenerator {

    public static List<Camera> generate(
            int numCameras,
            int[][] map,
            long seed){

        Random rand = new Random(seed);

        int height = map.length;
        int width = map[0].length;

        List<Camera> cameras = new ArrayList<>();

        Set<String> used = new HashSet<>();

        while(cameras.size() < numCameras){

            int x = rand.nextInt(height);
            int y = rand.nextInt(width);

            // evitar muros
            if(map[x][y] == 0)
                continue;

            String key = x+"_"+y;

            if(used.contains(key))
                continue;

            used.add(key);

            cameras.add(
                new Camera(
                    cameras.size() + 1,
                    new Point(x,y)
                )
            );
        }

        return cameras;
    }

}