package kibu.kuhn.springboot.validation.controller;

import static org.springframework.http.HttpStatus.CREATED;
import javax.validation.constraints.Min;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kibu.kuhn.springboot.validation.domain.Person;
import kibu.kuhn.springboot.validation.exception.ErrorMessages;
import kibu.kuhn.springboot.validation.validation.Validation;

@Api(tags = {"New"})
@RestController
@RequestMapping("/validation/new")
public class NewController {

    @Validation
    @ApiResponses({
      @ApiResponse(message = "Creates a person", code = 201, response = Person.class),
      @ApiResponse(message = "Validation error message", code = 406, response = ErrorMessages.class)
    })
    @ApiOperation("Creates a person")
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> createMixed(
        @ApiParam(name = "person", required = true, value = "Person data")
        @RequestBody(required = true)
        Person person,
        @ApiParam(name = "id", required = true, value = "Person id, must be >= 2", allowEmptyValue = true, example = "2")
        @PathVariable(name = "id", required = true)
        @Min(value = 2, message = "id must be >= 2") Long id) {
      person.setPersonId(id);
      return ResponseEntity.status(CREATED).body(person);
    }

}
