package no.ssb.barn.fiksio.client;

import no.ssb.barn.fiksio.client.model.KontoId;

import java.security.cert.X509Certificate;

public class KatalogPublicKeyProvider implements PublicKeyProvider {

    final private KatalogHandler katalogHandler;

    public KatalogPublicKeyProvider(KatalogHandler katalogHandler) {
        this.katalogHandler = katalogHandler;
    }

    @Override
    public X509Certificate getPublicKey(KontoId kontoId) {
        return katalogHandler.getPublicKey(kontoId);
    }

}
