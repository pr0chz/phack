## Usage


### With docker

build image
```
Docker build -t task2 .
```
run program
```
cat example | docker run -i task2
```

### Without docker - prepare environment
```
virtualenv venv
$ source venv/bin/activate
venv$ pip install -r requirements.pip
```

Run directly python script
```
cat input | python task2.py
```
