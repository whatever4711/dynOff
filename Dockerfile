FROM maven:3-alpine
WORKDIR /dynOff
ADD dynOff-ActorsUtility dynOff-ActorsUtility/
ADD dynOff-EAR dynOff-EAR/
ADD dynOff-EJB dynOff-EJB/
ADD dynOff-Web dynOff-Web/
COPY pom.xml .
RUN mvn package


FROM glassfish:4.1-jdk8
ENV ADMIN_PASSWORD 12wert45
ENV APP /dynOff-EAR-1.0.ear
WORKDIR /
COPY docker-entrypoint.sh .
COPY --from=0 /dynOff/dynOff-EAR/target${APP} .
# Dirty Hack -> Maybe include in EAR?
COPY --from=0 /dynOff/dynOff-EAR/target/dynOff-EAR-1.0/akka-actor*.jar /dynOff/dynOff-EAR/target/dynOff-EAR-1.0/config*.jar /dynOff/dynOff-EAR/target/dynOff-EAR-1.0/scala-*.jar /dynOff/dynOff-EAR/target/dynOff-EAR-1.0/dynOff-ActorsUtility-*.jar /usr/local/glassfish4/glassfish/domains/domain1/lib/
ENTRYPOINT ["./docker-entrypoint.sh"]
CMD ["asadmin", "start-domain", "--verbose"]
