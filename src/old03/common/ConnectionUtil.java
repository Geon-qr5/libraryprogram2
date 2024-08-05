package old03.common;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl", "LIBRARY_PRACTICE", "1234");

        } catch (ClassNotFoundException e) {
            System.out.println("※주의※ 라의브러리 확인필요");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("※주의※ DB접속 정보를 확인필요");
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(ResultSet rs, PreparedStatement pstmt, Connection con, Statement st) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
            if (st != null) st.close();
        } catch (SQLException e) {
            System.out.println("※주의※ closeConnection 처리 중 예외 발생");
            e.printStackTrace();
        }
    }
}