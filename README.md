# ataccama
Springboot MVC application for database connection detail storage and retrival using store details

Example usage 
GET  localhost:8080/dbview/connection/list
POST  localhost:8080/dbview/connection/save  {"name":"demo","hostname":"localhost","port":83306,"databaseName":"dbview","username":"root", "password": "xxx"}

data browsing

GET http://localhost:8080/dbview/browse/connection/demo/schema/list  
GET http://localhost:8080/dbview/browse/connection/demo/schema/dbview/table/connection_detail/column/list  
GET http://localhost:8080/dbview/browse/connection/demo/schema/dbview/table/connection_detail/data/list

statistics

GET http://localhost:8080/dbview/statistics/connection/demo/schema/dbview/table/connection_detail/column/port/funct/MAX  

