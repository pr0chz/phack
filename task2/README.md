## Usage


### With docker

1. build image
    ```
    Docker build -t task2 .
    ```
1. run program
    ```
    cat example | docker run -i task2
    ```

1. run server

    ```
    docker run -it -p 8080:8080 task2 server.py
    ```

1. then call server with the example data

      ```
      curl -X POST -d @example http://localhost:9999
      ```

### Without docker

1. prepare environment
    ```
    virtualenv venv
    $ source venv/bin/activate
    venv$ pip install -r requirements.pip
    ```

1. Run directly python script
    ```
    cat input | python task2.py
    ```
