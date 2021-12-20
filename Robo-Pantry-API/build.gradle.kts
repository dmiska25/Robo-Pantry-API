import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.20"
	id("org.liquibase.gradle") version "2.0.4"
	kotlin("plugin.spring") version "1.5.20"
	kotlin("plugin.jpa") version "1.5.20"
	kotlin("plugin.serialization") version "1.5.20"
}

group = "com.dylanmiska"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.junit.jupiter:junit-jupiter:5.7.0")
	runtimeOnly("org.postgresql:postgresql")
	implementation("org.valiktor:valiktor-core:0.12.0")
// testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.mockk:mockk:1.12.0")
	testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.24")
	implementation("com.h2database:h2")
//  spring security
//	implementation("org.springframework.boot:spring-boot-starter-security")
//	testImplementation("org.springframework.security:spring-security-test")
//  liquibase
	liquibaseRuntime("ch.qos.logback:logback-classic:1.2.3")
	liquibaseRuntime("ch.qos.logback:logback-core:1.2.3")
	liquibaseRuntime("org.postgresql:postgresql")
	liquibaseRuntime("org.liquibase:liquibase-core:3.5.1")
	liquibaseRuntime(sourceSets.getByName("main").output)
}

this.loadProperties()

fun loadProperties() {
	val environment = if (hasProperty("env"))
		property("env").toString()
	else "local"

	val configFile = when (environment) {
		"dev", "qa", "uat" -> file("config.remote.groovy")
		else -> file("config.groovy")
	}

	project.extra["config"] = groovy.util.ConfigSlurper(environment).parse(configFile.readText())
}

liquibase {
	val config = (rootProject.extra["config"] as groovy.util.ConfigObject).flatten()

	activities.register("main") {
		this.arguments = mapOf(
			"logLevel" to "info",
			"changeLogFile" to config["spring.datasource.changelog"],
			"url" to config["spring.datasource.url"],
			"username" to config["spring.datasource.username"],
			"password" to config["spring.datasource.password"]
		)
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
