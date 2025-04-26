FROM ubuntu:xenial

# SETUP THE ENVIRONMENT
ENV PATH=/opt/apache-maven-3.5.3/bin:$PATH
ENV MAVEN_OPTS='-Xms512m -Xmx1024m -Djava.util.Arrays.useLegacyMergeSort=true'

# INSTALL DEPENDENCIES
RUN apt-get update -yq
RUN apt-get install -yq --no-install-recommends wget ca-certificates xz-utils libtcnative-1

# INSTALL JAVA
RUN apt-get update -yq && apt-get upgrade -yq
RUN apt-get install -yq --no-install-recommends openjdk-8-jdk maven

# RETRIEVE AND EXTRACT THE SOURCE CODE                                                                                                                                
WORKDIR /                                                                                                                                                             
RUN wget https://samate.nist.gov/SATE6/sakai-11.2-sate6.tar.xz                                                                                                        
RUN tar --no-same-owner -Jxf sakai-11.2-sate6.tar.xz                                                                                                                  
RUN find . -name pom.xml -exec bash -c 'sed -i "s;http://repo1.maven.org/maven2;https://repo1.maven.org/maven2;g" "$0"' {} \;                                         
RUN find . -name pom.xml -exec bash -c 'sed -i "s;http://nexus.opencast.org/nexus/content/groups/public;https://repo1.maven.org/maven2;g" "$0"' {} \;                 
RUN ln -s sakai-11.2-buggy sources_buggy                                                                                                                              
RUN ln -s sakai-11.2-fixed sources_fixed                                                                                                                              

# COMPILE THE SOURCE CODE                                                                                                                                             
WORKDIR /sources_buggy                                                                                                                                                
RUN mvn clean install -U sakai:deploy -Dmaven.tomcat.home=/target_buggy -Djava.net.preferIPv4Stack=true -Dmaven.test.skip=true                                        

WORKDIR /sources_fixed                                                                                                                                                
RUN mvn clean install -U sakai:deploy -Dmaven.tomcat.home=/target_fixed -Djava.net.preferIPv4Stack=true -Dmaven.test.skip=true                                        

WORKDIR /
