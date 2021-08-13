package cache;

class DoublyLinkedList {
	Node head;
	Node tail;

	public boolean isEmpty() {
		return this.head == null;
	}

	public void addToHead(Node node) {
		if (this.isEmpty()) {
			this.head = this.tail = node;
			node.prev = null;
			node.next = null;
		} else {
			node.prev = null;
			node.next = this.head;
			this.head.prev = node;
			this.head = node;
		}
	}

	public Node deleteFromTail() {
		Node node = this.tail;
		if (this.head == this.tail) {
			this.head = this.tail = null;
		} else {
			this.tail = this.tail.prev;
			this.tail.next = null;
		}
		return node;
	}

	public void moveToHead(Node node) {
		if (this.head == node)
			return;
		else if (this.tail == node) {
			this.deleteFromTail();
			this.addToHead(node);
		} else {
			node.prev.next = node.next;
			if (node.next != null)
				node.next.prev = node.prev;
			node.next = null;
			node.prev = null;
			this.addToHead(node);
		}
	}
}
