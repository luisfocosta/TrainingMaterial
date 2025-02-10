package undoredo;

import undoredo.action.Delete;
import undoredo.action.Edit;
import undoredo.action.Insert;
import undoredo.action.ToLowercase;
import undoredo.action.ToUppercase;

/**
 * Quick test for the {@link Editor}, involving all of the
 * {@link Action} classes as well.
 *
 * @author Will Provost
 */
public class TestProgram1 {
    /**
     * Test a sequence of actions, undo and redo in the editor,
     * verifying the changes to the model with each move.
     */
    public static void main(String[] args) {
        String text = "Every good boy does fine.";
        Editor editor = new Editor(text);

    	editor.execute(new Insert(13, "d"));
        System.out.println(editor.getText());
        editor.execute(new Delete(5, 6));
        System.out.println(editor.getText());
        editor.execute(new Edit(15, 4, "well"));
        System.out.println(editor.getText());
        editor.execute(new ToUppercase(15, 4));
        System.out.println(editor.getText());
        editor.execute(new ToLowercase(0, 5));
        System.out.println(editor.getText());

        editor.undo();
        System.out.println(editor.getText());
        editor.undo();
        System.out.println(editor.getText());
        editor.undo();
        System.out.println(editor.getText());
        editor.undo();
        System.out.println(editor.getText());
        editor.undo();
        System.out.println(editor.getText());

        editor.redo();
        System.out.println(editor.getText());
        editor.redo();
        System.out.println(editor.getText());
        editor.execute(new Edit(9, 7, "? F"));
        System.out.println(editor.getText());
    }
}
