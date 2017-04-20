## Usage


### Build JAR

```
sbt assembly
```

Self-contained jar will be located in `target/scala-2.11/task1-assembly-1.0.jar`

### Build docker image

This will package JAR from previous step into the docker image.

```
docker build -t task1 .
```
 
### Run

Run directly JAR or docker image.

```
cat example | java -jar task1-assembly-1.0.jar
docker run -it -p 8080:8080 -e PORT=8080 task1
```

### Call via cURL

```
curl -X POST -d @example http://localhost:8080/compute
```
