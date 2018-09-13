package org.kellpossible.rustytypes;

import java.util.function.Consumer;

/**
 * This type is inspired by <a href="https://doc.rust-lang.org/std/result/">Rust's Result enum </a>
 *
 * A {@link Result} method can be created by using its static constructor methods
 * {@link #ofValue(Object)} or {@link #ofError(Object)} to create a result which
 * contains either a result value or an error.
 *
 * <pre>
 * {@code
 * Result<ValueType, ErrorType> resultWithValue = Result.ofValue(value);
 * Result<ValueType, ErrorType> resultWithError = Result.ofError(error);
 * }
 * </pre>
 *
 * The only way to access the value inside the {@link Result} is to use the <b>handle()</b>
 * method. This method has two overloaded implementations. One makes use of lambdas
 * ({@link #handle(Consumer, Consumer)}), and the other ({@link #handle(ResultHandler)}
 * uses the {@link ResultHandler} interface along with its two methods. This method
 * forces consumers of the {@link Result} to consider the implications of either a value
 * being present or an error being present.
 *
 * <pre>
 * {@code
 * result.handle(
 *         value -> {
 *             // handle what happens if the result contains a value
 *         },
 *         error -> {
 *             // handle what happens if the result contains an error
 *         }
 * );
 * }
 * </pre>
 *
 * @param <V> the value type
 * @param <E> the error type
 */
public class Result<V, E> {
    private final V value;
    private final E error;

    public static<V, E> Result<V, E> ofValue(V value) {
        return new Result<>(value, null);
    }

    public static<V, E> Result<V, E> ofError(E error) {
        return new Result<>(null, error);
    }

    private Result(V value, E error) {
        this.value = value;
        this.error = error;
    }

    public boolean hasValue() {
        return value != null;
    }

    public boolean hasError() {
        return error != null;
    }

    public void handle(Consumer<V> handleValue, Consumer<E> handleError) {
        if (value != null) {
            handleValue.accept(value);
        } else if (error != null) {
            handleError.accept(error);
        }
    }

    public void handle(ResultHandler<V, E> resultHandler) {
        if (value != null) {
            resultHandler.handleValue(value);
        } else if (error != null) {
            resultHandler.handleError(error);
        }
    }
}
