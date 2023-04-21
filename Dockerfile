FROM maven:3.8.1-jdk-8

# Copy the pom and the code to the docker filesystem
ADD pom.xml /docker/
ADD src/ /docker/src/

# Folder for our temporary files, i.e., screenshots
RUN mkdir -p /docker/tmp

# Execute our maven command
RUN cd /docker/ && mvn clean test