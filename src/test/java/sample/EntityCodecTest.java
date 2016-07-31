package sample;

import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.BsonString;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.BsonValueCodecProvider;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.json.JsonReader;
import org.bson.json.JsonWriter;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

import static org.bson.codecs.configuration.CodecRegistries.*;

public class EntityCodecTest {

    private CodecRegistry codecRegistry;
    private BsonDocumentCodec bsonDocumentCodec;
    private EntityCodec entityCodec;

    @Before
    public void setup() {
        codecRegistry = fromRegistries(
                fromCodecs(new EntityCodec()),
                fromProviders(new BsonValueCodecProvider())
        );
        bsonDocumentCodec = new BsonDocumentCodec(codecRegistry);
        entityCodec = new EntityCodec();
    }

    @Test
    public void decode_entity() {
        BsonDocument output = new BsonDocument("longNum", new BsonInt64(1234))
                .append("foo", new BsonString("bar"))
                .append("name", new BsonString("some_object"));
        String json = output.toJson();
        System.out.println(json);

        Entity input = entityCodec.decode(new JsonReader(json), DecoderContext.builder().build());
        System.out.println(input);
    }

    @Test
    public void encode_entity() {
        Entity output = ImmutableEntity.builder().longNum(1234).name("some_object").build();
        StringWriter writer = new StringWriter();
        entityCodec.encode(new JsonWriter(writer), output, EncoderContext.builder().build());
        System.out.println(writer);

        BsonDocument input = bsonDocumentCodec.decode(new JsonReader(writer.toString()), DecoderContext.builder().build());
        System.out.println(input.toJson());
    }
}
