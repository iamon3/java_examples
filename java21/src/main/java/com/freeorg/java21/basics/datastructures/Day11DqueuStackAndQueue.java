package com.freeorg.java21.basics.datastructures;

import java.util.*;

public class Day11DqueuStackAndQueue {

    public static void main(String[] args) {
        Day11DqueuStackAndQueue collectUtil = new Day11DqueuStackAndQueue();
        collectUtil.understandDequeAsStackAndQueue();
    }

    void understandDequeAsStackAndQueue() {
        System.out.println("============= Stack Operations push(), pop(), peek() ============");
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        System.out.println("push() :" + stack);
        stack.push(2);
        System.out.println("push() :" + stack);
        stack.addFirst(3);
        System.out.println("addFirst() :" + stack);
        stack.addLast(4);
        System.out.println("addLast() :" + stack);
        stack.add(5);
        System.out.println("add() :" + stack);

        int poppedElement = stack.pop();
        System.out.println("pop() : " + poppedElement + " from : " + stack);
        int peekedElement = stack.peek();
        System.out.println("peek() : " + peekedElement + " from : " + stack);

        System.out.println("============= Queue Operations offer(), poll(), peek() ============");
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        System.out.println("offer() at tail :" + queue);
        queue.offer(2);
        System.out.println("offer() at tail :" + queue);
        queue.add(3);
        System.out.println("add() at tail :" + queue);
        peekedElement = queue.peek();
        System.out.println("peek() : " + peekedElement + " head from : " + queue);
        int polledElement = queue.poll();
        System.out.println("poll() : " + polledElement + " head from : " + queue);
    }

    boolean isBalancedParens(String s) {

        Set<Character> openingBrackets = Set.of('{', '(', '[');
        Set<Character> closingBrackets = Set.of('}', ')', ']');
        Map<Character, Character> bracketsMap = Map.of(
                '{', '}',
                '[', ']',
                '(', ')',
                '}', '{',
                ')', '(',
                ']', '['
        );

        Deque<Character> stack = new ArrayDeque<>();
        for (Character c : s.toCharArray()) {
            if (openingBrackets.contains(c)) {
                stack.push(c);
            } else if (closingBrackets.contains(c)) {
                if (stack.isEmpty() || !stack.pop().equals(bracketsMap.get(c)))
                    return false;
            }
        }
        return stack.isEmpty();
    }

    List<Integer> reverseUsingStack(List<Integer> list) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (Integer i : list) {
            stack.push(i);
        }

        List<Integer> reversedList = new ArrayList<>();
        while (!stack.isEmpty()) {
            reversedList.add(stack.pop());
        }
        return reversedList;
    }

    void slidingWindowMaxSetup(int[] arr) {
        Deque<Integer> window = new ArrayDeque<>();

        for (int e : arr) {
            window.addFirst(e);   // grows from the front
        }

        System.out.println("Front: " + window.peekFirst());
        System.out.println("Back: " + window.peekLast());

        while (!window.isEmpty()) {
            window.addLast(window.removeFirst()); // demonstrate moving front→back
        }
    }

    Integer safePeekEmptyStack() {
        Deque<Integer> dq = new ArrayDeque<>();

        try {
            dq.pop();
        } catch (NoSuchElementException nse) {
            System.out.println("pop() threw on empty deque");
        }

        Integer peeked = dq.peek(); // null, no exception
        System.out.println("peek() returned: " + peeked);

        try {
            dq.element();
        } catch (NoSuchElementException nse) {
            System.out.println("element() threw on empty deque");
        }

        Integer polled = dq.poll(); // null, no exception
        System.out.println("poll() returned: " + polled);

        return peeked; // returning something — the point was exploring behavior, this satisfies the signature
    }
}
