package cache;

import java.util.HashMap;

class LRUCache {

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
		if (nodes.containsKey(key)) {
			Node node = nodes.get(key);
			this.list.moveToHead(node);
			return node.value;
		} else
			return -1;
	}

	public void put(int key, int value) {
		if (nodes.containsKey(key)) {
			Node node = nodes.get(key);
			node.value = value;
			this.list.moveToHead(node);
		} else {
			Node node = new Node(key, value);
			nodes.put(key, node);
			this.count++;
			this.list.addToHead(node);
			if (this.count > this.capacity) {
				Node toDelete = this.list.deleteFromTail();
				this.nodes.remove(toDelete.key);
				this.count--;
			}
		}
	}
}