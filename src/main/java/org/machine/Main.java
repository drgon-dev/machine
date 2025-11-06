package org.machine;

import javax.swing.*;

public class Main {
    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimpleCalculator().setVisible(true);
            }
        });



        //String s1="",s2="";
        //Scanner input = new Scanner(System.in);
        //s1 = input.nextLine();
        //s2= input.nextLine();
        //System.out.println(StringSummer.sum(s1,s2));
        //Printer.work();
    }
}
