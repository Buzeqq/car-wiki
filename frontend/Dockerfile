FROM node:18 AS builder

WORKDIR /app

COPY . .

RUN npm i && npm run build

FROM nginx

WORKDIR /usr/share/nginx/html

RUN rm -rf ./*

COPY --from=builder /app/dist/frontend .

EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]
