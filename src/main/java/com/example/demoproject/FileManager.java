package com.example.demoproject;

import java.util.ArrayList;
import java.util.List;

public class FileManager {

    protected static final List<Component> ROOTS = new ArrayList<>();

    public static void addPath(String path) {
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

    public static Directory findOrCreateDirectory(Directory parent, String name) {
        for (Component component : parent.getChildren()) {
            if (component instanceof Directory directory && directory.getDirName().equals(name)) {
                return directory;
            }
        }
        Directory newDir = new Directory(name);
        parent.add(newDir);
        return newDir;
    }

    public static Directory findOrCreateRoot(String name) {
        for (Component component : ROOTS) {
            if (component instanceof Directory directory && directory.getDirName().equals(name)) {
                return directory;
            }
        }
        Directory newRoot = new Directory(name);
        ROOTS.add(newRoot);
        return newRoot;
    }


    public static void printFileSystem() {
        StringBuilder sb = new StringBuilder();
        for (Component root : ROOTS) {
            sb.append(root.print(0));
        }
        System.out.println(sb);
    }
}