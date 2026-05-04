package ru.dip.order_service.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class FileReportRepository {
    private int ids;
    private File file;

    public FileReportRepository(File file) {
        this.file = file;
    }

    public int saveOrder(String customer, double amount) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
            String ln = System.lineSeparator();
            writer.printf("Order #%d: %s, %.2f%s", ids++, customer, amount, ln);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ids;
    }
}
