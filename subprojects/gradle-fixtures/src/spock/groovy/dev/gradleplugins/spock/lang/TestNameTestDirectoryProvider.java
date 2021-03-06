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

package dev.gradleplugins.spock.lang;

import org.junit.runners.model.FrameworkMethod;

import java.io.File;
import java.io.IOException;

/**
 * A JUnit rule which provides a unique temporary folder for the test.
 */
public class TestNameTestDirectoryProvider extends AbstractTestDirectoryProvider {
    public TestNameTestDirectoryProvider(Class<?> testClass) {
        // NOTE: the space in the directory name is intentional
        super(new File("build/tmp/test files"), testClass);
    }

    private File createTemporaryFolderIn(File parentFolder) throws IOException {
        File createdFolder = File.createTempFile("junit", "", parentFolder);
        createdFolder.delete();
        createdFolder.mkdir();
        return createdFolder;
    }

    public static TestNameTestDirectoryProvider newInstance(Class<?> testClass) {
        return new TestNameTestDirectoryProvider(testClass);
    }

    public static TestNameTestDirectoryProvider newInstance(FrameworkMethod method, Object target) {
        TestNameTestDirectoryProvider testDirectoryProvider = new TestNameTestDirectoryProvider(target.getClass());
        testDirectoryProvider.init(method.getName());
        return testDirectoryProvider;
    }
}
