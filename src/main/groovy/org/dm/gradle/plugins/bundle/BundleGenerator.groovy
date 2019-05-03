package org.dm.gradle.plugins.bundle

import org.gradle.api.Action
import org.gradle.api.tasks.bundling.Jar

import static java.util.Objects.requireNonNull
import static org.dm.gradle.plugins.bundle.BundleUtils.*

/**
 * An action to be used for generating bundles.
 */
class BundleGenerator implements Action<Jar> {
    private final JarBuilderFactory jarBuilderFactory

    BundleGenerator(JarBuilderFactory jarBuilderFactory) {
        this.jarBuilderFactory = requireNonNull(jarBuilderFactory)
    }

    /**
     * Creates and initializes a new {@link JarBuilder} using
     * {@link BundleExtension} parameters and uses it to produce
     * a new bundle.
     * @param jarTask the task within which this action is
     * performed
     */
    @Override
    void execute(Jar jarTask) {
        JarBuilder jarBuilder = jarBuilderFactory.get()
        try {
            jarBuilder.writeJarTo(getOutput(jarTask))
        }
        finally {
            jarBuilder.close()
        }
    }
}
