package undoredo.action;

/**
 * Action that deletes a substring.
 * 
 * @author Will Provost
 */
public class Delete
    extends AbstractAction
{
    private int index;
    private int length;
    
    /**
     * Provide the offset and length of the substring to delete. 
     */
    public Delete(int index, int length) {
        this.index = index;
        this.length = length;
    }
    
    /**
     * Accessor for the offset.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Accessor for the length.
     */
    public int getLength() {
        return length;
    }

    /**
     * Execute by deleting the substring.
     * At this point, we know the string to re-insert if we are
     * undone, so call 
     * {@link AbstractAction#setOpposite(undoredo.Action) setOpposite}
     * to install an {@link Insert} instance as our opposite.
     */
    public void execute(StringBuffer model) {
        setOpposite 
            (new Insert(index, model.substring(index, index + length)));
        model.delete(index, index + length);
    }
}
