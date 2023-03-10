package tutorial.seho.foodapp.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}") private String redisHost;
    @Value("${spring.data.redis.port}") private Optional<Integer> redisPort;
    @Value("${spring.data.redis.user}")private String redisUsername;
    @Value("${spring.data.redis.password}")private String redisPassword;

    /*
     * spring.data.redis.host=${REDISHOST}
     * spring.data.redis.port=${REDISPORT}
     * spring.data.redis.username=${REDISUSER}
     * spring.data.redis.password=${REDISPASSWORD}
     * spring.data.redis.client.type=jedis
     */

     @Bean
     @Scope("singleton")
    @Primary
     public RedisTemplate<String, String> redisTemplate(){

        //Set redis config parameters
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        config.setPort(redisPort.get());
        if(!redisUsername.isEmpty() && !redisPassword.isEmpty()){
            config.setUsername(redisUsername);
            config.setPassword(redisPassword);
        }
        config.setDatabase(0);

        //Use redis config for jedis client
        final JedisClientConfiguration jedisClient =  JedisClientConfiguration
        .builder()
        .build();
        final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config,jedisClient);
        jedisFac.afterPropertiesSet();

        //set up redistemplate and associated KV hashing
        RedisTemplate<String, String> r = new RedisTemplate<String,String>();
        r.setConnectionFactory(jedisFac);
        r.setKeySerializer(new StringRedisSerializer()); 
        r.setHashKeySerializer(new StringRedisSerializer());
        r.setValueSerializer(r.getKeySerializer());
        r.setHashValueSerializer(r.getKeySerializer());

        System.out.println("Connected to RedisHost" + redisHost + "Database" + Integer.toString(config.getDatabase()));
        return r;



    }
}