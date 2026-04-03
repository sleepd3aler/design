package ru.srp;

import java.util.Formatter;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        var numberGenerator = new SimpleNumberGenerator(new Random(), 10);
        var generator = new SimpleSequenceGenerator(numberGenerator);
        var validateGenerator = new ValidateGenerator<>(generator);
        var loggingGenerator = new LoggingGenerator<>(validateGenerator);
        SequenceFormatter<Integer> formatter = new SimpleSequenceFormatter(new Formatter());
        Output output = new SequenceOutput();
        List<Integer> sequence = loggingGenerator.generate(2);
        String formatedResult = formatter.format(sequence);
        output.printMessage(formatedResult);
    }
}
