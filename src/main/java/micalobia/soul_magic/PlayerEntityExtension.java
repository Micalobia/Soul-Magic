package micalobia.soul_magic;

public interface PlayerEntityExtension {
    int getSoulCount();
    int getSoulMax();
    void setSoulCount(int value);
    void addSouls(int value);
    void takeSouls(int value);
    void addIncomingSouls(float value);
    void scaleIncomingSouls(float value);
}
