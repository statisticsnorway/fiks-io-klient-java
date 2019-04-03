package no.ks.fiks.svarinn.client.model;

import no.ks.fiks.svarinn2.klient.SendtMeldingApiModel;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SendtMeldingTest {

    @Test
    void fromSendResponse() {
        final SendtMeldingApiModel sendtMeldingApiModel = SendtMeldingApiModel.builder()
                                                                     .meldingId(UUID.randomUUID())
                                                                     .meldingType("meldingType")
                                                                     .mottakerKontoId(UUID.randomUUID())
                                                                     .avsenderKontoId(UUID.randomUUID())
                                                                     .svarPaMelding(UUID.randomUUID())
                                                                     .dokumentlagerId(UUID.randomUUID())
                                                                     .ttl(TimeUnit.DAYS.toMillis(5L))
                                                                     .build();
        final SendtMelding sendtMelding = SendtMelding.fromSendResponse(sendtMeldingApiModel);
        assertAll(
            () -> assertEquals(sendtMeldingApiModel.getMeldingId(), sendtMelding.getMeldingId().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getMeldingType(), sendtMelding.getMeldingType()),
            () -> assertEquals(sendtMeldingApiModel.getMottakerKontoId(), sendtMelding.getMottakerKontoId().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getAvsenderKontoId(), sendtMelding.getAvsenderKontoId().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getSvarPaMelding(), sendtMelding.getSvarPaMelding().getUuid()),
            () -> assertEquals(sendtMeldingApiModel.getTtl(), sendtMelding.getTtl().toMillis())
        );
    }
}