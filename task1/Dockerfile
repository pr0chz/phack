FROM 1science/sbt:0.13.8-oracle-jre-8

MAINTAINER code.vire@gmail.com

WORKDIR /app

RUN sbt update

COPY . /app

RUN sbt compile

CMD [ "sbt", "run"]

