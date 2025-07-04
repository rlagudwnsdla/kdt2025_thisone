package two;
import java.util.*;

public class SumPositives {
    private Vector<Integer> v= new Vector<>();
    public void read() {
        Scanner ss = new Scanner(System.in);
        while (true) {
            int q = ss.nextInt();
            if(q == 0) {
                break;
            }
            else {
                v.add(q);
            }
        }
    }
    public void changeToZero() {
        for(int i=0; i< v.size(); i++) {
            int q = v.get(i);
            if(q < 0) {
                v.set(i,0);
            }
        }
    }
    public void showAll() {
        for(int i : v) {
            System.out.println(i + " ");
        }
        System.out.println();
    }
    public int add() {
        int sum = 0;
        for(int i=0; i<v.size();i++) {
            int q = v.get(i);
            sum += q;
        }
        return sum;
    }


    public static void main(String[] args) {
        SumPositives sp = new SumPositives();
        sp.read();
        sp.changeToZero();
        System.out.println("음수를 0으로 바꾸면");
        sp.showAll();
        System.out.println("양수들의 합은 "+ sp.add());
    }
}