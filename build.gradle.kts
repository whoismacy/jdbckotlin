plugins {
    kotlin("jvm") version "2.2.21"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.mysql:mysql-connector-j:9.5.0")
    implementation(kotlin("stdlib-jdk8"))
}

application {
    mainClass.set("MainKt")
}

kotlin {
    jvmToolchain(11)
}
