package mgm.devtask.dbview.service;

import java.util.List;
import java.util.Map;

/**
 * Service for supporting list/data preview operation over saved connection details
 * using newly created connection
 *   
 */
public interface DataBrowseService {

	public List<String> listSchemas(String connectionName);
	
	public List<String> listTables(String connectionName, String schemaName);
	
	public List<Map<String, Object>> listColumns(String connectionName, String schemaName, String tableName);
	
	public List<Map<String, Object>> previewData(String connectionName, String schemaName, String tableName);
}
