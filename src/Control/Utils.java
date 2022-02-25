package Control;

import Entity.Playable;

import java.util.ArrayList;
import java.util.List;

public class Utils
{
	/**
	 * turn underscores into spaces
	 * capitalize first letter of each word
	 * 
	 * @param rawName String from enum.name()
	 * @return 
	 */
	public static String convertEnumString(String rawName)
	{
		// Assumes rawName is not null, will throw NPE otherwise
		// Choosing not to explicitly handle this since it would be programmer error anyway
		
		String lowerCaseName = rawName.toLowerCase();
		String[] wordArray = lowerCaseName.split("_");
		StringBuilder printNameBuilder = new StringBuilder();
		
		for(int i=0; i<wordArray.length; i++)
		{
			String word = wordArray[i];
			
			if(!word.isEmpty())
			{
				String firstLetter = word.substring(0, 1);
				String remainingWord = word.substring(1);
				
				printNameBuilder.append(firstLetter.toUpperCase() + remainingWord);
				
				if(i < wordArray.length-1)
				{
					printNameBuilder.append(" ");
				}
			}
		}
		
		return printNameBuilder.toString();
	}

	/**
	 * Turns an array into an array list
	 *
	 * @param players Playable[]
	 * @return ArrayList<Playable>
	 */
	public static List<Playable> arrayToList(Playable[] players)
	{
		List<Playable> newPlayers = new ArrayList<>();

		for (int i = 0; i < players.length; i++)
			newPlayers.add(players[i]);

		return newPlayers;
	}
}
