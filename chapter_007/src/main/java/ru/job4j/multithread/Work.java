package ru.job4j.multithread;

public class Work {
    private String s;

    public Work(String s) {
        this.s = s;
    }

    public void doSomething() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(s);
        }
    }
}
