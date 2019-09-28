package com.freeorg.dataStructures.misc.cc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Stream;

public class ParseHttpLogs {

	public static void main(String[] args) {	

		HashMap<String, Integer> pathCountsMap = new HashMap<>();
		LongAdder logsCount = new LongAdder();

		try (Stream<String> logsStream = Files.lines(Paths.get("elb.log"))){
			
			// 1. Parse Log file			
			//String badString = "1.1/\" /\"-/\" - -"; 			
			logsStream.forEach(log -> {
				logsCount.increment();				
				String relativePath = log.split(".json")[0];
				String resultPath = relativePath.substring(relativePath.lastIndexOf("/")+1);				
				if(pathCountsMap.containsKey(resultPath))
				{
					pathCountsMap.put(resultPath, pathCountsMap.get(resultPath)+1);
				}
				else {
					pathCountsMap.put(resultPath, 1);
				}
			});
		}
		catch (Exception e) {		
			e.printStackTrace();
		}
		
		// 2. Sort result and Display output			 
		System.out.println("Total Logs : " + logsCount.doubleValue());
		String output = "%s : count = %d  percent = %f ";

		pathCountsMap
		.entrySet()
		.stream()			
		.sorted((o1,o2) -> o2.getValue().compareTo(o1.getValue()))
		.forEachOrdered(entry -> System.out.println(String.format(output, 
				entry.getKey(), 
				entry.getValue(), 
				(entry.getValue()/logsCount.doubleValue())*100)));
	}	
}
