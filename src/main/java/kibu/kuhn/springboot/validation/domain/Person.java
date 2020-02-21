package kibu.kuhn.springboot.validation.domain;

import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModelProperty;

public class Person {

    @ApiModelProperty(example = "2019-05-29", notes = "Date of Birth as YYYY-MM-DD", required = true)
    @NotNull(message = "Date of bearth not set")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfBirth;
    @Size(min=2, message = "Name must have at least 2 characters")
    private String name;
    @Email(message = "Email not valid")
    private String email;
    @JsonProperty(required = false)
    @NotNull(message="personId must not be null")
    private Long personId;
    @Valid
    @JsonProperty(required = false)
    private List<Person> friends;

    public List<Person> getFriends() {
      return friends;
    }

    void setFriends(List<Person> friends) {
      this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
      return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
    }

    public Long getPersonId() {
      return personId;
    }

    public void setPersonId(Long id) {
      this.personId = id;
    }
}
