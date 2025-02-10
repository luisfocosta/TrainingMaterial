package undoredo.action;

import undoredo.Action;

/**
 * Base class for actions that can be defined as sequences of
 * other actions.
 * 
 * @author Will Provost
 */
public class CompositeAction
    implements Action
{
    private Action[] actions;
    
    /**
     * Provide any number of component actions, to be executed
     * in series.
     */
    public CompositeAction(Action... actions) {
        this.actions = actions;
    }
    
    /**
     * Accessor for the sequence of actions.
     */
    public Action[] getActions() {
        return actions;
    }
    
    /**
     * Mutator for the sequence of actions.
     */
    public void setActions(Action... actions) {
        this.actions = actions;
    }
    
    /**
     * Act by running our component actions in sequence.
     */
    public void execute(StringBuffer model) {
        for (Action action : actions) {
            action.execute(model);
        }
    }

    /**
     * Undo by undoing our component actions, in reverse order.
     */
    public void undo(StringBuffer model) {
        for (int i = actions.length - 1; i >= 0; --i) {
            actions[i].undo(model);
        }
    }
}
