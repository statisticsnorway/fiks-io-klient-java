package no.ssb.barn.fiksio.client.model;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class KontoId {
    @NonNull private UUID uuid;

    @Override
    public String toString(){
        return uuid.toString();
    }
}
