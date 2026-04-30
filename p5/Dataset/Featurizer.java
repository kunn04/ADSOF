import java.util.*;

public interface Featurizer<T> {
    List<String> getFeatureNames();
    Map<String, Object> getFeatureValue(T object);
}
