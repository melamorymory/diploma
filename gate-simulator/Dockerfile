FROM node:16.14.0
WORKDIR /src/app
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 9999
CMD [ "node", "app.js" ]