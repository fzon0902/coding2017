package com.coding.basic;

/**
 * 用双向链表实现LRU算法
 * @author liuxin
 *
 */
public class LRUPageFrame {
	
	private static class Node{
		
		Node prev;
		Node next;
		int pageNum;

		Node(Node prev, Node next, int pageNum) {
			this.prev = prev;
			this.next = next;
			this.pageNum = pageNum;
		}
	}

	private int capacity;
	private int size;
	
	
	private Node first;// 链表头
	private Node last;// 链表尾

	
	public LRUPageFrame(int capacity) {
		
		this.capacity = capacity;
		
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		
		if(this.first == null){
			Node node = new Node(null,null,pageNum);
			this.first = node;
			this.last = node;
			size++;
		}else{
			Node curNode = this.first;
			while(curNode != null){
				if(pageNum == curNode.pageNum){
					
					Node prev  = curNode.prev;
					Node next = curNode.next;
					
					if(curNode.equals(last)){
						return;
					}
					
					if(null == prev){
						next.prev = null;
						first = next;
//							Node lastNode = last;
						last.next = new Node(last, null, pageNum);
						last = last.next;
					}else{
						
						prev.next = next;
						next.prev = prev;
						last.next = new Node(last, null, pageNum);
						last = last.next;
						
					}
					return;
				}
			
				curNode = curNode.next;
			}
			
			if(size < capacity){
				last.next = new Node(last,null,pageNum);
				last = last.next;
				size++;	
				return;
				
			}else{
				
				Node secondNode = first.next;
				secondNode.prev = null;
				first = secondNode;
				last.next = new Node(last,null,pageNum);
				last = last.next;
	
			}
			
		}

	}

	public String toString(){
		StringBuilder buffer = new StringBuilder();
		Node node = first;
		while(node != null){
			buffer.append(node.pageNum);			
			
			node = node.next;
			if(node != null){
				buffer.append(",");
			}
		}
		buffer.reverse();
		return buffer.toString();
	}
	
}
