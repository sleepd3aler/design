package ru.report_generation_app.currency;

public interface CurrencyConverter {
    double convert(Currency source, double sourceValue, Currency target);
}
