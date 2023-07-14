<<<<<<< HEAD

plugins {
    id("java")
=======
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    kotlin("jvm") version "1.9.0"
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
}

group = "ru.tinkoff.summer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
}



dependencies {
    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
<<<<<<< HEAD
    implementation (project(":task-shared-domain"))
    testImplementation("org.assertj:assertj-core:3.24.2")
    testCompileOnly("org.projectlombok:lombok:1.18.28")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.28")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
=======
    implementation(project(":task-shared-domain"))

    implementation("org.apache.kafka:kafka-clients:3.2.3")
    implementation("net.logstash.logback:logstash-logback-encoder:7.4")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.7.1")
   implementation("net.logstash.logback:logstash-logback-encoder:7.4")

    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
implementation("ch.qos.logback:logback-classic:1.4.8")
// https://mvnrepository.com/artifact/ch.qos.logback/logback-core
implementation("ch.qos.logback:logback-core:1.4.8")

    testImplementation("org.assertj:assertj-core:3.24.2")
    testCompileOnly("org.projectlombok:lombok:1.18.28")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.28")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    implementation(kotlin("stdlib-jdk8"))
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

<<<<<<< HEAD
tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "ru.tinkoff.summer.taskexecutor.Main"
    }
}
=======

tasks.jar {
     duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Main-Class"] = "ru.tinkoff.summer.taskexecutor.Main"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })


}
>>>>>>> abe64b06c86ae596b1dcb093b0d2d008782c59ce
