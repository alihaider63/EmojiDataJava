import com.vanniktech.maven.publish.JavaLibrary
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java-library")
    id("com.vanniktech.maven.publish") version "0.29.0"
    alias(libs.plugins.jetbrains.kotlin.jvm)
    signing
}

dependencies {
    implementation(libs.json)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "17"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "17"
}

group = "com.alihaider.emojidatajava"
version = "1.0.0"

mavenPublishing {
    configure(
        JavaLibrary(
            javadocJar = JavadocJar.Javadoc(),
            sourcesJar = true,
        )
    )

    coordinates("io.github.alihaider63", "emojidatajava", "1.0.0")

    pom {
        name.set("EmojiDataJava")
        description.set("A Java/Kotlin library for handling Emoji data")
        inceptionYear.set("2024")
        url.set("https://github.com/alihaider63/EmojiDataJava")

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                id.set("alihaider63")
                name.set("Ali Haider")
                email.set("alihaiderkhokharjee@gmail.com")
            }
        }

        scm {
            connection.set("scm:git:https://github.com/alihaider63/EmojiDataJava.git")
            developerConnection.set("scm:git:ssh://git@github.com:alihaider63/EmojiDataJava.git")
            url.set("https://github.com/alihaider63/EmojiDataJava")
        }
    }

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()
}

signing {
    useGpgCmd()
}