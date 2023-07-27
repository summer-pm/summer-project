FROM node:lts-alpine as build-main-stage
WORKDIR /app/frontend
COPY frontend/package*.json ./
RUN npm install
COPY frontend/. .
RUN npm run build

FROM node:lts-alpine as build-chat-stage
WORKDIR /app/chat
COPY frontend-chat/package*.json ./
RUN npm install
COPY frontend-chat/. .
RUN npm run build


# этап production (production-stage)
FROM nginx:stable-alpine as production-stage
COPY frontend/nginx.conf /etc/nginx/nginx.conf
COPY --from=build-main-stage /app/frontend/dist /usr/share/nginx/html
COPY --from=build-chat-stage /app/chat/dist /usr/share/nginx/html/chat
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
