package db;

import java.sql.*;


public class SQLiteJDBC {

    public static void connect(){
        Connection conn = null;

        String sql = "CREATE TABLE IF NOT EXISTS employees (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " capacity real\n"
                + ");";

        try{
            conn = DriverManager.getConnection("jdbc:sqlite::memory:");
            System.out.println("Connection to SQLite has been established");

            Statement stmt = conn.createStatement();
            stmt.execute(sql);

            String sql2 = "INSERT INTO employees(name, capacity) VALUES(?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, "Aryan");
            pstmt.setDouble(2, 30000);
            pstmt.executeUpdate();

            System.out.println("Checking output");

            String sql3 = "SELECT * FROM employees";

            Statement stmt2  = conn.createStatement();
            ResultSet rs    = stmt2.executeQuery(sql3);

            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("name") + "\t" +
                        rs.getDouble("capacity"));
            }


        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
