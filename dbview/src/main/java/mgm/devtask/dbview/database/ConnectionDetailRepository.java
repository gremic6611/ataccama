package mgm.devtask.dbview.database;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mgm.devtask.dbview.api.ConnectionDetail;

//Spring auto-implemented
public interface ConnectionDetailRepository extends CrudRepository<ConnectionDetail, Long> {
	
	List<ConnectionDetail> findAll();
	
	ConnectionDetail findByName(String name);
	
}