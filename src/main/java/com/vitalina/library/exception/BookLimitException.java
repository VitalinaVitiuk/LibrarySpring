package com.vitalina.library.exception;

public class BookLimitException extends LibraryException {
    final static String message = "Exceed the limit of the ordered books";
    public BookLimitException() {
        super(message);
    }
}
