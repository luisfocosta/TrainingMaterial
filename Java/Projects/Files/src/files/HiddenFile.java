package files;

import java.time.LocalDate;

/**
 * The File type encapsulates name, size, and mod date,
 * along with display logic.
 */
public class HiddenFile extends File {

	public HiddenFile(String name, int size, LocalDate lastModified) {
		super(name, size, lastModified);
	}
	
	public HiddenFile(String name, int size, String lastModified) {
		super(name, size, lastModified);
	}
	
	@Override
	public String asHTML() {
		return "<span style='color: grey;' >" + super.asHTML() +
			      " <em>[hidden]</em></span>";
	}
}
