package com.freeorg.java7.tryWithResources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Before java 7, we had to use finally blocks to cleanup the resources. 
 * Finally blocks were not mandatory, but resource clean up was to prevent the system from being corrupt. 
 * With java 7, there is no need of explicit resource cleanup in finally block. 
 * Its done automatically. Automatic resource cleanup is done when initializing resource in try-with-resources block (try(...) {...}).
 * Cleanup happens because of new interface AutoCloseable. 
 * See : FileInputStream extends InputStream, InputStream implements Closeable, Closeable extends AutoCloseable{close();} 
 * Its close method is invoked by JVM as soon as try block finishes. 
 * You are not supposed to call close() method in your code. This should be called automatically by JVM. 
 * Calling it manually may cause unexpected results.
 */

// Example 1
public class ResourceManagementInJava7 {

	// After Java7
	public void testTryWithResourcesStatement() throws FileNotFoundException, IOException{
	    try (FileInputStream in = new FileInputStream("java7Input.txt")) {
	        System.out.println(in.read());
	    }
	}
	
	// Before Java7
	public void noramlTryStatement() throws FileNotFoundException, IOException{
	     FileInputStream in = null;
	    try {
	        in = new FileInputStream("java7.txt");
	        System.out.println(in.read());
	    } finally {
	        if (in != null) {
	            in.close();
	        }
	    }
	}
}

// Example 2
class MultipleAutoClosealeObjects{

	// After Java7
	public List<User> getUserAfter(int userId) {
	    try (Connection con = DriverManager.getConnection("");
	         PreparedStatement ps = createPreparedStatement(con, userId); 
	         ResultSet rs = ps.executeQuery()) {
	    	
	         // process the resultset here, all resources will be cleaned up	    	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	private PreparedStatement createPreparedStatement(Connection con, int userId) throws SQLException {
	    String sql = "SELECT id, username FROM users WHERE id = ?";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setInt(1, userId);
	    return ps;
	}
	
	// Before Java7
	public List<User> getUserBefore(int userId) {
	    String sql = "SELECT id, name FROM users WHERE id = ?";
	    List<User> users = new ArrayList<User>();
	    try {
	        Connection con = DriverManager.getConnection("");
	        PreparedStatement ps = con.prepareStatement(sql); 
	        ps.setInt(1, userId);
	        ResultSet rs = ps.executeQuery();
	        while(rs.next()) {
	            users.add(new User(rs.getInt("id"), rs.getString("name")));
	        }
	        rs.close();
	        ps.close();
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return users;
	}
}

class User{

	public User(int int1, String string) {
	}
	
}
