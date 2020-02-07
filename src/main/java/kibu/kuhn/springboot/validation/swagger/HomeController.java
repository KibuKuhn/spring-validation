package kibu.kuhn.springboot.validation.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class HomeController {

  @RequestMapping({"/validation","/validation/"})
  public String home() {
    return "redirect:swagger-ui.html";
  }
}