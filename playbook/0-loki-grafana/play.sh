#!/bin/bash

GRAFANA_ADMIN_PASSWORD=$(kubectl get secret loki-stack-grafana --context kind-0-loki-grafana -n monitoring -o jsonpath="{.data.admin-password}" | base64 --decode)
if [ $? -eq 0 ]; then
  echo "GRAFANA ADMIN USERNAME: admin"
  echo "GRAFANA ADMIN PASSWORD: $GRAFANA_ADMIN_PASSWORD"
else
  echo "Failed to retrieve Grafana admin password."
  exit 1
fi

# Run kubectl port-forward
kubectl port-forward --context kind-0-loki-grafana -n monitoring service/loki-stack-grafana 3000:80 &

# Get the PID of the last background process
KUBECTL_PID=$!

# Wait a few seconds to ensure port-forward is established
sleep 5

# Open the default web browser with the specified URL
xdg-open http://localhost:3000

# Optional: Wait for the user to press Enter to terminate the port-forward
read -p "Press Enter to stop port-forwarding and close the script..."

# Terminate the kubectl port-forward process
kill $KUBECTL_PID
