package com.freeorg.java21;

public class BCLay {

    public static void main(String[] args) {
        char[] directions = new char[]{'S', 'R', 'S'};
        int flips = 1;
        BCLay bc = new BCLay();
        System.out.println("Ans - " + bc.solve(directions, flips));
    }

    // Calculate Max Distance
    int solve(char[] directions, int flips) {
        return Math.max(distanceUsing('S', directions, flips), distanceUsing('R', directions, flips));
    }

    int distanceUsing(char direction, char[] directions, int flips) {
        int flipsUsed = 0;
        int distance = 0;
        for(int i=0; i < directions.length; i++){
            if (direction != directions[i]){
                if(flipsUsed < flips){
                    ++flipsUsed;
                }
                else {
                    return i;
                }
            }
        }

        if(flipsUsed < flips){
            if (1 == (flips - flipsUsed) % 2){
                return directions.length - 1;
            }
            else
                return directions.length;
        }

        return directions.length;
    }
}
