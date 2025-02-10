package files;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The File type encapsulates name, size, and mod date,
 * along with display logic.
 */
public class File extends Node {
	private int size;
	private LocalDate lastModified;

	public static final DateTimeFormatter PARSER =
			DateTimeFormatter.ofPattern("M/d/yy");
	public static final DateTimeFormatter FORMATTER =
			DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public File(String name, int size, String lastModified) {
		this(name, size, LocalDate.parse(lastModified, PARSER));
	}
	
	public File(String name, int size, LocalDate lastModified) {
		super(name);
		this.size = size;
		this.lastModified = lastModified;
	}
	
	public int getSize() {
		return size;
	}

	public LocalDate getLastModified() {
		return lastModified;
	}

	@Override
	public String asHTML() {
		return "<strong>" + getName() + "</strong> (" + getSize() +
		        " bytes, last modified " + getLastModified().format(FORMATTER) + ")";
	}
}
