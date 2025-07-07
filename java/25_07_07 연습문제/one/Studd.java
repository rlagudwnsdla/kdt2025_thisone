package one;

public class Studd {
    private int id;
    private String name;
    private int korean;
    private int math;
    private int english;

    public Studd(int id, String name, int korean, int math, int english) {
        this.id = id;
        this.name = name;
        this.korean = korean;
        this.math = math;
        this.english = english;
    }

    public int getTotal() {
        return korean + math + english;
    }

    public double getAverage() {
        return getTotal() / 3.0;
    }

    public void printInfo() {
        System.out.printf("학번: %d, 이름: %s, 국어: %d, 수학: %d, 영어: %d, 총점: %d, 평균: %.2f\n",
                id, name, korean, math, english, getTotal(), getAverage());
    }



    //강사님 풀이
//private String hakbun;
//    private String name;
//    private int korean, eng, math;
//
//    public Studd(String hakbun, String name, int korean, int eng, int math) {
//        this.hakbun = hakbun;
//        this.name = name;
//        this.korean = korean;
//        this.eng = eng;
//        this.math = math;
//    }
//
//    public String getHakbun() {
//        return hakbun;
//    }
//    public void setHakbun(String hakbun) {
//        this.hakbun = hakbun;
//    }
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public int getKorean() {
//        return korean;
//    }
//    public void setKorean(int korean) {
//        this.korean = korean;
//    }
//    public int getEng() {
//        return eng;
//    }
//    public void setEng(int eng) {
//        this.eng = eng;
//    }
//    public int getMath() {
//        return math;
//    }
//    public void setMath(int math) {
//        this.math = math;
//    }
//
//    double getAverage() {
//        return getTotal() / 3;
//    }
//    int getTotal() {
//        return (korean + eng + math);
//    }
}