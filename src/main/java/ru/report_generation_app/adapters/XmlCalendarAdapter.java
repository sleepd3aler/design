package ru.report_generation_app.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class XmlCalendarAdapter extends XmlAdapter<String, Calendar> {
    private String format;

    public XmlCalendarAdapter() {
    }

    public XmlCalendarAdapter(String format) {
        this.format = format;
    }

    @Override
    public Calendar unmarshal(String string) throws Exception {
        Calendar dateTime = new GregorianCalendar();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        dateTime.setTime(formatter.parse(string));
        return dateTime;
    }

    @Override
    public String marshal(Calendar calendar) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(calendar.getTime());
    }
}