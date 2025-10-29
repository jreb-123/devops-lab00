FROM openjdk:8-jdk-alpine
COPY App.java /App.java
RUN javac App.java
EXPOSE 8080
CMD ["java", "App"]
