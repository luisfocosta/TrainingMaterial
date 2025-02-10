package undoredo;

/**
 * Defines the behavior of an undoable/redoable action on a text model. 
 * 
 * @author Will Provost
 */
public interface Action
{
	/**
	 * Carry out the action as configured.
	 */
    public void execute(StringBuffer model);
    
    /**
     * Undo the action.
     */
    public void undo(StringBuffer model);
}
