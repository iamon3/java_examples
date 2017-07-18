package com.freeorg.java7.typeInference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Before java 7, while using generics you had to supply type parameters to variables types and to their actual types. 
 * Now, it has been relieved a bit in this new java 7 feature, and a blank diamond on right side of declaration will work fine.
 * Compiler is smart enough in java 7 to identify that blank diamond infer to type defined on left hand side of declaration.
 */
public class TestDiamondOperator {

	public static void main(String[] args) {
		 List<String> list = new ArrayList<>();
		 Map<String,String> map = new HashMap<>();
	}
}
