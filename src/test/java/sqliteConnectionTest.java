import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class sqliteConnectionTest {
    @Test
    public void connectionTest(){
        Connection c =null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            System.err.print(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        System.out.println("成功连接数据库！");

    }
}
