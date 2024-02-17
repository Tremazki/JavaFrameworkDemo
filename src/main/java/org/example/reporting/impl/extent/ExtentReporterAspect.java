package org.example.reporting.impl.extent;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.reporting.IReporter;
import org.example.reporting.TestStep;
import org.example.reporting.impl.ReporterSupplierFactory;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
public class ExtentReporterAspect {

    private final IReporter reporter;
    private String          testStep;
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

        testStep = method.getAnnotation(TestStep.class).value();
        args     = call.getArgs();

        return call.proceed(args);
    }

    @AfterReturning(pointcut = "publicMethod() && testStepAnnotation()")
    public void methodSuccess() {
        StringBuilder builder = Arrays.stream(args).collect(StringBuilder::new, StringBuilder::append, (a, b) -> a.append(",").append(b));
        reporter.passStep(testStep, "Step arguments: " + builder);
    }

    @AfterThrowing(pointcut = "publicMethod() && testStepAnnotation()", throwing = "_e")
    public void methodFail(Throwable _e) {
        reporter.failStep(testStep, "Failed with the following exception: \n" + _e.getMessage());
    }
}
