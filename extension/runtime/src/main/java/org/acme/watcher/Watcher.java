package org.acme.watcher;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@Singleton
public class Watcher {

    private static final Logger LOGGER = Logger.getLogger(Watcher.class.getName());

    private static final String BANNER = "\n" +
            "\n" +
            " __      ___ _____ ___ _  _    ___  _   _ _____ _ \n" +
            " \\ \\    / /_\\_   _/ __| || |  / _ \\| | | |_   _| |\n" +
            "  \\ \\/\\/ / _ \\| || (__| __ | | (_) | |_| | | | |_|\n" +
            "   \\_/\\_/_/ \\_\\_| \\___|_||_|  \\___/ \\___/  |_| (_)\n" +
            "                                                  \n" +
            "\n" +
            "";

    @ConfigProperty(name = "quarkus.watcher.limit")
    long limit;

    void onLimitExceeded(@Observes LimitExceeded event) {
        LOGGER.warn(BANNER);
        LOGGER.warnf("Invocation of [%s] exceeded the limit [%s] by [%s ms]",
                event.methodInfo, limit,
                event.time - limit);
    }

}
