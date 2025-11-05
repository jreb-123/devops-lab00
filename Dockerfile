FROM eclipse-temurin:17-jdk-alpine
COPY App.java /App.java
RUN javac App.java
EXPOSE 8080
CMD ["java", "App"]
