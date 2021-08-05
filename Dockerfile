# from openjdk:8-jdk-oracle
from openjdk:8-jdk-buster

RUN apt-get update -yq \
   && apt-get install curl gnupg -yq \
   && curl -sL https://deb.nodesource.com/setup_8.x | bash \
   && apt-get install nodejs -yq \
   && apt-get install npm -yq \
   && apt-get clean -y

# Installs Ant
ENV ANT_VERSION 1.10.11
RUN cd && \
    wget -q https://downloads.apache.org/ant/binaries/apache-ant-${ANT_VERSION}-bin.tar.gz && \
    tar -xzf apache-ant-${ANT_VERSION}-bin.tar.gz && \
    mv apache-ant-${ANT_VERSION} /opt/ant && \
    rm apache-ant-${ANT_VERSION}-bin.tar.gz
ENV ANT_HOME /opt/ant
ENV PATH ${PATH}:/opt/ant/bin

# RUN wget https://bintray.com/vertx/downloads/download_file?file_path=vert.x-2.1.6.tar.gz -O vert.x-2.1.6.tar.gz
COPY lib/vert.x-2.1.6.tar.gz /
RUN tar xzf /vert.x-2.1.6.tar.gz
RUN cp vert.x-2.1.6/bin/vertx /usr/bin/

WORKDIR /soile

ADD http_server http_server

RUN npm install -g bower gulp

WORKDIR /soile/http_server/src/main/resources/
RUN npm i

WORKDIR /soile/http_server
RUN npm i

# RUN npm run gulp
RUN gulp install --allow-root
# RUN npm run gulpsass --allow-root

WORKDIR /soile
ADD . .


# run apt-get install openjdk-8-jdk
RUN chmod +x http_server/gradlew
run bash deployAll.sh

WORKDIR /soile/prod
RUN cp jars/apache-log4j-extras-1.2.17.jar /vert.x-2.1.6/lib/

run chmod +x docker_run.sh
CMD ["./docker_run.sh"]
#CMD ["./docker_run.sh && watch -n 2 bash ../rsync_http.sh"]
# ENTRYPOINT ["tail"]
# CMD ["-f","/dev/null"]
