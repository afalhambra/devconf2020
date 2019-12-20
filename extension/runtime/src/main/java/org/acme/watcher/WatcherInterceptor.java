package org.acme.watcher;

import javax.annotation.Priority;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Watch
@Priority(1)
@Interceptor
public class WatcherInterceptor {

    @ConfigProperty(name = "quarkus.watcher.limit")
    long limit;

    @Inject
    Event<LimitExceeded> event;

    @AroundInvoke
    Object aroundInvoke(InvocationContext context) throws Exception {
        long start = System.currentTimeMillis();
        try {
            return context.proceed();
        } finally {
            long time = System.currentTimeMillis() - start;
            if (time > limit) {
                event.fire(new LimitExceeded(time,
                        context.getMethod().getDeclaringClass().getName() + "#" + context.getMethod().getName() + "()"));
            }
        }
    }

}
