package org.example.reporting.impl.extent;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.reporting.IReporter;
import org.example.reporting.TestStep;
import org.example.reporting.impl.ReporterSupplierFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
public class ExtentReporterAspect {

    private final IReporter reporter;
    private String          testStep;

    //Temporary variables
    private boolean         logParams = false;
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

        testStep   = annotation.value();
        logParams  = annotation.logArguments();
        paramNames = signature.getParameterNames();
        args       = call.getArgs();

        return call.proceed(args);
    }

    @AfterReturning(pointcut = "publicMethod() && testStepAnnotation()")
    public void methodSuccess() {
        reporter.passStep(testStep, getSuccessDetails());
    }

    @AfterThrowing(pointcut = "publicMethod() && testStepAnnotation()", throwing = "_e")
    public void methodFail(Throwable _e) {
        reporter.failStep(testStep, "Test step has failed with the following exception:<br><br>" + _e.getMessage());
    }

    private String getSuccessDetails() {
        StringBuilder builder = new StringBuilder();
        builder.append("Test step has passed successfully");
        if(logParams) {
            builder.append("<br><br>");
            for (int i = 0; i < paramNames.length; i++) {
                builder.append(String.format("Parameter: [%s] with value: [%s]<br>", paramNames[i], args[i]));
            }
        }
        return builder.toString();
    }
}
