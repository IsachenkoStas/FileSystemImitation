package com.example.demoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class DemoProjectApplication {

    private static final List<Component> roots = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(DemoProjectApplication.class, args);

        Directory root = new Directory("");
        roots.add(root);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("exit")) {
            System.out.println("Enter a file system path or 'print':");
            input = scanner.nextLine();
            if (input.equals("print")) {
                printFileSystem();
            } else {
                try {
                    addPath(input);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid path format. Please try again.");
                }
            }
        }
    }

    private static void addPath(String path) {
        String[] components = path.split("/");
        if (components.length == 1) {
            System.out.println("Wrong input");
        }
        for (int i = 0; i < components.length - 1; i++) {
            if (components[i].contains(".")) {
                System.out.println("Wrong input. Try again.");
            }
        }
        Directory currentDirectory = null;
        for (int i = 0; i < components.length; i++) {
            if (components[i].isEmpty()) {
                continue;
            }
            if (i == components.length - 1) {
                if (currentDirectory != null) {
                    if (components[i].contains(".")) {
                        currentDirectory.add(new File(components[i]));
                    } else {
                        currentDirectory = findOrCreateDirectory(currentDirectory, components[i]);
                    }
                }
            } else {
                if (currentDirectory == null) {
                    currentDirectory = findOrCreateRoot(components[i]);
                } else {
                    currentDirectory = findOrCreateDirectory(currentDirectory, components[i]);
                }
            }
        }
    }

    private static Directory findOrCreateDirectory(Directory parent, String name) {
        for (Component component : parent.getChildren()) {
            if (component instanceof Directory directory && directory.getDirName().equals(name)) {
                return directory;
            }
        }
        Directory newDir = new Directory(name);
        parent.add(newDir);
        return newDir;
    }

    private static Directory findOrCreateRoot(String name) {
        for (Component component : roots) {
            if (component instanceof Directory directory && directory.getDirName().equals(name)) {
                return directory;
            }
        }
        Directory newRoot = new Directory(name);
        roots.add(newRoot);
        return newRoot;
    }


    private static void printFileSystem() {
        StringBuilder sb = new StringBuilder();
        for (Component root : roots) {
            sb.append(root.print(0));
        }
        System.out.println(sb);
    }
}