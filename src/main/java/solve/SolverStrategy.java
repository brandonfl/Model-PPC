package solve;

import stats.Stats;

public interface SolverStrategy {
    Stats solve(int N);
    String getName();
}
