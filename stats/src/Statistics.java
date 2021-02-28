public interface Statistics {


    void event(int value);
    float mean();
    float mean(int lastNMinutes);
    float variance();
    int minimum();
    int maximum();


}
