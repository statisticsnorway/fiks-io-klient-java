package no.ssb.barn.fiksio.client;

import no.ssb.barn.fiksio.client.model.KontoId;

import java.security.cert.X509Certificate;

public interface PublicKeyProvider {

    X509Certificate getPublicKey(final KontoId kontoId);

}
