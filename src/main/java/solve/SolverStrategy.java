package solve;

import utils.Stats;

public interface SolverStrategy {
    Stats solve(int N);
    String getName();
}
