package com.ITI0302._happy.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionTest {
    @Test
    void exceptionTest() {
        String error = new NotFoundException("Kankel").getMessage();
        Assertions.assertEquals("Kankel", error);
    }

}