package ru.srp.reports.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class XmlCalendarAdapter extends XmlAdapter<String, Calendar> {
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    private final Calendar dateTime = new GregorianCalendar();

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
