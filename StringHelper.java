/*Joseph Fabisevich
 *Michael Knower
 */
 
/*StringHelper class
 *
 *Turner
 *
 *This class is to be used for manipulating Strings without the use of arrays.
 *
 *It is a static class, designed so that an object does not need to be left
 *hanging in memory.
 *
 *
 *Give class file and documentation only.
*/

/**
 * Work with Strings without the benefit of arrays.
 *
 *
 * @version 	1.29, 03/01/23
 * @author	Ivan Turner
 * @since	JDK1.4
 */
public class StringHelper
{
	//replaceCharAtidx
	//Used to replace one character at one idx with one other character.
    /**
     * Replaces the character in str at idx with 
     *replacement.
     *
     * @param  str   		The original String
     * @param  idx		An Integer representing the idx of the char being replaced.
     * @param  replacement	The char being inserted into str at idx.
     *
     * @return The new String with replacement at idx.
     *
     */
	public static String replaceCharAtIdx(String str, int idx, char replacement)
	{
		char array[] = str.toCharArray();
		array[idx] = replacement;
		str = new String(array);
		return str;
	}
	
	//isLetter
	//Determines whether or not the character at a specified idx is a letter.
    /**
     *
     * @param  ch   		The Character.
     *
     * @return true if the character is a capital or lowercase letter.
     *
     */
	public static boolean isLetter(char ch)
	{
		if (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z')
			return true;
			
		return false;
	}
	
	//fillString
	//Fills a string with all of the same character.
    /**
     *
     * @param  str   		The String.
     * @param  ch			The character placed into the string.
     *
     * @return the new string.
     *
     */
	public static String fillString(String str, char ch)
	{
		int i;
		for (i = 0; i < str.length(); i++)
			str = replaceCharAtIdx(str, i, ch);
			
		return str;
	}

	//hasAllLetters
	//Checks to see if a string has only letters.
    /**
     *
     * @param str   		The String.
     *
     * @return true if the string is all letters.
     *
     */
    public static boolean hasAllLetters(String str)
    {
    	int i;
    	for (i = 0; i < str.length(); i++)
    		if (!isLetter(str.charAt(i)))
    			return false;
    			
    	if(str.length() == 0)
    		return false;
    			
    	return true;
    }
}