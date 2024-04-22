package com.example.dao;

import com.example.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao 
{

    private static final String URL = "jdbc:postgresql://localhost:5432/university";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "sakthi";

    public static List<User> getRecords(int start, int total) 
	{
        List<User> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try 
		{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String sql = "SELECT * FROM students ORDER BY id LIMIT ? OFFSET ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, total);
            stmt.setInt(2, start - 1);
            rs = stmt.executeQuery();

            while (rs.next()) 
			{
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
				                user.setRollNo(rs.getString("roll_no"));
                user.setFees(rs.getDouble("fees"));
                user.setStatus(rs.getString("status"));
                users.add(user);
            }
        } 
		catch (SQLException | ClassNotFoundException e) 
		{
            e.printStackTrace();
        } 
		finally 
		{
            try 
			{
                if (rs != null) 
				{
                    rs.close();
                }
                if (stmt != null) 
				{
                    stmt.close();
                }
                if (conn != null) 
				{
                    conn.close();
                }
            } 
			
			catch (SQLException e) 
			{
                e.printStackTrace();
            }
        }
        return users;
    }

    public static int getTotalRecords() 
	{
        int total = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try 
		{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String sql = "SELECT COUNT(*) FROM students";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) 
			{
                total = rs.getInt(1);
            }
        } 
		catch (SQLException | ClassNotFoundException e) 
		{
            e.printStackTrace();
        } 
		finally 
		{
            try 
			{
                if (rs != null) 
				{
                    rs.close();
                }
                if (stmt != null) 
				{
                    stmt.close();
                }
                if (conn != null)
				{
                    conn.close();
                }
            } 
			catch (SQLException e) 
			{
                e.printStackTrace();
            }
        }
        return total;
    }
}

               
