package ru.ood.srp;

import java.util.Formatter;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        NumberGenerator<Integer> numberGenerator = new SimpleNumberGenerator(new Random(), 100);
        SequenceGenerator<Integer> generator = new SimpleSequenceGenerator(numberGenerator);
        SequenceFormatter<Integer> formatter = new SimpleSequenceFormatter(new Formatter());
        Output output = new SequenceOutput();
        List<Integer> sequence = generator.generate(100);
        String formatedResult = formatter.format(sequence);
        output.printMessage(formatedResult);
    }
}
