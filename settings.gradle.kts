rootProject.name = "spring-micro-demo"


include(":eureka-server")
include(":message-microservice")
include(":api-gateway")
include(":user-microservice")
include(":task-executor")
include(":task-microservice")
include(":task-shared-domain")
include("task-microservice:domain")
findProject(":task-microservice:domain")?.name = "domain"
