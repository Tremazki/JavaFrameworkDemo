package org.example;

public interface IFactory<T,V> {
    T create();
    T create(V _condition);
}
