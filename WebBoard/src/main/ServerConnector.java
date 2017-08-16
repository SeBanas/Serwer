package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Class responsible for communication between server and DataBase
 * having functions sending and getting data by specified
 * pattern
 *
 */
public class ServerConnector implements DataBaseConnector{
	Connection connection;
	
	/**
	 * Gives reference to a DataBase and checks if table is
	 * created if not, creates it.
	 * @param aConnection Connection do DataBase
	 */
	public ServerConnector(Connection aConnection){
			connection=aConnection;
			PreparedStatement stmt = null;
			try {
				stmt = connection
				          .prepareStatement("select * from message");
				stmt.execute();
				
			} catch (SQLException e) {
				ServerInfo.updateError("Couldnt connect to DataBase table: Created one");
				try {
					stmt = connection
					          .prepareStatement("create table message(person VARCHAR(50), message VARCHAR(200), date DATE, );");
					stmt.execute();
				} catch (SQLException e1) {
					ServerInfo.updateError("Couldnt create DataBase table");
				}
			}
	}
	
	@Override
	public String sendData(String aPerson, String aMessage){
		 PreparedStatement stmt = null;
		 if(aPerson==null || aMessage==null) return "String missing";
		 if(aPerson.equals("")||aPerson.equals("*")||aMessage.equals("")) return "Bad input entered";
		 else try {
			 	aPerson=aPerson.replace("+"," ");
			 	aMessage=aMessage.replace("+"," ");
	            stmt = connection
	                  .prepareStatement("insert into message (person,message,date) "
	                    + "values(?,?,?)");
	            stmt.setString(1,aPerson);
	            stmt.setString(2,aMessage);
	            Calendar calendar = GregorianCalendar.getInstance();
	            Date date= new Date(calendar.getTime().getTime());
	            stmt.setDate(3,date);
	            stmt.executeUpdate();
	            return "Sending data completed";
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return "Sending data intterupted";
	        } 
		 finally {
	            if (stmt != null) {
	                try {
	                    stmt.close();
	                } catch (SQLException e) {
	                	ServerInfo.updateError("Couldnt connect to DataBase");
	                }
	            }
	        }
	}
	
	@Override
	public List<String> receiveDataByName(String name){
		LinkedList<String> list=new LinkedList<String>();
		PreparedStatement stmt = null;
		
		if(name==null) return null;
		 else try {
			if(name.equals("*")){
				stmt = connection
	                    .prepareStatement("select * from message");
			}
			else {
				stmt = connection
						.prepareStatement("select person,message,date from message where person like ?");
            	stmt.setString(1, name);
			}
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            	list.addLast("<strong>Author:</strong>"+rs.getString(1)+" <strong>Message:</strong>"+rs.getString(2)+" <strong>sended</strong> "+rs.getDate(3));
            return list;
        } catch (SQLException e) {
        	ServerInfo.updateError("Couldnt connect to DataBase");
            return null;
        } finally {
            
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
		
	}
	
	@Override
	 public List<String> receiveDataByDate(String dates1,String dates2){
		LinkedList<String> list=new LinkedList<String>();
		PreparedStatement  stmt=null;
		
		if(dates1==null || dates2==null) return null;
		 else try {
			int[] spliter=splitDate(dates1);
			if (spliter==null) {ServerInfo.updateError("Bad date format inserted");
								return null;}
        	Date date =  new Date(spliter[0]-1900, spliter[1]-1,spliter[2]);
        	spliter=splitDate(dates2);
    		Date date2 = new Date(spliter[0]-1900, spliter[1]-1,spliter[2]);
    		System.out.println(date);
    		System.out.println(date2);
    		stmt = connection
                      .prepareStatement("select * from message");
              ResultSet rs = stmt.executeQuery();
              while(rs.next())
            	  if(checkDate(date,date2,rs.getDate(3)))
            		  	list.addLast("<strong>Author</strong>:"+rs.getString(1)+" <strong>Message:</strong>"+rs.getString(2)+" sended "+rs.getDate(3));       
              return list;
        } catch (SQLException e) {
        	ServerInfo.updateError("Bad date format inserted");
            return null;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {}
            }
        }
		
	}
	
	public String deleteByName(String name){
		PreparedStatement  stmt=null;
		if(name.equals("")) return "Bad name format inserted";
		try{
		PreparedStatement stmt2 = connection
	               .prepareStatement("select * from message where person like ?");
		stmt2.setString(1, name);
		ResultSet rs = stmt2.executeQuery();
			if(!rs.next()) return "No user found with that name";
		stmt = connection
                .prepareStatement("delete from message where person like ?");
		stmt.setString(1, name);
		stmt.executeUpdate();
		return "Deleting completed";
		}catch (SQLException e) {
        	ServerInfo.updateError("Cant connect to server");
            return "Deleting incompleted";
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {};
           }
        }
	}
	
	private int[] splitDate(String date){
		String[] split=date.split("-");
		if(split.length!=3) return null;
		int[] tab=new int[3];
		for(int i=0;i<3;i++)
			tab[i]=Integer.parseInt(split[i]);
		checkIfCorrect(tab);
		return tab;
	}
	
	private boolean checkIfCorrect(int[] tab){
		
		return true; 
	}
	
	private boolean checkDate(Date date,Date date2,Date date3){
		if(date.compareTo(date3)<=0 && date2.compareTo(date3)>=0)
			return true;
		else return false;
	}
	
}
