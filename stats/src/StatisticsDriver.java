import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatisticsDriver {


    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Statistics stat=new StatisticsImpl();

        while(true) {
            int x=(int)Math.random();
            executor.execute(() -> stat.event(x));
        }
    }
}
