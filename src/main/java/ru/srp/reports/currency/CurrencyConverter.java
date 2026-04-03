package ru.srp.reports.currency;

public interface CurrencyConverter {
    double convert(Currency source, double sourceValue, Currency target);
}
