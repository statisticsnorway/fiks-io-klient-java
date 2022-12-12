package no.ssb.barn.fiksio.client;

import feign.FeignException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import no.ssb.barn.fiksio.client.api.katalog.api.FiksIoKatalogApi;
import no.ssb.barn.fiksio.client.model.Konto;
import no.ssb.barn.fiksio.client.model.KontoId;
import no.ssb.barn.fiksio.client.model.LookupRequest;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Optional;


@Slf4j
public class KatalogHandler {
    private FiksIoKatalogApi katalogApi;
    private CertificateFactory cf;

    public KatalogHandler(@NonNull FiksIoKatalogApi katalogApi) {
        this.katalogApi = katalogApi;
    }

    public Optional<Konto> lookup(@NonNull LookupRequest request) {
        try {
            return Optional.ofNullable(katalogApi.lookup(
                request.getIdentifikator()
                    .getIdentifikatorType()
                    .name() + "." + request.getIdentifikator()
                    .getIdentifikator(),
                request.getMeldingsprotokoll(), request.getSikkerhetsNiva()))
                .map(Konto::fromKatalogModel);
        } catch (FeignException e) {
            if (e.status() == 404)
                return Optional.empty();
            else
                throw e;
        }
    }

    X509Certificate getPublicKey(@NonNull KontoId mottakerKontoId) {
        try {
            if (cf == null)
                cf = CertificateFactory.getInstance("X.509");

            return (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(katalogApi.getOffentligNokkel(mottakerKontoId.getUuid()).getNokkel().getBytes()));
        } catch (CertificateException e) {
            throw new RuntimeException(String.format("Feil under generering av offentlig sertifikat for mottaker %s", mottakerKontoId), e);
        }
    }
}
