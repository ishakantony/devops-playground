#!/bin/bash

# Function to check if a command exists
command_exists() {
  command -v "$1" >/dev/null 2>&1
}

# Check for terraform
if command_exists terraform; then
  echo "Terraform is installed."
else
  echo "Terraform is not installed. Install Terraform at https://www.terraform.io/"
  exit 1
fi

# Check for kind
if command_exists kind; then
  echo "Kind is installed."
else
  echo "Kind is not installed. Install Kind at https://kind.sigs.k8s.io/"
  exit 1
fi

# Check for kubectl
if command_exists kubectl; then
  echo "Kubectl is installed."
else
  echo "Kubectl is not installed."
  exit 1
fi

# Check for existing terraform files/directories
terraform_files=(
  "terraform/.terraform"
  "terraform/.terraform.lock.hcl"
  "terraform/terraform.tfstate"
  "terraform/terraform.tfstate.backup"
)

for file in "${terraform_files[@]}"; do
  if [ -e "$file" ]; then
    echo "Terraform file/directory '$file' exists. Please clean up your terraform setup before proceeding."
    exit 1
  fi
done

# Check if the cluster already exists
if kind get clusters | grep -q '0-loki-grafana'; then
  echo "Cluster '0-loki-grafana' already exists. Please delete the cluster before creating a new one."
  exit 1
fi

# Create Kubernetes Cluster
kind create cluster --name 0-loki-grafana

if [ $? -eq 0 ]; then
  echo "Cluster '0-loki-grafana' created successfully."

  # Run terraform commands
  terraform -chdir=terraform init
  if [ $? -ne 0 ]; then
    echo "Terraform initialization failed."
    exit 1
  fi

  terraform -chdir=terraform apply --auto-approve
  if [ $? -ne 0 ]; then
    echo "Terraform apply failed."
    exit 1
  fi

  echo "Terraform commands executed successfully."

  kubectl apply -f k8s --context kind-0-loki-grafana
  if [ $? -ne 0 ]; then
    echo "K8s apply failed."
    exit 1
  fi

  echo "K8s commands executed successfully."

else
  echo "Failed to create cluster '0-loki-grafana'."
  exit 1
fi
