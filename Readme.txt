DB Issues:
	I used Embedded H2 DB.
	I created 1 entry table for all rss feed entries.
	Spring JPA auto creates tables under "jdbc:h2:mem:testdb"

Application Issues:
	I used Spring Schedular to call method per 2 minutes. 
	Rss Feed Link is written in application.properties file. It can be updated from there.
	Inserting all entries in Embedded DB
API Documantation
	http://localhost:8080/swagger-ui.html#/
						-->Rss Feed Controller
	

