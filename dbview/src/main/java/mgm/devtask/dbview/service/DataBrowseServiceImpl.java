package mgm.devtask.dbview.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import mgm.devtask.dbview.api.ConnectionDetail;
import mgm.devtask.dbview.api.DataBrowseServiceException;
import mgm.devtask.dbview.enums.AggregationFunctionEnum;

@Service
public class DataBrowseServiceImpl implements DataBrowseService {
	
	@Autowired
    private ConnectionService connectionService;
	
	@Autowired
    private CustomJdbcTemplateService customJdbcService;
	
	
	private JdbcTemplate getJdbcTemplate(String connectionName) {
		ConnectionDetail detail = connectionService.getConnectionDetail(connectionName);
		JdbcTemplate template = customJdbcService.getJdbcTempleForConnectionDetail(detail, detail.getDatabaseName());
		return template;
	}
	
	//in Mysql there is no difference between DB and schema - but I will support it regardless :)
	private JdbcTemplate getJdbcTemplate(String connectionName, String schemaName) {
		ConnectionDetail detail = connectionService.getConnectionDetail(connectionName);
		JdbcTemplate template = customJdbcService.getJdbcTempleForConnectionDetail(detail, schemaName);
		return template;
	}
	
	
	@Override
	public List<String> listSchemas(String connectionName) {
		JdbcTemplate template = getJdbcTemplate(connectionName);
		List<Map<String, Object>> rows = template.queryForList("show schemas;");
		return flatenToList(rows);
	}
	
	@Override
	public List<String> listTables(String connectionName, String schemaName) {
		JdbcTemplate template = getJdbcTemplate(connectionName, schemaName);
		List<Map<String, Object>> rows = template.queryForList("show tables;");
		return flatenToList(rows);
	}


	@Override
	public List<Map<String, Object>> listColumns(String connectionName, String schemaName, String tableName) {
		JdbcTemplate template = getJdbcTemplate(connectionName, schemaName);
		try {
			List<Map<String, Object>> rows = template.queryForList("describe " + tableName + ";");
			return rows;
		} catch (Exception e) { //I dont want gramer exception messages in controller
			throw new DataBrowseServiceException("Problem with retrieval of data for table "+ tableName, e);
		}
	}


	@Override
	public List<Map<String, Object>> previewData(String connectionName, String schemaName, String tableName) {
		JdbcTemplate template = getJdbcTemplate(connectionName, schemaName);
		try {
			// this is mysql specific - rownum didnt work
			List<Map<String, Object>> rows = template.queryForList("select * from " + tableName + " limit 100;");
			return rows;
		} catch (Exception e) {
			throw new DataBrowseServiceException("Problem with retrieval of data for table " + tableName, e);
		}
	}
	
	/**
	 * Helper method to return List<String> when needed
	 */
	private List<String> flatenToList(List<Map<String, Object>> rows) {
		List<String> result = new ArrayList<String>();
		if (rows==null || rows.isEmpty())
			return result;
		
		Set<String> keys = rows.get(0).keySet();
		
		for (Map<String, Object> row:rows) {
			for (String key:keys) {
				result.add(row.get(key).toString());
			}
		}
		
		return result;
	}

	@Override
	public List<Map<String, Object>> callAggregationFunction(String connectionName, String schemaName, String tableName,
			String columnName, String agregationFunction) {
		
		//to validate - will throw exception - should be in validator
		AggregationFunctionEnum fnct = AggregationFunctionEnum.valueOf(agregationFunction);
		
		JdbcTemplate template = getJdbcTemplate(connectionName, schemaName);
		try {
			List<Map<String, Object>> rows = template.queryForList("select " + agregationFunction+ "("+ columnName+ ") from "+tableName + ";");
			return rows;
		} catch (Exception e) { //I dont want gramer exception messages in controller
			throw new DataBrowseServiceException("Problem with retrieval of data for table "+ tableName, e);
		}
	}
}
