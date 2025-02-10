package undoredo;

import undoredo.action.Edit;
import undoredo.action.Insert;

/**
 * Quick test for the {@link Editor}, involving all of the
 * {@link Action} classes as well.
 *
 * @author Will Provost
 */
public class TestProgram2 {
    /**
     * Test a sequence of actions, undo and redo in the editor,
     * verifying the changes to the model with each move.
     */
    public static void main(String[] args) {
        String text = "She sells shells.";
        Editor editor = new Editor(text);

    	editor.execute(new Insert(10, "sea"));
        System.out.println(editor.getText());
        editor.execute(new Edit(10, 3, "she"));
        System.out.println(editor.getText());
        editor.execute(new Edit(0, 3, "He"));
        System.out.println(editor.getText());

        editor.undo();
        System.out.println(editor.getText());
        editor.undo();
        System.out.println(editor.getText());
        editor.execute(new Insert(19, " by the seashore"));
        System.out.println(editor.getText());
        editor.redo();
        System.out.println(editor.getText());
    }
}
