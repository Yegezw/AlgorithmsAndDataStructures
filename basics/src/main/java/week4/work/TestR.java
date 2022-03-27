package week4.work;

public class TestR {
    public static void main(String[] args) {
        LinkedListR<Integer> linkedListR = new LinkedListR<>();
        linkedListR.addLast(1);
        linkedListR.addLast(1);
        linkedListR.addLast(1);
        System.out.println(linkedListR);

        linkedListR.removeElement(1);
        System.out.println(linkedListR);
    }
}
