# FROM 사용할 base image
# Use the official Windows Server Core as the base image
FROM openjdk:17-jdk-slim

# compile profile prod => docker 용 (MySQL)
ENV SPRING_PROFILES_ACTIVE=prod

# open 시켜놓을 port
EXPOSE 8080

# 실행시킬 jar file 이름은 argument 로 받을것이며
ARG JAR_FILE

# argument 로 받은 jar file 명에 해당하는 파일을 docker 내부에 app.jar 로 저장
COPY ${JAR_FILE} app.jar
#"-Djava.security.edg=file:/dev/./urandom",
#"-Dspring.profiles.active=prod",

# 해당 파일 복사가 되었을 때 /app.jar 를 아래 command 로 실행시킬 것이다. 
ENTRYPOINT ["java", "-jar", "/app.jar"]