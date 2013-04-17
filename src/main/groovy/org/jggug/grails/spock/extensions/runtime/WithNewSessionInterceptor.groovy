package org.jggug.grails.spock.extensions.runtime

import grails.util.Holders
import org.spockframework.runtime.extension.IMethodInterceptor
import org.spockframework.runtime.extension.IMethodInvocation

class WithNewSessionInterceptor implements IMethodInterceptor {

    @Override
    void intercept(IMethodInvocation invocation) throws Throwable {
        def domainClass = Holders.grailsApplication.domainClasses?.first()?.clazz
        assert domainClass
        
        domainClass.withNewSession {
            invocation.proceed()
        }
    }
}
