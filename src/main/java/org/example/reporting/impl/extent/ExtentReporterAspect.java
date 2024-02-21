package org.example.reporting.impl.extent;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.reporting.IReporter;
import org.example.reporting.TestStep;
import org.example.reporting.impl.ReporterSupplierFactory;
import org.example.selenium.ScreenshotUtilities;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Method;

@Aspect
public class ExtentReporterAspect {

    private final IReporter reporter;

    //Temporary variables
    private boolean         screenshotFail  = false;
    private boolean         screenshotPass  = false;
    private boolean         reportArguments = false;
    private String[]        paramNames;
    private Object[]        args;

    ExtentReporterAspect() {
       reporter = new ReporterSupplierFactory().create().supply();
    }

    @Pointcut("execution (public * *(..))")
    public void publicMethod() {}

    @Pointcut("@annotation(org.example.reporting.TestStep)")
    public void testStepAnnotation() {}

    @Around("publicMethod() && testStepAnnotation()")
    public Object retrieveMethodInformation(ProceedingJoinPoint call) throws Throwable {
        MethodSignature signature   = (MethodSignature) call.getSignature();
        Method          method      = signature.getMethod();
        TestStep        annotation  = method.getAnnotation(TestStep.class);

        // Flags
        screenshotPass  = annotation.screenshotPass();
        screenshotFail  = annotation.screenshotFail();
        reportArguments = annotation.reportArguments();

        // Arguments
        paramNames = signature.getParameterNames();
        args       = call.getArgs();

        reporter.beginStep(annotation.value());
        return call.proceed(args);
    }

    @AfterReturning(pointcut = "publicMethod() && testStepAnnotation()")
    public void methodSuccess() {
        takeScreenshot(screenshotPass);
        reporter.passStep(generateSuccessMessage());
    }

    @AfterThrowing(pointcut = "publicMethod() && testStepAnnotation()", throwing = "_e")
    public void methodFail(Throwable _e) {
        takeScreenshot(screenshotFail);
        reporter.failStep("Test step has failed with the following exception:<br><br>" + _e.getMessage());
    }

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
