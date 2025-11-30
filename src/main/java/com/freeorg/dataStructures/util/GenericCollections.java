package com.freeorg.dataStructures.util;

import com.freeorg.dataStructures.linkedList.myOwn.GenericLinkedList;
import com.freeorg.dataStructures.linkedList.myOwn.GenericNode;
import com.freeorg.dataStructures.linkedList.myOwn.Student;

public class GenericCollections {

	public static <E> GenericLinkedList<E> reverseGenricLinkedList(GenericLinkedList<E> genericLinkedListToBeReversed){
        Student s1 = new Student(11,"Stud1");
		//genericLinkedListToBeReversed.add(s1);
//        error: incompatible types: Student cannot be converted to E
//        genericLinkedListToBeReversed.add(s1);
//		                                  ^
//        where E is a type-variable:
//        E extends Object declared in method <E>reverseGenricLinkedList(GenericLinkedList<E>)
		genericLinkedListToBeReversed.setTail(genericLinkedListToBeReversed.getHead());
		genericLinkedListToBeReversed.setHead(reverseList(genericLinkedListToBeReversed.getHead()));
		return genericLinkedListToBeReversed;
	}		

	private static <E> GenericNode<E> reverseList(GenericNode<E>  head) {
		if(head == null || head.getNextNode() == null){
		 return head;	
		}
		GenericNode<E> reverseLLHead = reverseList(head.getNextNode());
		head.getNextNode().setNextNode(head);
		head.setNextNode(null);
		return reverseLLHead;
	}
	
	public static GenericLinkedList reverseGenricLinkedList2(GenericLinkedList genericLinkedListToBeReversed){
		Student s1 = new Student(11,"Stud1");
		genericLinkedListToBeReversed.add(s1);
		genericLinkedListToBeReversed.setTail(genericLinkedListToBeReversed.getHead());
		genericLinkedListToBeReversed.setHead(reverseList(genericLinkedListToBeReversed.getHead()));
		return genericLinkedListToBeReversed;
	}		

}
