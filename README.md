# Headers Demo for Android
Demonstrates behavior with values of 'Cache-Control' response headers on Android using Retrofit/OkHttp.
For more info and explanations of how 'Cache-Control' response header values affect
your apps, see [Use Your Headers](https://blog.diveinsoft.com/2024/04/for-on-device-caching-use-your-headers.html)

See the blog post entry on how to perform the forensics on viewing cache data in the emulator.


## API Layer
The source code for the fake API is also located in Github, but is deployed to Google App Engine (see "urlBase" in the view model.)
The backend will frequently spin down to zero instances, so when executing the demo, allow a few seconds for the backend to launch.

### API Client
I've generated the classes in the `apis`, `infrastructure` and `models` folders using the [OpenAPI generator](https://github.com/OpenAPITools/openapi-generator), which accepts OpenAPI (formerly Swagger) documentation URLs and produces generated client code.
The command for generating these is 
`openapi-generator generate -g kotlin --additional-properties packageName=com.diveinsoft.blog.headers,library=jvm-retrofit2,serializationLibrary=gson --skip-validate-spec -i http://localhost:8080/v3/api-doc`.  
You can also use the GAE-hosted URL to generate these files, as well.

