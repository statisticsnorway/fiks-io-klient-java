package no.ssb.barn.fiksio.client;

import no.ssb.barn.fiksio.client.konfigurasjon.*;
import no.ssb.barn.fiksio.client.model.KontoId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FiksIOKlientFactoryTest {

    @Test
    void build() {
        final KontoKonfigurasjon kontoKonfigurasjon = KontoKonfigurasjon.builder()
                                                                        .kontoId(new KontoId(UUID.randomUUID()))
                                                                        .privatNokkel(TestUtil.generatePrivateKey())
                                                                        .build();
        final VirksomhetssertifikatKonfigurasjon virksomhetssertifikatKonfigurasjon = VirksomhetssertifikatKonfigurasjon.builder()
                                                                                                                        .keyStorePassword("PASSWORD")
                                                                                                                        .keyStore(
                                                                                                                            TestUtil.readAliceVirksomhetssertifikat())
                                                                                                                        .keyAlias("et alias")
                                                                                                                        .keyPassword("PASSWORD")
                                                                                                                        .build();
        final IdPortenKonfigurasjon idPortenKonfigurasjon = IdPortenKonfigurasjon.builder()
                                                                                 .accessTokenUri("http://localhost")
                                                                                 .idPortenAudience("audience")
                                                                                 .klientId(UUID.randomUUID()
                                                                                               .toString())
                                                                                 .build();
        final FiksIntegrasjonKonfigurasjon fiksIntegrasjonKonfigurasjon = FiksIntegrasjonKonfigurasjon.builder()
                                                                                                      .integrasjonId(UUID.randomUUID())
                                                                                                      .integrasjonPassord(UUID.randomUUID()
                                                                                                                              .toString())
                                                                                                      .idPortenKonfigurasjon(idPortenKonfigurasjon)
                                                                                                      .build();
        final FiksApiKonfigurasjon fiksApiKonfigurasjon = FiksApiKonfigurasjon.builder()
                                                                              .host("localhost")
                                                                              .port(9190)
                                                                              .build();
        final FiksIOKonfigurasjon fiksIOKonfigurasjon = FiksIOKonfigurasjon.builder()
                                                                           .kontoKonfigurasjon(kontoKonfigurasjon)
                                                                           .virksomhetssertifikatKonfigurasjon(
                                                                                  virksomhetssertifikatKonfigurasjon)
                                                                           .fiksIntegrasjonKonfigurasjon(fiksIntegrasjonKonfigurasjon)
                                                                           .fiksApiKonfigurasjon(fiksApiKonfigurasjon)
                                                                           .build();
        assertThrows(RuntimeException.class, () -> new FiksIOKlientFactory(fiksIOKonfigurasjon).build());

    }

}