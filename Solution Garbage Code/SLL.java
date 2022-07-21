package application;

import java.io.Serializable;

public class SLL<T> implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected SLLNode<T> head, tail;

	public SLL() {
		head = tail = null;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void addToHead(T el) {
		head = new SLLNode<T>(el, head);
		if (tail == null)
			tail = head;
	}

	public void addToTail(T el) {
		if (!isEmpty()) {
			tail.next = new SLLNode<T>(el);
			tail = tail.next;
		} else
			head = tail = new SLLNode<T>(el);
	}

	public T deleteFromHead() { // delete the head and return its info;
		if (isEmpty())
			return null;
		T el = head.info;
		if (head == tail) // if only one node on the list;
			head = tail = null;
		else
			head = head.next;
		return el;
	}

	public T deleteFromTail() { // delete the tail and return its info;
		if (isEmpty())
			return null;
		T el = tail.info;
		if (head == tail) // if only one node in the list;
			head = tail = null;
		else { // if more than one node in the list,
			SLLNode<T> tmp; // find the predecessor of tail;
			for (tmp = head; tmp.next != tail; tmp = tmp.next)
				;
			tail = tmp; // the predecessor of tail becomes tail;
			tail.next = null;
		}
		return el;
	}

	public void delete(T el) { // delete the node with an element el;
		if (!isEmpty())
			if (head == tail && el.equals(head.info)) // if only one
				head = tail = null; // node on the list;
			else if (el.equals(head.info)) // if more than one node on the list;
				head = head.next; // and el is in the head node;
			else { // if more than one node in the list
				SLLNode<T> pred, tmp;// and el is in a nonhead node;
				for (pred = head, tmp = head.next; tmp != null
						&& !tmp.info.equals(el); pred = pred.next, tmp = tmp.next)
					;
				if (tmp != null) { // if el was found;
					pred.next = tmp.next;
					if (tmp == tail) // if el is in the last node;
						tail = pred;
				}
			}
	}

	@Override
	public String toString() {
		if (head == null)
			return "[ ]";
		String str = "[ ";
		SLLNode<T> tmp = head;
		while (tmp != null) {
			str += tmp.info + " ";
			tmp = tmp.next;
		}
		return str + "]";
	}

	public boolean contains(T el) {
		if (head == null)
			return false;
		SLLNode<T> tmp = head;
		while (tmp != null) {
			if (tmp.info.equals(el))
				return true;
			tmp = tmp.next;
		}
		return false;
	}

	public int size() {
		if (head == null)
			return 0;

		int count = 0;
		SLLNode<T> p = head;
		while (p != null) {
			count++;
			p = p.next;
		}

		return count;
	}

//  Please write the methods of Task02 here:
	public void insertBefore(int index, T newElem) throws UnsupportedOperationException {
		SLLNode<T> after = new SLLNode<T>(newElem);
		SLLNode<T> tmp = head;
		if (isEmpty() || index > size() - 1 || index < 0) {
			throw new UnsupportedOperationException();
		} else {
			if (head == tail) {
				head.next = head;
				head = after;
			} else {
				for (int i = 1; i <= index - 1; i++) {
					tmp = tmp.next;
				}
				after.next = tmp.next;
				tmp.next = after;
			}
		}
	}

	public T delete(int index) throws UnsupportedOperationException {
		SLLNode<T> prev = head;
		SLLNode<T> tmp = head.next;
		if (isEmpty() || index > size() - 1 || index < 0) {
			throw new UnsupportedOperationException();
		} else {
			for (int i = 1; i <= index - 1; i++) {
				tmp = tmp.next;
				prev = prev.next;
			}
			if (tmp != null) {
				prev.next = tmp.next;
				if (tmp == tail) {
					tail = prev;
				}
			}
		}
		return tmp.info;
	}

	public void insertAfterSecondOccurrence(T e1, T e2) throws UnsupportedOperationException {
		SLLNode<T> newNode = new SLLNode<T>(e1);
		SLLNode<T> tmp = head;
		int occur = 0;
		if (isEmpty()) {
			throw new UnsupportedOperationException();
		} else {
			for (int i = 0; i < size() - 1; i++) {
				if (tmp.info.equals(e2)) {
					occur++;
				}
				if (occur == 2) {
					newNode.next = tmp.next;
					tmp.next = newNode;
					break;
				}
				tmp = tmp.next;
			}
			if (occur < 2) {
				throw new UnsupportedOperationException();
			}
		}
	}

	public void insertSpecial(T x) {
		SLLNode<T> newNode = new SLLNode<T>(x);
		if (isEmpty() || !contains(x)) {
			addToTail(x);
			return;
		}
		SLLNode<T> tmp = head;
		while (tmp != null) {
			if (tmp.info.equals(x)) {
				newNode.next = tmp.next;
				tmp.next = newNode;
				return;
			}
			tmp = tmp.next;
		}

	}

}