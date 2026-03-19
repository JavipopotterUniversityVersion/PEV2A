package ga;

import gui.*;
import java.util.*;
import javax.swing.SwingUtilities;
import operators.*;
import operators.Mutation.MutationInsertion;
import operators.Mutation.MutationInversion;
import operators.Mutation.MutationSwap;
import operators.Selection.SelectionEsthocastic;
import operators.Selection.SelectionRank;
import operators.Selection.SelectionRest;
import operators.Selection.SelectionRoulette;
import operators.Selection.SelectionTournament;
import operators.Selection.SelectionTrunc;

public class GeneticAlgorithm {

    private int populationSize;
    private int generations;

    private double crossoverProb;
    private double mutationProb;

    private MainWindow window;
    String crossoverType;
    String mutationType;
    String selectionType;

    public GeneticAlgorithm(
            int populationSize,
            int generations,
            double crossoverProb,
            double mutationProb,
            MainWindow window,
            String crossoverType,
            String mutationType,
            String selectionType) {

        this.populationSize = populationSize;
        this.generations = generations;
        this.crossoverProb = crossoverProb;
        this.mutationProb = mutationProb;
        this.window = window;
        this.crossoverType = crossoverType;
        this.mutationType = mutationType;
        this.selectionType = selectionType;
    }

    public Individual run(Population pop, FitnessCalculator fitness) {

        Random rand = new Random();

        double[] bestOfGenHistory = new double[generations];
        double[] bestOfAllHistory = new double[generations];
        double[] averageHistory = new double[generations];

        evaluate(pop,fitness);

        Individual best = getBest(pop);

        for(int g=0; g<generations; g++){

            Population newPop = new Population(populationSize,
                        pop.individuals[0].chromosome.length);

            for(int i=0;i<populationSize;i++){

                Individual p1 = null, p2 = null;

                //#region SELECTION_SWITCH
                switch(selectionType){
                    case "Tournament":
                        p1 = SelectionTournament.select(pop,3);
                        p2 = SelectionTournament.select(pop,3);
                        break;
                    case "Roulette":
                        p1 = SelectionRoulette.select(pop);
                        p2 = SelectionRoulette.select(pop);
                        break;
                    case "Rest":
                       p1 = SelectionRest.select(pop);
                       p2 = SelectionRest.select(pop);
                        break;
                    case "Esthocastic":
                       p1 = SelectionEsthocastic.select(pop);
                       p2 = SelectionEsthocastic.select(pop);
                        break;
                    case "Rank":
                       p1 = SelectionRank.select(pop);
                       p2 = SelectionRank.select(pop);
                        break;
                    case "Truncation":
                       p1 = SelectionTrunc.select(pop, 0.5f);
                       p2 = SelectionTrunc.select(pop, 0.5f);
                        break;
                    default:
                        p1 = SelectionTournament.select(pop,3);
                        p2 = SelectionTournament.select(pop,3);
                        break;
                    
                }
                //#endregion

                Individual child = p1.copy();

                if(rand.nextDouble() < crossoverProb){

                    int[] c = null;

                    //#region CROSSOVER_SWITCH
                    switch(crossoverType){
                        case "CO":
                            c = CrossoverCO.crossover(p1.chromosome, p2.chromosome);
                            break;
                        case "CX":
                            c = CrossoverCX.crossover(p1.chromosome, p2.chromosome);
                            break;
                        case "ERX":
                            c = CrossoverERX.crossover(p1.chromosome, p2.chromosome);
                            break;
                        case "OX":
                            c = CrossoverOX.crossover(p1.chromosome, p2.chromosome);
                            break;
                        case "OXPP":
                            c = CrossoverOXPP.crossover(p1.chromosome, p2.chromosome);
                            break;
                        case "PMX":
                            c = CrossoverPMX.crossover(p1.chromosome, p2.chromosome)[0];
                            break;
                    }
                    //#endregion

                    child.chromosome = c;
                }

                if(rand.nextDouble() < mutationProb)
                    //#region MUTATION_SWITCH
                    switch(mutationType){
                        case "Insertion":
                           MutationInsertion.mutate(child.chromosome);
                            break;
                        case "Swap":
                            MutationSwap.mutate(child.chromosome);
                            break;
                        case "Inversion":
                           MutationInversion.mutate(child.chromosome);
                            break;
                        case "Heuristic":
                        //    MutationHeuristic.mutate(child.chromosome);
                            break;
                    }
                    //#endregion

                newPop.individuals[i] = child;
            }

            evaluate(newPop,fitness);

            pop = newPop;

            Individual genBest = getBest(pop);
            bestOfGenHistory[g] = genBest.fitness;
            
            if(genBest.fitness < best.fitness)
                best = genBest;

            bestOfAllHistory[g] = best.fitness;

            updateGUI(bestOfAllHistory, bestOfGenHistory, averageHistory,g);

        }

        return best;
    }

    private void updateGUI(
    		double[] bestOfAllHistory, 
    		double[] bestOfGenHistory, 
    		double[] averageHistory, 
    		int generation
    		){

        double[] data = Arrays.copyOf(bestOfAllHistory,generation+1);

        SwingUtilities.invokeLater(() -> {

            window.getPlotPanel().plotFitness(bestOfAllHistory, bestOfGenHistory, averageHistory);
            //window.getMapPanel().setPath(bestPath);

        });
    }

    private void evaluate(Population pop, FitnessCalculator fit){

        for(Individual ind : pop.individuals)
            ind.fitness = fit.evaluate(ind);
    }

    private Individual getBest(Population pop){

        Individual best = pop.individuals[0];

        for(Individual ind : pop.individuals)
            if(ind.fitness < best.fitness)
                best = ind;

        return best;
    }
}