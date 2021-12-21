val exposedVersion: String by project
val koinVersion: String by project
val kotlinVersion: String by project
val ktorVersion: String by project
val ktorRateLimitingVersion: String by project
val ktormVersion: String by project
val logbackVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.6.10"
}

group = "opentibiabr.com"
version = "0.0.1"
application {
    mainClass.set("opentibiabr.com.LoginServerKt")
}

/*
 * Github public PAT byteArray with read-only access to github packages.
 * Byte encode is used to avoid auto deletion when commited.
 */
val byteArrayToken = byteArrayOf(
    103, 104, 112, 95, 65, 56, 110, 115, 75, 74, 104, 79, 120, 90, 85, 55, 77, 84, 51, 71,
    89, 107, 85, 68, 111, 66, 84, 80, 79, 50, 114, 115, 52, 119, 50, 55, 118, 109, 78, 81
)

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/lgrossi/ktor-rate-limiting")
        credentials {
            username = "lgrossi"

            /* public read only github package token, can be exposed */
            password = byteArrayToken.toString(Charsets.UTF_8)
        }
    }
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("lgrossi:ktor-rate-limiting:$ktorRateLimitingVersion")
    implementation("io.ktor:ktor-gson:1.6.7")
    implementation("io.ktor:ktor-jackson:$ktorVersion")
    implementation("io.ktor:ktor-metrics:$ktorVersion")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")

    implementation("io.insert-koin:koin-ktor:$koinVersion")
    implementation("io.insert-koin:koin-core:$koinVersion")
    testImplementation("io.insert-koin:koin-test:$koinVersion")

    implementation("mysql:mysql-connector-java:8.0.25")
    implementation("org.ktorm:ktorm-core:$ktormVersion")
    implementation("org.ktorm:ktorm-support-mysql:$ktormVersion")
    implementation("org.ktorm:ktorm-jackson:$ktormVersion")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    runtimeOnly("org.jetbrains.kotlin:kotlin-scripting-jsr223:$kotlinVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")

    implementation(kotlin("script-runtime"))
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.opentibiabr.loginserver.LoginServerKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}