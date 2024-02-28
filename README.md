## Execution

Run the framework using the test goal and specify what test you want to run by using the available profiles.

- Available profiles:
  - `junit-selenium`, `junit-cucumber`

Some system properties must be set if you plan on using Selenium WebDriver, these include:
- Browser (Required - Browser name)
  - e.g. `-Dbrowser=edge`
  - Available options: `chrome`, `edge`, `firefox`, `safari`, `explorer`
- Grid (URL if you're connecting to a Grid instance)
  - e.g. `-Dgrid=http//localhost:4444`
- Remote (Boolean for if you're connecting to a remote instance i.e. Grid)
    - e.g. `-Dremote=true`

e.g. 
```
test -Pjunit-selenium -Dremote=false -Dbrowser=edge
```

## Limitations

In order for the reports to automatically and accurately log the steps and pass/fail status, methods denoted by @TestStep 
must not be called within other @TestStep methods. 

Please see the example below for how we can organize our methods to avoid this.

### Incorrect:
```Java
public class ExamplePage extends Page<ExamplePage> {

    public ExamplePage(WebDriver driver) {
        super(driver);
    }

    ...

    @TestStep("The user enters information into the form and submits")
    public void performAction1() {
        performAction2();
        performAction3();
    }

    @TestStep("The user enters the value into the username field")
    public void performAction2() {
        ...
    }

    @TestStep("The user clicks the submit button")
    public void performAction3() {
        ...
    }
}
```

Doing this will cause each call, including the calls in performAction1() to create a new entry in the report for that test step.


### Correct:
```Java
public class ExamplePage extends Page<ExamplePage> {

    public ExamplePage(WebDriver driver) {
        super(driver);
    }

    ...

    // This ensures that the method calls within performAction1 aren't creating additional report calls
    // This does mean the code will be a little less flexible however it just requires additional planning.
    @TestStep("The user enters information into the form and submits")
    public void performAction1() {
        performAction2();
        performAction3();
    }

    public void performAction2() {
        ...
    }

    public void performAction3() {
        ...
    }
}
```

I'm exploring possible ways of excluding these calls from AspectJ weaving however I have not found a way to do this yet.