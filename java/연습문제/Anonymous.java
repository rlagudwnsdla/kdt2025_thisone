package ddddd33;

public class Anonymous {
    Worker field = new Worker() {
        @Override
        public void start() {
            System.out.println("익명 객체: 일을 시작합니다!");
        }
    };

    void method1() {
        Worker local = new Worker() {
            @Override
            public void start() {
                System.out.println("익명 객체: 지역 작업 시작!");
            }
        };

        local.start();
    }
}