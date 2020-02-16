package kibu.kuhn.springboot.validation.exception;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path.Node;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ConstraintViolationExceptionHandler {

  private static class KeyMapper implements Function<ConstraintViolation<?>, String> {

    @Override
    public String apply(ConstraintViolation<?> cv) {
      List<Node> nodes = StreamSupport.stream(cv.getPropertyPath().spliterator(), false)
                                      .collect(Collectors.toList());

      if (nodes.isEmpty()) {
        return null;
      }

      if (nodes.size() == 1) {
        return nodes.get(0).getName();
      }

      String name = null;
      Node n2 = nodes.get(nodes.size() - 2);
      if (n2.toString().matches(".+\\[\\d+]")) {
        name = n2.toString();
      }
      Node lastNode = nodes.get(nodes.size() - 1);
      name = name == null ? lastNode.getName() : name + "." + lastNode.getName();
      return name;
    }
  }

  private static Function<ConstraintViolation<?>, String> valueMapper = cv -> cv.getMessage();

  @ExceptionHandler(value = ConstraintViolationException.class)
  public ResponseEntity<ErrorMessages> handle(ConstraintViolationException exception) {

    List<ConstraintViolation<?>> list = exception.getConstraintViolations()
                                                 .stream()
                                                 .collect(Collectors.toList());
    Map<String, String> errors = list.stream().collect(Collectors.toMap(new KeyMapper(), valueMapper));
    return toResponse(errors);
  }

  private ResponseEntity<ErrorMessages> toResponse(Map<String, String> errors) {
    TreeMap<String, String> sortedErrors = new TreeMap<>((k1, k2) -> k1.compareToIgnoreCase(k2));
    sortedErrors.putAll(errors);

    ErrorMessages errorMessages = new ErrorMessages();
    errorMessages.setErrors(sortedErrors);
    errorMessages.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorMessages);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorMessages> handle(MethodArgumentNotValidException exception) {
    BindingResult bindingResult = exception.getBindingResult();
    Map<String, String> errors = bindingResult.getFieldErrors().stream().collect(Collectors.toMap(fe -> fe.getField(), fe -> fe.getDefaultMessage()));
    return toResponse(errors);
  }
}
