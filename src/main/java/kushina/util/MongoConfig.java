/*
 * @author Anshul Saraf
 */

package kushina.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration { //not to add field _class in mongo

    public final String DATABASE_NAME = "narutoDatabook";
    public final String CONNECTION_URL = "mongodb://localhost:27017";

    @Autowired
    MongoDbFactory mongoDbFactory;
    @Autowired
    MongoMappingContext mongoMappingContext;

    @Override
    protected String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter() {

        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return converter;
    }

    public @Bean
    MongoClient mongoClient() {
        return MongoClients.create(CONNECTION_URL);
    }

    public @Bean
    MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), DATABASE_NAME);
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}
