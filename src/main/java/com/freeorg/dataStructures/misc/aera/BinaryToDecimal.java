package com.freeorg.dataStructures.misc.aera;

public class BinaryToDecimal {

	public static long getNumber(SinglyLinkedListNode binary) {

		SinglyLinkedListNode reverseHead = reverse(binary);
		return toDecimalNumber(reverseHead);
	}

	private static long toDecimalNumber(SinglyLinkedListNode reverseHead) {
		long sum = 0;
		int i = 0;
		SinglyLinkedListNode current = reverseHead;
		while(null != current) {
			sum += (current.data*Math.pow(2, i));
			current = current.next;
			i++;
		}
		return sum;
	}

	private static SinglyLinkedListNode reverse(SinglyLinkedListNode node) 
	{ 
		SinglyLinkedListNode prev = null; 
		SinglyLinkedListNode current = node; 
		SinglyLinkedListNode next = null; 
		while (current != null) { 
			next = current.next; 
			current.next = prev; 
			prev = current; 
			current = next; 
		} 
		node = prev; 
		return node; 
	} 

	private static void printList(SinglyLinkedListNode node) 
    { 
        while (node != null) { 
            System.out.print(node.data + " "); 
            node = node.next; 
        } 
    }
	public static void main(String[] args) {		

	}

}

class SinglyLinkedListNode {
	int data;
	SinglyLinkedListNode next;
}