package org.example.reporting.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.reporting.IReporter;
import org.example.reporting.TestStep;
import org.example.selenium.ScreenshotUtilities;

import java.lang.reflect.Method;

@Aspect
public class ReporterAspect {

    private final IReporter reporter;

    //Temporary variables
    private boolean         screenshotFail  = false;
    private boolean         screenshotPass  = false;
    private boolean         reportArguments = false;
    private String[]        paramNames;
    private Object[]        args;

    ReporterAspect() {
       reporter = new ReporterSupplierFactory().create().supply();
    }

    @Pointcut("execution(@org.example.reporting.TestStep * *(..))")
    public void testStepPointCut() {}

    @Before("testStepPointCut() && !cflowbelow(testStepPointCut())")
    public void retrieveMethodInformation(JoinPoint call) {
        MethodSignature signature   = (MethodSignature) call.getSignature();
        Method          method      = signature.getMethod();
        TestStep        annotation  = method.getAnnotation(TestStep.class);

        screenshotPass  = annotation.screenshotPass();
        screenshotFail  = annotation.screenshotFail();
        reportArguments = annotation.reportArguments();

        paramNames = signature.getParameterNames();
        args       = call.getArgs();

        reporter.beginStep(annotation.value());
    }

    @AfterReturning(pointcut = "testStepPointCut() && !cflowbelow(testStepPointCut())")
    public void methodSuccess() {
        takeScreenshot(screenshotPass);
        reporter.passStep(generateSuccessMessage());
    }

    @AfterThrowing(pointcut = "testStepPointCut() && !cflowbelow(testStepPointCut())", throwing = "_e")
    public void methodFail(Throwable _e) {
        takeScreenshot(screenshotFail);
        reporter.failStep("Test step has failed with the following exception:<br><br>" + _e.getMessage());
    }

    /**
     * Call ScreenshotUtilities and embed the returning Base64 image into the current node
     * @param _perform Boolean for whether the screenshot should be taken and embedded into the report
     */
    private void takeScreenshot(boolean _perform) {
        if(_perform) {
            reporter.embedImage(ScreenshotUtilities.takeScreenShotAsBase64());
        }
    }

    private String generateSuccessMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append("Test step has passed successfully");
        if(reportArguments) {
            builder.append("<br><br>");
            for (int i = 0; i < paramNames.length; i++) {
                builder.append(String.format("Parameter: [%s] with value: [%s]<br>", paramNames[i], args[i]));
            }
        }
        return builder.toString();
    }
}
