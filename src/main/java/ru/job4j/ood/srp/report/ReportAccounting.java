package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.store.Store;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.model.Employee;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportAccounting implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final CurrencyConverter converter;
    private final Currency from;
    private final Currency to;

    public ReportAccounting(Store store, DateTimeParser<Calendar> dateTimeParser,
                            CurrencyConverter converter, Currency from, Currency to) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.converter = converter;
        this.from = from;
        this.to = to;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(converter.convert(from, employee.getSalary(), to))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
