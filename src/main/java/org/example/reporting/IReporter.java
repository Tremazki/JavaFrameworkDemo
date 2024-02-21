package org.example.reporting;

public interface IReporter {
    void startTest(String _name);
    void beginStep(String _title);
    void passStep(String _details);
    void failStep(String _details);
    void embedImage(String _path);
    void write();
}
