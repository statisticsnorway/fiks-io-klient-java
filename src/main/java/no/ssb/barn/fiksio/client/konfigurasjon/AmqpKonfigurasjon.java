package no.ssb.barn.fiksio.client.konfigurasjon;

import lombok.Builder;
import lombok.Data;
import no.ssb.barn.fiksio.client.Meta;
import no.ssb.barn.fiksio.client.model.MeldingId;

import java.util.function.Predicate;
/**
 * Konfigurer amqp-klienten som benyttes for å lytte på meldinger fra Fiks IO. Hvis denne ikke er konfigurert vil hosten konfigurert over benyttes sammen med default port.
 */
@Data
@Builder
public class AmqpKonfigurasjon {

    /**
     * Ikke påkrevd felt. Om feltet ikke er oppgitt benyttes fiksApi.host
     */
    private String host;

    /**
     * Ikke påkrevd. Hvis port ikke settes vil default amqp port 5671 benyttes.
     */
    @Builder.Default private Integer port = 5671;

    /**
     * Ikke påkrevd. Det er her mulig å konfigurere et predikat som forteller om en spesifikk melding har blitt behandlet tidligere, slik at man unngår duplikat meldinger som ellers kan oppstå gjennom en amqp kobling, pga. nettverksbrudd eller lignende.
     */
    @Builder.Default private Predicate<MeldingId> meldingErBehandlet = m -> false;

    /**
     * Meta informasjon om klienten som skal motta meldinger
     */
    @Builder.Default private String applikasjonNavn = String.format("Fiks IO klient (Java) %s", Meta.VERSJON);

    /**
     * Hvor mange meldinger skal buffres ved mottak. Trenger stort sett ikke endres.
     * Teknisk informasjon: brukes som AMQP QOS/prefetch- størrelse for konsumentsiden
     */
    @Builder.Default private int mottakBufferStorrelse = 10;

    /**
     * Konfigurasjon for prod.
     */
    public static final AmqpKonfigurasjon PROD = AmqpKonfigurasjon.builder().host("io.fiks.ks.no").build();
    /**
     * Konfigurasjon for test.
     */
    public static final AmqpKonfigurasjon TEST = AmqpKonfigurasjon.builder().host("io.fiks.test.ks.no").build();
}
