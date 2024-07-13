#!/bin/bash

kubectl delete -f k8s --context kind-0-loki-grafana
if [ $? -ne 0 ]; then
  echo "K8s delete failed."
  exit 1
fi

echo "K8s commands executed successfully."

terraform -chdir=terraform destroy --auto-approve
if [ $? -ne 0 ]; then
  echo "Terraform destroy failed."
  exit 1
fi

echo "Terraform commands executed successfully."

kind delete cluster --name 0-loki-grafana
if [ $? -ne 0 ]; then
  echo "Kind delete cluster failed."
  exit 1
fi

echo "Kind commands executed successfully."
