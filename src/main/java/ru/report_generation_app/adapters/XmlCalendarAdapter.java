package ru.report_generation_app.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class XmlCalendarAdapter extends XmlAdapter<String, Calendar> {
    private SimpleDateFormat formatter = new SimpleDateFormat();
    private final Calendar dateTime = new GregorianCalendar();

    public XmlCalendarAdapter() {
    }

    public XmlCalendarAdapter(String format) {
        formatter = new SimpleDateFormat(format);
    }

    @Override
    public Calendar unmarshal(String string) throws Exception {
        dateTime.setTime(formatter.parse(string));
        return dateTime;
    }

    @Override
    public String marshal(Calendar calendar) throws Exception {
        return formatter.format(calendar.getTime());
    }
}
