import stack.LinkedStack;

public class Main {

    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        for(int i = 0; i < 5; ++ i){
            stack.push(i);
//            System.out.println(stack);
        }
        System.out.println(stack);
        while(! stack.isEmpty()){
            System.out.println(stack.pop());
            System.out.println(stack);
        }
    }
}
