package org.example.runner;

import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("org.example")
@ExcludePackages({"org.example.cucumber"})
public class JUnitTestRunner {
    // Intentionally empty, the above annotations will tell JUnit what to look for
}
