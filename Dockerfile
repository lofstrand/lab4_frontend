FROM tomee:latest
MAINTAINER Sebastian Löfstrand <selo@kth.se>

RUN rm -fr /usr/local/tomee/webapps/ROOT
COPY target/community.war /usr/local/tomee/webapps/ROOT.war
CMD ["catalina.sh", "run"]