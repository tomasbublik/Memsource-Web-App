# Memsource Web App Assignment

To build and run this project you can use the following command:
 
```bash
./gradlew clean build && java -jar build/libs/memsource-web-app-0.1.1.jar

```

This will start the application on the port 8080. To control the application, do the login first by entering this to the browser:

```bash
http://localhost:8080/api/v1/log?name=<your_username>&password=<your_password>
```

Once you are logged in, you can list your projects by entering: 

```bash
http://localhost:8080/api/v1/projects
```


To test the app use the command as follows:
```bash
./gradlew test

```
