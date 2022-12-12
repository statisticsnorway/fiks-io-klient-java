package no.ssb.barn.fiksio.client.model;

import no.ks.fiks.io.asice.model.Content;

import java.io.InputStream;

public interface Payload {
    String getFilnavn();
    InputStream getPayload();

    Content toContent();

}
