package org.example.selenium.driver;

public interface IFactory<T,V> {
    T create();
    T create(V _condition);
}
