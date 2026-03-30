package ru.ood.srp;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        NumberGenerator<Integer> numberGenerator = new SimpleNumberGenerator();
        SequenceGenerator<Integer> generator = new SimpleSequenceGenerator(numberGenerator);
        SequenceFormatter<Integer> formatter = new SimpleSequenceFormatter();
        Output output = new SequenceOutput();
        List<Integer> sequence = generator.generate(100);
        String formatedResult = formatter.format(sequence);
        output.printMessage(formatedResult);
    }
}
