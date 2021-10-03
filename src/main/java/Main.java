import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // задачи
        Callable<Integer> task1 = new MsgCallable();
        Callable<Integer> task2 = new MsgCallable();
        Callable<Integer> task3 = new MsgCallable();
        // список задач
        List<Callable<Integer>> taskList = List.of(task1, task2, task3);
        // пул потоков
        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        // Отправляем задачу на выполнение в пул потоков
        List<Future<Integer>> tasksAll = null;
        try {
            tasksAll = threadPool.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // результат выполнения
        if(tasksAll != null) {
            tasksAll.forEach(x -> {
                try {
                    System.out.println(x.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        }
        // выполнение 1 задачи
        try {
            System.out.println(threadPool.invokeAny(taskList));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // закрываем пул потоков
        threadPool.shutdown();
    }
}
