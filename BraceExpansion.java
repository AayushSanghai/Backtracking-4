import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// "static void main" must be defined in a public class.
//Method 1 , Sorting the result at last
public class Main {

    // Time Complexity : 0(k^n);n is the no. of blocks and k is the average length.
// Space Complexity : 0(nk)
// Did this code successfully run on Leetcode : No, I don't have premium
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach


    public static List<String> result;

    public static String[] expand(String s) {
        if (s == null || s.length() == 0) {
            return new String[]{};
        }

        result = new ArrayList<>(); //to store the result
        List<List<Character>> blocks = new ArrayList<>();   //to store the input as blocks. I am taking input inside the {} as 1 block and outside the brackets as separate block
        int i = 0;

        while (i < s.length()) {    //navigating over the string to convert it into blocks
            char c = s.charAt(i);   //taking the 1st character
            List<Character> block = new ArrayList<>();  //to store block inside blocks
            if (c == '{') { //if i encounter an opening bracket, I store all the contents as a single block
                i++;
                while (s.charAt(i) != '}') {    //till I reach the end of the bracket
                    if (s.charAt(i) != ',') {   //I omit the commas
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            } else {    //if a character is outside the {} then I treat it as a single entity or single block
                block.add(c);
            }
            blocks.add(block);  //finally I add the entire thing to my blocks
            i++;
        }

        backtracking(blocks, new StringBuilder(), 0);   //callling the backtracking function where stringbuilder will store the different combinations formed and i will iterate over my blocks list

        String[] answer = new String[result.size()];    //I need to convert the list of list to string

        for (int j = 0; j < result.size(); j++) {
            answer[j] = result.get(j);
        }
        Arrays.sort(answer);    //I have to take care of lexicographical order
        return answer;
    }

    public static void backtracking(List<List<Character>> blocks, StringBuilder sb, int index) {
        //base
        if (index == blocks.size()) {
            result.add(sb.toString());  //when index is at its last block in blocks list, I will record the answer
            return;
        }
        //logic
        List<Character> block = blocks.get(index);  //to get the characters inside each block
        for (int i = 0; i < block.size(); i++) {
            //action
            sb.append(block.get(i));    //adding the character to my sb
            //recurse
            backtracking(blocks, sb, index + 1);    //calling backtracking on block where it ill move to my next index
            //backtrack
            sb.setLength(sb.length() - 1);  //removing the last character once my answer is recorded to store form a string with the 2nd character from single block if present
        }
    }

    public static void main(String[] args) {
        String s = "{a,b}c{d,e}f";
        String[] answer = expand(s);
        System.out.print(Arrays.asList(answer));
    }
}

// Method-2 optimizing the sorting
public class Main {

    // Time Complexity : 0(k^n);n is the no. of blocks and k is the average length. But the internal sorting operation is nklogk from nklognk
// Space Complexity : 0(nk)
// Did this code successfully run on Leetcode : No, I don't have premium
// Any problem you faced while coding this : No


    public static List<String> result;

    public static String[] expand(String s){
        if(s == null || s.length() == 0){
            return new String[] {};
        }

        result = new ArrayList<>();
        List<List<Character>> blocks = new ArrayList<>();
        int i = 0;

        while(i < s.length()){
            char c = s.charAt(i);
            List<Character> block = new ArrayList<>();
            if(c == '{'){
                i++;
                while(s.charAt(i) != '}'){
                    if(s.charAt(i) != ','){
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            }
            else{
                block.add(c);
            }
            Collections.sort(block);
            blocks.add(block);
            i++;
        }

        backtracking(blocks, new StringBuilder(), 0);

        String[] answer = new String[result.size()];

        for(int j = 0; j < result.size(); j++){
            answer[j] = result.get(j);
        }
        return answer;
    }

    public static void backtracking(List<List<Character>> blocks, StringBuilder sb, int index){
        //base
        if(index == blocks.size()){
            result.add(sb.toString());
            return;
        }
        //logic
        List<Character> block = blocks.get(index);
        for(int i = 0; i < block.size(); i++){
            //action
            sb.append(block.get(i));
            //recurse
            backtracking(blocks, sb, index + 1);
            //backtrack
            sb.setLength(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        String s = "{a,b}c{d,e}f";
        String[] answer = expand(s);
        System.out.print(Arrays.asList(answer));
    }
}