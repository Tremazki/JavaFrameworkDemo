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