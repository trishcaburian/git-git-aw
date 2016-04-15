import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Date;
import java.sql.Time;

public class PostgreSQLClient {

	public PostgreSQLClient(){
		
		
	}

	public int getAllLocations() throws Exception {
		String sql = "SELECT province FROM location ORDER BY locid DESC";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			results = statement.executeQuery();
			List<String> loc = new ArrayList<String>();
			
			while (results.next()) {
				loc.add(results.getString("province"));
			}
			
			return loc.size();

		} finally {
			if (results != null) {
				results.close();
			}
			
			if (statement != null) {
				statement.close();
			}
			
			if (connection != null) {
				connection.close();
			}
		}
	}

	public int addLocation() throws Exception{

		String sql = "INSERT INTO location (region, province) VALUES ('ncr', 'caloocan'),"+
					 "('ncr', 'makati'),"+
					 "('ncr', 'malabon'),"+
					 "('ncr', 'mandaluyong'),"+
					 "('ncr', 'manila'),"+
					 "('ncr', 'marikina'),"+
					 "('ncr', 'muntinlupa'),"+
					 "('ncr', 'navotas'),"+
					 "('ncr', 'paranaque'),"+
					 "('ncr', 'pasay'),"+
					 "('ncr', 'pasig'),"+
					 "('ncr', 'pateros'),"+
					 "('ncr', 'quezon'),"+
					 "('ncr', 'san juan'),"+
					 "('ncr', 'taguig'),"+
					 "('ncr', 'valenzuela');";

		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			

			int rows = statement.executeUpdate();
			connection.commit();
			
			return rows;
		} catch (SQLException e) {
			SQLException next = e.getNextException();
			
			if (next != null) {
				throw next;
			}
			
			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}
			
			if (connection != null) {
				connection.close();
			}
		}

	}

	public void createTable() throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS users (uid serial primary key, lname text, fname text, province text," +
                     		"city text, brgy text, street text, mobile text, uname text, password text);" +
                     "CREATE TABLE IF NOT EXISTS location (locid serial primary key, region text, province text);" +
                     "CREATE TABLE IF NOT EXISTS user_loc (uid integer, locid integer, PRIMARY KEY(uid, locid));";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } finally {         
            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    //static funct
	private Connection getConnection() throws Exception {

        Map<String, String> env = System.getenv();

        if (env.containsKey("VCAP_SERVICES")) {
            // we are running on cloud foundry, let's grab the service details from vcap_services
            JSONParser parser = new JSONParser();
            JSONObject vcap = (JSONObject) parser.parse(env.get("VCAP_SERVICES"));
            JSONObject service = null;

            // We don't know exactly what the service is called, but it will contain "postgresql"
            for (Object key : vcap.keySet()) {
                String keyStr = (String) key;
                if (keyStr.toLowerCase().contains("postgresql")) {
                    service = (JSONObject) ((JSONArray) vcap.get(keyStr)).get(0);
                    break;
                }
            }

            if (service != null) {
                JSONObject creds = (JSONObject) service.get("credentials");
                String name = (String) creds.get("name");
                String host = (String) creds.get("host");
                Long port = (Long) creds.get("port");
                String user = (String) creds.get("user");
                String password = (String) creds.get("password");

                String url = "jdbc:postgresql://" + host + ":" + port + "/" + name;

                return DriverManager.getConnection(url, user, password);
            }
        }

        throw new Exception("No PostgreSQL service URL found. Make sure you have bound the correct services to your app.");
	}
	
	public void createEventTable() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        String createEventTB = "CREATE TABLE IF NOT EXISTS event "
                + "(eID serial primary key NOT NULL, "
                + "eName varchar(45) NOT NULL,"
                + "eDescription varchar(45) NOT NULL,"
                + "eDate varchar(45) NOT NULL, "
                + "startTime varchar(45) NOT NULL, "
                + "endTime varchar(45) NOT NULL, "
				+ "eventLocation varchar(45) NOT NULL"
                + ");";
				
		/*createquery = "CREATE TABLE IF NOT EXISTS location "
                + "(locID serial NOT NULL PRIMARY KEY, "
                + "region varchar(45) NOT NULL,"
                + "Province varchar(45) NOT NULL,"
                + ");";*/
				
		String createEventLocTB = "CREATE TABLE IF NOT EXISTS event_loc "
                + "(eID integer NOT NULL, "
                + "locID integer NOT NULL,"
                + "PRIMARY KEY(eID, locID));";
	

        try {
            connection = getConnection();
            statement = connection.prepareStatement(createEventTB);
            statement.executeUpdate();
			statement = connection.prepareStatement(createEventLocTB);
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
	
	public void addEvent(String eName, String eDescription, String eDate, String startTime, String endTime, String eventLocation) throws Exception{
		
		
		String sql = "INSERT INTO event (eName, eDescription, eDate, startTime, endTime, eventLocation) VALUES (?, ?, ?, ?, ?, ?);";

		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			statement.setString(1,eName);
			statement.setString(2,eDescription);
			statement.setString(3,eDate);
			statement.setString(4,startTime);
			statement.setString(5,endTime);
			statement.setString(6,eventLocation);
			statement.executeUpdate();
			connection.commit();
			
		} catch (SQLException e) {
			SQLException next = e.getNextException();
			
			if (next != null) {
				throw next;
			}
			
			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}
			
			if (connection != null) {
				connection.close();
			}
		}

	}
	
	public List<EventInfo> getAllEvents() throws Exception {
		String sql = "SELECT * FROM event";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			results = statement.executeQuery();
			List<EventInfo> events = new ArrayList<>();
			
			
			while (results.next()) {
				EventInfo ei = new EventInfo();
				events.add(ei);
			}
			
			return events;

		} finally {
			if (results != null) {
				results.close();
			}
			
			if (statement != null) {
				statement.close();
			}
			
			if (connection != null) {
				connection.close();
			}
		}
	}
	
/*	public String getSpecificLocationEvent(int ) throws Exception {
		String sql = "SELECT event FROM eName, eDescription, eDate, startTime, endTime";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			results = statement.executeQuery();
			List<String> events = new ArrayList<String>();
			
			while (results.next()) {
				events.add(results.getString("province"));
			}
			
			return events.size();

		} finally {
			if (results != null) {
				results.close();
			}
			
			if (statement != null) {
				statement.close();
			}
			
			if (connection != null) {
				connection.close();
			}
		}
	}*/

}