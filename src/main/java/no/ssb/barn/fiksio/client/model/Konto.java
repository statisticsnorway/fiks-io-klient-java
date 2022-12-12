package no.ssb.barn.fiksio.client.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import no.ssb.barn.fiksio.client.api.katalog.model.KatalogKonto;

@Builder
@Value
public class Konto {
    @NonNull private KontoId kontoId;
    @NonNull private String kontoNavn;
    @NonNull private FiksOrgId fiksOrgId;
    @NonNull private String fiksOrgNavn;
    private boolean isGyldigAvsender;
    private boolean isGyldigMottaker;

    public static Konto fromKatalogModel(@NonNull KatalogKonto konto) {
        return Konto.builder()
            .kontoId(new KontoId(konto.getKontoId()))
            .kontoNavn(konto.getKontoNavn())
            .fiksOrgId(new FiksOrgId(konto.getFiksOrgId()))
            .fiksOrgNavn(konto.getFiksOrgNavn())
            .isGyldigAvsender(konto.getStatus().getGyldigAvsender())
            .isGyldigMottaker(konto.getStatus().getGyldigMottaker())
            .build();
    }
}
