package christmas.util;

public class ChristmasUtil {
    public long countChar(String str, char ch){
        return str.chars()
                .filter(c -> c==ch)
                .count();
    }
}
