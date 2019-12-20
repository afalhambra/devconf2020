package org.acme;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;

import org.acme.watcher.LimitExceeded;

@Singleton
public class EventsCollector {

    private List<LimitExceeded> events = new ArrayList<>();
    
    void observeLimitExceeded(@Observes LimitExceeded event) {
        events.add(event);
    }

    public List<LimitExceeded> getEvents() {
        return events;
    }
    
}
