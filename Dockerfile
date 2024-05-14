FROM eclipse-temurin:17-jdk-alpine
WORKDIR /runDockerSpringBoot
COPY ./nds-0.0.1-SNAPSHOT.jar /runDockerSpringBoot
CMD ["java","-jar","nds-0.0.1-SNAPSHOT.jar"]