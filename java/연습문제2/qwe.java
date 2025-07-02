package eeeee33;

public class qwe {
    Vehicle field = new Vehicle() {
        @Override
        public void run() {
            System.out.println("자전거 달립니다");
        }
    };
    void me1() {
        Vehicle loo = new Vehicle() {
            @Override
            public void run() {
                System.out.println("승용차가 달립니다");
            }
        };
        loo.run();
    }
    void me2(Vehicle v) {
        v.run();
    }
}
