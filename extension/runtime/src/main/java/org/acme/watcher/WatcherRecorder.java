package org.acme.watcher;

import java.util.Set;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;

import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class WatcherRecorder {

    private static final Logger LOGGER = Logger.getLogger(WatcherRecorder.class);

    public void summarizeBootstrap(WatcherConfig config, Set<String> affectedMethods) {
        LOGGER.infof(
                "Watcher extension is configured with the regular expression [%s] and interceptor threshold limit [%s ms]\nMonitored JAX-RS methods include: \n\t- %s",
                config.regularExpression, config.limit, affectedMethods.stream().collect(Collectors.joining("\n\t- ")));
    }
}
