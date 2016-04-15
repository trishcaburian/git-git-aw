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


public class PostgreSQLClient {
/*
	String name;
	String host;
	Long port;
	String user;
	String password;

*/
	public PostgreSQLClient(){
		/*
		this.name = "de5c229ee8634450ba0167da5d9a7d7cc";
		this.host = "198.11.228.49";
		this.port = 5433L;
		this.user = "u4155bb930ceb4b3991ae17cbc795794a";
		this.password = "pda76652263ec48a8a6f8cd3875149c10";
		*/
	}

	public int getAllLocations() throws Exception {
		String sql = "SELECT province FROM location ORDER BY id DESC";
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
		/*
		String sql = "INSERT INTO location (region, province) VALUES ('ncr', 'caloocan');"+
					 "INSERT INTO location (region, province) VALUES ('ncr', 'makati');"+
					 "INSERT INTO location (region, province) VALUES ('ncr', 'malabon');"+
					 "INSERT INTO location (region, province) VALUES ('ncr', 'mandaluyong');"+
					 "INSERT INTO location (region, province) VALUES ('ncr', 'manila');"+
					 "INSERT INTO location (region, province) VALUES ('ncr', 'marikina');"+
					 "INSERT INTO location (region, province) VALUES ('ncr', 'muntinlupa');"+
					 "INSERT INTO location (region, province) VALUES ('ncr', 'navotas');"+
					 "INSERT INTO location (region, province) VALUES ('ncr', 'paranaque');"+
					 "INSERT INTO location (region, province) VALUES ('ncr', 'pasay');"+
					 "INSERT INTO location (region, province) VALUES ('ncr', 'pasig');"+
					 "INSERT INTO location (region, province) VALUES ('ncr', 'pateros');"+
					 "INSERT INTO location (region, province) VALUES ('ncr', 'quezon');"+
					 "INSERT INTO location (region, province) VALUES ('ncr', 'san juan');"+
					 "INSERT INTO location (region, province) VALUES ('ncr', 'taguig');"+
					 "INSERT INTO location (region, province) VALUES ('ncr', 'valenzuela');";
		*/
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
                     "CREATE TABLE IF NOT EXISTS user_loc (uid integer, locid integer);";

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


	private static Connection getConnection() throws Exception {

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

}