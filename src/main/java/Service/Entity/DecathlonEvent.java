package Service.Entity;

/**
 * Event type can be track(time based) or field(distance|height) based
 */
public enum DecathlonEvent {

    TRACK_100M(25.4347f, 18.0f, 1.81f),
    FIELD_LONG_JUMP(0.14354f, 220f, 1.40f),
    FIELD_SHOT_PUT(51.39f, 1.5f, 1.05f),
    FIELD_HIGH_JUMP(0.8465f, 75f, 1.42f),
    TRACK_RUN_400M(1.53775f, 82f, 1.81f),
    TRACK_110M_HURDLES(5.74352f, 28.5f, 1.92f),
    FIELD_DISCUS_THROW(12.91f, 4.0f, 1.1f),
    FIELD_POLE_VAULT(0.2797f, 100f, 1.35f),
    FIELD_JAVELIN_THROW(10.14f, 7.0f, 1.08f),
    TRACK_RUN_1500M(0.03768f, 480f, 1.85f);

    private final float a;
    private final float b;
    private final float c;

    DecathlonEvent(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public float getA() {
        return a;
    }

    public float getB() {
        return b;
    }

    public float getC() {
        return c;
    }
}
