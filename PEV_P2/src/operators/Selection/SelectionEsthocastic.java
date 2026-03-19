package operators.Selection;

import ga.*;

public class SelectionEsthocastic {

    public static Individual select(Population pop) {
        Individual[] individuals = pop.individuals;

        double totalFitness = 0.0;
        for (Individual ind : individuals) {
            totalFitness += ind.fitness;
        }

        double point = Math.random() * totalFitness;

        double accumulated = 0.0;
        for (Individual ind : individuals) {
            accumulated += ind.fitness;
            if (accumulated >= point) {
                return ind;
            }
        }

        return individuals[individuals.length - 1];
    }

}

// **¿Cómo funciona la selección estocástica (ruleta)?**

// 1. Se calcula la **suma total del fitness** de todos los individuos.
// 2. Se genera un **punto aleatorio** entre `0` y `totalFitness`.
// 3. Se recorre la población acumulando fitness hasta superar el punto — el individuo en ese tramo es el seleccionado.

// **Ejemplo visual:**
// Individuo  Fitness  Segmento
//    A          10    [ 0,  10)   10%
//    B          30    [10,  40)   30%
//    C          50    [40,  90)   50%
//    D          10    [90, 100)   10%

// Punto aleatorio = 55  →  seleccionado: C