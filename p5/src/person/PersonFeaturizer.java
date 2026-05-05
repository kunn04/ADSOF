package person;

import java.util.*;
import dataset.*;

/**
 * Esta clase implementa un featurizer para objetos Person,
 * extrayendo características relevantes como edad, peso y género.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: PersonFeaturizer.java
 */

public class PersonFeaturizer implements Featurizer<Person> {

    /** 
     * Obtiene los nombres de las características.
     * @return lista de nombres de características
     */
    @Override
    public List<String> getFeatureNames() {
        return Arrays.asList("age", "weight", "gender");
    }

    /** 
     * Extrae los valores de las características de una persona.
     * @param p objeto Person
     * @return mapa con nombre de característica y su valor
     */
    @Override
    public Map<String, Object> getFeatureValue(Person p) {
        Map<String, Object> features = new HashMap<>();
        features.put("age", p.getAge());
        features.put("weight", p.getWeight());
        features.put("gender", p.isMale() ? "MALE" : "FEMALE"); 
        return features;
    }
}

