package mgm.devtask.dbview.service;

import org.springframework.jdbc.core.JdbcTemplate;

import mgm.devtask.dbview.api.ConnectionDetail;

/**
 * Service to Retrieves jdbc connection for connection details if possible
 * should use simple cache-ing to save resources  
 *
 */
public interface CustomJdbcTemplateService {

	public JdbcTemplate getJdbcTempleForConnectionDetail(ConnectionDetail detail, String schemaName);
	
}
