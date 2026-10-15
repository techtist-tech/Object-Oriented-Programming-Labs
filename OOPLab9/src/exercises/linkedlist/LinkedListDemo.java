package exercises.linkedlist;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("alpha");
        list.add("beta");
        list.add("gamma");

        for (String s : list) {                 // works because LinkedList implements Iterable
            System.out.println("item: " + s);
        }
        System.out.println("size = " + list.size());

        LinkedList<Integer> nums = new LinkedList<>();
        for (int i = 1; i <= 5; i++) nums.add(i * i);
        int sum = 0;
        for (int n : nums) sum += n;
        System.out.println("sum of squares = " + sum);
    }
}
