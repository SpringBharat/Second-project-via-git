package com.csipl.insurance.commons ;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggerAOP {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //What kind of method calls I would intercept
    //execution(* PACKAGE.*.*(..))
    //Weaving & Weaver
    
    @AfterThrowing(pointcut ="execution(* com.csipl.insurance..*(..))", throwing = "error") 
    public void afterThrowing(JoinPoint joinPoint, Throwable error)  {
    	//PropertyConfigurator.configure("src/main/resources/log4j.properties");
    	 logger.info("=========================== Chack * Your * Exception ==================================");
        //Advice
        logger.info(" Check for user access "+joinPoint.getSignature()+Arrays.deepToString(joinPoint.getArgs()));
        logger.info(" Allowed execution for {}", joinPoint.getTarget().toString());
        logger.info("Exception===================",error);
         
     
    }
}

 