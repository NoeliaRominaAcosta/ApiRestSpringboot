package com.itrsa.monolith.aspects;

import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	// NO QUEREMOS UN LOGGER ESTATICO REFERENCIANDO AL ASPECTO
	// QUEREMOS UN ASPECTO HUMILDE: HACE SU TRABAJO REFERENCIANDO AL CONSUMIDOR
	// ESPECIALMENTE PORQUE ESTOS SON LOS VERDADEROS PROTAGONISTAS PARA
	// EL NEGOCIO
	
    //private final Logger log = LoggerFactory.getLogger(this.getClass());

	// TOMO EL PACKAGE MAS GENERAL, PERO CONSIDERAR POINTCUTS POR CAPAS
	// O QUIZAS POR ALGUN OTRO CRITERIO
	// O QUIZAS SERIA MAS APROPIADO USAR OTROS ASPECTOS	
	@Pointcut("within(com.itrsa.monolith..*)")
    public void applicationPackagePointcut() {
    	System.out.println("pasé por el pointcut applicationPackagePointcut");    	
    }


	// ADVICE QUE HACE EÑ TRABAJO DE LOGUEAR
	@Around("applicationPackagePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) {

		// OBTENEMOS EL LOGGER A PARTIR DE LA CLASE DEL OBJETO "ASPECTEADO"
    	Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
    	
    	// EL LOGGER DE DICHA CLASE DEBE ESTAR SETEADO EN ON/OFF POR REGLAS QUE SE DETERMINAN EN LA
    	// CFG DEL MISMO. EL ASPECTO DEPENDE DEL LOGGER, PERO NO LO INVADE: SI EL LOGUEO ESTA APAGADO
    	// NO LOGUEARÁ NADA
    
        if (log.isDebugEnabled()) {
        	log.debug(">>>>>>>"+this.getClass().getCanonicalName()+" haciendo su trabajo...");
            log.debug("      > {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
            log.debug(">>>>>>>");
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (Throwable t) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            // CONVERTIMOS LAS POSIBLES CHECKED EXCEPTION EN UNCHECKED 
            throw new RuntimeException(t);
        }
    }
}