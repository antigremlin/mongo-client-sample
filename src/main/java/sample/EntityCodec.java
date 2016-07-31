package sample;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class EntityCodec implements Codec<Entity> {
    public Entity decode(BsonReader bsonReader, DecoderContext decoderContext) {
        ImmutableEntity.Builder builder = ImmutableEntity.builder();
        bsonReader.readStartDocument();
        while (bsonReader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            switch (bsonReader.readName()) {
                case "longNum":
                    builder.longNum(bsonReader.readInt64());
                    break;
                case "name":
                    builder.name(bsonReader.readString());
                    break;
                default:
                    bsonReader.skipValue();
            }
        }
        bsonReader.readEndDocument();
        return builder.build();
    }

    public void encode(BsonWriter bsonWriter, Entity entity, EncoderContext encoderContext) {
        bsonWriter.writeStartDocument();
        bsonWriter.writeInt64("longNum", entity.longNum());
        bsonWriter.writeString("name", entity.name());
        bsonWriter.writeEndDocument();
    }

    public Class<Entity> getEncoderClass() {
        return Entity.class;
    }
}
