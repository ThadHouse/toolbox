/*
 * Copyright 2018 the original author or authors.
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

package dev.gradleplugins.integtests.fixtures;

import dev.gradleplugins.test.fixtures.gradle.executer.GradleDistribution;
import org.gradle.util.GradleVersion;

public class GradleDistributionTool implements AbstractMultiVersionSpecRunner.VersionedTool {
    private final GradleDistribution distribution;
    private final String ignored;

    public GradleDistributionTool(GradleDistribution distribution) {
        this(distribution, null);
    }

    public GradleDistributionTool(GradleDistribution distribution, String ignored) {
        this.distribution = distribution;
        this.ignored = ignored;
    }

    @Override
    public boolean matches(String criteria) {
        if (criteria.equals("snapshot") && distribution.getVersion().isSnapshot()) {
            return true;
        }
        return GradleVersion.version(criteria).equals(distribution.getVersion());
    }

    public GradleDistribution getDistribution() {
        return distribution;
    }

    public String getIgnored() {
        return ignored;
    }
}
