package com.marcnuri.yakc.quarkus.extension.deployment;

import javax.net.ssl.SSLHandshakeException;

import org.jboss.logging.Logger;

public class KubernetesClientErrorHanlder {

    private static final Logger LOG = Logger.getLogger(KubernetesClientErrorHanlder.class);

    public static void handle(Exception e) {
        if (e.getCause() instanceof SSLHandshakeException) {
            LOG.error(
                    "The application could not be deployed to the cluster because the Kubernetes API Server certificates are not trusted. The certificates can be configured using the relevant configuration properties under the 'quarkus.yakc' config root, or \"quarkus.yakc.insecure-skip-tls-verify=true\" can be set to explicitly trust the certificates (not recommended)");
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            } else {
                throw new RuntimeException(e);
            }
        }
    }
}
