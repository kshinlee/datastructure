/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package namoosori.datastructure.list.array;

import java.util.Arrays;

import namoosori.datastructure.iterator.MyStringIterator;
import namoosori.datastructure.iterator.logic.MyStringIteratorLogic;
import namoosori.datastructure.list.facade.MyStringList;

public class MyStringArrayList implements MyStringList  {
	//
	private static final int INITIAL_CAPACITY = 10; 

	private int length; 
	private int capacity; 
	private String[] elements; 
	
	public MyStringArrayList() {
		// 
		this.clear(); 
	}

	public MyStringArrayList(int capacity) {
		// 
		this.clear(capacity); 
	}

	@Override
	public String toString() {
		// 
		StringBuilder builder = new StringBuilder(); 
		
		builder.append("length:").append(length); 
		builder.append(", capacity:").append(capacity);
		builder.append(", elements:").append(elements); 
		
		return builder.toString(); 
	}
	
	@Override
	public int size() {
		// 
		return length;
	}

	@Override
	public boolean empty() {
		//
		if (length == 0) {
			return true; 
		}
		
		return false;
	}

	@Override
	public boolean contains(String o) {
		// 
		boolean contained = false; 
		
		for(int index = 0; index<length; index++) {
			// 
			if (elements[index].equals(o)) {
				contained = true; 
				break; 
			}
		}
		
		return contained;
	}

	@Override
	public MyStringIterator iterator() {
		// 
		return new MyStringIteratorLogic(toArray()); 
	}

	@Override
	public void add(String e) {
		// 
		if(length == capacity) {
			increaseCapacity(); 
		}
		this.elements[length] = e;
		this.length++; 
	}

	@Override
	public void add(int index, String element) {
		// 
		if (index != length) {
			this.checkArrayIndex(index);
		}
		
		if(length == capacity) {
			increaseCapacity(); 
		}
		
		if(length > 0) {
			shiftRightFrom(index);
		}

		elements[index] = element; 
		length++; 
	}
	
	@Override
	public String get(int index) {
		// 
		checkArrayIndex(index);
		
		return elements[index]; 
	}

	@Override
	public void remove(String element) {
		// 
		int index = -1; 
		for(int i=0; i<length; i++) {
			// 
			if(elements[i].equals(element)) {
				index = i; 
				break; 
			}
		}

		if(index > -1) {
			remove(index); 
		}
	}

	@Override
	public void remove(int index) {
		// 
		elements[index] = null; 
		shiftLeftTo(index); 
		length--; 
	}

	@Override
	public void addAll(MyStringList sourceList) {
		//
		int targetCapacity = length + sourceList.size(); 
		if (targetCapacity > capacity) {
			increaseCapacity(targetCapacity);  
		}

		int sourceLength = sourceList.size(); 
		for(int i=0; i<sourceLength; i++) {
			elements[length++] = sourceList.get(i);  
		}
	}

	@Override
	public void clear() {
		//
		clear(INITIAL_CAPACITY); 
	}
	
	private void clear(int capacity) {
		//
		this.length = 0; 
		this.capacity = capacity; 
		this.elements = new String[capacity];
	}

	@Override
	public String[] toArray() {
		// 
		return Arrays.copyOf(elements, length); 
	}
	
	private void increaseCapacity() {
		// 
		increaseCapacity(capacity+INITIAL_CAPACITY);
	}
	
	private void increaseCapacity(int targetCapacity) {
		// 
		while(targetCapacity < capacity) {
			capacity += INITIAL_CAPACITY; 
		}
		
		String[] newElements = new String[capacity];
		System.arraycopy(elements, 0, newElements, 0, length); 
		elements = newElements; 
	}

	private void shiftRightFrom(int index) {
		//
		for(int i=length; i>index; i--) {
			// 
			elements[i] = elements[i-1]; 
		}
	}
	
	private void shiftLeftTo(int index) {
		// 
		for(int i=index; i<length; i++) {
			// 
			elements[i] = elements[i+1]; 
		}
	}
	
	private void checkArrayIndex(int index) {
		// 
		if(index < 0 || index >= length) {
			throw new IndexOutOfBoundsException("index:" + index); 
		}
	}
}