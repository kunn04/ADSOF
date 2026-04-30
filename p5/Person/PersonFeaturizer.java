import java.util.*;

public class PersonFeaturizer implements Featurizer<Person> {

    @Override
    public List<String> getFeatureNames() {
        return Arrays.asList("age", "weight", "gender");
    }

    @Override
    public Map<String, Object> getFeatureValue(Person p) {
        Map<String, Object> features = new HashMap<>();
        features.put("age", p.getAge());
        features.put("weight", p.getWeight());
        features.put("gender", p.isMale() ? "MALE" : "FEMALE"); 
        return features;
    }
}

