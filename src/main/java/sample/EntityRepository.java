package sample;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class EntityRepository {

    // Annotation can specify a class name, an enum constant, a class constant name
    @Query(value = Queries.byName)
    @Query2(value = ByName.class)
    public abstract FindIterable<Entity> findByName(String name);

    enum Queries {
        byName((String name) -> Filters.eq("name", name)),
        byNameAndStatus((String name, String status) -> Filters.and(Filters.eq("name", name), Filters.eq("status", status)));

        public Function<?, Bson> fun;

        public BiFunction<?, ?, Bson> fun2;
        Queries(Function<?, Bson> fun) { this.fun = fun; }

        Queries(BiFunction<?, ?, Bson> fun) { this.fun2 = fun; }
    }

    static final Function<String, Bson> byName = name -> Filters.eq("name", name);

    Bson byName(String name) {
        return Filters.eq("name", name);
    }

    class ByName implements Function<String, Bson> {
        @Override public Bson apply(String s) {
            return Filters.eq("name", s);
        }
    }
}
