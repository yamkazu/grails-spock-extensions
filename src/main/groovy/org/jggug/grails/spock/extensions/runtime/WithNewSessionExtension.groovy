package org.jggug.grails.spock.extensions.runtime

import org.jggug.grails.spock.extensions.WithNewSession
import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.MethodInfo
import org.spockframework.runtime.model.SpecInfo

class WithNewSessionExtension extends AbstractAnnotationDrivenExtension<WithNewSession> {

    @Override
    void visitSpecAnnotation(WithNewSession profile, SpecInfo spec) {
        spec.features.each { FeatureInfo feature ->
            if (!feature.getFeatureMethod().getReflection().isAnnotationPresent(WithNewSession.class)) {
                visitFeatureAnnotation(profile, feature);
            }
        }
    }

    @Override
    void visitFeatureAnnotation(WithNewSession profile, FeatureInfo feature) {
        feature.getFeatureMethod().addInterceptor(new WithNewSessionInterceptor())
    }

    @Override
    void visitFixtureAnnotation(WithNewSession profile, MethodInfo fixtureMethod) {
        fixtureMethod.addInterceptor(new WithNewSessionInterceptor())
    }
}
