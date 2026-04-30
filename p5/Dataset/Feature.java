import java.util.*;

public class Feature<V extends Comparable<? super V>> extends ArrayList<V> {

    public V min() {
        if (this.isEmpty()) return null;

        return Collections.min(this);
    }

    public V max() {
        if (this.isEmpty()) return null;

        return Collections.max(this);
    }

    public Map<V, Integer> distribution() {
        Map<V, Integer> freqOfValue = new LinkedHashMap<>();
        
        for (V value : this) {
            freqOfValue.put(value, freqOfValue.getOrDefault(value, 0) + 1);
        }

        return freqOfValue;
    }
    
}
