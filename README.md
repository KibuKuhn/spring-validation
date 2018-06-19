# Spring Boot Validation Example
## How to use validation in New Project.
##### 1. Add Dependencies
>in file "build.gradle"
```
compile('org.springframework.boot:spring-boot-starter-validation')
```
##### 2. Using Validation Annotations
>in any file "Model" or "Entity"
```
@NotNull
int id;
@Size(min=2)
String name;
@Email(message = "Email not valid.")
String email;
```
see [other annotation](#validation-annotations)
##### 3. 

## Validation Annotations
- DecimalMax
- DecimalMin
- Digits
- Email
- Future
- FutureOrPresent
- Max
- Min
- Negative
- NegativeOrZero
- NotBlank
- NotEmpty
- NotNull
- Null
- Past
- PastOrPresent
- Pattern
- Positive
- PositiveOrZero
