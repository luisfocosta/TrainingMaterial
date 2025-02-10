package com.amica.help.data;

import java.util.List;

import com.amica.help.HelpDesk;
import com.amica.help.Tags;
import com.amica.help.Ticket;

/**
 * Common interface for sets of test data.
 * 
 * @author Will Provost
 */
public interface DataSet {
	public List<Ticket> build();
	public Tags getTags();
	public HelpDesk getHelpDesk();
}
