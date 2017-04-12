## Usage


### Build docker image

```
docker build -t task1 .  
```

### Run docker container

```
docker run -it -p 8080:8080 -e PORT=8080 task1
```

### Call via cURL

```
curl -X POST -d @example http://localhost:8080/compute
```

### Build JAR

```
sbt assembly
```

Self-contained jar will be located in `target/scala-2.11/task1-assembly-1.0.jar`
 
### Run

```
cat example | java -jar task1-assembly-1.0.jar
```