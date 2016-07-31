package sample;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public abstract class Entity {
    public abstract long longNum();
    public abstract String name();
    public abstract List<String> lines();
}
