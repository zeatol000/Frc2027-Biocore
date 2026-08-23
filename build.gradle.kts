plugins {
    java
	 `java-library`
	 id("edu.wpi.first.GradleRIO") version "2026.2.1"
}

group = "org.team4153"
version = "0.1.0"

repositories {
	mavenCentral()
	maven {
		url = uri("https://frcmaven.wpi.edu/artifactory/release")
	}
}

dependencies {
	implementation("com.typesafe:config:1.4.3")
}


// Java Compilation //
java.toolchain.languageVersion.set(JavaLanguageVersion.of(26)) // set to 28 when it releases (est: March 2027)

tasks.withType<JavaCompile> {
	options.compilerArgs.addAll(listOf(
		"-deprecation",
		//"-implicit:class",
		"-Werror",			// no errors allowed because the compiler knows best
	))
}
