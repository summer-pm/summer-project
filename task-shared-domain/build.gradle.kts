plugins {
    id("java")
}

group = "ru.tinkoff.summer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
       compileOnly ("org.projectlombok:lombok:1.18.28")
	annotationProcessor ("org.projectlombok:lombok:1.18.28")
implementation("org.apache.kafka:kafka-clients:3.4.0")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.1")

}

