package com.freeorg.java7.tryWithResources;

/**
	Refer : https://docs.oracle.com/javase/7/docs/technotes/guides/language/try-with-resources.html
	
	The try-with-resources statement is a try statement that declares one or more resources. 
	A resource is as an object that must be closed after the program is finished with it. 
	The try-with-resources statement ensures that each resource is closed at the end of the statement. 
	Any object that implements java.lang.AutoCloseable, which includes all objects which implement java.io.Closeable, can be used as a resource.
 */
public class TestTryWithMyResource {

	public static void main(String[] args) {
		try(MyResource1 r1 = new MyResource1(); MyResource2 r2 = new MyResource2();){
			r1.mtd1();
			r2.mtd2();
			int zero = 0;
			int y = 7/zero;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// No Need to write finally block, since close() of Autocloseable resources will be called by JVM automatically.
	}

}

class MyResource1 implements AutoCloseable{

	@Override
	public void close() throws Exception {
		System.out.println("Finally, JVM automatically called MyResource1 in finally block.");
	}

	public void mtd1() {
	}
}

class MyResource2 implements AutoCloseable{

	@Override
	public void close() throws Exception {
		System.out.println("Finally, JVM automatically called MyResource2 in finally block.");
	}

	public void mtd2() {		
	}
}