FROM python:3.10-slim

ENV PYTHONUNBUFFERED True

ENV APP_HOME /app
WORKDIR $APP_HOME
copy . ./

RUN pip instal -r requirement.txt

CMD exex gunicorn --bind :$PORT --worker 1 --threads 8 --timeout 0 main:app