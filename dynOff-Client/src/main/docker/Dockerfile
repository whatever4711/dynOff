FROM openjdk:8-jre-alpine
ENV CLIENT @bundle@
WORKDIR /mvn
ADD client-entrypoint.sh .
COPY /maven/* /mvn/lib/
RUN mv lib/${CLIENT} .
ENTRYPOINT ["./client-entrypoint.sh"]
