package solve.minDomUBSearch;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.extension.Tuples;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.variables.IntVar;
import solve.SolverStrategy;
import stats.Stats;

public class AllIntervalTable implements SolverStrategy {
    public String name;


    public AllIntervalTable() {
        this.name = "MDUBS Table";

    }

    @Override
    public Stats solve(int N) {
        Model model = new Model("AllIntveral of "+ N);
        IntVar[] S = model.intVarArray("s", N, 0, N - 1, false);
        IntVar[] V = model.intVarArray("V", N - 1, 1, N - 1, false);
        Tuples tuples = new Tuples();

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N-1; j++){
                for (int k=0; k < N-1; k++){
                    if(Math.abs(i - j) == k){
                        tuples.add(i,j,k);
                    }
                }

            }

        }
        //System.out.println(tuples.toString());
        //System.out.println("nb tuples = " + tuples.nbTuples());

        for (int i = 0; i < N - 2; i++) {
            model.table(new IntVar[]{S[i],S[i+1],V[i]}, tuples).post();
        }

        model.allDifferent(S).post();
        model.allDifferent(V).post();

        Solver solver = model.getSolver();
        solver.setSearch(Search.minDomLBSearch(S));

        solver.findAllSolutions();
        //solver.printStatistics();

        Stats stats = new Stats(name,N,solver.getSolutionCount(),solver.getTimeCount(),solver.getBackTrackCount(),solver.getFailCount());
        return stats;

    }

    @Override
    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        SolverStrategy solver = new AllIntervalTable();

        int N = 8;
        Stats stats = solver.solve(N);

        System.out.println(String.format("%20s %10s %5s %5s %20s %10s %20s %10s %20s", "Name", "|","N", "|", "Resolution time", "|","Solutions","|","Backtracks","|","Fails"));
        System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------------------------------"));

        System.out.println(stats.toTable());

    }
}
