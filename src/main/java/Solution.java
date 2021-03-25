import java.util.HashSet;
import java.util.Set;

class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] passwords = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> set = new HashSet<>();
        for(String word: words){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < word.length(); ++ i){
                int pos = (word.charAt(i) - 'a');
                sb.append(passwords[pos]);
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}
