package kibu.kuhn.springboot.validation.validation;

import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.Iterators;
import kibu.kuhn.springboot.validation.domain.Person;

public class PersonValidator implements ConstraintValidator<PersonValid, Person> {

  @Autowired
  private Validator validator;

  @Override
  public boolean isValid(Person person, ConstraintValidatorContext context) {
    Set<ConstraintViolation<Person>> errors = validator.validate(person);
    context.disableDefaultConstraintViolation();
    errors.forEach(cv -> {
      String rootName = cv.getRootBean().getClass().getSimpleName();
      String nodeName = Character.toLowerCase(rootName.charAt(0)) + rootName.substring(1);
      String name = Iterators.getLast(cv.getPropertyPath().iterator()).getName();
      context.buildConstraintViolationWithTemplate(cv.getMessageTemplate())
             .addPropertyNode(name)
             .addConstraintViolation();
    });

    return errors.isEmpty();
  }
}
