package com.amica.help.data;

import java.util.List;

import com.amica.help.Clock;
import com.amica.help.Ticket;
import com.amica.help.HelpDesk;
import com.amica.help.Tags;
import com.amica.help.Ticket.Priority;

import lombok.Getter;

@Getter
public class OriginalDataSet implements DataSet {

	private int IDBase;

	private Tags tags;
	private HelpDesk helpDesk;

	private void reset() {
		tags = new Tags();
		helpDesk = new HelpDesk(tags);
	}
	
	private void addNote(int ticketID, String note) {
		helpDesk.getTicketByID(IDBase + ticketID).addNote(note);
	}
	
	private void suspend(int ticketID, String reason) {
		helpDesk.getTicketByID(IDBase + ticketID).suspend(reason);
	}
	
	private void resume(int ticketID, String reason) {
		helpDesk.getTicketByID(IDBase + ticketID).resume(reason);
	}
	
	private void resolve(int ticketID, String reason) {
		helpDesk.getTicketByID(IDBase + ticketID).resolve(reason);
	}

	private void addTags(int ticketID, String... tags) {
		helpDesk.addTags(IDBase + ticketID, tags);
	}
	
	public List<Ticket> build() {
		reset();
		
		tags.addSynonym("RDP", "remoting");
		tags.addSynonym("remote desktop", "remoting");
		tags.getTag("CMA");
		tags.getTag("GitHub");
		tags.getTag("VM");
		tags.getTag("VPN");

		helpDesk.createTechnician("A05589", "Andree", 55491);
		helpDesk.createTechnician("A12312", "Boris", 12399);
		helpDesk.createTechnician("A17440", "Caelem", 34002);
		helpDesk.createTechnician("A20265", "Dineh", 60709);

		Clock.setTime("11/1/21 8:22");
		IDBase = helpDesk.createTicket("A21013", "Unable to log in.", Priority.HIGH) - 1;
		Clock.setTime("11/1/21 8:23");
		addTags(1, "remoting");
		Clock.setTime("11/1/21 8:33");
		helpDesk.createTicket("A19556", "Can't connect to remote desktop from my laptop.", Priority.HIGH);
		Clock.setTime("11/1/21 8:34");
		addTags(2, "remoting", "laptop");
		Clock.setTime("11/1/21 8:36");
		suspend(2, "Checking if the user can connect from other machines.");
		Clock.setTime("11/1/21 8:37");
		helpDesk.createTicket("A05989", "Need GitHub access.", Priority.MEDIUM);
		Clock.setTime("11/1/21 8:38");
		addTags(3, "permissions", "GitHub");
		Clock.setTime("11/1/21 8:39");
		suspend(3, "Requested approval from manager.");
		Clock.setTime("11/1/21 9:05");
		helpDesk.createTicket("T17549", "Can't use just one screen for remote desktop.", Priority.MEDIUM);
		Clock.setTime("11/1/21 9:06");
		addTags(4, "remote desktop");
		Clock.setTime("11/1/21 9:07");
		resolve(4, "Explained that this is not a feature we support right now.");
		Clock.setTime("11/1/21 9:48");
		addNote(1, "Determined that it's a VPN problem rather than RDP.");
		Clock.setTime("11/1/21 9:51");
		addNote(1, "Recommended that the user update their browser.");
		Clock.setTime("11/1/21 9:52");
		addTags(1, "VPN");
		Clock.setTime("11/1/21 14:11");
		helpDesk.createTicket("A24490", "Files on my user drive are currupt.", Priority.HIGH);
		Clock.setTime("11/1/21 14:12");
		addTags(5, "VM");
		Clock.setTime("11/1/21 14:14");
		resume(2, "User: Yes, I can connect from other desktop machines at Amica.");
		Clock.setTime("11/1/21 14:17");
		suspend(5, "Requested examples of corrupt files.");
		Clock.setTime("11/1/21 16:39");
		helpDesk.createTicket("T24090", "Need CMA access.", Priority.MEDIUM);
		Clock.setTime("11/1/21 16:41");
		addTags(6, "Permissions", "CMA");
		Clock.setTime("11/1/21 16:42");
		suspend(6, "Requested approval from manager.");

		Clock.setTime("11/2/21 8:11");
		helpDesk.createTicket("A15711", "Laptop won't start up.", Priority.URGENT);
		Clock.setTime("11/2/21 8:12");
		addTags(7, "laptop");
		Clock.setTime("11/2/21 8:45");
		resume(6, "Received approval.");
		resolve(6, "Added permission.");
		Clock.setTime("11/2/21 8:52");
		helpDesk.createTicket("A20271", "Can't login.", Priority.HIGH);
		Clock.setTime("11/2/21 8:53");
		addTags(8, "remoting");
		Clock.setTime("11/2/21 10:19");
		helpDesk.createTicket("T13370", "Need to reset MobilePass.", Priority.HIGH);
		Clock.setTime("11/2/21 10:20");
		resume(3, "Received approval.");
		resolve(3, "Added permission.");
		Clock.setTime("11/2/21 10:21");
		addTags(9, "vpn");
		Clock.setTime("11/2/21 10:22");
		suspend(9, "Tried to contact user; left voice mail.");
		Clock.setTime("11/2/21 11:00");
		helpDesk.createTicket("A14401", "Unable to log in.", Priority.HIGH);
		Clock.setTime("11/2/21 11:01");
		addTags(10, "RDP");
		Clock.setTime("11/2/21 11:32");
		helpDesk.createTicket("T11918",
				"No disk space left! I don't have that much stuff on here; not sure what's taking up all the space.",
				Priority.URGENT);
		Clock.setTime("11/2/21 11:33");
		addTags(11, "vm");
		Clock.setTime("11/2/21 14:49");
		resolve(1, "User reports that the browser update fixed it.");

		Clock.setTime("11/3/21 9:22");
		helpDesk.createTicket("A13288", "Need GitHub access.", Priority.MEDIUM);
		Clock.setTime("11/3/21 9:23");
		addTags(12, "permissions", "github");
		Clock.setTime("11/3/21 9:24");
		suspend(12, "Requested approval from manager.");
		Clock.setTime("11/3/21 11:11");
		helpDesk.createTicket("A22465", "Laptop audio seems to be broken.", Priority.MEDIUM);
		Clock.setTime("11/3/21 11:12");
		addTags(13, "laptop", "audio");
		Clock.setTime("11/3/21 11:39");
		helpDesk.createTicket("A18087", "Can't log in.", Priority.HIGH);
		Clock.setTime("11/3/21 11:40");
		addTags(14, "remote desktop");
		Clock.setTime("11/3/21 13:11");
		resolve(10, "Opened remote access to RI150WS3344; confirmed user can connect.");
		Clock.setTime("11/3/21 13:16");
		resume(5, "User: See /Users/A10551/Projects/Spec_20211015.pdf.");
		Clock.setTime("11/3/21 13:17");
		addNote(5, "Building a new VM.");
		Clock.setTime("11/3/21 13:18");
		resolve(5, "Migrated most files to new VM, restored remaining files from backups, switched IP address over.");
		Clock.setTime("11/3/21 13:19");
		resolve(11, "Found user's ME2020 Maven cache way overloaded, recommended cleaning it out.");
		
		return helpDesk.getTickets().toList();
	}
}
