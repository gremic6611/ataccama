package mgm.devtask.dbview.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mgm.devtask.dbview.service.DataBrowseService;

/**
 * Controller to provide basic RESTfull resource operations for browsing data using connection detail to
 * connect to selected data source
 * Supported operations: list schemas, list tables, list columns, preview data
 * 
 */
@RestController
@RequestMapping("/dbview/browse")
public class DataBrowseController {

	 	@Autowired
	    private DataBrowseService dataBrowseService;
	 	
	 	Logger log = LoggerFactory.getLogger(DataBrowseController.class);
	
	    @RequestMapping(value = "/connection/{connName}/schema/list", method = RequestMethod.GET)
	    public ResponseEntity<Object> listSchemas(@PathVariable("connName") String connName) {
	    	log.info(String.format("List of schemas called for connection %s", connName));
	    	List<String> result = dataBrowseService.listSchemas(connName);
	    	return new ResponseEntity<>(result, HttpStatus.OK);
	    }
	
	    @RequestMapping(value = "/connection/{connName}/schema/{schemaName}/table/list", method = RequestMethod.GET)
	    public ResponseEntity<Object> listTables(@PathVariable("connName") String connName, 
	    		@PathVariable("schemaName") String schemaName) {
	    	log.info(String.format("List of tables called for connection %s, schema %s", connName, schemaName));
	    	List<String> result = dataBrowseService.listTables(connName, schemaName);
	    	return new ResponseEntity<>(result, HttpStatus.OK);
	    }
	    
	    @RequestMapping(value = "/connection/{connName}/schema/{schemaName}/table/{tableName}/column/list", method = RequestMethod.GET)
	    public ResponseEntity<Object> listColumns(@PathVariable("connName") String connName, 
	    		@PathVariable("schemaName") String schemaName, 
	    		@PathVariable("tableName") String tableName) {
	    	log.info(String.format("List of columns called for connection %s, schema %s, table %s", connName, schemaName, tableName));
	    	List<Map<String, Object>> result = dataBrowseService.listColumns(connName, schemaName, tableName);
	    	return new ResponseEntity<>(result, HttpStatus.OK);
	    }
	    
	    @RequestMapping(value = "/connection/{connName}/schema/{schemaName}/table/{tableName}/data/list", method = RequestMethod.GET)
	    public ResponseEntity<Object> dataPreview(@PathVariable("connName") String connName, 
	    		@PathVariable("schemaName") String schemaName, 
	    		@PathVariable("tableName") String tableName) {
	    	log.info(String.format("Preview of data called for connection %s, schema %s, table %s", connName, schemaName, tableName));
	    	List<Map<String, Object>> result = dataBrowseService.previewData(connName, schemaName, tableName);
	    	return new ResponseEntity<>(result, HttpStatus.OK);
	    }
	
}
