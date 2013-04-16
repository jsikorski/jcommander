package events;

import model.ListingContext;

public class ListingContextChanged implements Event {

	private ListingContext newContext;
	
	public ListingContextChanged(ListingContext newContext) {
		this.newContext = newContext;
	}
	
	public ListingContext getNewContext() {
		return newContext;
	}
}
