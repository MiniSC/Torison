package Redis;

import redis.clients.jedis.*;

public class RedisClient {

  static   Jedis jedis ;

    public static void testClient(){
        jedis = new Jedis("123.207.68.131",6379);
        jedis.auth("admin");
    }

    public static void main(String[] args) {
        RedisClient.testClient();
        System.out.println(jedis.get("firsttest"));
    }

}

