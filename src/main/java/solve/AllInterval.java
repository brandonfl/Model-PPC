package solve;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.variables.IntVar;
import stats.Stats;

import java.util.Dictionary;

public class AllInterval implements SolverStrategy{
    public String name;


    public AllInterval() {
        this.name = "Fast AllInterval";

    }



    public Stats solve(int N){
        Model model = new Model(name + " of "+ N);
        IntVar[] S = model.intVarArray("s", N, 0, N - 1, false);
        IntVar[] V = model.intVarArray("V", N - 1, 1, N - 1, false);
        for (int i = 0; i < N - 1; i++) {
            model.distance(S[i + 1], S[i], "=", V[i]).post();

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



    public static void main(String[] args) {
        SolverStrategy solver = new AllInterval();

        int N = 13;
        Stats stats = solver.solve(N);

        System.out.println(String.format("%20s %10s %5s %5s %20s %10s %20s %10s %20s", "Name", "|","N", "|", "Resolution time", "|","Solutions","|","Backtracks","|","Fails"));
        System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------------------------------"));

        System.out.println(stats.toTable());

    }
}
