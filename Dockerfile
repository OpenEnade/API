FROM openjdk:8

RUN sed -i 's/# \(.*multiverse$\)/\1/g' /etc/apt/sources.list && \
	apt-get update && \
	apt-get upgrade -y && \
	apt-get install -y maven nano

RUN git clone https://github.com/OpenEnade/API.git

WORKDIR /root/API

RUN mvn install -Dmaven.test.skip=true

CMD mvn spring-boot:run
