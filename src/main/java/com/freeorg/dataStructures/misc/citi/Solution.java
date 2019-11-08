package com.freeorg.dataStructures.misc.citi;

import java.util.HashSet;
import java.util.Set;

public class Solution {

	public String solution(String S) {
		
		Set<String> musicF = new HashSet<>();
		musicF.add("mp3");
		musicF.add("aac");
		musicF.add("flac");
		
		Set<String> imgF = new HashSet<>();
		imgF.add("jpg");
		imgF.add("bmp");
		imgF.add("gif");
		
		Set<String> movieF = new HashSet<>();
		movieF.add("mp4");
		movieF.add("avi");
		movieF.add("mkv");
		
		int music = 0, images = 0, movies = 0, other = 0;
		
		for(String line : S.split("\n")) {
			String file = line.split(" ")[0];
			String sizeString = line.split(" ")[1];
			int size = Integer.parseInt(sizeString.substring(0, sizeString.length()-1));
			String extension = file.substring(1+file.lastIndexOf('.'));	
			if(musicF.contains(extension)) {
				music += size;
			}
			else if(imgF.contains(extension)) {
				images += size;
			}
			else if(movieF.contains(extension)) {
				movies += size;
			}
			else {
				other += size;
			}					
		}
		
		String result = "music %sb\nimages %sb\nmovies %sb\nother %sb";
        return String.format(result, music,images,movies,other);
    }
	
	
	int solution2(int M, int[] A) {
        int N = A.length;
        int[] count = new int[M + 1];
        for (int i = 0; i <= M; i++)
            count[i] = 0;
        int maxOccurence = 1;
        int index = -1;
        for (int i = 0; i < N; i++) {
            if (count[A[i]] > 0) {
                int tmp = count[A[i]];
                if (tmp > maxOccurence) {
                    maxOccurence = tmp;
                    index = i;
                }
                count[A[i]] = tmp + 1;
            } else {
                count[A[i]] = 1;
            }
        }
        return A[index];
    }
	
	public static void main(String[] args) {
		String s = "my.song.mp3 11b\ngreatSong.flac 1000b\nnot3.txt 5b\nvideo.mp4 200b\ngame.exe 100b\nmov!e.mkv 10000b";
		System.out.println(new Solution().solution(s));
	}
}
