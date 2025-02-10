package undoredo.action;

/**
 * Action that converts a given substring to lower case.
 * 
 * @author Will Provost
 */
public class ToUppercase
    extends AlgorithmicEdit
{
	/**
	 * Provide offset and length of the substring to be converted.
	 */
    public ToUppercase(int index, int length) {
        super(index, length);
    }
    
    /**
     * We modify by converting the given string to lowercase;
     * the base class does the rest.
     */
    protected String modified(StringBuffer model) {
        return model.substring(getIndex(), getIndex() + getLength())
            .toUpperCase();
    }
}
