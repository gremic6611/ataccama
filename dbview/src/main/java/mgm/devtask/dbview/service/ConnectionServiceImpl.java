package mgm.devtask.dbview.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import mgm.devtask.dbview.api.ConnectionDetail;

@Service
public class ConnectionServiceImpl implements ConnectionService {

	 private static Map<String, ConnectionDetail> connections = new HashMap<>();
	   static {
		  ConnectionDetail demoConnection = new ConnectionDetail("demoConn", "localhost", 8080, "demoDb", "root", "root");
		  connections.put(demoConnection.getName(), demoConnection);
	   }
	
	@Override
	public ConnectionDetail getConnectionDetail(String name) {
		return connections.get(name);
	}

	@Override
	public void saveConnectionDetail(ConnectionDetail connection) {
		connections.put(connection.getName(), connection);
	}

	@Override
	public void updateConnectionDetail(String name, ConnectionDetail connection) {
		connection.setName(name);
		connections.put(name, connection);
	}

	@Override
	public Collection<ConnectionDetail> listConnectionDetails() {
		return connections.values();
	}

	@Override
	public void deleteConnectionDetail(ConnectionDetail connection) {
		connections.remove(connection.getName());
	}

}
