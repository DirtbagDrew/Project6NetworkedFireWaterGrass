import java.io.Serializable;
import java.util.Arrays;
/**
 * creates patttern object that stores a pattern from a user
 * @author Andrew
 *
 */
public class Pattern implements Serializable 
{
    /**
     * character array that holds the user inputted pattern
     */
    private String pattern;

    /**
     * Pattern constructor
     * @param patt inputted pattern
     */
    public Pattern(String patt) 
    {
        pattern = patt;
    }

    /**
     * getting pattern character array
     * 
     * @return The pattern character array.
     */
    public String getPattern() 
    {
        return pattern;
    }

    /**
     * Override the hashCode().
     */
    @Override
    public int hashCode() 
    {
        final int prime=31;
        int result=1;
        result=prime*result+pattern.hashCode();
        return result;
    }

    /**
     * Override the equals()
     */
    @Override
    public boolean equals(Object o) 
    {
        if(o instanceof Pattern)
        {
        	Pattern p = (Pattern) o;
        	return pattern.equals(p.pattern);
        }
        return false;
    }
    public String toString()
    {
    	return pattern;
    }
}