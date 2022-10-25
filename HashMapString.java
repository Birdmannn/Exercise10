public class HashMapString {

    private static class Node {
        String item, key;
        Node next;
    }
    private static Node root;
    private static int size;

    public String get(String key) {
        Node runner = root;
        while(runner.next != null) {
            if(runner.key.equals(key))
                return runner.item;
            runner = runner.next;
        }
        //here we haven't found the item.
        return null;
    }

    public void put(String key, String value) {
        Node newNode = new Node();
        newNode.key = key;
        newNode.item = value;

        if(root == null) {
            root = newNode;
        }
        else {
            Node runner = root;
            while(runner.next != null) {
                runner = runner.next;
            }
            runner.next = newNode;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public boolean remove(String key) {
        if(root == null)     //nothing to do here
            return false;
        if(root.key.equals(key)) {
            root = root.next;
            size--;
            return true;
        }
        else {
            //Traverse the whole list
            Node previous = root;
            Node runner = root.next;
            while(runner != null) {
                if(runner.key.equals(key))
                    break;
                previous = runner;
                runner = runner.next;
            }
            if(runner != null) {
                previous.next = runner.next;
                size--;
                return true;
            }
        }
        //item is not in this list
        return false;
    }

    public boolean containsKey(String key) {
        if(root == null)
            return false;
        Node runner = root;
        while(runner.next != null) {
            if(runner.next.key.equals(key))
                return true;
            runner = runner.next;
        }
        return false;   //no key found.
    }
}
