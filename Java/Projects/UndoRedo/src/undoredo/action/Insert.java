package undoredo.action;

/**
 * Action that inserts a given string into the model. 
 * 
 * @author Will Provost
 */
public class Insert
    extends AbstractAction
{
    private int index;
    private String toInsert;
    
    /**
     * Provide the insertion index and string to insert.
     * We construct our opposite action immediately, as a {@link Delete}
     * at the same index of the number of characters we'll be inserting.
     */
    public Insert(int index, String toInsert) {
        super(new Delete(index, toInsert.length()));
        
        this.index = index;
        this.toInsert = toInsert;
    }

    /**
     * Execute by inserting the given string at the given index.
     */
    public void execute(StringBuffer model) {
        model.insert(index, toInsert);
    }
}
