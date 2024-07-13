# View Traces with Jaeger

This playbook will spin up a few microservices that sends traces to Jaeger.

## How to run this?

### Requirement

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [K6](https://k6.io/) to run load testing

### Steps

1. Run `docker-compose up`
2. Run `k6 run tests/load-traffic.js`
3. Open your browser and navigate to [http://localhost:16686/](http://localhost:16686/)
