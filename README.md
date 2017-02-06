# Tasker #
Java + Spring Web Application for assigning tasks to employees.

It was developed during *Java + Oracle for Enterprise Solutions* course I took within a company. 

There's a presentation for anyone willing to see it in work (it's RU but there are pics and demo-video): https://www.j.mp/nc_tasker

It is *not* production-ready per se, and I see it's use in being a template for a full-stack MVC Java Spring Web App. Also as some sort of proof or accomplishment for myself.

## Build ##
`$ mvn clean package`
 
## Deploy ##
Tested on **Glassfish 3.1.2**  
For a deploy just `mv` your *.war* into `$GLASSFISH_HOME/glassfish/domains/<domain_name>/autodeploy/`
