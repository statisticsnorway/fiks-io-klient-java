package no.ssb.barn.fiksio.client.konfigurasjon;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import no.ssb.barn.fiksio.client.model.KontoId;

import java.security.PrivateKey;

/**
 * Konfigurer Fiks IO kontoen som skal benyttes for å sende meldinger.
 */
@Value
@Builder
public class KontoKonfigurasjon {
    /**
     * Konfigurasjon for nøkler og sertifikater som skal benyttes for å signere utgående meldinger
     */
    @NonNull
    KontoId kontoId;

    /**
     * Påkrevd felt. Privat nøkkel som matcher det offentlige sertifikatet som er spesifisert for kontoen i fiks-konfigurasjon. Benyttes for å dekryptere inkommende meldinger.
     */
    @NonNull
    PrivateKey privatNokkel;

    @Override
    public String toString() {
        return "KontoKonfigurasjon{" +
            "kontoId=" + kontoId +
            ", privatNokkel=*****" +
            '}';
    }
}
