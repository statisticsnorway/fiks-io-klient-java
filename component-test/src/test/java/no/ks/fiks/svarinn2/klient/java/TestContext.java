package no.ks.fiks.svarinn2.klient.java;

import feign.codec.StringDecoder;
import feign.form.FormEncoder;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import no.ks.fiks.componenttest.support.ComponentTestConfiguration;
import no.ks.fiks.componenttest.support.ComponentTestConfigurationProperties;
import no.ks.fiks.componenttest.support.autorisasjon.AutorisasjonQueryMock;
import no.ks.fiks.componenttest.support.autorisasjon.AutorisasjonUpdateMock;
import no.ks.fiks.componenttest.support.feign.TestApi;
import no.ks.fiks.componenttest.support.feign.TestApiBuilder;
import no.ks.fiks.componenttest.support.konfigurasjon.KonfigurasjonMock;
import no.ks.fiks.svarinn2.katalog.swagger.api.v1.SvarInnKatalogApi;
import no.ks.fiks.svarinn2.katalog.swagger.api.v1.SvarInnKontoApi;
import no.ks.fiks.svarinn2.swagger.api.v1.SvarInnApi;
import org.springframework.context.annotation.Bean;


@ComponentTestConfiguration
public class TestContext {

    public static final String FIKS_SVARINN2_SERVICE = "fiks-svarinn2-service";
    public static final String FIKS_SVARINN2_KATALOG_SERVICE = "fiks-svarinn2-katalog-service";

    @Bean
    public TestApiBuilder<SvarInnKatalogApi> katalogApiBuilder(TestApi testApi) {
        return testApi
                .buildApi(new no.ks.fiks.svarinn2.katalog.swagger.invoker.v1.ApiClient(), SvarInnKatalogApi.class, FIKS_SVARINN2_KATALOG_SERVICE)
                .withDecoder(new StringDecoder());
    }

    @Bean
    public TestApiBuilder<SvarInnKontoApi> kontoApiBuilder(TestApi testApi) {
        return testApi
                .buildApi(new no.ks.fiks.svarinn2.katalog.swagger.invoker.v1.ApiClient(), SvarInnKontoApi.class, FIKS_SVARINN2_KATALOG_SERVICE)
                .withEncoder(new FormEncoder(new JacksonEncoder()));
    }


    @Bean
    public TestApiBuilder<SvarInnApi> svarInnApiBuilder(TestApi testApi) {
        return testApi
                .buildApi(new no.ks.fiks.svarinn2.swagger.invoker.v1.ApiClient(), SvarInnApi.class, FIKS_SVARINN2_SERVICE)
                .withEncoder(new FormEncoder(new JacksonEncoder()))
                .withClient(new OkHttpClient());
    }

    @Bean
    public SvarInn2KlientGenerator svarInn2KlientGenerator(ComponentTestConfigurationProperties properties, AutorisasjonQueryMock queryMock, KonfigurasjonMock konfigurasjonMock, AutorisasjonUpdateMock autorisasjonUpdateMock,  TestApiBuilder<SvarInnKontoApi> kontoApiTestApiBuilder, TestApiBuilder<SvarInnKatalogApi> katalogApiTestApiBuilder, TestApiBuilder<SvarInnApi> svarInnApiTestApiBuilder){
        return new SvarInn2KlientGenerator(properties, queryMock, konfigurasjonMock, autorisasjonUpdateMock, kontoApiTestApiBuilder, katalogApiTestApiBuilder, svarInnApiTestApiBuilder);
    }

}
