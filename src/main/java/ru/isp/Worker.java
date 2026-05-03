package ru.isp;

public interface Worker {
    void work();

    interface Living {
        void eat();
    }

    class Robot implements Worker {
        @Override
        public void work() {

        }
    }

    class Human implements Worker, Living {
        @Override
        public void work() {

        }

        @Override
        public void eat() {

        }
    }

    class Dog implements Living {
        @Override
        public void eat() {

        }
    }
}
