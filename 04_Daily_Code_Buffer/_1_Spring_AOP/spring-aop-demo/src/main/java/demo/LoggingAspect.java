package demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    //This Logger method Call Before Checkout method call
    @Before("execution(* demo.ShoppingCart.checkout(..))")
    public void beforeLogger(JoinPoint jp){
        // sanitize the data using JoinPoint
        //System.out.println(jp.getSignature());

        //get argument data
        String args = jp.getArgs()[0].toString();
        System.out.println("Before Loggers with Argument: " + args);


        //System.out.println("Before Loggers");
    }

    //This Logger method Call After Checkout method call
    @After("execution(* *.*.checkout(..))")
    public void afterLogger(){
        System.out.println("After Logger");
    }

    @Pointcut("execution(* demo.ShoppingCart.quantity(..))")
    public void afterReturningPointCut(){

    }

    // get return value
    @AfterReturning(pointcut = "afterReturningPointCut()", returning = "retVal")
    public void afterReturning(String retVal){
        System.out.println("After Returning : " + retVal);
    }

}
