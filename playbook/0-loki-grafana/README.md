# View Logs in Loki

This playbook will install Loki to consolidate the logs, Promtail to collect the logs from the nodes and Grafana to visualize it.

There's a simple logger app setup to simulate adding logs.

## How to run this?

### Requirement

- Install [Terraform](https://www.terraform.io/)
- Have a Kubernetes cluster ready

### Steps (Base)

1. Open your terminal, make sure you are in this folder (`/playbook/0-loki-grafana`)
2. Run `terraform -chdir=terraform init`
3. Run `terraform -chdir=terraform apply`
4. To get Grafana admin password, run `kubectl get secret loki-stack-grafana -n monitoring -o jsonpath="{.data.admin-password}" | base64 --decode`
5. To access Grafana from your host machine, run `kubectl port-forward -n monitoring service/loki-stack-grafana 3000:80`. You can access Grafana at [http://localhost:3000](http://localhost:3000) provided the port is not being used.
6. The Loki datasource should've been setup already, just in case it doesn't, simply setup the datasource.
7. You can use the **Explore** menu in Grafana to access Loki

### Steps (Adding App)

Now that we have the base setup up and running, let's add an app to simulate real environment. The manifest is already setup at `k8s` folder.

1. Run `kubectl apply -f k8s` to create the deployment
2. Check the deployment is ready by doing `kubectl get pods -n default -w`, it will watch the command, once all pods ready, simply exit the command
3. Check the logs are coming in Loki, you can filter by `{app="simple-logger"}` in the **Log browser**.

### How to get Promtail config?

1. Run `kubectl get secret loki-stack-promtail -o jsonpath="{.data.promtail\.yaml}" | base64 --decode > promtail.yaml`
2. If you wanna update the config, simply update the secret and delete the promtail pods or restart the DeamonSet.
