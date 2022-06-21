FROM maven:3.8.5-openjdk-8 AS BUILD_STAGE

COPY ./ ./

RUN mvn clean package

FROM openjdk:8-jre

COPY --from=BUILD_STAGE /target/Project1-0.0.1-SNAPSHOT-shaded.jar /pancake-bank.jar

ENV db_url=jdbc:postgresql://database-2.cjdho29bgj7p.us-east-1.rds.amazonaws.com/postgres
ENV db_user=postgres
ENV db_pass=ragingbull


CMD ["java","-jar","pancake-bank.jar"]

