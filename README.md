# Naruto DB

## ALERT
RUN THIS ON JAVA version != 13 , it'll cause `SSLHandshakeException`

## Description
Rest API for Naruto Anime created using [JSOUP](https://jsoup.org) to get the data from
the [NarutoFandom](https://naruto.fandom.com/wiki/Narutopedia) web page and save it in the mongoDB database
for caching.

## What's New (v-1.2.*)
- v.1.2.0
    - change data-base config from cli using `-Dspring-boot.run.arguments=--database.host=XXX,database.name=XXX`

## What's New (v-1.1.*)
- v.1.1.3
    - Add new Debutante endpoint for fetching debut anime info of a character
    - New 404 page
- v-1.1.2
    - Add new Izanami endpoint for fetching ranged/filtered info about Chapter
- v-1.1.1
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

## How To Guide

### How to use the API

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
* Others
   * chapters example
      * **localhost:8080/chapter/id?id=A_Shinobi's_Determination**
      * **localhost:8080/chapter/sorted?page=1**

   * character example
      * **localhost:8080/character/id?id=Hinata_Hy??ga**
      * **localhost:8080/character/name?name=hinata**
      * **localhost:8080/character/like_paged/?name=itac&size=10**

   * village exapmle
      * **localhost:8080/village/page/?page=1&size=5&sort=name.english**
      * **localhost:8080/village/like?name=konoha**

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
- Build the project , if want to override the default after build pass the args `-Dspring.profiles.active=dev` , see [this](https://stackoverflow.com/questions/37700352/setting-the-default-active-profile-in-spring-boot/37700521)

## Contributing 

[![Maintaner](https://img.shields.io/badge/maintainer-AnshulSaraf-Green)](https://github.com/sarafanshul)

[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

## License

    Copyright 2021 Anshul Saraf

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
