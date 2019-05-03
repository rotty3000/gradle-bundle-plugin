package org.dm.gradle.plugins.bundle

import org.codehaus.groovy.control.io.NullWriter
import org.gradle.api.java.archives.Manifest
import spock.lang.Specification

class ManifestSubstituteSpec extends Specification {
    Manifest manifest
    JarBuilderFactory factory
    ManifestSubstitute substitute
    JarBuilder jarBuilder

    def setup() {
        manifest = Mock(Manifest)
        factory = Mock(JarBuilderFactory)
        substitute = new ManifestSubstitute(factory, manifest)
        jarBuilder = Mock(JarBuilder)
    }

    def "Redirects writeTo for Writer"() {
        when:
        substitute.writeTo(NullWriter.DEFAULT)

        then:
        1 * factory.get() >> jarBuilder
        1 * jarBuilder.writeManifestTo(*_)
        0 * manifest.writeTo(_)
    }
}
