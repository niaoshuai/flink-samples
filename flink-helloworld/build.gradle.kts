plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version ("7.1.2")
}

group = "ren.shuaipeng.flink.samples"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

application {
    mainClass.set("ren.shuaipeng.flink.samples.FlinkJob")
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(dependencyNotation = "org.apache.flink:flink-streaming-java:1.17.1")
    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}