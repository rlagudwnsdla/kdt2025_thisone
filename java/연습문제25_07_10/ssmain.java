package one;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ssmain {
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
    public static void main(String[] args) {
        ssnmain mm = new ssnmain();

        mm.sselect();
        mm.supdate();
        mm.sinsert();
        mm.sdelete();
        mm.ifselect();
    }
}
