package ro.msg.learning.shop.service.exception;

public class NullEntityException extends RuntimeException {
    public NullEntityException(Class<?> entityClass) {
        super(String.format("Tried to save a null entity of type '%s'", entityClass));
    }
}
