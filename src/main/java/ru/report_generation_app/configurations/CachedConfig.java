package ru.report_generation_app.configurations;

import static ru.report_generation_app.constants.Constants.QUERY_INTERVAL;

public class CachedConfig implements Config, AutoCloseable {
    private String format;
    private final Config config;
    private long lastUpdate;

    public CachedConfig(Config config) {
        this.config = config;
    }

    @Override
    public String get(String dateFormat) {
        if (lastUpdate == 0 || System.currentTimeMillis() - lastUpdate > QUERY_INTERVAL) {
            lastUpdate = System.currentTimeMillis();
            format = config.get(dateFormat);
            return format;
        }
        return format;
    }

    @Override
    public void close() throws Exception {
        config.close();
    }
}
