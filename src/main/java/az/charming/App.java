package az.charming;

import az.charming.connect.MySQLConnect;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        MySQLConnect.connect();
    }
}
