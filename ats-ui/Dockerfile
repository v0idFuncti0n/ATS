FROM node:alpine

WORKDIR /usr/src/app

COPY . /usr/src/app

RUN npm install -g @angular/cli@16.2.0

RUN npm install --legacy-peer-deps

CMD ["ng", "serve", "--configuration=production", "--host", "0.0.0.0"]
