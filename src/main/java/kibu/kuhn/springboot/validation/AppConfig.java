package kibu.kuhn.springboot.validation;

import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.base.Predicate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class AppConfig  {


  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                                                  .select().paths(paths())
                                                  .apis(basePackage(ValidationApp.class.getPackage().getName()))
                                                  .build();
  }


  private Predicate<String> paths() {
    return regex("/validation/.*");
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Spring Validation Demo").build();
  }

}
