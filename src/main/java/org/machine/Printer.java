package org.machine;

public class Printer {
    public static String work(int input){
        String result = "";

        for(int i = 0; i < input; i++){
            result += (char)i;

            if(i > 1 && i % 10 == 0)
                result += "\n";
        }

        return result;
    }
}
