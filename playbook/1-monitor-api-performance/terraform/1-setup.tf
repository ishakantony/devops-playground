data "kubectl_path_documents" "ingress_nginx" {
  pattern = "../k8s/ingress-nginx/*.yaml"
}

resource "kubectl_manifest" "ingress_nginx" {
    for_each  = toset(data.kubectl_path_documents.ingress_nginx.documents)
    yaml_body = each.value
}
