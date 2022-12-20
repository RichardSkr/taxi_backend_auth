package com.taxireview.api.exceptions;

import java.io.Serial;

public class ReviewNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 2;

    public ReviewNotFoundException(String message) {
        super(message);
    }
}
