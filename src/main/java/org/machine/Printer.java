package org.machine;

import java.util.Scanner;

public class Printer {
    public static void work(){
        Scanner input = new Scanner(System.in);

        int counter = input.nextInt();

        for(int i = 0; i < counter; i++){
            System.out.print((char)i);

            if(i > 1 && i % 10 == 0)
                System.out.print("\n");
        }
    }
}
