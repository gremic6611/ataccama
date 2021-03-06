package mgm.devtask.dbview.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mgm.devtask.dbview.api.ConnectionDetail;
import mgm.devtask.dbview.service.ConnectionService;

/**
 * Controller to provide basic RESTfull resource operations for ConnectionDetail entity
 * using mainly ConnectionSevice 
 * 
 */
@RestController
@RequestMapping("/dbview/connection")
public class ConnectionDetailController {

	    @Autowired
	    private ConnectionService connectionService;
	
	    Logger log = LoggerFactory.getLogger(ConnectionDetailController.class);
	    
	    @RequestMapping(value = "/list")
	    public ResponseEntity<Object> listConnections() {
	       log.info("List connections called");
	       return new ResponseEntity<>(connectionService.listConnectionDetails(), HttpStatus.OK);
	    }
	    
	    @RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
	    public ResponseEntity<Object> getConnection(@PathVariable("name") String name) {
	       log.info("Get connection called with param: "+name);
	       ConnectionDetail connection = connectionService.getConnectionDetail(name);
	       return new ResponseEntity<>(connection, HttpStatus.OK);
	    }

	    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	    public ResponseEntity<Object> deleteConnection(@RequestBody ConnectionDetail connection) {
	       log.info("Delete connection called with: "+connection);
	       connectionService.deleteConnectionDetail(connection);
	       return new ResponseEntity<>("Deleted", HttpStatus.OK);
	    }
	    
	    @RequestMapping(value = "/update/{name}", method = RequestMethod.PUT)
	    public ResponseEntity<Object> updateConnection(@PathVariable("name") String name,
	    		@RequestBody ConnectionDetail connection) {
	       log.info("Update connection called with: "+connection);
	       connectionService.updateConnectionDetail(name, connection);
	       return new ResponseEntity<>("Updated", HttpStatus.OK);
	    }
	    
	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public ResponseEntity<Object> saveConnection(@RequestBody ConnectionDetail connection) {
	       connectionService.saveConnectionDetail(connection);
	       log.info("Save connection called with: "+connection);
	       return new ResponseEntity<>("Saved", HttpStatus.CREATED);
	    }
	    
	    @RequestMapping("/")
	    public @ResponseBody String connection() {
	        return "Dbview Application connection detail RESTfull API - use ./save ./update ./list ./delete endpoints";
	    }
	    
}
