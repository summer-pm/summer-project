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
}

