package mgm.devtask.dbview.service;

import java.util.Collection;
import java.util.List;

import mgm.devtask.dbview.api.ConnectionDetail;

/**
 * Connection service used for manipulation and basic db opperations with connection details
 */
public interface ConnectionService {
	
	public ConnectionDetail getConnectionDetail(String name);
	
	public void saveConnectionDetail(ConnectionDetail connection); 
	
	public void updateConnectionDetail(String name, ConnectionDetail connection);
	
	public Collection<ConnectionDetail> listConnectionDetails();
	
	public void deleteConnectionDetail(ConnectionDetail connection);
	
}
