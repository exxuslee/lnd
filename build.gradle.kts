import com.google.protobuf.gradle.proto

plugins {
    kotlin("jvm") version "2.0.0"
    id("com.google.protobuf") version "0.9.2" // Plugin для работы с proto-файлами
}

group = "node.lnd"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar")))) // Подключение AAR
    api(files("libs/Lndmobile.aar"))
    api(fileTree("libs") { include("*.jar") })

    implementation("com.google.protobuf:protobuf-java:4.28.2")

    implementation("io.grpc:grpc-okhttp:1.53.0")
    implementation("io.grpc:grpc-netty:1.53.0")
    implementation("io.grpc:grpc-protobuf:1.53.0")
    implementation("io.grpc:grpc-stub:1.53.0")
    implementation("io.netty:netty-tcnative-boringssl-static:2.0.28.Final")
    implementation("commons-codec:commons-codec:1.11")

    implementation("javax.annotation:javax.annotation-api:1.3.2") // Аннотации

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}

sourceSets {
    main {
        proto {
            srcDir("src/main/proto") // Путь к вашим .proto файлам
        }
        java {
            srcDir("D:/git/lnd/build/generated/source/proto/main/java")
        }
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:4.28.2"
    }
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.53.0"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                create("grpc")
            }
        }
    }
}

tasks.withType<JavaCompile> {
    options.isIncremental = true
}

tasks.withType<ProcessResources> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}