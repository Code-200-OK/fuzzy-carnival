package others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***Given a List of words, return the words that can be typed using letters of alphabet on only 
		one row's of American keyboard like the image below.**
*/
public class KeyboardRow {
    public String[] findWords(String[] words) {
        if(words==null || words.length==0)
            return new String[0];
        List<Set<Character>> keyboard = new ArrayList<Set<Character>>();
        keyboard.add(new HashSet<Character>());
        keyboard.add(new HashSet<Character>());
        keyboard.add(new HashSet<Character>());
        keyboard.get(0).addAll(Arrays.asList('q','w','e','r','t','y','u','i','o','p'));
        keyboard.get(1).addAll(Arrays.asList('a','s','d','f','g','h','j','k','l'));
        keyboard.get(2).addAll(Arrays.asList('z','x','c','v','b','n','m'));
        
        int current;
        char[] letters;
        boolean found;
        List<String> ans = new ArrayList<String>();
        for(int i=0;i<words.length;i++){
            letters = words[i].toCharArray();
            current = -1;
            found = true;
            for(int j=0;j<letters.length;j++){
                if(current==-1){
                    for(int z=0;z<3;z++){
                        if(keyboard.get(z).contains(Character.toLowerCase(letters[j]))){
                            current = z;
                            break;
                        }
                    }
                }
                else{
                    if(!keyboard.get(current).contains(Character.toLowerCase(letters[j]))){
                        found = false;
                        break;
                    }
                }
            }
            if(found)ans.add(words[i]);
        }
        String[] answer = ans.toArray(new String[0]);
        return answer;
    }
}
/***Method**: Use list of sets.*/
