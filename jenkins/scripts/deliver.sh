#!/usr/bin/env bash

#Kubernetes run
echo 'run k8s.'
set -x
kubectl apply -f kubernetes-conf.yaml
set +x

echo 'END DELIVER.'

