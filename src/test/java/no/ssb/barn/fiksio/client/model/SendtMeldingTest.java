package no.ssb.barn.fiksio.client.model;

import no.ks.fiks.io.klient.SendtMeldingApiModel;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class SendtMeldingTest {

    @Test
    void fromSendResponse() {
        final SendtMeldingApiModel sendtMeldingApiModel = SendtMeldingApiModel.builder()
            .meldingId(UUID.randomUUID())
            .mottakerKontoId(UUID.randomUUID())
            .avsenderKontoId(UUID.randomUUID())
            .meldingType("meldingsprotokoll")
            .svarPaMelding(UUID.randomUUID())
            .dokumentlagerId(UUID.randomUUID())
            .ttl(TimeUnit.DAYS.toMillis(5L))
            .headere(Collections.emptyMap())
            .build();
        final SendtMelding sendtMelding = SendtMelding.fromSendResponse(sendtMeldingApiModel);
        assertAll(
            () -> assertEquals(sendtMeldingApiModel.getMeldingId(), sendtMelding.getMeldingId().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getMottakerKontoId(), sendtMelding.getMottakerKontoId().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getAvsenderKontoId(), sendtMelding.getAvsenderKontoId().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getSvarPaMelding(), sendtMelding.getSvarPaMelding().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getTtl(), sendtMelding.getTtl().toMillis())
        );
    }

    @Test
    void fromSendResponseUtenTTL() {
        final SendtMeldingApiModel sendtMeldingApiModel = SendtMeldingApiModel.builder()
            .meldingId(UUID.randomUUID())
            .mottakerKontoId(UUID.randomUUID())
            .avsenderKontoId(UUID.randomUUID())
            .meldingType("meldingsprotokoll")
            .svarPaMelding(UUID.randomUUID())
            .dokumentlagerId(UUID.randomUUID())
            .headere(Collections.emptyMap())
            .build();
        final SendtMelding sendtMelding = SendtMelding.fromSendResponse(sendtMeldingApiModel);
        assertAll(
            () -> assertEquals(sendtMeldingApiModel.getMeldingId(), sendtMelding.getMeldingId().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getMottakerKontoId(), sendtMelding.getMottakerKontoId().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getAvsenderKontoId(), sendtMelding.getAvsenderKontoId().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getSvarPaMelding(), sendtMelding.getSvarPaMelding().getUuid()),
            () -> assertNull(sendtMelding.getTtl())
        );
    }
}