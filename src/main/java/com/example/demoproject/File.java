package com.example.demoproject;

public class File implements Component {

    private final String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String print(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("  ");
        }
        return sb + fileName + "\n";
    }
}