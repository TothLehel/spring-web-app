FROM tomcat:8
MAINTAINER toth_lehel
# a server.xmlt és a context.xmlt is át kell másolni
#ADD ./tomcat-config/server.xml /usr/local/tomcat/conf/server.xml
#ADD ./tomcat-config/context.xml /usr/local/tomcat/conf/context.xml

#mountpoint mappát csinálunk a volumenak
RUN mkdir $CATALINA_HOME/webapps/springTest	
ENV JPDA_ADDRESS="8000"
ENV JPDA_TRANSPORT="dt_socket"
EXPOSE 8080
ENTRYPOINT ["catalina.sh","jpda", "run"]



