FROM golang:1.21.3 AS build

WORKDIR /workspace

COPY go.mod ./

COPY go.sum ./

RUN go mod download && go mod verify

COPY cmd/api/main.go ./cmd/api/main.go

RUN go build -o /app cmd/api/main.go

FROM gcr.io/distroless/base-debian12

COPY --from=build /app /app

ARG BUILD_VERSION=V1

ENV VERSION=${BUILD_VERSION}

EXPOSE 10000/tcp

ENTRYPOINT ["/app"]
