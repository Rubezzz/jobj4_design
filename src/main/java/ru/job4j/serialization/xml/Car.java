package ru.job4j.serialization.xml;

import java.util.Arrays;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private boolean isWorking;
    @XmlAttribute
    private String model;
    @XmlAttribute
    private int year;
    @XmlElementWrapper(name = "parameters")
    @XmlElement(name = "parameter")
    private String[] parameters;
    private Owner owner;

    public Car() {
    }

    public Car(boolean isWorking, String model, int year, String[] parameters, Owner owner) {
        this.isWorking = isWorking;
        this.model = model;
        this.year = year;
        this.parameters = parameters;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Car{"
                + "isWorking=" + isWorking
                + ", model='" + model + '\''
                + ", year=" + year
                + ", parameters=" + Arrays.toString(parameters)
                + ", owner=" + owner
                + '}';
    }
}
