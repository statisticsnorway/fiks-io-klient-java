package no.ssb.barn.fiksio.client.konfigurasjon;

import no.ssb.barn.fiksio.client.TestUtil;
import no.ssb.barn.fiksio.client.model.KontoId;
import org.junit.jupiter.api.Test;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FiksIOKonfigurasjonTest {

    @Test
    void defaultProdConfiguration() {
        final String clientId = UUID.randomUUID().toString();
        final UUID integrationId = UUID.randomUUID();
        final String integrationPassword = UUID.randomUUID().toString();
        final PrivateKey privateKey = TestUtil.generatePrivateKey();
        final UUID kontoId = UUID.randomUUID();
        final String keyStorePassword = UUID.randomUUID().toString();
        final String keyAlias = UUID.randomUUID().toString();
        final String keyPassword = UUID.randomUUID().toString();
        final KeyStore keyStore = TestUtil.readAliceVirksomhetssertifikat();

        final FiksIOKonfigurasjon fiksIOKonfigurasjon = FiksIOKonfigurasjon.defaultProdConfiguration(
            clientId,
            integrationId,
            integrationPassword,
            KontoKonfigurasjon.builder()
                .kontoId(new KontoId(kontoId))
                .privatNokkel(privateKey)
                .build(),
            VirksomhetssertifikatKonfigurasjon.builder()
                .keyAlias(keyAlias)
                .keyPassword(keyPassword)
                .keyStore(keyStore)
                .keyStorePassword(keyStorePassword)
                .build());

        assertEquals(clientId, fiksIOKonfigurasjon.getFiksIntegrasjonKonfigurasjon().getIdPortenKonfigurasjon().getKlientId());
        assertEquals(integrationId, fiksIOKonfigurasjon.getFiksIntegrasjonKonfigurasjon().getIntegrasjonId());
        assertEquals(integrationPassword, fiksIOKonfigurasjon.getFiksIntegrasjonKonfigurasjon().getIntegrasjonPassord());
        assertEquals(privateKey, fiksIOKonfigurasjon.getKontoKonfigurasjon().getPrivatNokkel());
        assertEquals(kontoId, fiksIOKonfigurasjon.getKontoKonfigurasjon().getKontoId().getUuid());
        assertEquals(keyStorePassword, fiksIOKonfigurasjon.getVirksomhetssertifikatKonfigurasjon().getKeyStorePassword());
        assertEquals(keyAlias, fiksIOKonfigurasjon.getVirksomhetssertifikatKonfigurasjon().getKeyAlias());
        assertEquals(keyPassword, fiksIOKonfigurasjon.getVirksomhetssertifikatKonfigurasjon().getKeyPassword());
        assertEquals(keyStore, fiksIOKonfigurasjon.getVirksomhetssertifikatKonfigurasjon().getKeyStore());

        assertEquals("api.fiks.ks.no", fiksIOKonfigurasjon.getFiksApiKonfigurasjon().getHost());
        assertEquals(443, fiksIOKonfigurasjon.getFiksApiKonfigurasjon().getPort());
        assertEquals("io.fiks.ks.no", fiksIOKonfigurasjon.getAmqpKonfigurasjon().getHost());
        assertEquals(5671, fiksIOKonfigurasjon.getAmqpKonfigurasjon().getPort());
    }

    @Test
    void defaultTestConfiguration() {
        final String clientId = UUID.randomUUID().toString();
        final UUID integrationId = UUID.randomUUID();
        final String integrationPassword = UUID.randomUUID().toString();
        final PrivateKey privateKey = TestUtil.generatePrivateKey();
        final UUID kontoId = UUID.randomUUID();
        final String keyStorePassword = UUID.randomUUID().toString();
        final String keyAlias = UUID.randomUUID().toString();
        final String keyPassword = UUID.randomUUID().toString();
        final KeyStore keyStore = TestUtil.readAliceVirksomhetssertifikat();

        final FiksIOKonfigurasjon fiksIOKonfigurasjon = FiksIOKonfigurasjon.defaultTestConfiguration(
            clientId,
            integrationId,
            integrationPassword,
            KontoKonfigurasjon.builder()
                .kontoId(new KontoId(kontoId))
                .privatNokkel(privateKey)
                .build(),
            VirksomhetssertifikatKonfigurasjon.builder()
                .keyAlias(keyAlias)
                .keyPassword(keyPassword)
                .keyStore(keyStore)
                .keyStorePassword(keyStorePassword)
                .build());

        assertEquals(clientId, fiksIOKonfigurasjon.getFiksIntegrasjonKonfigurasjon().getIdPortenKonfigurasjon().getKlientId());
        assertEquals(integrationId, fiksIOKonfigurasjon.getFiksIntegrasjonKonfigurasjon().getIntegrasjonId());
        assertEquals(integrationPassword, fiksIOKonfigurasjon.getFiksIntegrasjonKonfigurasjon().getIntegrasjonPassord());
        assertEquals(privateKey, fiksIOKonfigurasjon.getKontoKonfigurasjon().getPrivatNokkel());
        assertEquals(kontoId, fiksIOKonfigurasjon.getKontoKonfigurasjon().getKontoId().getUuid());
        assertEquals(keyStorePassword, fiksIOKonfigurasjon.getVirksomhetssertifikatKonfigurasjon().getKeyStorePassword());
        assertEquals(keyAlias, fiksIOKonfigurasjon.getVirksomhetssertifikatKonfigurasjon().getKeyAlias());
        assertEquals(keyPassword, fiksIOKonfigurasjon.getVirksomhetssertifikatKonfigurasjon().getKeyPassword());
        assertEquals(keyStore, fiksIOKonfigurasjon.getVirksomhetssertifikatKonfigurasjon().getKeyStore());

        assertEquals("api.fiks.test.ks.no", fiksIOKonfigurasjon.getFiksApiKonfigurasjon().getHost());
        assertEquals(443, fiksIOKonfigurasjon.getFiksApiKonfigurasjon().getPort());
        assertEquals("io.fiks.test.ks.no", fiksIOKonfigurasjon.getAmqpKonfigurasjon().getHost());
        assertEquals(5671, fiksIOKonfigurasjon.getAmqpKonfigurasjon().getPort());
    }


}