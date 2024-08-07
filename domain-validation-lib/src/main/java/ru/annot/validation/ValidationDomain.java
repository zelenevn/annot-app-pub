package ru.annot.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ValidationDomain {

    private final List<String> errors;
    private final List<DomainValidation> validations;

    public ValidationDomain() {
        errors = new ArrayList<>();
        validations = new ArrayList<>();
    }

    protected void registerValidation(Supplier<Boolean> condition, String message) {
        validations.add(new DomainValidation(condition, message));
    }

    protected void validate(Function<List<String>, DomainValidationException> exceptionCreator) throws DomainValidationException {
        validations.forEach(DomainValidation::validate);

        if (!errors.isEmpty()) {
            throw exceptionCreator.apply(errors);
        }
    }

    private final class DomainValidation {

        private final Supplier<Boolean> condition;
        private final String message;

        private DomainValidation(Supplier<Boolean> condition, String message) {
            this.condition = condition;
            this.message = message;
        }

        private void validate() {
            if (condition.get()) {
                errors.add(message);
            }
        }
    }


}
