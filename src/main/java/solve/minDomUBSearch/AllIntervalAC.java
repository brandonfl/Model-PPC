package solve.minDomUBSearch;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.variables.IntVar;
import solve.SolverStrategy;
import utils.Stats;


public class AllIntervalAC implements SolverStrategy {
    public String name;


    public AllIntervalAC() {
        this.name = "MDUBS AC";

    }


    @Override
    public Stats solve(int N){
        Model model = new Model(name + " of "+ N);
        IntVar[] S = model.intVarArray("s", N, 0, N - 1, false);
        IntVar[] V = model.intVarArray("V", N - 1, 1, N - 1, false);
        for (int i = 0; i < N - 1; i++) {
            model.distance(S[i + 1], S[i], "=", V[i]).post();

        }
        model.allDifferent(S,"AC").post();
        model.allDifferent(V,"AC").post();

        Solver solver = model.getSolver();
        solver.setSearch(Search.minDomUBSearch(S));

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
        SolverStrategy solver = new AllIntervalAC();

        int N = 13;
        Stats stats = solver.solve(N);

        System.out.println(String.format("%20s %10s %5s %5s %20s %10s %20s %10s %20s", "Name", "|","N", "|", "Resolution time", "|","Solutions","|","Backtracks","|","Fails"));
        System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------------------------------"));

        System.out.println(stats.toTable());

    }
}
