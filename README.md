# Spring Boot Validation Example
## How to use example project.
#### 1. Clone project.
```
git clone https://github.com/MosHelper/spring-boot-validation-example.git
```
#### 2. Let's Run gradle project.


## How to use validation in new project.
#### 1. Add Dependencies
>in file "build.gradle"
```
compile('org.springframework.boot:spring-boot-starter-validation')
```
#### 2. Using Validation Annotations
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
#### 3. Enabling Validation
insert `@Valid` before `@RequestBody` .
>in controller file
```
@PostMapping("/post")
public Student postStudent(@Valid @RequestBody Student student){
    return student;
}
```
Complete.
## Response Body
>Request
``` 
{
	"id":2,
	"name":"s",
	"email":"testtest" // <--- not email valid
}
```
>Response Body
``` 
{
    "timestamp": "2018-06-19T07:56:51.406+0000",
    "status": 400,
    "error": "Bad Request",
    "errors": [
        {
            "codes": [
                "Email.student.email",
                "Email.email",
                "Email.java.lang.String",
                "Email"
            ],
            "arguments": [
                {
                    "codes": [
                        "student.email",
                        "email"
                    ],
                    "arguments": null,
                    "defaultMessage": "email",
                    "code": "email"
                },
                [],
                {
                    "defaultMessage": ".*",
                    "arguments": null,
                    "codes": [
                        ".*"
                    ]
                }
            ],
            "defaultMessage": "Email not valid.",
            "objectName": "student",
            "field": "email",
            "rejectedValue": "testtest",
            "bindingFailure": false,
            "code": "Email"
        }
    ],
    "message": "Validation failed for object='student'. Error count: 1",
    "path": "/post"
}
```

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
## References
- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation
- http://www.springboottutorial.com/spring-boot-validation-for-rest-services
