# Generating MongoDB Repositores

## Pure codecs

Writing `Codec<T>` implementations makes `BsonValue` unnecessary. If you know your set
of fields in advance then you can work with the reader and writer classes directly. The
reading general approach:

- read values in order, send them to the builder
- separate reader method for lists if the builder has separate add methods
- read nested objects with pre-created nested Codecs

General writing approach:

- write values of primitive types directly
- write arrays with a helper method
- write nested objects with pre-created nested Codecs

### Codec to-do

1. Produce a template for `@Immutable.Value`
2. Produce a template for POJOs
3. Support all primitive types
4. Support dates and timestamps
5. Support ObjectIds
6. Support nested objects
7. Produce a `CodecProvider` class for all entity classes

### Benchmarking to-do

1. Generate data with [TPC-H][1]
2. Import MongoDb collections with this data
3. Compare deserialization performance for `BsonDocument` and generated codecs

[1]: http://www.tpc.org/tpc_documents_current_versions/current_specifications.asp

## Integration into mongo-java-driver

To use custom Codecs they must be included in the `codecRegistry` used by the `MongoCollection`.
The registry is inherited from the database and the `MongoClientOptions` as the configuration
object.

Ultimate goal:

1. Generate codec implementations for entity classes.
2. Generate custom codec registry (or codec provider) from these classes.
3. Generate repository implementations from interfaces (like with JDBA).

### Repository use-cases

- find with criteria
- update with criteria
- update with $set and other operators

To specify search criteria we can create methods returning Mongo search filters
by either calling Filters.xxx() methods or by producing filter documents. These
filters will be called by the generated repository code.
