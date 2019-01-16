package mgm.devtask.dbview.service;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import mgm.devtask.dbview.api.ConnectionDetail;

@Service
public class CustomJdbcTemplateServiceImpl implements CustomJdbcTemplateService {
	
	private Map<String, JdbcTemplate> cache = new HashMap<>();
	
	Logger log = LoggerFactory.getLogger(CustomJdbcTemplateServiceImpl.class);
	
	@Override
	public JdbcTemplate getJdbcTempleForConnectionDetail(ConnectionDetail detail, String schemaName) {
		JdbcTemplate existing = cache.get(detail.getName());
		if (existing != null) {
			log.info("Reusing existing template for connection "+detail.getName()); 
			return existing;
		}	
		return createNewTemplate(detail, schemaName);
	}
	
	
	private JdbcTemplate createNewTemplate(ConnectionDetail detail, String schemaName) {
		log.info("Creating new connection template for connection "+detail);
		
		// set in stone for now -could be added to connection detail-but sql dialect could change
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		String jdbcUrl = String.format("jdbc:mysql://%s:%d/%s", 
				detail.getHostname(), detail.getPort(),	schemaName);

		DataSource dataSource = DataSourceBuilder.create().driverClassName(driverClassName).url(jdbcUrl).
				username(detail.getUsername()).password(detail.getPassword()).
				build();

		return new JdbcTemplate(dataSource);
	}
	
	
}
