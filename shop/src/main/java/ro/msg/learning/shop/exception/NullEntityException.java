package ro.msg.learning.shop.exception;

import lombok.Getter;

@Getter
public class NullEntityException extends RuntimeException {
    private final Class<?> entityClass;

    public NullEntityException(Class<?> entityClass) {
        super(String.format("Tried to save a null entity of type '%s'", entityClass));
        this.entityClass = entityClass;
    }
}
