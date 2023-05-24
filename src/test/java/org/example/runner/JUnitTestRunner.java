package org.example.runner;

import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("org.example")
@ExcludePackages({"org.example.cucumber", "org.example.suite"})
public class JUnitTestRunner {
}
