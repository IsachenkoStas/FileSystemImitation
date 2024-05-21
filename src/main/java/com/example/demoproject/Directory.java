package com.example.demoproject;

import java.util.ArrayList;
import java.util.List;


public class Directory implements Component {

    String dirName;

    List<Component> children;

    public List<Component> getChildren() {
        return children;
    }

    public String getDirName() {
        return dirName;
    }

    public Directory(String dirName) {
        this.dirName = dirName;
        this.children = new ArrayList<>();
    }

    public void add(Component component) {
        children.add(component);
    }

    @Override
    public String print(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("  ");
        }
        sb.append(dirName).append("\n");
        for (Component component : children) {
            sb.append(component.print(depth + 1));
        }
        return sb.toString();
    }
}