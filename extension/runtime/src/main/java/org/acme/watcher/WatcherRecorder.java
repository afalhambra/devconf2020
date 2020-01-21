package org.acme.watcher;

import java.util.Set;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;

import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class WatcherRecorder {

    private static final Logger LOGGER = Logger.getLogger(WatcherRecorder.class);

    public void summarizeBootstrap(WatcherConfig config, Set<String> affectedMethods) {
        LOGGER.infof("\n" +
                " _       __ ___   ______ ______ __  __ ______ ____ \n" +
                "| |     / //   | /_  __// ____// / / // ____// __ \\\n" +
                "| | /| / // /| |  / /  / /    / /_/ // __/  / /_/ /\n" +
                "| |/ |/ // ___ | / /  / /___ / __  // /___ / _, _/ \n" +
                "|__/|__//_/  |_|/_/   \\____//_/ /_//_____//_/ |_|  \n" +
                "                                                   \n" +
                "\n" +
                "" +
                "Regular expression: [%s]\n" +
                "Interceptor threshold limit: %s ms\n" +
                "Monitored JAX-RS methods: \n\t- %s\n",
                config.regularExpression, config.limit, affectedMethods.stream().collect(Collectors.joining("\n\t- ")));
    }
}
