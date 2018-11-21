import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SendMessage {
    public static void main(String[] args) {
         ExecutorService executorService;

         Runnable runnable;

        runnable= new JMSConnectRunnable();

        executorService = Executors.newSingleThreadExecutor();

        executorService.execute(runnable);

    }
}
