package cs544;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println(isValid("()"));
    }
    static boolean isValid(String s) {
        String op;
        Stack<String> st = new Stack<>();
        for(int i=0; i<s.length(); i++){
            String b = String.valueOf(s.charAt(i));
            if(b.equals("(") || b.equals("{") || b.equals("[")){
                st.push(b);
            } else {
                if(st.isEmpty()){
                    return false;
                }
                op = st.pop();
                if(!isPareBrackets(op, b)){
                    return false;
                }
            }
        }
        return st.isEmpty();
    }

    public static boolean isPareBrackets(String op, String cl){
        String brackets = op+cl;
        return brackets.equals("()") || brackets.equals("{}") || brackets.equals("[]");
    }
}
