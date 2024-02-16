package org.example.reporting.impl.extent;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.reporting.Reporter;
import org.example.reporting.TestStep;
import org.example.reporting.impl.ReporterSupplierFactory;

import java.lang.reflect.Method;

@Aspect
public class ExtentReporterAspect {

    private final Reporter<?>          reporter;
    private String                     testStep;

    ExtentReporterAspect() {
       reporter = new ReporterSupplierFactory().create().supply();
    }

    @Pointcut("execution (public * *(..))")
    public void publicMethod() {}
//
//    @Pointcut("if()")
//    public boolean test(JoinPoint thisJoinPoint) {
//    }

    @Pointcut("@annotation(org.example.reporting.TestStep)")
    public void testStepAnnotation() {}

    @Around("publicMethod() && testStepAnnotation()")
    public Object retrieveMethodInformation(ProceedingJoinPoint call) throws Throwable {
        MethodSignature signature   = (MethodSignature) call.getSignature();
        Method          method      = signature.getMethod();

        testStep = method.getAnnotation(TestStep.class).value();
        return call.proceed(call.getArgs());
    }

    @AfterReturning(pointcut = "publicMethod() && testStepAnnotation()")
    public void methodSuccess() {
        reporter.passStep(testStep, "The step has passed successfully");
    }

    @AfterThrowing(pointcut = "publicMethod() && testStepAnnotation()", throwing = "_e")
    public void methodFail(Throwable _e) {
        reporter.failStep(testStep, "Failed with the following exception: \n" + _e.getMessage());
    }
}
