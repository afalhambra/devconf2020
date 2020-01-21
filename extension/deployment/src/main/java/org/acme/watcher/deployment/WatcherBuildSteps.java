package org.acme.watcher.deployment;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

public class WatcherBuildSteps {
    
    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem("watcher");
    }

}
