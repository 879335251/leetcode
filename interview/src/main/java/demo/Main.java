package demo;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Hello world!");
    }
}