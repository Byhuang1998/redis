package com.byhuang;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/14 14:46
 * @description simulate a verification code sender
 */
public class VerificationCode {

    public static void main(String[] args) {
        judgeVerification("13112345678", "077546");
    }

    // step 1: Generate verification code
    public static String genCode() {

        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            code.append(rand);
        }
        return code.toString();
    }

    // step 2: Set the number of times to send verification codes
    public static void sendCode(String phone) {

//        redis要保存    电话 -- 发送次数   电话 -- 验证码

        String keyCnt = "cnt" + phone;
        String keyCode = "code" + phone;

        Jedis jedis = MyJedis();
        String cnt = jedis.get(keyCnt);

        if (cnt == null) {
            jedis.set(keyCnt, "1");
            jedis.setex(keyCode, 120, genCode());
        } else if (Integer.parseInt(cnt) < 3) {
            jedis.incr(keyCnt);
            jedis.setex(keyCode, 120, genCode());
        } else {
            System.out.println("already three times");
        }
    }

    // step 2: Determine whether the verification code is equal
    public static void judgeVerification(String phone, String input) {
        String keyCnt = "cnt" + phone;
        String keyCode = "code" + phone;
        Jedis jedis = MyJedis();
        String code = jedis.get(keyCode);
        if (code == null) {
            System.out.println("verification code not sent or expired");
        }
        if (input.equals(code)) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
    }

    public static Jedis MyJedis() {
        return new Jedis("192.168.119.130", 6379);
    }
}
