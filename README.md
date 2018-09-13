# RustyTypes for Java

This project is a collection of Java types which are inspired by the Rust programming language. 

## Types

### Result

This type is inspired by [Rust's Result enum](https://doc.rust-lang.org/std/result/).

A `Result` method can be created by using its static constructor methods `ofValue()` or `ofError()` to create a result which contains either a result value or an error. 

```java
Result<ValueType, ErrorType> resultWithValue = Result.ofValue(value);
Result<ValueType, ErrorType> resultWithError = Result.ofError(error);
```

The only way to access the value inside the `Result` is to use the `handle()` method. This method has two overloaded implementations. One makes use of lambdas, and the other uses the `ResultHandler` interface along with its two methods. This method forces consumers of the `Result` to consider the implications of either a value being present or an error being present.

```java
result.handle(
        value -> {
            // handle what happens if the result contains a value
        },
        error -> {
            // handle what happens if the result contains an error
        }
);
```