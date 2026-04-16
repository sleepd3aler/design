package ru.report_generation_app.currency;

public class InMemoryCurrencyConverter implements CurrencyConverter {
    private static final int CURRENCIES_COUNT = Currency.values().length;
    private final double[][] conversationTable = new double[CURRENCIES_COUNT][CURRENCIES_COUNT];

    public InMemoryCurrencyConverter() {
        this.conversationTable[Currency.RUB.ordinal()][Currency.USD.ordinal()] = 0.0123;
        this.conversationTable[Currency.RUB.ordinal()][Currency.EUR.ordinal()] = 0.0107;
        this.conversationTable[Currency.USD.ordinal()][Currency.EUR.ordinal()] = 0.8700;
        this.conversationTable[Currency.USD.ordinal()][Currency.RUB.ordinal()] = 81.3D;
        this.conversationTable[Currency.EUR.ordinal()][Currency.USD.ordinal()] = 1.1500;
        this.conversationTable[Currency.EUR.ordinal()][Currency.RUB.ordinal()] = 93.4D;

    }

    @Override
    public double convert(Currency source, double sourceValue, Currency target) {
        return sourceValue * conversationTable[source.ordinal()][target.ordinal()];
    }
}
