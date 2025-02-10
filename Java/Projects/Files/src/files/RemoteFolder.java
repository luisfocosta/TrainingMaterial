package files;

/**
 * The File type encapsulates name, size, and mod date,
 * along with display logic.
 */
public class RemoteFolder extends Folder {

	public RemoteFolder(String name) {
		super(name);
	}
	
	@Override
	public int getSize() {
		return 0;
	}
	
	@Override
	public String asHTML() {
		return "<em>" + super.asHTML() + "</em>";
	}
}
