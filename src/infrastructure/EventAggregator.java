package infrastructure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import events.Event;

public class EventAggregator {
	public Map<EventHandler, Class<? extends Event>[]> handlers = new HashMap<>();
		
	public void subscribe(EventHandler eventHandler, Class<? extends Event>[] eventsTypes) {
		handlers.put(eventHandler, eventsTypes);
	}
	
	public void publish(Event event) {
		for (EventHandler handler: handlers.keySet()) {
			if (handlers.containsKey(handler) && 
				Arrays.asList(handlers.get(handler)).contains(event.getClass())) {
				
				handler.handle(event);
			}
		}
	}
}
