package com.esah2111.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esah2111.springaop.model.Employee;


@Aspect
@Component
public class EmployeeServiceAspect {
	
	Logger logger = LoggerFactory.getLogger(EmployeeServiceAspect.class);
	
	@Before(value = "execution(* com.esah2111.springaop.service.EmployeeService.*(..)) and args(name,empId)")
	public void beforeAdvice(JoinPoint joinPoint, String name, String empId) {
		logger.info("Before method:" + joinPoint.getSignature());

		logger.info("Creating Employee with name - " + name + " and id - " + empId);
	}

	@After(value = "execution(* com.esah2111.springaop.service.EmployeeService.*(..)) and args(name,empId)")
	public void afterAdvice(JoinPoint joinPoint, String name, String empId) {
		logger.info("After method:" + joinPoint.getSignature());

		logger.info("Successfully created Employee with name - " + name + " and id - " + empId);
	}
	
	@AfterThrowing(pointcut="execution(* com.esah2111.springaop.service.EmployeeService.*(..))",throwing = "ex")
	public void logException(JoinPoint joinPoint,Exception ex) {
		logger.info("After throwing :"+ joinPoint.getSignature() );
		logger.error("Exception ",ex );
		
	}
	
	@AfterReturning(pointcut = "execution(* com.esah2111.springaop.service.EmployeeService.*(..))",returning = "emp")
	public void logResult(JoinPoint joinPoint,Employee emp) {
		logger.info("After returning :"+ joinPoint.getSignature() );
		logger.info("Result " + emp.toString());
	}
	
	@Around(value = "execution(* com.esah2111.springaop.service.EmployeeService.*(..))")
	public Object trace(ProceedingJoinPoint proceedingJP ) throws Throwable {
		String methodInformation = 
				proceedingJP.getStaticPart().getSignature().toString();
		
		logger.info("Around "+methodInformation);
		
		try {
			return proceedingJP.proceed();
		} catch (Throwable ex) {
			logger.error("Exception in "+methodInformation, ex);
			return Boolean.FALSE;
		} finally {
			logger.info("Exiting "+methodInformation);
		}
	}

}