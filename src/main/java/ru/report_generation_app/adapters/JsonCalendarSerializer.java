package ru.report_generation_app.adapters;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JsonCalendarSerializer implements JsonSerializer<Calendar> {
    private final SimpleDateFormat formatter;

    public JsonCalendarSerializer(String dateFormat) {
        this.formatter = new SimpleDateFormat(dateFormat);
    }

    @Override
    public JsonElement serialize(Calendar calendar, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(formatter.format(calendar.getTime()));
    }
}
