FROM node:lts-alpine as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build



# этап production (production-stage)
FROM nginx:stable-alpine as production-stage
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build-stage /app/dist /usr/share/nginx/html
COPY install-serts.sh /
RUN apk add --update python3 py3-pip
RUN apk add --no-cache --upgrade bash
RUN apk add certbot
RUN pip install certbot-nginx
EXPOSE 80
CMD ["su","./install-serts.sh"]
