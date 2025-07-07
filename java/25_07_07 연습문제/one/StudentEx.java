package one;

import java.io.*;
import java.util.*;

public class StudentEx {
    public static void main(String[] args) {
        ArrayList<Studd> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");

                int id = Integer.parseInt(st.nextToken().trim());
                String name = st.nextToken().trim();
                int korean = Integer.parseInt(st.nextToken().trim());
                int math = Integer.parseInt(st.nextToken().trim());
                int english = Integer.parseInt(st.nextToken().trim());

                Studd s = new Studd(id, name, korean, math, english);
                students.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (Studd s : students) {
            s.printInfo();
        }
    }



    //강사님 풀이
//static ArrayList<Studd> students = new ArrayList<>();
//
//    public static void readFromFile() throws Exception{
//        Reader reader = new FileReader("student.txt");
//        BufferedReader inFile = new BufferedReader(reader);
//        while ( true ) {
//            String sLine = null;
//            if ( (sLine = inFile.readLine()) == null )
//                break;
//            StringTokenizer st = new StringTokenizer(sLine, ",");
//            if ( st.countTokens() != 5)
//                continue;
//            String hakbun =null;
//            String name = null;
//            int kor, eng, math;
//
//            hakbun = st.nextToken().trim();
//            name = st.nextToken().trim();
//            kor = Integer.parseInt(st.nextToken().trim());
//            eng = Integer.parseInt(st.nextToken().trim());
//            math= Integer.parseInt(st.nextToken().trim());
//
//            students.add(new Studd(hakbun, name, kor, eng, math));
//
//        }
//
//    }
//    public static void main(String[] args) {
//
//        try {
//            readFromFile();
//        } catch(Exception e) {}
//
//        for (Studd st: students) {
//            System.out.println(st.getHakbun() + " " + st.getName());
//        }
//
//    }
}

