package one;

import java.util.Scanner;
import java.util.Vector;

public class Scanners {
    public static void main(String[] args) {
        Scanner ss = new Scanner(System.in);
        Vector<Integer> vv = new Vector<>();

        while (true) {
            int q = ss.nextInt();

            if(q == -1) {
                vv.add(q);
                break;
            }
            else {
                vv.add(q);
            }
        }
        int min= vv.get(0);
        for(int i=1; i < vv.size(); i++) {
            int v1 = vv.get(i);
            if(min > v1) {
                min = v1;
            }
        }
        System.out.println(vv);
        System.out.println("제일 작은 수 : " + min);
    }
}
