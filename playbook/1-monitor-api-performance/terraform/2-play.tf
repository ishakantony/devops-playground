data "kubectl_path_documents" "dummy_api" {
  pattern = "../k8s/*.yaml"
}

resource "kubectl_manifest" "dummy_api" {
    for_each  = toset(data.kubectl_path_documents.dummy_api.documents)
    yaml_body = each.value

    depends_on = [ kubectl_manifest.ingress_nginx ]
}
