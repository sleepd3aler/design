package ru.srp;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingGenerator<T> implements SequenceGenerator<T> {
    private final SequenceGenerator<T> generator;
    private static final Logger LOG = LoggerFactory.getLogger(LoggingGenerator.class.getName());

    public LoggingGenerator(SequenceGenerator<T> generator) {
        this.generator = generator;
    }

    @Override
    public List<T> generate(int size) {
        try {
            List<T> result = generator.generate(size);
            LOG.info("Sequence generated successful;");
            return result;
        } catch (IllegalArgumentException e) {
            LOG.error("Sequence size can not be zero or negative.");
            throw e;
        }
    }
}
