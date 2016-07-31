package sample;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class EntityRepositoryImpl extends EntityRepository {
    private MongoCollection<Entity> coll;

    @Override
    public FindIterable<Entity> findByName(String name) {
        return coll.find(byName(name));
    }

    public FindIterable<Entity> find2(String name) {
        return coll.find(byName.apply(name));
    }

/*
    public void find2(String name) {
        return coll.find(Queries.byName.fun.apply(name));
    }
*/

    public FindIterable<Entity> find3(String name) {
        return coll.find(new ByName().apply(name));
    }
}
