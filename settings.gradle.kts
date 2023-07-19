rootProject.name = "spring-micro-demo"


include(":eureka-server")
include(":api-gateway")
include(":task-executor")
include(":task-microservice")
include(":task-shared-domain")
include(":crud-microservice")
include("task-microservice:domain")
include(":social-login")
findProject(":task-microservice:domain")?.name = "domain"
