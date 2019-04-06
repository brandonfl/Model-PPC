import solve.AllInterval;
import stats.Stats;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {

       public static void main(String[] args) {
        System.out.println("===============================================================================");
        System.out.println("Solving...\n");

           ArrayList<Stats> statistics = new ArrayList<>();

           statistics.add(AllInterval.solve(13));

           System.out.println("name\t\t\t\t\tN\tResolution time\t\tSolutions\t\tBacktracks\t\tFails");
           
           Iterator<Stats> iterator = statistics.iterator();
           while (iterator.hasNext()){
               Stats stats = iterator.next();
               System.out.println(stats.toTable());
           }

    }
}
