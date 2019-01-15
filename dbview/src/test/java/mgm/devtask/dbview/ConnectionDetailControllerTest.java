package mgm.devtask.dbview;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mgm.devtask.dbview.controller.ConnectionDetailController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConnectionDetailControllerTest {
	
	    @Autowired
	    private ConnectionDetailController controller;

	    @Test
	    public void testControllerNotNull() throws Exception {
	        Assert.assertNotNull(controller);
	    }
}
