package DataStructure.Module2_StackQueueDeqeue;

import java.util.Arrays;

public class M2 {
    
    // Some applications about stack.
    // Matching Parentheses and HTML Tags.
    // Parentheses: "(" and ")";
    // Braces "{" and "}";
    // Brackets: "[" and "]";
    // An Algorithm for Matching Delimiters.
    public static boolean isMatched(String expression){
        final String opening = "({[";
        final String closing = ")}]";
        Stack<Character> buffer = new LinkedStack<>();
        for(char c: expression.toCharArray()){
            if(opening.indexOf(c) != -1){
                buffer.push(c);
            }else if(closing.indexOf(c) != -1){
                if(buffer.isEmpty()) return false;
                if(buffer.pop() != c) return false;
            }
        }
        return buffer.isEmpty();
    }

    // Test if every opening tag has a matching closing tag in HTML string.
    public static boolean isHTMLMatched(String html){
        Stack<String> buffer = new ArrayStack<>();
        int j = html.indexOf('<');
        while(j != -1){
            int k = html.indexOf('>', j+1);
            if(k == -1) return false;
            String tag = html.substring(j+1, k);
            if(!tag.startsWith("/")){
                buffer.push(tag);
            }else{
                if(buffer.isEmpty()){
                    return false;
                }
                if(!tag.substring(1).equals(buffer.pop())){
                    return false;
                }
            }
            j = html.indexOf('<', k+1);
        }
        return true;
    }
    // Solve Josephus Problem using a Queue.
    class Josephus{
        public static <E> E Josephus(CircularQueue<E> queue, int k){
            if(queue.isEmpty()) return null;
            while(queue.size() > 1){
                // skip past k-1 items
                for(int i = 0; i < k-1; i++){
                    queue.rotate();
                }
                E e = queue.poll(); // remove the front element from the collection.
                System.out.println("        " + e + " is out "); 
            }
            return queue.poll();
        }
    }
    // Task-2: Calculator.
    public double basicCalculator(String s){
        Stack<Double> stack = new ArrayStack<>();
        double currentNum = 0;
        char sign = '+';
        int n = s.length();
        for(int i = 0; i < n; i++){
            // if it is number, then add it to currentNumber;
            if(Character.isDigit(s.charAt(i))){currentNum = currentNum * 10 + (s.charAt(i) - '0');}
            // If it is signs or it is in the end of the String, then process it.
            if(!Character.isDigit(s.charAt(i)) || i == n-1){
                if(sign == '+'){
                    stack.push(currentNum);
                }else if(sign == '-'){
                    stack.push(-currentNum);
                }else if(sign == '*'){
                    stack.push(stack.pop()*currentNum);
                }else if(sign == '/'){
                    stack.push(stack.pop() / currentNum);
                }else{
                    return Double.NaN;
                }
                sign = s.charAt(i);
                currentNum = 0;
            }
        } 
        // Add all of the numbers in the stack.
        int res = 0;
        while(!stack.isEmpty()){
            res += stack.pop();
        }
        return res;
    }
    // realize stack with Queue.
    public void    


}
