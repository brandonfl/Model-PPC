import solve.Default.AllInterval;
import solve.minDomLBSearch.*;
import solve.SolverStrategy;
import stats.Stats;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {

       public static void main(String[] args) {
           // Add solver strategies
           ArrayList<SolverStrategy> Solvers = new ArrayList();

           // minDomUBSearch
           Solvers.add(new solve.minDomUBSearch.AllInterval());
           Solvers.add(new solve.minDomUBSearch.AllIntervalAC());
           Solvers.add(new solve.minDomUBSearch.AllIntervalBC());
           Solvers.add(new solve.minDomUBSearch.AllIntervalTable());
           Solvers.add(new solve.minDomUBSearch.AllIntervalWithGT());

           // minDomLBSearch
           Solvers.add(new solve.minDomLBSearch.AllInterval());
           Solvers.add(new solve.minDomLBSearch.AllIntervalAC());
           Solvers.add(new solve.minDomLBSearch.AllIntervalBC());
           Solvers.add(new solve.minDomLBSearch.AllIntervalTable());
           Solvers.add(new solve.minDomLBSearch.AllIntervalWithGT());

           // default
           Solvers.add(new solve.Default.AllInterval());
           Solvers.add(new solve.Default.AllIntervalAC());
           Solvers.add(new solve.Default.AllIntervalBC());
           Solvers.add(new solve.Default.AllIntervalTable());
           Solvers.add(new solve.Default.AllIntervalWithGT());


        ArrayList<Stats> statistics = new ArrayList<>();

        System.out.println("===============================================================================");
        System.out.println("Solving...\n");
        System.out.println(String.format("%20s %10s %5s %5s %20s %10s %20s %10s %20s", "Name", "|","N", "|", "Resolution time", "|","Solutions","|","Backtracks","|","Fails"));



        Iterator<SolverStrategy> iterator = Solvers.iterator();
           while (iterator.hasNext()){
               System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------------------------------"));
               SolverStrategy solver = iterator.next();
               System.out.println(solver.solve(8).toTable());
               System.out.println(solver.solve(10).toTable());
               System.out.println(solver.solve(12).toTable());
               System.out.println(solver.solve(13).toTable());


           }
           System.out.println("\n");

    }
}
