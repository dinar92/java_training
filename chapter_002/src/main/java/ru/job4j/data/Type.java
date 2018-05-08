package ru.job4j.data;


public enum Type {

    BUG {
        @Override
        public String toString() {
            return "BUG";
        }
    },
    TASK {
        @Override
        public String toString() {
            return "TASK";
        }
    }
}
