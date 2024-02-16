package org.example.reporting;

public interface Reporter<T> {
    void startTest(String _name);
    void passStep(String _title, String _details);
    void failStep(String _title, String _details);
    void write();
}
