import java.util.concurrent.Callable;

public class MsgCallable implements Callable<Integer> {
    public static final int MSG_CNT = 5;
    public static final int THREAD_TIMEOUT = 2000;
    private static int id = 0;

    @Override
    public Integer call() throws Exception {
        int i;
        id++;
        // имя потока
        String name = "msgThread"+id;
        Thread.currentThread().setName(name);
        // работа потока
        for(i =0; i < MSG_CNT; i++) {
            // проверка на остановку
            if(Thread.currentThread().isInterrupted()) return i;
            // вывод сообщений
            try {
                Thread.sleep(THREAD_TIMEOUT);
                System.out.println("Tread " + name + ": msg #"+(i+1));
            } catch (InterruptedException e) {
                System.out.println(name + " was interrupted when sleeping!");
                return i;
            }
        }
        return i;
    }


}
