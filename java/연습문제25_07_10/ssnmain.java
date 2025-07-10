package one;

import java.sql.*;

public class ssnmain {
    public static Connection makeConnection() {
        String url = "jdbc:mysql://localhost:3306/new_table";
        String userName="root";
        String userPass = "khj01040322@";
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("데이터베이스 연결중 ...");
            con = DriverManager.getConnection(url, userName, userPass);
            System.out.println("데이터베이스 연결 성공");
        }catch(ClassNotFoundException e) {
            System.out.println("JDBC 드라이버를 찾지 못했습니다...");
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 실패");
            System.out.println(e.getMessage());
        }
        return con;

    }


    public void sselect() {
        try {
            Connection con = makeConnection();
            Statement stmt = con.createStatement();

            String ssqq = "select * from student";
            PreparedStatement pstmt = con.prepareStatement(ssqq);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("학번 : " + rs.getInt(1) + "\t" + "이름 : " + rs.getString("stdname") + "\t" + "전화번호 : " + rs.getString("phone") + "\t" + "이메일 : " + rs.getString("email"));
            }
            con.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }





    public void sinsert() {
        try {
            Connection con = makeConnection();
            Statement stmt = con.createStatement();

            String sql = "insert into student (stdno, stdname, phone, email) values "
                    + "(200, '유재석', '010-3626-1111', 'rhu@gmail.com'), "
                    + "(300, '나검사', '010-8888-9999', 'naking@naver.com')";

            int result = stmt.executeUpdate(sql); // 삽입된 행 수 반환

            if (result >= 1)
                System.out.println("성공");
            else
                System.out.println("실패");

            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }





    public void sdelete() {
        try {
            Connection con = makeConnection();
            Statement stmt = con.createStatement();

            String sql = "delete from student where stdname = '이순신'";

            int result = stmt.executeUpdate(sql);

            if (result == 1)
                System.out.println("성공");
            else
                System.out.println("실패");

            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




    public void supdate() {
        try {
            Connection con = makeConnection();
            Statement stmt = con.createStatement();

            String sql = "update student set phone = '010-6666-7878' where stdname = '오짱구'";

            int result = stmt.executeUpdate(sql); // 변경된 행 수 반환

            if (result == 1)
                System.out.println("성공");
            else
                System.out.println("실패");

            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void ifselect() {
        try {
            Connection con = makeConnection();
            Statement stmt = con.createStatement();

            String sql = "select * from student where phone = '010-3626-1111'";

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                System.out.println("학번: " + rs.getInt("stdno"));
                System.out.println("이름: " + rs.getString("stdname"));
                System.out.println("전화번호: " + rs.getString("phone"));
                System.out.println("이메일: " + rs.getString("email"));
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
