package org.machine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        String s1="",s2="";
        Scanner input = new Scanner(System.in);
        s1 = input.nextLine();
        s2= input.nextLine();
        System.out.println(StringSummer.sum(s1,s2));
        //Printer.work();
    }
}
