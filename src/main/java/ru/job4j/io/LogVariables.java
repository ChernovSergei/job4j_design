package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogVariables {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte zero = 0;
        Object[] vars = new Object[] {"Name", 18, 8F, 1L, 'C', 2D, true, zero};
        for (int i = 0; i < vars.length; i++) {
            LOG.debug("Var value = {}. Var type - {}", vars[i], vars[i].getClass());
    }
}
}
