package org.example.reporting.impl.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.annotations.reporting.ScreenshotOnFail;
import org.example.annotations.reporting.ScreenshotOnPass;
import org.example.reporting.IReporter;
import org.example.annotations.reporting.TestStep;
import org.example.reporting.ReporterFactorySupplier;
import org.example.selenium.ScreenshotUtilities;

import java.lang.reflect.Method;

@Aspect
public class ReporterAspect {

    private final IReporter reporter;

    private boolean         reportArguments = false;
    private String[]        paramNames;
    private Object[]        args;

    ReporterAspect() {
       reporter = new ReporterFactorySupplier().supply().create();
    }

    @Pointcut("execution(@org.example.annotations.reporting.TestStep * *(..))")
    public void testStepPointCut() {}

    @Before("testStepPointCut() && !cflowbelow(testStepPointCut())")
    public void retrieveMethodInformation(JoinPoint call) {
        MethodSignature signature   = (MethodSignature) call.getSignature();
        Method          method      = signature.getMethod();

        TestStep annotation = method.getAnnotation(TestStep.class);
        reportArguments = annotation.reportArguments();

        paramNames = signature.getParameterNames();
        args       = call.getArgs();

        reporter.beginStep(annotation.value());
    }

    @AfterReturning(pointcut = "testStepPointCut() && !cflowbelow(testStepPointCut())")
    public void methodSuccess(JoinPoint call) {
        MethodSignature signature = (MethodSignature) call.getSignature();
        Method          method    = signature.getMethod();

        if(method.isAnnotationPresent(ScreenshotOnPass.class)) {
            takeScreenshot();
        }
        reporter.passStep(generateSuccessMessage());
    }

    @AfterThrowing(pointcut = "testStepPointCut() && !cflowbelow(testStepPointCut())", throwing = "_e")
    public void methodFail(JoinPoint call, Throwable _e) {
        MethodSignature signature = (MethodSignature) call.getSignature();
        Method          method    = signature.getMethod();

        if(method.isAnnotationPresent(ScreenshotOnFail.class)) {
            takeScreenshot();
        }
        reporter.failStep("Test step has failed with the following exception:<br><br>" + _e.getMessage());
    }

    /**
     * Call ScreenshotUtilities and embed the returning Base64 image into the current node
     */
    private void takeScreenshot() {
        reporter.embedImage(ScreenshotUtilities.takeScreenShotAsBase64());
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
