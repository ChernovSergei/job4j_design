package ru.job4j.serialization.xml;

import ru.job4j.serialization.java.Contact;

import java.io.StringWriter;
import java.util.Arrays;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "god")
@XmlAccessorType(XmlAccessType.FIELD)
public class God {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private int yearOfBirth;

    private Contact contact;

    @XmlAttribute
    private boolean isImmortal;

    @XmlElementWrapper
    @XmlElement(name = "children")
    private String[] children;

    public God() { }

    public God(String name, int yearOfBirth, Contact contact, boolean isImmortal, String[] children) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.contact = contact;
        this.isImmortal = isImmortal;
        this.children = children;
    }

    @Override
    public String toString() {
        return "God{"
                + "name=" + name
                + ", year of creation=" + yearOfBirth
                + ", contact =" + children
                + ", immortal =" + isImmortal
                + ", children =" + Arrays.toString(children)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        final God athena = new God("Athena", -500, new Contact(10558, "+30 21 0321 4172"), true, new String[] {"Erichthonius", "Hygiea"});

        JAXBContext context = JAXBContext.newInstance(God.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(athena, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
