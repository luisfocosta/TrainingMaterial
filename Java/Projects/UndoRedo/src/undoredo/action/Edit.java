package undoredo.action;

/**
 * We model "editing" as deleting and then inserting in the same place. 
 * 
 * @author Will Provost
 */
public class Edit
    extends CompositeAction
{
	/**
	 * Given an offset, length, and replacement string,
	 * build a composite of {@link Delete} and {@link Insert} actions.
	 */
    public Edit(int index, int length, String replacement) {
        super(new Delete(index, length), new Insert(index, replacement));
    }
}
