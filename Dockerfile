FROM claytonsilva/docker-centos-play:latest
MAINTAINER Clayton Silva <clayton@xdevel.com.br>
# COMMIT PROJECT FILES
ADD         app /root/app
ADD         test /root/test
ADD         conf /root/conf
ADD         public /root/public
ADD         project   /root/project
ADD	    modules /root/modules
# TEST AND BUILD THE PROJECT -- FAILURE WILL HALT IMAGE CREATION
RUN         cd /root; /usr/local/activator/activator test stage
RUN         rm /root/target/universal/stage/bin/*.bat
# TESTS PASSED -- CONFIGURE IMAGE
WORKDIR /root


EXPOSE 9000

#comando para executar a aplicacao rodando na porta 900
ENTRYPOINT /root/target/universal/stage/bin/$(ls /root/target/universal/stage/bin/) -J-Xms128M -J-Xmx512M -J-server  -DConfig=application.conf  -DapplyEvolutions.default=true

