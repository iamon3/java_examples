package com.freeorg.java6.collections.set;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class WordCountSorter {

	public static void main(String[] args) {		
		String[] inputParagraph = new String[] {"a","b","c","a","d","e","c","a","f"};


		Comparator<WordCount> sortByCountComparator = (o1,o2) -> {
			if(o1.count == o2.count)
				return 0;
			else if(o1.count > o2.count) {
				return -1;
			}
			return 1;
		};

		final Set<WordCount> sortedWordCountsSet = new TreeSet<>(sortByCountComparator);

		Arrays.stream(inputParagraph)
		.forEach(word -> {
			WordCount newWordCount = new WordCount(word);
			if(sortedWordCountsSet.contains(newWordCount)) {
				for(WordCount wc : sortedWordCountsSet) {
					if(wc.word.equals(word)) {
						wc.count += wc.count;
						return;
					}
				}

			}
			else {
				sortedWordCountsSet.add(newWordCount);
			}
		});

		sortedWordCountsSet
		.stream()
		.forEach(System.out::println);

		/*
		 * Iterator<WordCount> wcItr = sortedWordCountsSet.iterator();
		 * while(wcItr.hasNext()){ System.out.println(wcItr.next().toString()); }
		 */
	}
}

class WordCount{
	String word;
	int count;

	public WordCount(String w) {
		this.word = w;
	}

	@Override
	public int hashCode() {
		return word.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(null != obj) {
			if(obj instanceof WordCount) {				
				this.word.equals(((WordCount) obj).word);
			}
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {	
		return word + " : "+count;
	}
}
