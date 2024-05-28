import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
	kotlin("jvm") version "2.0.0"
	id("org.liquibase.gradle") version "2.2.2"
	kotlin("plugin.spring") version "2.0.0"
	kotlin("plugin.jpa") version "2.0.0"
	kotlin("plugin.serialization") version "2.0.0"
	id("com.github.ben-manes.versions") version "0.51.0"
}

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

group = "com.dylanmiska"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter:3.3.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.3.0")
	implementation("org.springframework.boot:spring-boot-starter-web:3.3.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.1")
	implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.0")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.0.0")
	implementation("org.junit.jupiter:junit-jupiter:5.11.0-M2")
	implementation("org.postgresql:postgresql:42.7.3")
	implementation("org.valiktor:valiktor-core:0.12.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test:3.3.0")
	testImplementation("io.mockk:mockk:1.13.11")
	testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.28.1")
	implementation("com.h2database:h2:2.2.224")
	liquibaseRuntime("ch.qos.logback:logback-classic:1.5.6")
	liquibaseRuntime("ch.qos.logback:logback-core:1.5.6")
	liquibaseRuntime("org.postgresql:postgresql")
	liquibaseRuntime("info.picocli:picocli:4.7.6")
	liquibaseRuntime("org.liquibase:liquibase-core:4.28.0")
	liquibaseRuntime(sourceSets.getByName("main").output)
}

this.loadProperties()

fun loadProperties() {
	val environment = System.getenv("system_env") ?: "local"

	val configFile = when (environment) {
		"dev" -> file("config.remote.groovy")
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

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile>().configureEach {
	compilerOptions {
		languageVersion.set(KotlinVersion.KOTLIN_2_0)
		jvmTarget.set(JvmTarget.JVM_17)
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
