import java.io.*;
import java.util.*;
/**
 * Stores and saves hashmap of Pattern objects. loads the patterns to predict what move to 
 * do to beat the user
 * @author Andrew
 *
 */

public class Computer 
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
        if(patternMap.containsKey(p))
        {
        	patternIter=patternMap.get(p)+1;
        	patternMap.put(patt, patternIter);
        }
        else
        {
        	patternMap.put(patt, 1);
        }
        
       // System.out.println(patternMap);
    }
    /**
     * decides what ove to use against the user
     * @param user the pattern the user has last used
     * @return
     */
    public int makePrediction(String user) 
    {
    	//sets the count to 0
        int f = 0, w = 0, g = 0,compPred=0;
        if (user.length()==0) 
        {
            user=user+"";
        }
        
        String water=user.substring(1,user.length())+"W";
        String fire=user.substring(1,user.length())+"F";
        String grass=user.substring(1,user.length())+"G";
        
        if(patternMap.containsKey(new Pattern(water)))
        {
        	w=patternMap.get(new Pattern(water));
        }
        else if(patternMap.containsKey(new Pattern(fire)))
        {
        	f=patternMap.get(new Pattern(fire));
        }
        else if(patternMap.containsKey(new Pattern(grass)))
        {
        	g=patternMap.get(new Pattern(grass));
        }
        else
        {
        	compPred=1+(int)(Math.random()*3);
        }
        
        if(f>w && f>g)
        {
        	compPred=2;
        }
        else if(w>f && w>g)
        {
        	compPred=3;
        }
        else if(g>f && g>w)
        {
        	compPred=1;
        }
        
        return compPred;
        
    }    
}
                