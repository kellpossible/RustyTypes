package org.kellpossible.rustytypes;

import org.junit.Assert;
import org.junit.Test;

public class TestResult {
    @Test
    public void testHandleLambdas()
    {
        Result<Integer, String> resultWithValue = Result.ofValue(24);
        resultWithValue.handle(
                value -> Assert.assertEquals(24, value.intValue()),
                error -> Assert.fail());

        Result<Integer, String> resultWithError = Result.ofError("Error Message");
        resultWithError.handle(
                value -> Assert.fail(),
                error -> Assert.assertEquals("Error Message", error));

    }

    @Test
    public void testHandleInterface() {
        Result<Integer, String> resultWithValue = Result.ofValue(24);
        resultWithValue.handle(
                new ResultHandler<Integer, String>() {
                    @Override
                    public void handleValue(Integer value) {
                        Assert.assertEquals(24, value.intValue());
                    }

                    @Override
                    public void handleError(String error) {
                        Assert.fail();
                    }
                }
        );

        Result<Integer, String> resultWithError = Result.ofError("Error Message");
        resultWithError.handle(
                new ResultHandler<Integer, String>() {
                    @Override
                    public void handleValue(Integer value) {
                        Assert.fail();
                    }

                    @Override
                    public void handleError(String error) {
                        Assert.assertEquals("Error Message", error);
                    }
                }
        );
    }
}
