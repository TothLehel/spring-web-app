FROM tomcat:8
MAINTAINER toth_lehel
# a server.xmlt és a context.xmlt is át kell másolni
# kivettem őket mert a tesztprogramhoz nincsenek olyan dolgok konfigurálva amik a céges programoknál vannak.
# COPY ./tomcat-config/server.xml /usr/local/tomcat/conf/server.xml
#COPY ./tomcat-config/context.xml /usr/local/tomcat/conf/context.xml

#mountpoint mappát csinálunk a volumenak
RUN mkdir $CATALINA_HOME/webapps/springTest	

#Remote debug portot nyitun a 8000-en
ENV JPDA_OPTS="-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n"

EXPOSE 8080
WORKDIR $CATALINA_HOME/bin
ENTRYPOINT ["catalina.sh","jpda", "run"]



