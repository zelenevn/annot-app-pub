package ru.annot.validation;

import java.util.List;

public class DomainValidationException extends Exception {

    public DomainValidationException(List<String> messages, String delimeter) {
        super(String.join(delimeter, messages));
    }

}
