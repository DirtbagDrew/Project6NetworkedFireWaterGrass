import java.io.*;
import java.util.*;
/**
 * Stores and saves hashmap of Pattern objects. loads the patterns to predict what move to 
 * do to beat the user
 * @author Andrew
 *
 */

public class Computer implements Serializable
{
    /**
     * hashmap to store the Pattern objects
     */
    private HashMap<Pattern, Integer> patternMap;

    /**
     * constructor that creates a new hashmap
     */
    public Computer() 
    {
        patternMap = new HashMap<Pattern, Integer>();
    }

    /**
     * stores the pattern object into the hashmap
     * @param p the pattern being stored
     */
    public void storePattern(String p) 
    {
        int patternIter=0;
        Pattern patt=new Pattern(p);
        
        //if the pattern is already stored, add to the amount of iterations it has
        if(patternMap.containsKey(patt))
        {
        	patternIter=patternMap.get(patt)+1;
        	patternMap.put(patt, patternIter);
        }
        //else add with one iteration
        else
        {
        	patternMap.put(patt, 1);
        }
        
        
    }
    /**
     * decides what over to use against the user
     * @param user the pattern the user has last used
     * @return computers move
     */
    public int makePrediction(String user) 
    {
    	//sets to 0
        int f = 0, w = 0, g = 0;
        int compPred=1+(int)(Math.random()*3);;
        //make user length 1 to avoid null pointer
        if (user.length()==0) 
        {
            user=user+"";
        }
        
        String water=user.substring(1,user.length())+"W";
        String fire=user.substring(1,user.length())+"F";
        String grass=user.substring(1,user.length())+"G";
        
        //counts the amount of time each move is picked next
        if(patternMap.containsKey(new Pattern(water)))
        {
        	w=patternMap.get(new Pattern(water));
        }
        if(patternMap.containsKey(new Pattern(fire)))
        {
        	f=patternMap.get(new Pattern(fire));
        }
        if(patternMap.containsKey(new Pattern(grass)))
        {
        	g=patternMap.get(new Pattern(grass));
        }
        
        //determines what the computer should choose
        if(f>w && f>g)
        {
        	compPred=2;
        }
        if(w>f && w>g)
        {
        	compPred=3;
        }
        if(g>f && g>w)
        {
        	compPred=1;
        } 
        
        
        return compPred;
        
        
    }    
}
                