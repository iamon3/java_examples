package com.freeorg.java6.misc;

public class PolyMorphism {

	private GrandParent grandParent;
	private Parent parent = new Child();
	private int[] primitivesArr =  new int[4];
	StringBuilder sb = null;
	private Parent[] parentsArr = {} ; 
	
	{
		System.out.println(primitivesArr[2]);
		// Downcasting
		Child child = (Child) parent;
		// Upcasting
		grandParent = parent;
		int[] arr1 = new int[5];
		arr1 = new int[7];
		
	}

	public static void main(String[] args) {
		PolyMorphism polyMorphism = new PolyMorphism();
		polyMorphism.parent.aMethod();
		if (polyMorphism.parent instanceof GrandParent) {
			System.out.println("Grandparent isntance.");
		}
		System.out.println(polyMorphism.parentsArr.length);
	}

	private class Child extends Parent {
		@Override
		public void aMethod() {
			System.out.println("In Child.");
		}
	}
}

class Parent extends GrandParent{
	protected static void aStaticMethod() {

	}
	public void aMethod() {
		System.out.println("In Parent.");
	}
}

class GrandParent {
	protected static void aStaticMethod() {

	}
}