plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "edu.bsu.cs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.openjfx:javafx-controls:23.0.1")
    implementation("org.openjfx:javafx-fxml:23.0.1")
    implementation("com.opencsv:opencsv:5.7.1")
}

tasks.test {
    useJUnitPlatform()
}

javafx {
    version = "23.0.1"
    modules("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("edu.bsu.cs.Application.Main")
}

tasks.withType<JavaExec> {
    standardInput = System.`in`
}
