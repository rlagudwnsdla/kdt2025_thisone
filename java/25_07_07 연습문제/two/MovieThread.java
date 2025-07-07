package two;

public class MovieThread extends Thread {
    @Override
    public void run() {
        for(int i=0; i<3; i++) {
            System.out.println("재생");
        }
        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {

        }
    }
}
