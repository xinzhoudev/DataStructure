package DataStructure.Module2_StackQueueDeqeue;

public class M2 {
    
    // Task-1: Implementing a stack data structure from scratch has already been finished in ArrayStack.java, LinkedStack.java and ArrayStack.java, LinkedStack.java.

    // Task-2: Calculator.
    public static double basicCalculator(String s){
        Stack<Double> stack = new LinkedStack<>();
        s = s.replace(" ", "");
        double currentNum = 0;
        char sign = '+';
        int n = s.length();
        boolean isNegative = false;
        if(s.charAt(0) == '*' || s.charAt(0) == '/'){
            System.out.println("This cannot be valid expression, please check it again.");
            return Double.NaN;
        }
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // judge whether the next number is negative or not.
            if (c == '-' && (i == 0 || !Character.isDigit(s.charAt(i - 1)))) {
                isNegative = true;
                continue;
            }

            if (Character.isDigit(c)) {
                currentNum = currentNum * 10 + (c - '0');
            }

            // if the current char is operation or in the last position of the string.
            if (!Character.isDigit(c) || i == n - 1) {
                // if there are two operations are not digits.
                if(i > 0 && !Character.isDigit(s.charAt(i-1)) && isMultiplyOrDivide(c) && isMultiplyOrDivide(s.charAt(i-1))){
                    System.out.println("Error operation signs */");
                    return Double.NaN; 
                }
                if(i > 0 && !Character.isDigit(s.charAt(i-1)) && isPlusOrMinus(c) && isPlusOrMinus(c)){
                    System.out.println("Error operation signs +-");
                    return Double.NaN; 

                }
                if (isNegative) currentNum = -currentNum;

                if (sign == '+') stack.push(currentNum);
                else if (sign == '-') stack.push(-currentNum);
                else if (sign == '*') stack.push(stack.pop() * currentNum);
                else if (sign == '/') stack.push(stack.pop() / currentNum);

                sign = c;       // renew the sign
                currentNum = 0; // reset the current number.
                isNegative = false;
            }
        }

        // Add all of the numbers in the stack.
        int res = 0;
        while(!stack.isEmpty()){
            res += stack.pop();
        }
        return (int)res;
    }

    private static boolean isMultiplyOrDivide(char c){
        return (c == '*' || c == '/');
    }

    private static boolean isPlusOrMinus(char c){
        return (c == '-' || c == '+');
    }

    // Task-3: Queue is in ArrayQueue.java or LinkedQueue.java.
    // Task-4: realize stack with Queue, in the file StackWithQueue.java.





    public static void main(String[] args){
        System.out.println(basicCalculator("150/-3"));
        StackWithTwoQueue<Integer> sq = new StackWithTwoQueue<>();
        sq.push(1);
        sq.push(3);
        sq.push(9);
        sq.push(399);
        sq.pop();
        sq.push(333);
        sq.push(334);
        sq.push(335);
        sq.push(336);
        sq.push(337);
        sq.push(-3000);
        System.out.println(sq.size());

        while(!sq.isEmpty()){
            System.out.println(sq.pop());
        }

        // test the real stack
        ArrayStack<Integer> sq2 = new ArrayStack<>();
        sq2.push(11);
        sq2.push(22);
        sq2.push(33);
        sq2.push(44);
        sq2.push(55);
        sq2.push(66);
        sq2.push(100);
        sq2.push(300);
        System.out.println(sq2.top());
        sq2.pop();
        sq2.pop();
        sq2.pop();
        System.out.println(sq2.peek());
        while(!sq2.isEmpty()){
            System.out.println(sq2.pop());
        }

        LinkedStack<Integer> lsq = new LinkedStack<>();
        lsq.push(3000);
        lsq.push(4000);
        lsq.push(5000);
        lsq.push(6000);
        lsq.push(7000);
        lsq.push(8000);
        while(!lsq.isEmpty()){
            System.out.println(lsq.pop());
        }
        // first in first out.
        ArrayQueue<String> q1 = new ArrayQueue<>();
        q1.enQueue("XiaoMengJiang");
        q1.enQueue("XinZhou");
        q1.enQueue("XianJi");
        q1.enQueue("XiaoZhu");
        q1.enQueue("YaNan");
        while(!q1.isEmpty()){
            System.out.println(q1.deQueue());
        }

        LinkedQueue<String> q2 = new LinkedQueue<>();
        q2.enQueue("WoBuGuaiNi");
        q2.enQueue("XiaoMengJiang");
        q2.enQueue("XinZhou");
        q2.enQueue("XianJi");
        q2.enQueue("XiaoZhu");
        q2.enQueue("YaNan");
        while(!q2.isEmpty()){
            System.out.println(q2.deQueue());
        }
        System.out.println(basicCalculator("3*-150/-3"));
        System.out.println(basicCalculator("1*8/4+3*3+7/7"));
        System.out.println(basicCalculator("1*8/4++3*3+7/7"));

    }

}
