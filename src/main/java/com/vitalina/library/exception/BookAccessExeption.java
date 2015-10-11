package com.vitalina.library.exception;

public class BookAccessExeption extends LibraryException {
    final static String message = "Book is not available";

    public BookAccessExeption() {
        super(message);
    }
}
