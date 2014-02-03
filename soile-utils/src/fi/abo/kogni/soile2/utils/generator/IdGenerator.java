package fi.abo.kogni.soile2.utils.generator;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public final class IdGenerator {
    
    private IdGenerator(MessageDigest md) {
        digest = md;
        seed = null;
    }
    
    public static IdGenerator shortIdGenerator() {
        return new IdGenerator(DigestUtils.getSha1Digest());
    }
    
    public static IdGenerator shortIdGenerator(byte[] bytes) {
        IdGenerator gen = shortIdGenerator();
        gen.seed(bytes);
        return gen;
    }
    
    public static IdGenerator mediumIdGenerator() {
        return new IdGenerator(DigestUtils.getSha256Digest());
    }
    
    public static IdGenerator mediumIdGenerator(byte[] bytes) {
        IdGenerator gen = mediumIdGenerator();
        gen.seed(bytes);
        return gen;
    }
    
    public static IdGenerator longIdGenerator() {
        return new IdGenerator(DigestUtils.getSha512Digest());
    }
    
    public static IdGenerator longIdGenerator(byte[] bytes) {
        IdGenerator gen = longIdGenerator();
        gen.seed(bytes);
        return gen;
    }
    
    public void seed() {
        seed(128);
    }
    
    public void seed(int length) {
        length = Math.abs(length);
        if (length == 0) {
            length = 128;
        }
        byte[] bytes = new byte[length];
        Random rand = new Random();
        rand.nextBytes(bytes);
        seed(bytes);
    }
    
    public void seed(byte[] bytes) {
        this.seed = bytes;
    }
    
    public void init() {
        if (seed == null) {
            seed();
        }
        digest.update(seed);
    }
    
    public void update(byte input) {
        digest.update(input);
    }
    
    public void update(byte[] input) {
        digest.update(input);
    }
    
    public void update(String input) {
        digest.update(input.getBytes());
    }
    
    public void update(ByteBuffer input) {
        digest.update(input.array());
    }
    
    public void reset() {
        digest.reset();
        digest.update(seed);
    }
    
    public String getId() {
        byte[] bytes = digest.digest();
        return new String(Hex.encodeHex(bytes));
    }
    
    private MessageDigest digest;
    private byte[] seed;
    
}
