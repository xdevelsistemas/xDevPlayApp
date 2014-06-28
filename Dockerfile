FROM centos:latest
MAINTAINER Clayton Silva

RUN yum install -y wget && yum clean all

#add yum source mirror
RUN mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup && \
    cd /etc/yum.repos.d/ && \
    wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-6.repo 

#install epel
RUN yum localinstall -y  http://mirrors.aliyun.com/epel/6/x86_64/epel-release-6-8.noarch.rpm  && \
    rm /var/tmp/* -rf && yum clean all

#add epel source  mirror
RUN mv /etc/yum.repos.d/epel.repo /etc/yum.repos.d/epel.repo.backup && \
    mv /etc/yum.repos.d/epel-testing.repo /etc/yum.repos.d/epel-testing.repo.backup && \
    cd /etc/yum.repos.d/ && \
    wget -O /etc/yum.repos.d/epel.repo http://mirrors.aliyun.com/repo/epel-6.repo 

#install tools
RUN yum update -y && \
    yum install -y --enablerepo=epel  \
      unzip \
      tar  &&\
    yum clean all

CMD ["bash"]

#install oracle java
RUN wget "http://xdevel.com.br/downloads/jdk-7u60-linux-x64.rpm" \
         -O /var/tmp/jdk-7-linux-x64.rpm  && \
    yum localinstall -y /var/tmp/jdk-7-linux-x64.rpm && \
    rm /var/tmp/* -rf && yum clean all

ENV         JAVA_HOME /usr/java/default
ENV         ACTIVATOR_VERSION 1.2.2
# INSTALL TYPESAFE ACTIVATOR
RUN         cd /tmp && \
            wget http://downloads.typesafe.com/typesafe-activator/$ACTIVATOR_VERSION/typesafe-activator-$ACTIVATOR_VERSION.zip && \
            unzip typesafe-activator-$ACTIVATOR_VERSION.zip -d /usr/local && \
            mv /usr/local/activator-$ACTIVATOR_VERSION /usr/local/activator && \
            rm typesafe-activator-$ACTIVATOR_VERSION.zip

# COMMIT PROJECT FILES
ADD         app /root/app
ADD         test /root/test
ADD         conf /root/conf
ADD         public /root/public
ADD         build.sbt /root/
ADD         project/plugins.sbt /root/project/
ADD         project/build.properties /root/project/

# TEST AND BUILD THE PROJECT -- FAILURE WILL HALT IMAGE CREATION
RUN         cd /root; /usr/local/activator/activator test stage
RUN         rm /root/target/universal/stage/bin/*.bat

# TESTS PASSED -- CONFIGURE IMAGE
WORKDIR     /root
ENTRYPOINT  target/universal/stage/bin/$(ls target/universal/stage/bin)
EXPOSE      9000

