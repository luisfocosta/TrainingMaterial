package undoredo.action;

/**
 * Base class for composite actions that work by carrying out
 * some algorithm, however simple, on the given text, and that,
 * as a result, don't know how to define their full behavior
 * until they are executed.
 * 
 * @author Will Provost
 */
public abstract class AlgorithmicEdit
    extends CompositeAction
{
    private int index;
    private int length;
    
    /**
     * Set the offset and length at which we'll operate.
     */
    public AlgorithmicEdit (int index, int length) {
        this.index = index;
        this.length = length;
    }
    
    /**
     * Accessor for the index or offset.
     */
    public int getIndex () {
        return index;
    }

    /**
     * Accessor for the number of characters.
     */
    public int getLength () {
        return length;
    }
    
    /**
     * Derived classes use this to define their "algorithm" --
     * operate on the given substring, nothing more.
     */
    protected abstract String modified(StringBuffer model);
    
    /**
     * We define execution as the deletion of the existing text
     * and the insertion of the {@link #modified modified text}.
     * This allows us to handle execution and undo for all
     * derived classes.
     */
    @Override
    public void execute (StringBuffer model) {
        setActions (new Delete (index, length), 
            new Insert (index, modified(model)));
        super.execute (model);
    }
}
