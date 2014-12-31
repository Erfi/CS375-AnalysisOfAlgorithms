/*
 * Modified by Erfan Azad on 30 December 2014
 * File: DogSearch.java
 * Description: This class finds the number of times
 * a word appears in an input string. 
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DogSearch
{
 public static void main(String[] args)
  {
    //This code will not compile correctly until you fix it.
    //
    //We are trying to count the number of times the word
    //dog appears in some input text, but we can't seem to
    //remember the appropriate method call in the String class,
    //and are not sure our approach will work.
    //task: Use the JDK documentation to help us finish the code,
    //then discuss the ambiguity in the problem description, and
    //reword it to remove the ambiguity in the space provided below.
    //
	//The ambiguity comes from the fact that it is not clear
	//if the word dog can be part of the other words (eg: hotdog) or not
	//or if it should be case-sensitive.
	//
    //PROBLEM: Count the number of times the word "dog" appears in some input.
	//search for patterns with the following properties
	//-upper-case or lower-case or mix
    //-not part of other words
    //

    String input = new String("The Dogman was no ordinary dog, nor man, but rather a peculiar dog-like man who barked like a dog, and panted like a dog, he even ate like a dog.  He owned a dog named Doglips, and interestingly enough, his favorite food was hotdogs.");
    System.out.println(input);
    
    System.out.print("Counting dogs: ");
    int count = 0;
    Pattern pattern = Pattern.compile("[ ][dD][oO][gG][ .,:;]");
    Matcher matcher = pattern.matcher(input);
    while(matcher.find()){
    	count++;
    	System.out.print(count+" ");
    }
    System.out.println("\nThe word \"dog\" appears "+count+" times.");
  } 
}



