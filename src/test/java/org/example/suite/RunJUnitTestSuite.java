package org.example.suite;

import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("org.example")
@ExcludePackages("org.example.cucumber")
public class RunJUnitTestSuite {
}
