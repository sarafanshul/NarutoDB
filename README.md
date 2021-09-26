# Naruto DataBase API

## Description
Web scraping API created using [JSOUP](https://jsoup.org) to get the Naruto anime data from
the [NarutoFandom](https://naruto.fandom.com/wiki/Narutopedia) web page and save it in the mongoDB database.

## What's New (v-1.1)
- Add new endpoints in characters and episodes for ease and extensiblity
- The endpoints are now query based like `/id?id=x` instead of old statically mapped `id/x` for ease
- Add autuator for metrics and heath statistics
- Add Profiles `dev` and `prod` for different usecase

## Technologies used
* Java
* Spring Boot
* JSOUP
* Maven
* MongoDB

## Requests

<details><summary>Example Character Endpoint</summary>

```http
POST /character/id
```

```http
GET /character/id
GET /character/name
GET /character/like
GET /character/all
GET /character/page
GET /character/power
GET /character/like_paged
GET /character/core
```

### Example
* Use the same name found at the end of the url
    * https://naruto.fandom.com/wiki/Naruto_Uzumaki
* Use an HTTP POST /id request to save data in mongoDB
   * **localhost:8080/clan/id?id=Hyuga_Clan**
* Use an HTTP GET /like request to regex match data in mongoDB
    * **localhost:8080/character/like?name=hina**
* Use an HTTP GET /all request to fetch all save data in mongoDB
    * **localhost:8080/character/all**
* Use an HTTP GET /page request to fetch pageable data in mongoDB
    * **localhost:8080/character/page?page=0&size=5&sort=id,asc**

</details>


### How to build a jar file yourself.
- Make sure you are on release branch
- add `<packaging>jar</packaging>` to your `pom.xml` | refer [this](https://www.youtube.com/watch?v=UvyYv3WhzjI)
- build with `mvn clean package`
- if you get `no main manifest attribute, in .\NarutoDB-1.0-SNAPSHOT.jar`
- build with `mvn package spring-boot:repackage`
- run the `**SNAPSHOT.jar` with `java -jar filename.jar`

### How to setup your own metrics
- Add Spring Boot Actuator to your dependency
- Setup your metrics properties , refer [here](https://levelup.gitconnected.com/application-monitoring-using-spring-boot-actuators-part-1-dab8576f4db6)
- toggle properties according to need and setup a different port for metrics
- Access the ports for requests metrics `host:port/actuator/metrics/http.server.requests`
