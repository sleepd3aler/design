package ru.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws JAXBException {
        final Person person = new Person(
                false, 30, new Contact("11-111"), new String[]{"Worker", "Married"}
        );

        final Device device = new Device(
                false, 18000, "MacBook Air 2020(Intel i3)", new Seller("89783358791"),
                new String[]{"89787123313", "89788133712"}
        );

        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            try (StringReader reader = new StringReader(result)) {
                Person resPerson = (Person) unmarshaller.unmarshal(reader);
                System.out.println(resPerson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        context = JAXBContext.newInstance(Device.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(device, writer);
            String xml = writer.getBuffer().toString();
            System.out.println(xml);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            try (StringReader reader = new StringReader(xml)) {
                Device resDevice = (Device) unmarshaller.unmarshal(reader);
                System.out.println(resDevice);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
