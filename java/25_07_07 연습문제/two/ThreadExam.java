package two;

public class ThreadExam {
    public static void main(String[] args) {
        Thread thread1 = new MovieThread();
        thread1.start();

        Thread thread2 = new MusicRunnable();
        thread2.start();
    }
}
