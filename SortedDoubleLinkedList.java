import java.util.Comparator;
import java.util.ListIterator;


public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	private Comparator<T> comparator2;
	
	public SortedDoubleLinkedList(Comparator<T> comparator2) {
		super();
		this.comparator2 = comparator2;
	}
	
	public T add(T data) {
		Node checkNode = getFirstNode();
		Node newNode = new Node(data);
		T result = null;
		if(getSize() == 0) {
			setFirstNode(newNode);
			setLastNode(newNode);
		}else {
			while((comparator2.compare(data, checkNode.getData()) > 0) && checkNode != getLastNode()) {
				checkNode = checkNode.getNext();
			}
			if (checkNode == getFirstNode()) {
				newNode.setNext(getFirstNode());
				getFirstNode().setPrevious(newNode);
				setFirstNode(newNode);
			}else if(checkNode == getLastNode()) {
				newNode.setPrevious(getLastNode());
				getLastNode().setNext(newNode);
				setLastNode(newNode);
			}else {
				result = checkNode.getData();
				checkNode.getPrevious().setNext(checkNode.getNext());
				checkNode.getNext().setPrevious(checkNode.getPrevious());
			}
		}
		return result;
	}
	
	public void addToEnd(T data) {
		throw new UnsupportedOperationException();
	}
	
	public void addToFront(T data) {
		throw new UnsupportedOperationException();
	}
	
	public ListIterator<T> interator(){
		return super.iterator();
	}
	
	public void remove(T data, Comparator<T> comparator) {
		super.remove(data, comparator);
	}

}
