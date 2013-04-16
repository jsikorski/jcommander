package commands;

import model.ListingContext;
import events.DirectoryChanged;
import infrastructure.EventAggregator;

public class ChangeDirectory implements Command {
	
	private ListingContext listingContext;
	private String path;

	public ChangeDirectory(ListingContext listingContext, String path) {
		this.listingContext = listingContext;
		this.path = path;
	}

	@Override
	public void execute() {
		listingContext.setCurrentPath(path);
	}
}
