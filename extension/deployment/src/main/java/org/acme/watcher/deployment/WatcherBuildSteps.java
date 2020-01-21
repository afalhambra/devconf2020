package org.acme.watcher.deployment;

import java.lang.reflect.Modifier;

import javax.ws.rs.GET;

import org.acme.watcher.WatcherConfig;
import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationTarget.Kind;
import org.jboss.jandex.DotName;
import org.jboss.jandex.IndexView;
import org.jboss.jandex.MethodInfo;

import io.quarkus.arc.deployment.BeanArchiveIndexBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

public class WatcherBuildSteps {
    
    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem("watcher");
    }
    
    @BuildStep
    void collectResourceMethods(BeanArchiveIndexBuildItem beanArchive,
            BuildProducer<WatchedResourceMethodBuildItem> resourceMethods,
            WatcherConfig config) {

        IndexView index = beanArchive.getIndex();
        DotName getDotName = DotName.createSimple(GET.class.getName());

        for (AnnotationInstance annotation : index.getAnnotations(getDotName)) {
            if (annotation.target().kind() == Kind.METHOD) {
                MethodInfo method = annotation.target().asMethod();
                // filter out methods based on config expression
                if (Modifier.isPublic(method.flags())
                        && method.declaringClass().name().toString().matches(config.regularExpression)) {
                    resourceMethods.produce(new WatchedResourceMethodBuildItem(method));
                }
            }
        }
    }

}
