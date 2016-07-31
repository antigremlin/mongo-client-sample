package sample;

import org.bson.conversions.Bson;

import java.util.function.Function;

public @interface Query {
    EntityRepository.Queries value();
}
