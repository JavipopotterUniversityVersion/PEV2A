package ga;
import java.util.Random;

public class Population {

    public Individual[] individuals;
    public int size;

    public Population(int size,int chromSize){

        this.size = size;

        individuals = new Individual[size];
        
        Random rand = new Random();

        for(int i=0; i<size; ++i) {
        	individuals[i] = new Individual(chromSize);
        	individuals[i].randomize(rand);
        }

    }

}