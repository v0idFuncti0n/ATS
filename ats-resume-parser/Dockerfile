FROM python:3.8-slim-buster

RUN apt-get update
RUN apt-get install -y build-essential libpoppler-cpp-dev pkg-config python-dev iputils-ping

WORKDIR /ats-resume-parser

COPY . .

EXPOSE 5000

ENV OPENAI_API_KEY='insert openai key'

RUN pip3 install -r ./build/requirements.txt

CMD ["sh", "run.sh"]