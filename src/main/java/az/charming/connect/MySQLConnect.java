package az.charming.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLConnect {

    public static Connection connect() throws Exception{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String host = "sql7.freemysqlhosting.net";
            String port = "3306";
            String db = "sql7629150";
            String connUrl = String.format("jdbc:mysql://%s:%s/%s",host,port,db);
            Connection con = DriverManager.getConnection(connUrl,"sql7629150","MuU2MNjIwS");
            System.out.println("Connected");
            return con;
    }
}
