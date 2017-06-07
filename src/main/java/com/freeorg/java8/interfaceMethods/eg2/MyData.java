package com.freeorg.java8.interfaceMethods.eg2;

public interface MyData {

	/**
	 	1. Java interface default methods will help us in extending interfaces without having the fear of breaking implementation classes.
		2. Java interface default methods has bridge down the differences between interfaces and abstract classes.
		3. Java 8 interface default methods will help us in avoiding utility classes, such as all the Collections class method can be provided in the interfaces itself.
		4. Java interface default methods will help us in removing base implementation classes, 
		   we can provide default implementation and the implementation classes can chose which one to override.
		5. One of the major reason for introducing default methods in interfaces is to enhance the Collections API in Java 8 to support lambda expressions.
		6. If any class in the hierarchy has a method with same signature, then default methods become irrelevant. 
		7. A default method cannot override a method from java.lang.Object. The reasoning is very simple, it’s because Object is the base class for all the java classes. 
		   So even if we have Object class methods defined as default methods in interfaces, it will be useless because Object class method will always be used. 
		   That’s why to avoid confusion, we can’t have default methods that are overriding Object class methods.
		8. Java interface default methods are also referred to as Defender Methods or Virtual extension methods.
	 */
	default void print(String str) {
		if (!isNull(str))
			System.out.println("MyData Print::" + str);
	}

	/**
	 	1. Java interface static method is part of interface, we can’t use it for implementation class objects.
		2. Java interface static methods are good for providing utility methods, for example null check, collection sorting etc.
		3. Java interface static method helps us in providing security by not allowing implementation classes to override them.
		4. We can’t define interface static method for Object class methods, 
		   we will get compiler error as “This static method cannot hide the instance method from Object”. 
		   This is because it’s not allowed in java, since Object is the base class for all the classes 
		   and we can’t have one class level static method and another instance method with same signature.
		5. We can use java interface static methods to remove utility classes such as Collections 
		   and move all of it’s static methods to the corresponding interface, that would be easy to find and use.
	 */
	static boolean isNull(String str) {
		System.out.println("Interface Null Check");

		return str == null ? true : "".equals(str) ? true : false;
	}
}