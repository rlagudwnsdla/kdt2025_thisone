package three;

public class MovieThread extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("재생");
            if(Thread.currentThread().isInterrupted()) {
                System.out.println("인터럽트 감지");
                break;
            }
        }
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            System.out.println("끝");
        }
    }
}
