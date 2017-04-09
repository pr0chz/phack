FROM python:slim

COPY ./requirements.pip /app/requirements.pip

WORKDIR /app

RUN pip install -r requirements.pip

COPY . /app

ENTRYPOINT [ "python" ]

CMD [ "server.py" ]
