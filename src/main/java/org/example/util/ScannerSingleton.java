package org.example.util;

import java.util.Scanner;

public class ScannerSingleton {

    static Scanner scanner;

    public ScannerSingleton() {
        scanner= new Scanner(System.in);
    }

    public static synchronized Scanner getSessionFactory (){
        if(scanner == null){
            new ScannerSingleton();
        }
        return scanner;
    }
}
