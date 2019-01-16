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
 * Controller to provide access to database agregation functions and so on 
 * 
 */
@RestController
@RequestMapping("/dbview/statistics")
public class StatisticsController {

 	@Autowired
    private DataBrowseService dataBrowseService;
 	
 	Logger log = LoggerFactory.getLogger(StatisticsController.class);
	
	@RequestMapping(value = "/connection/{connName}/schema/{schemaName}/table/{tableName}/column/{columnName}/funct/{funct}", method = RequestMethod.GET)
    public ResponseEntity<Object> listColumns(@PathVariable("connName") String connName, 
    		@PathVariable("schemaName") String schemaName, 
    		@PathVariable("tableName") String tableName,
    		@PathVariable("columnName") String columnName,
    		@PathVariable("funct") String funct) {
    	log.info(String.format("Agregation function %s called for connection %s, schema %s, table %s, column %s",
    			funct, connName, schemaName, tableName, columnName));
    	List<Map<String, Object>> result = dataBrowseService.callAggregationFunction(connName, schemaName, tableName, columnName, funct);
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	
	
	
	
}
