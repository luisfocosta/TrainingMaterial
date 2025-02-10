package undoredo.action;

import undoredo.Action;

/**
 * Base for actions that can express their reversal in terms
 * of another {@link Action} instance.
 * 
 * @author Will Provost
 */
public abstract class AbstractAction
    implements Action
{
    private Action opposite;
    
    /**
     * Create an instance with no opposite action set.
     * You must call {@link #setOpposite(Action) setOpposite}
     * before attempting to undo this action.
     */
    public AbstractAction() {
    }
    
    /**
     * Create an instance with an opposite action.
     */
    public AbstractAction(Action opposite) {
        this.opposite = opposite;
    }
    
    /**
     * Accessor for the opposite action.
     */
    public Action getOpposite() {
        return opposite;
    }
    
    /**
     * Mutator for the opposite action.
     */
    public void setOpposite(Action opposite) {
        this.opposite = opposite;
    }
    
    /**
     * Undo by carrying out the opposite action.
     */
    public void undo(StringBuffer model) {
        opposite.execute(model);
    }
}
