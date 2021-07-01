FROM openjdk:11

EXPOSE 8080

ADD target/search-list.jar search-list.jar

ENTRYPOINT [ "java", "-jar", "search-list.jar" ]