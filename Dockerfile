FROM openjdk

WORKDIR /app

COPY target/mock-shirt-0.0.1-SNAPSHOT.jar /app/mock-shirt.jar

ENTRYPOINT [ "java", "-jar", "mock-shirt.jar" ]