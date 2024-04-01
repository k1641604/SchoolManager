import java.sql.*;

public class SchoolManagerMain {
    public static void main(String [] args){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test","root","password");

            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

}