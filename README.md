### Spring Boot bean validation (JSR303) demo ###
<b>Validate request body and parameters in one step</b>

Ever tried to validate body and parameters of a REST call in 1 step? This does not work.
Spring uses different approaches to validate body and parameters that cannot be mixed.

* Start the app and open localhost:8080/validation to get the swagger site
* Endpoint <i>Old</i> uses the standard annotations foŕ validation. Does not work
* Endpoint <i>New</i> uses a new approach. Works

The endpoints just return the person from the request.

Happy testing :-)

