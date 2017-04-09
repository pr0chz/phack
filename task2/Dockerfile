FROM python:slim

COPY ./requirements.pip /app/requirements.pip

WORKDIR /app

RUN pip install -r requirements.pip

COPY . /app

ENTRYPOINT [ "python" ]

CMD [ "task2.py" ]
