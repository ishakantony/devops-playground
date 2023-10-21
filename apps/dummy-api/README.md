# Dummy API

Simple API to simulate success and failed responses

## How to Use this App

### V1

V1 will always return success response with maximum response time of 3000ms

```shell
# From project root
cd ./apps/dummy-api

docker image build --build-arg BUILD_VERSION=V1 -t dummy-api:v1 .

docker container run --rm -p 10000:10000 dummy-api:v1
```

### V2

V2 will return either success or failed response with various status codes. Success response max response time is 1000ms whilst the error response between 1000ms and 4000ms

```shell
# From project root
cd ./apps/dummy-api

docker image build --build-args BUILD_VERSION=V2 -t dummy-api:v2 .

docker container run --rm -p 10000:10000 dummy-api:v2
```

### Vx

You can name this whatever you want using the `BUILD_VERSION` args. It will response less than 200ms and always success

```shell
# From project root
cd ./apps/dummy-api

docker image build --build-args BUILD_VERSION=V3 -t dummy-api:v3 .

docker container run --rm -p 10000:10000 dummy-api:v3
```

## Build All

```shell
# From project root
cd ./apps/dummy-api

docker image build --build-arg BUILD_VERSION=V1 -t dummy-api:v1 . && \
docker image build --build-arg BUILD_VERSION=V2 -t dummy-api:v2 . && \
docker image build --build-arg BUILD_VERSION=V3 -t dummy-api:v3 .

```

## Test the App

```shell

# Make request every 200ms
while true; do curl -s http://localhost:10000; sleep 0.2; done

```