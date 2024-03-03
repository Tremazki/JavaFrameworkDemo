package org.example;

public interface IFactory<T,V> {
    T create() throws Exception;
    T create(V _condition) throws Exception;
}
