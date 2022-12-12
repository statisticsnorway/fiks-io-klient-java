package no.ssb.barn.fiksio.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MetaTest {
    @Test
    void testLastVersjonnummer() {
        assertNotNull(Meta.VERSJON);
    }
}