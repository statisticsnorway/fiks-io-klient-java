package no.ssb.barn.fiksio.client.konfigurasjon;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FiksApiKonfigurasjon implements HostKonfigurasjon{

    /**
     * Påkrevd felt.
     */
    @Builder.Default private String host;

    /**
     * Påkrevd felt.
     */
    @Builder.Default private Integer port;

    /**
     * Påkrevd felt.
     */
    @Builder.Default private String scheme;

    /**
     * Konfigurasjon for prod.
     */
    public static final FiksApiKonfigurasjon PROD = FiksApiKonfigurasjon.builder().host("api.fiks.ks.no").port(443).scheme("https").build();
    /**
     * Konfigurasjon for test.
     */
    public static final FiksApiKonfigurasjon TEST = FiksApiKonfigurasjon.builder().host("api.fiks.test.ks.no").port(443).scheme("https").build();
}
