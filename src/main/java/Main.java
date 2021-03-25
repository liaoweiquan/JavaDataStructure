import map.LinkedListMap;
import set.BSTSet;
import set.LinkedListSet;
import stack.LinkedStack;

public class Main {

    public static void main(String[] args) {
        LinkedListMap<String, Integer> map = new LinkedListMap<>();
        for(int i = 1; i < 10; ++ i){
            map.add(String.valueOf(i), i);
        }
        System.out.println(map.getSize());
        for(int i = 1; i < 10; ++ i){
            System.out.println(i + ":" + map.get(String.valueOf(i)));
        }
    }
}
