package application;

import java.io.Serializable;

public class SLLNode<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected T info;
	protected SLLNode<T> next;

	public SLLNode() {
		this(null, null);
	}

	public SLLNode(T el) {
		this(el, null);
	}

	public SLLNode(T el, SLLNode<T> ptr) {
		info = el;
		next = ptr;
	}
}
