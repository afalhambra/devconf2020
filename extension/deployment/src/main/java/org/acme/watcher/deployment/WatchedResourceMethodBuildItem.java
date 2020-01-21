package org.acme.watcher.deployment;

import org.acme.watcher.WatcherInterceptor;
import org.jboss.jandex.MethodInfo;

import io.quarkus.builder.item.MultiBuildItem;

/**
 * {@link WatcherInterceptor} is automatically bound to a JAX-RS resource method represented by this build item.
 */
public final class WatchedResourceMethodBuildItem extends MultiBuildItem {

    private final MethodInfo method;

    public WatchedResourceMethodBuildItem(MethodInfo method) {
        this.method = method;
    }

    public MethodInfo getMethod() {
        return method;
    }

}
