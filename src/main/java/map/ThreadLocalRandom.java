package map;

import sun.misc.VM;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by qincasin on 2021/6/12.
 */
public class ThreadLocalRandom {
    /*
     * This class implements the java.util.Random API (and subclasses
     * Random) using a single static instance that accesses random
     * number state held in class Thread (primarily, field
     * threadLocalRandomSeed). In doing so, it also provides a home
     * for managing package-private utilities that rely on exactly the
     * same state as needed to maintain the ThreadLocalRandom
     * instances. We leverage the need for an initialization flag
     * field to also use it as a "probe" -- a self-adjusting thread
     * hash used for contention avoidance, as well as a secondary
     * simpler (xorShift) random seed that is conservatively used to
     * avoid otherwise surprising users by hijacking the
     * ThreadLocalRandom sequence.  The dual use is a marriage of
     * convenience, but is a simple and efficient way of reducing
     * application-level overhead and footprint of most concurrent
     * programs.
     *
     * Even though this class subclasses java.util.Random, it uses the
     * same basic algorithm as java.util.SplittableRandom.  (See its
     * internal documentation for explanations, which are not repeated
     * here.)  Because ThreadLocalRandoms are not splittable
     * though, we use only a single 64bit gamma.
     *
     * Because this class is in a different package than class Thread,
     * field access methods use Unsafe to bypass access control rules.
     * To conform to the requirements of the Random superclass
     * constructor, the common static ThreadLocalRandom maintains an
     * "initialized" field for the sake of rejecting user calls to
     * setSeed while still allowing a call from constructor.  Note
     * that serialization is completely unnecessary because there is
     * only a static singleton.  But we generate a serial form
     * containing "rnd" and "initialized" fields to ensure
     * compatibility across versions.
     *
     * Implementations of non-core methods are mostly the same as in
     * SplittableRandom, that were in part derived from a previous
     * version of this class.
     *
     * The nextLocalGaussian ThreadLocal supports the very rarely used
     * nextGaussian method by providing a holder for the second of a
     * pair of them. As is true for the base class version of this
     * method, this time/space tradeoff is probably never worthwhile,
     * but we provide identical statistical properties.
     */

    /** Generates per-thread initialization/probe field */
    private static final AtomicInteger probeGenerator =
            new AtomicInteger();

    /**
     * The next seed for default constructors.
     */
    private static final AtomicLong seeder = new AtomicLong(initialSeed());

    private static long initialSeed() {
        String sec = VM.getSavedProperty("java.util.secureRandomSeed");
        if (Boolean.parseBoolean(sec)) {
            byte[] seedBytes = java.security.SecureRandom.getSeed(8);
            long s = (long)(seedBytes[0]) & 0xffL;
            for (int i = 1; i < 8; ++i)
                s = (s << 8) | ((long)(seedBytes[i]) & 0xffL);
            return s;
        }
        return (mix64(System.currentTimeMillis()) ^
                mix64(System.nanoTime()));
    }

    /**
     * The seed increment
     */
    private static final long GAMMA = 0x9e3779b97f4a7c15L;

    /**
     * The increment for generating probe values
     */
    private static final int PROBE_INCREMENT = 0x9e3779b9;

    /**
     * The increment of seeder per new instance
     */
    private static final long SEEDER_INCREMENT = 0xbb67ae8584caa73bL;

    // Constants from SplittableRandom
    private static final double DOUBLE_UNIT = 0x1.0p-53;  // 1.0  / (1L << 53)
    private static final float  FLOAT_UNIT  = 0x1.0p-24f; // 1.0f / (1 << 24)

    /** Rarely-used holder for the second of a pair of Gaussians */
    private static final ThreadLocal<Double> nextLocalGaussian =
            new ThreadLocal<Double>();

    private static long mix64(long z) {
        z = (z ^ (z >>> 33)) * 0xff51afd7ed558ccdL;
        z = (z ^ (z >>> 33)) * 0xc4ceb9fe1a85ec53L;
        return z ^ (z >>> 33);
    }

    private static int mix32(long z) {
        z = (z ^ (z >>> 33)) * 0xff51afd7ed558ccdL;
        return (int)(((z ^ (z >>> 33)) * 0xc4ceb9fe1a85ec53L) >>> 32);
    }

    /**
     * Field used only during singleton initialization.
     * True when constructor completes.
     */
    boolean initialized;

    /** Constructor used only for static singleton */
    private ThreadLocalRandom() {
        initialized = true; // false during super() call
    }

    /** The common ThreadLocalRandom */
    static final ThreadLocalRandom instance = new ThreadLocalRandom();

    static final int getProbe() {
        return UNSAFE.getInt(Thread.currentThread(), PROBE);
    }

    static final void localInit() {
        int p = probeGenerator.addAndGet(PROBE_INCREMENT);
        int probe = (p == 0) ? 1 : p; // skip 0
        long seed = mix64(seeder.getAndAdd(SEEDER_INCREMENT));
        Thread t = Thread.currentThread();
        UNSAFE.putLong(t, SEED, seed);
        UNSAFE.putInt(t, PROBE, probe);
    }

    /**
     * Pseudo-randomly advances and records the given probe value for the
     * given thread.
     */
    static final int advanceProbe(int probe) {
        probe ^= probe << 13;   // xorshift
        probe ^= probe >>> 17;
        probe ^= probe << 5;
        UNSAFE.putInt(Thread.currentThread(), PROBE, probe);
        return probe;
    }




    // Unsafe mechanics
    private static final sun.misc.Unsafe UNSAFE;
    private static final long SEED;
    private static final long PROBE;
    private static final long SECONDARY;
    static {
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> tk = Thread.class;
            SEED = UNSAFE.objectFieldOffset
                    (tk.getDeclaredField("threadLocalRandomSeed"));
            PROBE = UNSAFE.objectFieldOffset
                    (tk.getDeclaredField("threadLocalRandomProbe"));
            SECONDARY = UNSAFE.objectFieldOffset
                    (tk.getDeclaredField("threadLocalRandomSecondarySeed"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
