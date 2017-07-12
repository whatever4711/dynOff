FROM @base@
ENV ADMIN_PASSWORD @admin.pwd@
ENV APP /@bundle@
WORKDIR /

COPY docker-entrypoint.sh .
COPY maven/ /maven

RUN mv /maven/*.jar /usr/local/glassfish4/glassfish/domains/domain1/lib/ && \
    mv /maven/*.ear .
ENTRYPOINT ["./docker-entrypoint.sh"]
CMD ["asadmin", "start-domain", "--verbose"]
