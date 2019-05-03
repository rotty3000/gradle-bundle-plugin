package org.dm.gradle.plugins.bundle

import org.gradle.api.tasks.bundling.Jar

import static java.util.Objects.requireNonNull
import static org.dm.gradle.plugins.bundle.BundleUtils.*

class JarBuilderFactory {
    private final Jar jarTask
    private JarBuilder jarBuilder

    /**
     * Only for testing.
     */
    JarBuilderFactory() {
        jarTask = null;
    }

    JarBuilderFactory(Jar jarTask) {
        this.jarTask = requireNonNull(jarTask)
    }

    JarBuilder get() {
        if (jarBuilder != null) {
            return jarBuilder
        }
        def project = jarTask.project
        return jarBuilder = new JarBuilder().
            withBase(getBase(project)).
            withProperties(getProperties(jarTask)).
            withClasspath(getClasspath(jarTask)).
            withSourcepath(getSources(project)).
            withResources(getResources(jarTask)).
            withVersion(getVersion(project)).
            withName(getBundleSymbolicName(project)).
            withFailOnError(getFailOnError(jarTask))
    }
}
