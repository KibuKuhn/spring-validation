package kibu.kuhn.springboot.validation.validation;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ValidationAspect {


    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Autowired
    private Validator validator;


    @Before("@annotation(kibu.kuhn.springboot.validation.validation.Validation)")
    public void before(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.toLongString());
        Object target = joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();
        Set<ConstraintViolation<Object>> violations = validate(args);

        List<Class<?>> params = Arrays.stream(args)
                                      .map(arg -> arg.getClass())
                                      .collect(Collectors.toList());
        Class<?>[] paramTypes = params.toArray(new Class<?>[0]);
        try {
          Method method = target.getClass().getMethod(joinPoint.getSignature().getName(), paramTypes);
          ExecutableValidator executableValidator = validator.forExecutables();
          Set<ConstraintViolation<Object>> parameterViolations = executableValidator.validateParameters(target, method, args);
          violations = mergeVolations(violations, parameterViolations);
        }
        catch (Exception ex) {
          throw new IllegalStateException(ex);
        }

        if (violations.isEmpty()) {
          return;
        }

        throw new ConstraintViolationException(violations);

    }

    private Set<ConstraintViolation<Object>> mergeVolations(Set<ConstraintViolation<Object>> violations,
        Set<ConstraintViolation<Object>> parameterViolations) {
      violations.addAll(parameterViolations);

      return violations;
    }

    private Set<ConstraintViolation<Object>> validate (Object[] params) {
      Set<ConstraintViolation<Object>> violations =
          IntStream.range(0, params.length)
                   .mapToObj(index -> validate(params[index], index))
                   .filter(iv -> !iv.isEmpty())
                   .flatMap(set -> set.stream())
                   .collect(Collectors.toSet());

      return violations;
    }

    private Set<ConstraintViolation<Object>> validate(Object param, int index) {
      Set<ConstraintViolation<Object>> violations = validator.validate(param);
      return violations;

    }
}
