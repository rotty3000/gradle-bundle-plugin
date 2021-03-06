package org.dm.gradle.plugins.bundle

/**
 * A Bundle plugin extension.
 */
class BundleExtension {
    private final def instructions = [:]

    private final def buildPathConfigurations = ["compileOnly", "runtime"]

    private final def excludeDependencies = []

    /**
     * Trace is deprecated in Bnd. This setting will be ignored.
     */
    boolean trace = false

    boolean failOnError = false

    boolean passProjectProperties = true

    boolean includeTransitiveDependencies = false

    def instruction(String name, String... values) {
        if (name == null || values == []) {
            return
        }
        String value = values.join(',')
        if (instructions.containsKey(name)) {
            instructions[name] += ",$value"
        } else {
            instructions[name] = value
        }
    }

    def exclude(Map excludeDeps) {
        excludeDependencies << excludeDeps
    }

    def getInstructions() {
        return instructions
    }

    def getExcludeDependencies() {
        return excludeDependencies
    }

    def buildPathConfigurations(String... configurations) {
        buildPathConfigurations.clear()
        buildPathConfigurations << Arrays.asList(configurations)
    }

    def getBuildPathConfigurations() {
        return buildPathConfigurations
    }
}