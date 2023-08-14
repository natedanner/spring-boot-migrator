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
package org.springframework.sbm.gradle.tooling;

import org.openrewrite.gradle.marker.GradleSettings;
import org.openrewrite.gradle.toolingapi.GradleProject;

import java.io.File;
import java.util.Collection;
import java.util.Map;

public interface GradleProjectData extends GradleProject {

    GradleSettings getGradleSettings();
    String getGradleVersion();
    boolean isRootProject();
    File getRootProjectDir();
    Collection<GradleProjectData> getSubprojects();
    File getProjectDir();
    File getBuildDir();
    File getBuildscriptFile();
    File getSettingsBuildscriptFile();
    Map<String, ?> getProperties();
    Collection<JavaSourceSetData> getJavaSourceSets();
    boolean isMultiPlatformKotlinProject();
    Collection<KotlinSourceSetData> getKotlinSourceSets();
    Collection<File> getBuildscriptClasspath();
    Collection<File> getSettingsClasspath();

}
