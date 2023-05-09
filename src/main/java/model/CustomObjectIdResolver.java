package model;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;

public class CustomObjectIdResolver extends SimpleObjectIdResolver {
    @Override
    public void bindItem(final IdKey id, final Object object) {
        if (_items == null) _items = new HashMap<>();

        _items.put(id, object);
    }

    @Override
    public ObjectIdResolver newForDeserialization(final Object object) {
        return new CustomObjectIdResolver();
    }
}