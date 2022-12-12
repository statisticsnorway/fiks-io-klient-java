package no.ssb.barn.fiksio.client.model;

import java.util.Map;

public interface Melding {

    static final String HeaderKlientMeldingId = "klientMeldingId";

    MeldingId getMeldingId();

    KontoId getAvsenderKontoId();

    KontoId getMottakerKontoId();

    java.time.Duration getTtl();

    MeldingId getSvarPaMelding();

    String getMeldingType();

    Map<String, String> getHeadere();

    MeldingId getKlientMeldingId();
}
