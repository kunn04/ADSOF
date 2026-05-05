import java.util.*;
import dataset.*;
import person.*;

/**
 * Esta clase prueba la funcionalidad básica del Dataset y Feature,
 * incluyendo inserción de datos, eliminación de duplicados y operaciones estadísticas.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: TestApartado1.java
 */
public class TestApartado1 {
    /** 
     * Método principal de ejecución.
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        Dataset<Person> dataSet = buildDataSet();
        System.out.println("dataset: " + dataSet);

        dataSet.removeDuplicates();
        System.out.println("dataset w/o duplicados: " + dataSet);

        Feature<Integer> ages = dataSet.feature("age");
        System.out.println("Ages: " + ages);
        Collections.sort(ages);
        System.out.println("Ages sorted: " + ages);
        System.out.println("Min age: " + ages.min());
        System.out.println("Gender distribution: " + dataSet.feature("gender").distribution());  //freq. of each value
    }

    /** 
     * Construye un dataset de ejemplo con personas.
     * @return dataset de personas
     */
    public static Dataset<Person> buildDataSet() {
        Person people [] = { new Person("Pedro", 66, 75, 180, true), // name, age, weight, height, male?
                            new Person("Ana", 47, 54, 158, false),
                            new Person("Luis", 34, 75, 176, true),
                            new Person("Rosa", 47, 54, 158, false)
        };

        Dataset<Person> dataSet = new Dataset<>(new PersonFeaturizer());
        dataSet.addAll(people);
        return dataSet;
    }
}