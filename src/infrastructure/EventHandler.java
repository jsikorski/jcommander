package infrastructure;

import events.Event;

public interface EventHandler {
	public void handle(Event event);
}