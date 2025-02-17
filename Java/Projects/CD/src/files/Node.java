package files;

/**
 * The File type encapsulates name, size, and mod date,
 * along with display logic.
 */
public abstract class Node {
	private String name;
	
	public Node(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract int getSize();
	public abstract String asHTML();
  
	/*
	 * This could be pushed down to the Folder class.
	 * That would require some reworking of TestProgram.gsp,
	 * so we're leaving it at this level.
	 */
	public void add(Node child) {}
}
