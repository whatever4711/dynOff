FROM openjdk:8-jre-alpine
ENV CLIENT @bundle@
ADD client-entrypoint.sh .
COPY maven/${CLIENT} .
ENTRYPOINT ["./client-entrypoint.sh"]
