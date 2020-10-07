plugins {
    java
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("org.springframework.boot") version "2.3.4.RELEASE"
}

java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    jcenter()
}

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("mysql:mysql-connector-java")
    implementation("org.apache.struts:struts2-core:2.5.25")
    implementation("org.apache.struts:struts2-json-plugin:2.5.25")
    implementation("org.apache.struts:struts2-spring-plugin:2.5.25")
    implementation("commons-codec:commons-codec")
    implementation("com.alibaba:fastjson:1.2.25.sec10")
    implementation("javax.activation:activation:1.1.1")
    testImplementation("org.apache.struts:struts2-junit-plugin:2.5.25")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    compileOnly("javax.servlet:javax.servlet-api:3.1.0")
    compileOnly("javax.servlet.jsp:jsp-api:2.2")
}
