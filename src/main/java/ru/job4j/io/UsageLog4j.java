package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        long longg = 25L;
        int intt = 15;
        short shortt = 5;
        byte bytee = 127;
        char charr = 'x';
        double doublee = 2.0;
        float floatt = 1F;
        boolean bool = true;
        LOG.debug("long = {}, int = {}, short = {}, byte = {}, char = {}, double = {}, float = {}, boolean = {}",
                longg, intt, shortt, bytee, charr, doublee, floatt, bool);
    }
}