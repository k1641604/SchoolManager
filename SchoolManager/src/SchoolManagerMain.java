import java.sql.*;

public class SchoolManagerMain {
    public static void main(String [] args){
        try{
            SchoolManagerFrame a = new SchoolManagerFrame();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/SchoolManager","root","password");

            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

}