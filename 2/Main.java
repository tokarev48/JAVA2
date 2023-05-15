public class Main {
    public static void main(String[] args) {

        IList linkedList = new ILinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        System.out.println(linkedList.get(0));
        linkedList.remove(2);
        System.out.println(linkedList.get(1));
        System.out.println(linkedList.get(2));

        System.out.println(linkedList);
    }

}