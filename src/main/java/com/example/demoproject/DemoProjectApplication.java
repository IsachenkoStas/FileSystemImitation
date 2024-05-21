package com.example.demoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DemoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProjectApplication.class, args);

        Directory root = new Directory("");
        FileManager.ROOTS.add(root);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Start or exit: ");
        String input = scanner.nextLine();
        while (!input.equals("exit")) {
            System.out.println("Enter a file system path or 'print':");
            input = scanner.nextLine();
            if (input.equals("print")) {
                FileManager.printFileSystem();
            } else {
                try {
                    FileManager.addPath(input);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid path format. Please try again.");
                }
            }
        }
    }
}