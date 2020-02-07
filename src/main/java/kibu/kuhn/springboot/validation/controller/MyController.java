package kibu.kuhn.springboot.validation.controller;

import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kibu.kuhn.springboot.validation.domain.Person;

@Api(tags = {"Validation"})
@RestController
@RequestMapping("/validation")
public class MyController {

    @ApiResponses({
      @ApiResponse(message = "Creates a student", code = 201, response = Person.class)
    })
    @ApiOperation("Creates a student")
    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person postStudent(@Valid @RequestBody(required = true) Person student){
        return student;
    }

}
