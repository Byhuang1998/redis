import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/14 14:29
 * @description TODO
 */
public class TestRedisDataStructure {

    Jedis jedis = new Jedis("192.168.119.130", 6379);


    @Test
    public void testSet() {

        jedis.set("name", "zx");

        String name = jedis.get("name");
        System.out.println(name);

    }

    @Test
    public void testKeys() {

        jedis.flushDB();

        jedis.set("name", "zx");
        jedis.set("another", "by");

        Set<String> keys = jedis.keys("*");
        keys.forEach(System.out::println);
    }
}
