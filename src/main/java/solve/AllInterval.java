package solve;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.variables.IntVar;
import stats.Stats;

import java.util.Dictionary;

public class AllInterval {

    public static final String name = "Fast AllInterval";

    public static Stats solve(int N){
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
        solver.printStatistics();

        return new Stats(name,N,solver.getSolutionCount(),solver.getTimeCount(),solver.getBackTrackCount(),solver.getFailCount());
    }

    public static void main(String[] args) {
        int N = 13;
        Stats stats = solve(N);
        System.out.println("name\t\t\t\t\tN\tResolution time\t\tSolutions\t\tBacktracks\t\tFails");
        System.out.println(stats.toTable());

    }
}
