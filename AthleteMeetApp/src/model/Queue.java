package model;

import model.Queue;

public class Queue<E>
{
	private class Node<E>
	{
		private E element;
		private Node<E> node;
		
		/**
		 * Basic Constructor
		 * @param el
		 * @param node
		 */
		public Node(E element, Node<E> node) 
		{
			this.element = element;
			this.node = node;
		}

		/**
		 * sets reference to Next Node
		 * @param node
		 */
		public void setNext(Node<E> node) 
		{
			this.node = node;
		}
		
		/**
		 * returns the reference to next Node
		 * @return Node
		 */
		public Node<E> getNext()
		{
			return node;
		}
		
		/**
		 * 
		 * @return E
		 */
		public E getElement() 
		{
			return element;
		}
	}

	private Node<E> head;
	private Node<E> tail;
	int size = 0;
	
	public Queue() 
	{
		head = null;
		tail = null;
	}
	
	public Queue(Node<E> head, Node<E> tail) 
	{
		this.head = head;
		this.tail = tail;
	}

	public E first() 
	{
		if(!isEmpty()) 
		{
			return head.getElement();
		}
		return null;
	}
	
	public void enqueue(E e) 
	{
		Node<E> newNode = new Node<>(e, null);
		
		if(isEmpty()) 
		{
			head = newNode;
			tail = head;
			size++;
			return;
		}
		
		tail.setNext(newNode);
		tail = newNode;

		size++;

	}
	
	public E dequeue() 
	{
		if(isEmpty())
		{
			return null;
		}
		
		Node<E> currentNode = head.getNext();
		E elem = head.getElement();
		head = null;
		head = currentNode;
		currentNode = null;
		size--;
		return elem;
	}
	
	public int size() 
	{
		return size;
	}
	
	private boolean isEmpty() 
	{
		if(size == 0) 
		{
			return true;
		}
		return false;
	}
}
