FROM maven:3.8.5-openjdk-17-slim
EXPOSE 8081
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","nl.hakktastic.leaseacarapi.LeaseCalculationServiceApplication"]