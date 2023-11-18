package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportXMLTest {

    @Test
    void whenGenerateXml() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(MemStore.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Max", now, now, 150);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportXML(store, marshaller);
        String formattedDate = parser.parse(now);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("<employees>")
                .append("    <employee>")
                .append("        <fired>").append(formattedDate).append("</fired>")
                .append("        <hired>").append(formattedDate).append("</hired>")
                .append("        <name>").append(worker.getName()).append("</name>")
                .append("        <salary>").append(worker.getSalary()).append("</salary>")
                .append("    </employee>")
                .append("    <employee>")
                .append("        <fired>").append(formattedDate).append("</fired>")
                .append("        <hired>").append(formattedDate).append("</hired>")
                .append("        <name>").append(worker2.getName()).append("</name>")
                .append("        <salary>").append(worker2.getSalary()).append("</salary>")
                .append("    </employee>")
                .append("</employees>");
        String cleanOutput = engine.generate(em -> true).replaceAll("\n", "").replaceAll("\r", "");
        assertThat(cleanOutput).isEqualTo(expect.toString());
    }
}