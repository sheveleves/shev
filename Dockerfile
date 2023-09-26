FROM eclipse-temurin:20-jdk
WORKDIR /APP

COPY . .
RUN ./gradlew build
CMD java -cp ./build/classes/java/main org.example.Main




