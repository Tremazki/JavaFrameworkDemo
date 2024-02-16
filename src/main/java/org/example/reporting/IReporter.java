package org.example.reporting;

public interface IReporter {
    void startTest(String _name);
    void passStep(String _title, String _details);
    void failStep(String _title, String _details);
    void embedImage(String _path);
    void write();
}
