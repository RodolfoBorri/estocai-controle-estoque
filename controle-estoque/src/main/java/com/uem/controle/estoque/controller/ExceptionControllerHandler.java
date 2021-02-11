package com.uem.controle.estoque.controller;

import java.util.logging.Logger;

public class ExceptionControllerHandler implements Thread.UncaughtExceptionHandler {

    private static Logger LOGGER = Logger.getLogger("Teste");

    public void uncaughtException(Thread t, Throwable e) {
        LOGGER.info("Unhandled exception caught!");
    }
}

