package no.ssb.barn.fiksio.client;

import com.google.common.collect.ImmutableMap;
import no.ks.fiks.io.klient.SendtMeldingApiModel;
import no.ssb.barn.fiksio.client.model.Melding;
import no.ssb.barn.fiksio.client.model.MeldingId;
import no.ssb.barn.fiksio.client.model.SendtMelding;
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
            .meldingType("meldingsprotokoll")
            .avsenderKontoId(UUID.randomUUID())
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
    void fromSendResponseWithKlientMeldingId() {
        final MeldingId klientMeldingId = new MeldingId(UUID.randomUUID());
        final SendtMeldingApiModel sendtMeldingApiModel = SendtMeldingApiModel.builder()
            .meldingId(UUID.randomUUID())
            .mottakerKontoId(UUID.randomUUID())
            .meldingType("meldingsprotokoll")
            .avsenderKontoId(UUID.randomUUID())
            .svarPaMelding(UUID.randomUUID())
            .dokumentlagerId(UUID.randomUUID())
            .ttl(TimeUnit.DAYS.toMillis(5L))
            .headere(ImmutableMap.of(Melding.HeaderKlientMeldingId, klientMeldingId.toString()))
            .build();
        final SendtMelding sendtMelding = SendtMelding.fromSendResponse(sendtMeldingApiModel);
        assertAll(
            () -> assertEquals(sendtMeldingApiModel.getMeldingId(), sendtMelding.getMeldingId().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getMottakerKontoId(), sendtMelding.getMottakerKontoId().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getAvsenderKontoId(), sendtMelding.getAvsenderKontoId().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getSvarPaMelding(), sendtMelding.getSvarPaMelding().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getTtl(), sendtMelding.getTtl().toMillis()),
            () -> assertEquals(sendtMeldingApiModel.getHeadere().get(Melding.HeaderKlientMeldingId), sendtMelding.getKlientMeldingId().toString())
        );
    }

    @Test
    void fromSendResponseWithKlientMeldingIdThatIsNotValidUUID() {
        final String klientMeldingId = "NotUUID";
        final SendtMeldingApiModel sendtMeldingApiModel = SendtMeldingApiModel.builder()
            .meldingId(UUID.randomUUID())
            .mottakerKontoId(UUID.randomUUID())
            .meldingType("meldingsprotokoll")
            .avsenderKontoId(UUID.randomUUID())
            .svarPaMelding(UUID.randomUUID())
            .dokumentlagerId(UUID.randomUUID())
            .ttl(TimeUnit.DAYS.toMillis(5L))
            .headere(ImmutableMap.of(Melding.HeaderKlientMeldingId, klientMeldingId))
            .build();
        final SendtMelding sendtMelding = SendtMelding.fromSendResponse(sendtMeldingApiModel);
        assertAll(
            () -> assertEquals(sendtMeldingApiModel.getMeldingId(), sendtMelding.getMeldingId().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getMottakerKontoId(), sendtMelding.getMottakerKontoId().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getAvsenderKontoId(), sendtMelding.getAvsenderKontoId().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getSvarPaMelding(), sendtMelding.getSvarPaMelding().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getTtl(), sendtMelding.getTtl().toMillis()),
            () -> assertEquals(sendtMeldingApiModel.getHeadere().get(Melding.HeaderKlientMeldingId), sendtMelding.getHeadere().get(Melding.HeaderKlientMeldingId)),
            () -> assertNull(sendtMelding.getKlientMeldingId())
        );
    }
}