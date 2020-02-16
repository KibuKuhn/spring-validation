package kibu.kuhn.springboot.validation.exception;

import java.util.Map;

public class ErrorMessages {
  private int status;
  private Map<String,String> errors;

  public Map<String, String> getErrors() {
    return errors;
  }

  public void setErrors(Map<String,String> map) {
    this.errors = map;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }


}
