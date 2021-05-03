package model; 
import java.sql.*; 
public class fund 
{ //A common method to connect to the DB
private Connection connect() 
 { 
 Connection con = null;
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fund", "root", "19189390"); 
 } 
 catch (Exception e) 
 {e.printStackTrace();} 
 return con; 
 } 
public String insertfund(String name, String amount, String pnumber, String nic, String city,String desc) 
 { 
 String output = ""; 

if( name.isEmpty() ||  amount.isEmpty() ||pnumber.isEmpty() ||nic.isEmpty() ||city.isEmpty() || desc.isEmpty() ){

output ="Please Fill all text boxes";

}
/*else if  (! name.matches("[0-9]+")) {
	 
	 output=" invalid code format!  ";
 }*/
else if ( amount.length() < 2) {
	 
	 output=" amount should be greater than 99 ";
}

else if (city.length()<2) {
	 
	 output="city should greater than 2 letters";
 }
else if  (!pnumber.matches("[0-9]+")) {
	 
	 output=" pleace enter only numbers for the phone number";
 }
else{

 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into fund (`fundid`,`name`,`amount`,`pnumber`,`nic`,`city`,`desc`)"
 + " values (?, ?, ?, ?, ?,?,?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, name); 
 preparedStmt.setString(3, amount); 
 preparedStmt.setString(4, pnumber); 
 preparedStmt.setString(5,nic); 
 preparedStmt.setString(6, city); 
 preparedStmt.setString(7, desc); 
// execute the statement3
 preparedStmt.execute(); 
 con.close(); 
 output = "Inserted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while inserting the item."; 
 System.err.println(e.getMessage()); 
 } 

 }
 return output; 
 } 
public String readfund() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>name</th><th>amount</th>" +
 "<th>pnumber</th>" + 
 "<th>nic</th>" +
 "<th>city</th>" +
 "<th>Disc</th>" +
 "<th>Update</th><th>Remove</th></tr>"; 
 
 String query = "select * from fund"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String fundid = Integer.toString(rs.getInt("fundid")); 
 String name = rs.getString("name"); 
 String amount = rs.getString("amount"); 
 String pnumber = rs.getString("pnumber");
 String nic = rs.getString("nic"); 
 String city = rs.getString("city"); 
 String desc = rs.getString("desc"); 
 
 // Add into the html table
 output += "<tr><td>" + name + "</td>"; 
 output += "<td>" + amount + "</td>"; 
 output += "<td>" + pnumber + "</td>"; 
 output += "<td>" + nic + "</td>"; 
 output += "<td>" + city + "</td>";
 output += "<td>" + desc + "</td>";
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
 + "<td><form method='post' action='fund.jsp'>"
 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
 + "<input name='fundid' type='hidden' value='" + fundid
 + "'>" + "</form></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while reading the fund."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String updatefund(String fundid,String name, String amount, String pnumber, String nic, String city,String desc)
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for updating."; } 
 // create a prepared statement
 String query = "UPDATE fund SET name=?,amount=?,pnumber=?,nic=?,city=?, desc=? WHERE fundid=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setString(1, name); 
 preparedStmt.setString(2, amount); 
 preparedStmt.setString(3, pnumber); 
 preparedStmt.setString(4, nic); 
 preparedStmt.setString(5, city);
 preparedStmt.setString(6, desc);
 preparedStmt.setInt(7, Integer.parseInt(fundid)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Updated successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while updating the item."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String deletefund(String fundid) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for deleting."; } 
 // create a prepared statement
 String query = "delete from fund where fundid=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(fundid)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Deleted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while deleting the item."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
} 

//udara
