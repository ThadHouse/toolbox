/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.gradleplugins.test.fixtures.sources.cpp;

import dev.gradleplugins.test.fixtures.sources.SourceElement;
import dev.gradleplugins.test.fixtures.sources.SourceFileElement;

/**
 * A C++ source file and corresponding header file.
 */
public abstract class CppSourceFileElement extends CppLibraryElement {
    public abstract SourceFileElement getHeader();

    public abstract SourceFileElement getSource();

    @Override
    public SourceElement getPublicHeaders() {
        return getHeader();
    }

    @Override
    public SourceElement getSources() {
        return getSource();
    }
}
