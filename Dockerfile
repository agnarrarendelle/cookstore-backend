FROM maven:3.9.5-eclipse-temurin-17 AS MAVEN_TOOL_CHAIN
WORKDIR /tmp/

COPY pom.xml ./
COPY api/pom.xml ./api/
COPY repository/pom.xml ./repository/
COPY service/pom.xml ./service/
COPY common/pom.xml ./common/
COPY security/pom.xml ./security/
RUN mvn -B -f pom.xml dependency:go-offline
COPY api ./api/
COPY repository ./repository/
COPY service ./service/
COPY common ./common/
COPY security ./security/

ARG PROFILE

RUN mvn clean package -U -P ${PROFILE}

FROM openjdk:17-oracle
COPY --from=MAVEN_TOOL_CHAIN /tmp/api/target/api-0.0.1-SNAPSHOT.jar  app.jar

RUN sh -c 'touch /app.jar'

ENTRYPOINT ["java","-jar","/app.jar"]