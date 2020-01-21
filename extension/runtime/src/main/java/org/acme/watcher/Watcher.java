package org.acme.watcher;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@Singleton
public class Watcher {

    private static final Logger LOGGER = Logger.getLogger(Watcher.class.getName());

    private static final String BANNER = "\n" +
            " _       __ ___   ______ ______ __  __   ____   __  __ ______ __\n" +
            "| |     / //   | /_  __// ____// / / /  / __ \\ / / / //_  __// /\n" +
            "| | /| / // /| |  / /  / /    / /_/ /  / / / // / / /  / /  / / \n" +
            "| |/ |/ // ___ | / /  / /___ / __  /  / /_/ // /_/ /  / /  /_/  \n" +
            "|__/|__//_/  |_|/_/   \\____//_/ /_/   \\____/ \\____/  /_/  (_)   \n" +
            "                                                                \n" +
            "Invocation of [%s] exceeded the limit [%s] by [%s ms]" +
            "\n";

    @ConfigProperty(name = "quarkus.watcher.limit")
    long limit;

    void onLimitExceeded(@Observes LimitExceeded event) {
        LOGGER.warnf(BANNER, event.methodInfo, limit, event.time - limit);
    }

}
