package sample;

import org.bson.conversions.Bson;

import java.util.function.Function;

public @interface Query2 {
    Class<? extends Function<?, Bson>> value();
}
