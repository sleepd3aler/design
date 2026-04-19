package ru.report_generation_app.configurations;

public class CachedConfig implements Config, AutoCloseable {
    private String cachedFormat;
    private final Config config;
    private long lastUpdate;

    public CachedConfig(SqlConfig config) {
        this.config = config;
    }

    public int getInterval(String interval) {
        return config.getInterval(interval);
    }

    @Override
    public String get(String dateFormat) {
        if (lastUpdate == 0) {
            lastUpdate = System.currentTimeMillis();
            cachedFormat = config.get(dateFormat);
            return cachedFormat;
        }
        return cachedFormat;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public void close() throws Exception {
        config.close();
    }

}
