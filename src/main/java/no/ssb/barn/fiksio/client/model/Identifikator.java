package no.ssb.barn.fiksio.client.model;

import lombok.Value;

@Value
public class Identifikator {
    IdentifikatorType identifikatorType;
    String identifikator;
}
