package co.edu.uptc.structure;

public class Node<T> {
	private T data;
	private Node<T> next;
	
	public Node(T data) {
		this.data=data;
		this.next=null;
	}
	
	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return data + " -> " + next;
	}
}
