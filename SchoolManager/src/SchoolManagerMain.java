import java.sql.*;

public class SchoolManagerMain {
    public static void main(String [] args){
        try{

            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/SchoolManager","root","password");
            Statement s = con.createStatement();
            new SchoolManagerFrame(con);
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

}


/* proper way to create table for teacher below, see canvas page for more tables to be created

CREATE TABLE IF NOT EXISTS teacher (teacher_id INTEGER Not Null Primary Key AUTO_INCREMENT,
first_name Text,
last_name Text
);



 */