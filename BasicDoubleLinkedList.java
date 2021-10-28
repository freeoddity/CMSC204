import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
public class BasicDoubleLinkedList<T> {
	private Node firstNode;
	private Node lastNode;
	private int size;
	
	BasicDoubleLinkedList(){
		firstNode = null;
		lastNode = null;
		size = 0;
	}
	
	public Node getFirstNode() {
		return firstNode;
	}
	
	public void setFirstNode(Node node) {
		firstNode = node;
	}
	
	public Node getLastNode() {
		return lastNode;
	}
	
	public void setLastNode(Node node) {
		lastNode = node;
	}
	
	public void addToEnd(T data) {
		Node newNode = new Node(data);
		if(getSize() == 0) {
			firstNode = newNode;
			lastNode = newNode;
		}else {
			newNode.setPrevious(lastNode);
			lastNode.setNext(newNode);
			lastNode = newNode;
		}
		size++;
	}
	
	public void addToFront(T data) {
		Node newNode = new Node(data);
		if(getSize() == 0) {
			firstNode = newNode;
			lastNode = newNode;
		}else {
			newNode.setNext(firstNode);
			firstNode.setPrevious(newNode);
			firstNode = newNode;
		}
		size++;
	}
	
	public T getFirst() {
		return firstNode.getData();
	}
	
	public T getLast() {
		return lastNode.getData();
	}
	
	public int getSize() {
		return size;
	}
	
	public ListIterator<T> iterator(){
		return new ListIter();
	}
	
	public void remove(T targetData, Comparator<T> comparator ) {
		if (getSize() == 0) {
			
		}else {		
			boolean found = false;
			Node current = firstNode;
			while(!found && (current != null) ) {
				if(current.getData().equals(targetData)) {
					if (current == firstNode) {
						firstNode = firstNode.getNext();
						firstNode.setPrevious(null);
					}else if(current == lastNode) {
						lastNode = lastNode.getPrevious();
						lastNode.setNext(null);
					}else {
						current.getPrevious().setNext(current.getNext());
						current.getNext().setPrevious(current.getPrevious());
					}
					found = true;
				}else {
					current = current.getNext();
				}
			}
		}
	}
	
	public T retrieveFirstElement() {
		T removedData = firstNode.getData();
		firstNode.getNext().setPrevious(null);
		firstNode = firstNode.getNext();
		return removedData;
	}
	
	public T retrieveLastElement() {
		T removedData = lastNode.getData();
		lastNode.getPrevious().setNext(null);
		lastNode = lastNode.getPrevious();
		return removedData;
	}
	
	public ArrayList<T> toArrayList(){
		ArrayList<T> result = new ArrayList<T>();
		ListIterator<T> it = iterator();
		while(it.hasNext()) {
			result.add((T)it.next());
		}
		return result;
	}
	
	
	protected class Node{
		private Node previous;
		private T data;
		private Node next;
		
		protected Node(T newEntry) {
			this(null, newEntry, null);
		}
		
		protected Node(Node previousNode, T newEntry, Node nextNode) {
			previous = previousNode;
			data = newEntry;
			next = nextNode;
		}

		public Node getPrevious() {
			return previous;
		}

		public void setPrevious(Node previous) {
			this.previous = previous;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		
		
	}


	public class ListIter implements ListIterator<T> {
		private Node previous;
		private Node next;
		
		private ListIter() {
			previous = null;
			next = firstNode;
		}//end constructor
		
		public boolean hasNext() {
			return !(next == null);
		}//end hasNext
		
		public T next() {
			if(hasNext()){
				previous = next;
				next = next.getNext();
				return previous.getData();
			}else {
				throw new NoSuchElementException("No more elements within list");
			}
		}
		
		public boolean hasPrevious() {
			return !(previous == null);
		}
		
		public T previous() {
			if(hasPrevious()) {
				next = previous;
				previous = previous.getPrevious();
				return next.getData();
			}else {
				throw new NoSuchElementException("Iterator is at the head of the list");
			}
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public void add(T e) {
			throw new UnsupportedOperationException();
		}
		
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}
		
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}
		
		public void set(T e) {
			throw new UnsupportedOperationException();
		}

	}
}
