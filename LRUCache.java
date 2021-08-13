
import java.util.HashMap;

class LRUCache {
    
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    
    class DoublyLinkedList {
        Node head;
        Node tail;
        
        public boolean isEmpty(){
            return this.head == null;
        }
        
        public void addToHead(Node node){
            if(this.isEmpty()){
                this.head = this.tail = node;
                node.prev = null;
                node.next = null;
            }
            else{
                node.prev = null;
                node.next = this.head;
                this.head.prev = node;
                this.head = node;
            }
        }
        
        public Node deleteFromTail(){
            Node node = this.tail;
            if(this.head == this.tail){
                this.head = this.tail = null;
            }
            else{
                this.tail = this.tail.prev;
                this.tail.next = null;   
            }
            return node;
        }
        
        public void moveToHead(Node node){
            if(this.head == node)
                return;
            else if(this.tail == node){
                this.deleteFromTail();
                this.addToHead(node);
            }
            else{
                node.prev.next = node.next;
                if(node.next != null)
                    node.next.prev = node.prev;
                node.next = null;
                node.prev = null;
                this.addToHead(node);
            }
        }
    }
    
    HashMap<Integer, Node> nodes;
    DoublyLinkedList list;
    int count, capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.nodes = new HashMap<>();
        this.list = new DoublyLinkedList();
    }
    
    public int get(int key) {
        if(nodes.containsKey(key)){
            Node node = nodes.get(key);
            this.list.moveToHead(node);
            return node.value;
        }
        else
            return -1;
    }
    
    public void put(int key, int value) {
        if(nodes.containsKey(key)){
            Node node = nodes.get(key);
            node.value = value;
            this.list.moveToHead(node);
        }
        else{
            Node node = new Node(key, value);
            nodes.put(key, node);
            this.count++;
            this.list.addToHead(node);
            if(this.count > this.capacity){
                Node toDelete = this.list.deleteFromTail();
                this.nodes.remove(toDelete.key);
                this.count--;
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
