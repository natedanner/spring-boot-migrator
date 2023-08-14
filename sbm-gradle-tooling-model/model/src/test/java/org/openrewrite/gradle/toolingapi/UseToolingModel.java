/*
 * Copyright 2023 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.gradle.toolingapi;

import org.openrewrite.InMemoryExecutionContext;
import org.openrewrite.SourceFile;
import org.openrewrite.gradle.ProjectInfo;
import org.springframework.sbm.gradle.tooling.GradleProjectData;
import org.springframework.sbm.gradle.tooling.GradleProjectParser;
import org.springframework.sbm.gradle.tooling.ModelBuilder;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class UseToolingModel {
    public static void main(String[] args) {
//        GradleProjectParser parser = new GradleProjectParser();
//
//        List<SourceFile> cus = parser.parse(new File("sample"), new File("sample/build.gradle"), new InMemoryExecutionContext(e -> e.printStackTrace())).collect(Collectors.toList());
//        System.out.println(cus.size());
//
//        cus = parser.parse(new File("demo"), new File("demo/build.gradle.kts"), new InMemoryExecutionContext(e -> e.printStackTrace())).collect(Collectors.toList());
//        System.out.println(cus.size());

        GradleProjectData projectData = ModelBuilder.forProjectDirectory(new File("sample"), new File("sample/build.gradle"), GradleProjectData.class);
        System.out.println(projectData.getBuildDir());
        System.out.println(projectData.getBuildscriptClasspath());
        System.out.println(projectData.getBuildscriptFile());
        System.out.println(projectData.getGradleSettings());
        System.out.println(projectData.getProjectDir());
        System.out.println(projectData.getGradleVersion());
        System.out.println(projectData.getJavaSourceSets());
        System.out.println(projectData.getKotlinSourceSets());
        System.out.println(projectData.getProperties());
        System.out.println(projectData.getRootProjectDir());
        System.out.println(projectData.getSettingsBuildscriptFile());
        System.out.println(projectData.getSettingsClasspath());
        System.out.println(projectData.getMavenRepositories());
        System.out.println(projectData.getPlugins());


    }
}
