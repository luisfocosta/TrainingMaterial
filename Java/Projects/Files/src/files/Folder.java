package files;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The File type encapsulates name, size, and mod date,
 * along with display logic.
 */
public class Folder extends Node {
	private List<Node> contents = new ArrayList<>();
	
	public Folder(String name) {
		super(name);
	}
	
	public List<Node> getContents() {
		return Collections.unmodifiableList(contents);
	}

	public void add(Node node) {
		contents.add(node);
	}
	
	public int getSize() {
		return contents.stream().mapToInt(node -> node.getSize()).sum(); 
	}

	@Override
	public String asHTML() {
		String result = "<strong>" + getName() + "</strong> (" +
				getSize() + " bytes)";
		if (contents.size() != 0) {
			result += "<ul>";
			for (Node node : contents) {
				result += "<li>" + node.asHTML() + "</li>";
			}
			result += "</ul>";
		}
		
		return result;
	}
}
