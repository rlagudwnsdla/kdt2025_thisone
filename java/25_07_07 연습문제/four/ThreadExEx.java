package four;

public class ThreadExEx {
    public static void main(String[] args) {
        Thread thread = new MovieThread();
        thread.setDaemon(true);
        thread.start();


        try {
            Thread.sleep(3000);
        }catch (Exception e) {

        }
    }
}
