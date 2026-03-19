package operators;

import java.util.*;

public class CrossoverCX {

    public static int[] crossover(int[] p1,int[] p2){

        int size = p1.length;

        int[] child = new int[size];
        Arrays.fill(child,-1);

        int index = 0;

        while(child[index] == -1){

            child[index] = p1[index];

            int val = p2[index];

            index = indexOf(p1,val);
        }

        for(int i=0;i<size;i++)
            if(child[i]==-1)
                child[i] = p2[i];

        return child;
    }

    static int indexOf(int[] arr,int v){

        for(int i=0;i<arr.length;i++)
            if(arr[i]==v)
                return i;

        return -1;
    }
}