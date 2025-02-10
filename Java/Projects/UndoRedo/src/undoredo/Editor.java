package undoredo;

import java.util.Stack;

/**
 * This class manages interactive editing of a textual model.
 * It manages the model itself, and will carry out {@link @Action}s
 * on the text, storing them in an undo buffer. It will then undo
 * any and all actions, in reverse order, on request.
 * It also remembers undone actions and will redo them on request.
 * 
 * @author Will Provost
 */
public class Editor
{
    private StringBuffer text;
    private Stack<Action> done = new Stack<>();
    private Stack<Action> undone = new Stack<>();
    
    /**
     * Build an editor over a given starting text.
     */
    public Editor(String text) {
        this.text = new StringBuffer(text);
    }
    
    /**
     * Accessor for the current text.
     */
    public String getText() {
        return text.toString();
    }
    
    /**
     * Carry out the given action on the current text.
     * Clear the redo buffer.
     */
    public void execute(Action action) {
        undone.clear();
        done.push(action);
        action.execute(text);
    }
    
    /**
     * Indicates whether there are any undoable actions.
     */
    public boolean isUndoable() {
    	return !done.isEmpty();
    }
    
    /**
     * Undo the most recent action.
     * 
     * @throws IllegalStateException If there are no actions to undo
     */
    public void undo() {
        if (!isUndoable())
            throw new IllegalStateException("Nothing to undo.");
        
        Action toUndo = done.pop();
        undone.push(toUndo);
        toUndo.undo(text);
    }
    
    /**
     * Indicates whether there are any redoable actions.
     */
    public boolean isRedoable() {
    	return !undone.isEmpty();
    }
    
    /**
     * Redo the most recently undone action.
     * 
     * @throws IllegalStateException If there are no actions to undo
     */
    public void redo() {
        if (!isRedoable())
            throw new IllegalStateException("Nothing to redo.");
        
        Action toRedo = undone.pop();
        done.push(toRedo);
        toRedo.execute(text);
    }
}
