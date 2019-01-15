package mgm.devtask.dbview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mgm.devtask.dbview.api.ConnectionDetail;
import mgm.devtask.dbview.api.ConnectionServiceDetailMissingException;
import mgm.devtask.dbview.database.ConnectionDetailRepository;

@Service
public class ConnectionServiceImpl implements ConnectionService {

	@Autowired
	private ConnectionDetailRepository repo;
	
	@Override
	public ConnectionDetail getConnectionDetail(String name) {
		ConnectionDetail result = repo.findByName(name);
		if (result == null)
			throw new ConnectionServiceDetailMissingException(String.format("Connection Detail for name %s not found.", name));
		return result;
	}

	@Override
	public void saveConnectionDetail(ConnectionDetail connection) {
		repo.save(connection);
	}

	@Override
	public void updateConnectionDetail(String name, ConnectionDetail connection) {
		connection.setName(name);
		repo.save(connection);
	}

	@Override
	public List<ConnectionDetail> listConnectionDetails() {
		return repo.findAll();
	}

	@Override
	public void deleteConnectionDetail(ConnectionDetail connection) {
		repo.delete(connection);
	}

}
