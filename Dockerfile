FROM amazoncorretto:17.0.3-alpine as corretto-jdk
ADD /build/libs/LTPlug-0.0.1-SNAPSHOT.jar demo.jar
ENTRYPOINT ["java","-jar","demo.jar"]
