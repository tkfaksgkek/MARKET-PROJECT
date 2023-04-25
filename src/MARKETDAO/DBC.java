package MARKETDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBC {
    public static Connection DBConnect(){
        Connection con = null;
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "HYEHWI";
        String password = "1111";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("DB접속 실패 : 드라이버 로딩 실패!");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("DB접속 실패 : 접속 정보 확인!");
            throw new RuntimeException(e);
        }
        return con;
    }
}
