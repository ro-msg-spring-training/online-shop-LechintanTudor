package ro.msg.learning.shop.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private final Class<?> entityClass;
    private final Object entityId;

    public EntityNotFoundException(Class<?> entityClass, Object entityId) {
        super(String.format("Entity of type `%s` with id `%s` was not found", entityClass.getName(), entityId));
        this.entityClass = entityClass;
        this.entityId = entityId;
    }
}
