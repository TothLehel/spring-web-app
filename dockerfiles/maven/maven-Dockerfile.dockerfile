FROM maven:3.3.9 as build
COPY ./src /usr/src/app
#COPY ./src /usr/src/app
COPY ./pom.xml /usr/src/app/pom.xml
WORKDIR /usr/src/app
#az alábbi kód kell az entr-hez mivel alapjáraton nem találja a helyét
RUN echo "deb [check-valid-until=no] http://archive.debian.org/debian jessie-backports main" > /etc/apt/sources.list.d/jessie-backports.list
RUN sed -i '/deb http:\/\/deb.debian.org\/debian jessie-updates main/d' /etc/apt/sources.list
RUN apt-get -o Acquire::Check-Valid-Until=false update && apt-get install entr -y 
RUN mvn clean install