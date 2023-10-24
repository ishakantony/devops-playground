import { sleep } from 'k6';
import http from 'k6/http';

export let options = {
    stages: [
        { duration: '5s', target: 5 },    // Ramp up to 5 VUs in 30 seconds
        { duration: '10s', target: 50 },   // Increase to 50 VUs in 30 seconds
        { duration: '5s', target: 200 },  // Increase to 200 VUs in 30 seconds
        { duration: '10s', target: 70 },   // Decrease to 70 VUs in 30 seconds
        { duration: '30s', target: 10 },   // Decrease to 10 VUs in 30 seconds
        { duration: '30s', target: 100 },  // Increase to 100 VUs in 30 seconds
        { duration: '30s', target: 0 },    // Ramp down to 0 VUs in 30 seconds
    ],
};

export default function () {
    // Make an HTTP GET request to http://localhost:8080/
    http.get('http://dummy-api.ishakantony.local/');

    // Sleep for a short duration between requests
    sleep(1);
}
