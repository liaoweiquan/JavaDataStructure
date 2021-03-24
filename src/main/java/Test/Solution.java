package Test;

class Solution {
    public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder();
        int top = -1;
        for(int i = 0; i < S.length(); ++ i){
            if(top >= 0 && sb.charAt(top) == S.charAt(i)){
                sb.deleteCharAt(top);
                -- top;
            }else{
                sb.append(S.charAt(i));
                ++ top;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.removeDuplicates("aaaaaaaaa"));
    }
}