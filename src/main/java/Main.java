import solve.AllInterval;
import solve.AllIntervalTable;
import solve.AllIntervalWithGT;
import solve.SolverStrategy;
import stats.Stats;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {

       public static void main(String[] args) {
           // Add solver strategies
           ArrayList<SolverStrategy> Solvers = new ArrayList();
           Solvers.add(new AllInterval());
           Solvers.add(new AllIntervalWithGT());
           Solvers.add(new AllIntervalTable());

           // Remove solvers that dont get enought disc space for big values
           ArrayList avoidBigN = new ArrayList();
           avoidBigN.add(AllIntervalTable.class);

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
               if(!avoidBigN.contains(solver.getClass())){
                   System.out.println(solver.solve(12).toTable());
                   System.out.println(solver.solve(13).toTable());
               }

           }

    }
}
