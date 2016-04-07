package hnust.sendbluetooth;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DemoDB {

	String className="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://121.201.8.196:3306/mx";
	String name="root";
	String password="tsunh";
	
   public  void send(String phone,String bluetooth){
	   Connection con = null;
	   Statement stmt = null;
	   try {
		   
		   Class.forName(className).newInstance();
		    con=(Connection) DriverManager.getConnection(url, name, password);
		    stmt=(Statement) con.createStatement();
		   String sql="insert into user(phone,bluetooth)values('"+phone+"','"+bluetooth+"')";
		   stmt.execute(sql);
		   con.close();
			stmt.close();
		
	} catch (Exception e) {
		// TODO: handle exception
	}finally {
		
	}
	
   }
}
