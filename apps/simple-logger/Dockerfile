FROM golang:1.21.3 AS build

WORKDIR /workspace

COPY go.mod ./

RUN go mod download && go mod verify

COPY cmd/main.go ./cmd/main.go

RUN go build -o /app cmd/main.go

FROM gcr.io/distroless/base-debian12

COPY --from=build /app /app

ENTRYPOINT ["/app"]
