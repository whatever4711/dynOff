FROM maven:3-alpine
WORKDIR /dynoff
ADD dynOff-GlassfishEAR dynOff-GlassfishEAR/
ADD dynOff-GlassfishEJB dynOff-GlassfishEJB/
ADD dynOff-GlassfishWeb dynOff-GlassfishWeb/
COPY pom.xml .
RUN mvn package


FROM oracle/glassfish:4.1.2
COPY --from=0 dynoff/pom.xml .
