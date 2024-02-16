package org.example.reporting;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.model.pages.TestStep;

import java.lang.reflect.Method;

@Aspect
public class ReportingAspect {

    public String testName;

    @Pointcut("@annotation(org.example.model.pages.TestStep)")
    public void methodToWrap() {}

    @Around("methodToWrap()")
    public Object around(ProceedingJoinPoint call) throws Throwable {
        MethodSignature signature   = (MethodSignature) call.getSignature();
        Method          method      = signature.getMethod();

        TestStep node = method.getAnnotation(TestStep.class);
        testName = node.value();

        return call.proceed(call.getArgs());
    }

    @AfterReturning(pointcut = "methodToWrap()")
    public void afterReturning() {
        ExtentReportUtilities.getExtentTest().createNode(testName).pass(
                "The step has passed successfully"
        );
    }

    @AfterThrowing(pointcut = "methodToWrap()", throwing = "_e")
    public void afterThrowing(Throwable _e) {
        ExtentReportUtilities.getExtentTest().createNode(testName).fail(
                "Failed with the following exception: \n" + _e.getMessage()
        );
    }
}
