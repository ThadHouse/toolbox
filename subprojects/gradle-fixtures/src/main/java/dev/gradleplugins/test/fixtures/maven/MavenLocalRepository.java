/*
 * Copyright 2019 the original author or authors.
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

package dev.gradleplugins.test.fixtures.maven;

import dev.gradleplugins.test.fixtures.file.TestFile;

import java.net.URI;

/**
 * A fixture for dealing with the Maven Local cache.
 */
public class MavenLocalRepository implements MavenRepository {
    private final TestFile rootDirectory;

    public MavenLocalRepository(TestFile rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public URI getUri() {
        return rootDirectory.toURI();
    }

    public TestFile getRootDirectory() {
        return rootDirectory;
    }
}
