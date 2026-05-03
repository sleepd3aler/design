package ru.isp;

public interface Artist {

    void perform();

    interface Tuner {
        void setTool();
    }

    interface Painter {
        void draw();
    }

    interface SoundEngineer {
        void checkSound();
    }
}
