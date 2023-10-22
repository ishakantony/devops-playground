resource "helm_release" "loki-stack" {
  name             = "loki-stack"
  repository       = "https://grafana.github.io/helm-charts"
  chart            = "loki-stack"
  namespace        = "monitoring"
  version          = "2.9.11"
  create_namespace = true

  values = [file("values/loki-stack.yaml")]
}
