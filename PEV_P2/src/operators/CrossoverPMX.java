package operators;

import java.util.*;

public class CrossoverPMX {

    public static int[][] crossover(int[] p1, int[] p2){

        Random rand = new Random();

        int size = p1.length;

        int c1 = rand.nextInt(size);
        int c2 = rand.nextInt(size);

        if(c1>c2){
            int t=c1; c1=c2; c2=t;
        }

        int[] h1 = new int[size];
        int[] h2 = new int[size];

        Arrays.fill(h1,-1);
        Arrays.fill(h2,-1);

        for(int i=c1;i<=c2;i++){
            h1[i]=p1[i];
            h2[i]=p2[i];
        }

        for(int i=0;i<size;i++){

            if(i>=c1 && i<=c2) continue;

            int val=p2[i];

            while(contains(h1,val)){
                int idx=indexOf(p2,val);
                val=p1[idx];
            }

            h1[i]=val;

            val=p1[i];

            while(contains(h2,val)){
                int idx=indexOf(p1,val);
                val=p2[idx];
            }

            h2[i]=val;

        }

        return new int[][]{h1,h2};

    }

    private static boolean contains(int[] arr,int v){
        for(int x:arr) if(x==v) return true;
        return false;
    }

    private static int indexOf(int[] arr,int v){
        for(int i=0;i<arr.length;i++) if(arr[i]==v) return i;
        return -1;
    }

}