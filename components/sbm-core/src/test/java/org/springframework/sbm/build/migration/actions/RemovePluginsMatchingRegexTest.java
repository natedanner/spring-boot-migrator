/*
 * Copyright 2021 - 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.sbm.build.migration.actions;

import org.intellij.lang.annotations.Language;
import org.springframework.sbm.engine.recipe.Action;
import org.junit.jupiter.api.Test;

import java.util.List;

class RemovePluginsMatchingRegexTest {

    @Test
    void apply() {
        @Language("xml")
        String given =
                """
                <?xml version="1.0" encoding="UTF-8" standalone="no"?>
                <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
                    <modelVersion>4.0.0</modelVersion>
                    <groupId>org.mule.examples</groupId>
                    <artifactId>hello-world</artifactId>
                    <version>2.1.5-SNAPSHOT</version>
                    <packaging>jar</packaging>
                    <name>hello-world</name>
                    <properties>
                        <mule.maven.plugin.version>2.1</mule.maven.plugin.version>
                        <munit.version>2.1.0</munit.version>
                        <app.runtime>4.1.5</app.runtime>
                        <http.connector.version>1.5.4</http.connector.version>
                        <sockets.connector.version>1.1.5</sockets.connector.version>
                        <maven.compiler.target>11</maven.compiler.target>
                        <maven.compiler.source>11</maven.compiler.source>
                        <spring-boot.version>3.1.2</spring-boot.version>
                    </properties>
                    <pluginRepositories>
                        <pluginRepository>
                            <name>Mule Releases</name>
                            <id>mule-releases</id>
                            <url>https://repository.mulesoft.org/releases/</url>
                        </pluginRepository>
                    </pluginRepositories>
                    <repositories>
                        <repository>
                            <name>Mule Releases</name>
                            <id>mule-releases</id>
                            <url>https://repository.mulesoft.org/releases/</url>
                        </repository>
                    </repositories>
                    <build>
                        <plugins>
                            <plugin>
                                <groupId>org.springframework.boot</groupId>
                                <artifactId>spring-boot-maven-plugin</artifactId>
                                <version>${spring-boot.version}</version>
                                <executions>
                                    <execution>
                                        <configuration>
                                            <mainClass>org.springframework.sbm.SpringShellApplication</mainClass>
                                        </configuration>
                                        <goals>
                                            <goal>repackage</goal>
                                        </goals>
                                    </execution>
                                </executions>
                            </plugin>
                            <plugin>
                                <groupId>org.mule.tools.maven</groupId>
                                <artifactId>mule-maven-plugin</artifactId>
                                <version>${mule.maven.plugin.version}</version>
                                <extensions>true</extensions>
                                <configuration>
                                    <classifier>mule-application-example</classifier>
                                </configuration>
                            </plugin>
                            <plugin>
                                <groupId>com.mulesoft.munit.tools</groupId>
                                <artifactId>munit-maven-plugin</artifactId>
                                <version>${munit.version}</version>
                                <executions>
                                    <execution>
                                        <id>test</id>
                                        <phase>test</phase>
                                        <goals>
                                            <goal>test</goal>
                                            <goal>coverage-report</goal>
                                        </goals>
                                    </execution>
                                </executions>
                                <configuration>
                                    <coverage>
                                        <runCoverage>true</runCoverage>
                                        <formats>
                                            <format>html</format>
                                        </formats>
                                    </coverage>
                                    <runtimeVersion>${app.runtime}</runtimeVersion>
                                    <dynamicPorts>
                                        <dynamicPort>http.port</dynamicPort>
                                    </dynamicPorts>
                                </configuration>
                            </plugin>
                        </plugins>
                    </build>
                </project>
                """;

        String expected =
                """
                <?xml version="1.0" encoding="UTF-8" standalone="no"?>
                <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
                    <modelVersion>4.0.0</modelVersion>
                    <groupId>org.mule.examples</groupId>
                    <artifactId>hello-world</artifactId>
                    <version>2.1.5-SNAPSHOT</version>
                    <packaging>jar</packaging>
                    <name>hello-world</name>
                    <properties>
                        <mule.maven.plugin.version>2.1</mule.maven.plugin.version>
                        <munit.version>2.1.0</munit.version>
                        <app.runtime>4.1.5</app.runtime>
                        <http.connector.version>1.5.4</http.connector.version>
                        <sockets.connector.version>1.1.5</sockets.connector.version>
                        <maven.compiler.target>11</maven.compiler.target>
                        <maven.compiler.source>11</maven.compiler.source>
                        <spring-boot.version>3.1.2</spring-boot.version>
                    </properties>
                    <pluginRepositories>
                        <pluginRepository>
                            <name>Mule Releases</name>
                            <id>mule-releases</id>
                            <url>https://repository.mulesoft.org/releases/</url>
                        </pluginRepository>
                    </pluginRepositories>
                    <repositories>
                        <repository>
                            <name>Mule Releases</name>
                            <id>mule-releases</id>
                            <url>https://repository.mulesoft.org/releases/</url>
                        </repository>
                    </repositories>                    
                    <build>
                        <plugins>
                            <plugin>
                                <groupId>org.springframework.boot</groupId>
                                <artifactId>spring-boot-maven-plugin</artifactId>
                                <version>${spring-boot.version}</version>
                                <executions>
                                    <execution>
                                        <configuration>
                                            <mainClass>org.springframework.sbm.SpringShellApplication</mainClass>
                                        </configuration>
                                        <goals>
                                            <goal>repackage</goal>
                                        </goals>
                                    </execution>
                                </executions>
                            </plugin>
                        </plugins>
                    </build>
                </project>
                """;

        List<String> regex = List.of("com\\.mulesoft\\.munit\\.tools\\:.*", "org\\.mule\\.tools\\..*");
        Action sut = new RemovePluginsMatchingRegex(regex);
        OpenRewriteMavenBuildFileTestSupport.verifyRefactoring(given, expected, sut);
    }
}