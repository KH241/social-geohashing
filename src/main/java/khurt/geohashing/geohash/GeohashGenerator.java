package khurt.geohashing.geohash;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Random;

public class GeohashGenerator {
    private static Random random = new Random();

    public static Geohash generate(LocalDate date, int pos_x, int pos_y){
        float dow = getDowJones(date);

        String md5hash = getHash(date, dow);

        String left = md5hash.substring(0, 16);
        String right = md5hash.substring(16);

        double loc_x = getCoordinate(left, pos_x);
        double loc_y = getCoordinate(right, pos_y);

        return new Geohash(loc_x, loc_y, date);
    }

    private static double getCoordinate(String hash, int pos) {
        BigInteger i = new BigInteger(hash, 16);
        return Double.parseDouble("0." + i) + pos;
    }

    private static String getHash(LocalDate date, float dow) {

        //Generate String
        String hashbase = date.toString() + "-" + (10000 + dow);

        //Convert to bytes
        byte[] hashbasebytes = hashbase.getBytes();

        //Hash the bytes with md5
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] md5hashbytes = md.digest(hashbasebytes);

        //Convert md5 to hexadecimal
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < md5hashbytes.length; i++) {
            sb.append(Integer.toString((md5hashbytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    private static float getDowJones(LocalDate date){
        //TODO acutally get dow jones
        return random.nextFloat();
    }
}
