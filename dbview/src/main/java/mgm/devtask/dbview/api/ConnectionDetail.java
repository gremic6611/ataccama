package mgm.devtask.dbview.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Connection details main bean, with changeable fields
 * @author Michal Grega
 *
 */
public class ConnectionDetail {

	private String name;
	
	private String hostname;
	
	private int port;
	
	private String databaseName;
	
	private String username;
	
	@JsonIgnore
	private String password;
	
	public ConnectionDetail() {
		//needed by framework
	}
	
	public ConnectionDetail(String name, String hostname, int port, String databaseName, String username, String password) {
		this.name=name;
		this.hostname=hostname;
		this.port=port;
		this.databaseName=databaseName;
		this.username=username;
		this.password=password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
