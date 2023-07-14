plugins {
    java
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
}

<<<<<<< HEAD
group = "com.example"
=======
group = "ru.tinkoff.summer"
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}


repositories {
    mavenCentral()
}

<<<<<<< HEAD
dependencies {
<<<<<<<< HEAD:task-microservice/build.gradle.kts
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.kafka:spring-kafka")

=======
extra["springCloudVersion"] = "2022.0.3"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.1.1")
    implementation (project(":task-shared-domain"))
    // https://mvnrepository.com/artifact/net.logstash.logback/logstash-logback-encoder
implementation("net.logstash.logback:logstash-logback-encoder:7.4")

    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
testImplementation("ch.qos.logback:logback-classic:1.4.8")
// https://mvnrepository.com/artifact/ch.qos.logback/logback-core
implementation("ch.qos.logback:logback-core:1.4.8")

testImplementation("org.mockito:mockito-core:5.4.0")
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}

dependencyManagement {
<<<<<<< HEAD
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
========
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.projectlombok:lombok:1.18.22")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.liquibase:liquibase-core:4.23.0")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation ("org.springframework.boot:spring-boot-starter-web")
>>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce:crud-microservice/build.gradle.kts
=======
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
}


tasks.withType<Test> {
    useJUnitPlatform()
}
