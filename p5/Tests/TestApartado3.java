import java.util.function.Predicate;

public class TestApartado3 {
    public static void main(String[] args) {
        DecisionTree<Person> dt = TestApartado2.buildDecisionTree();
        
        // 1. Obtenemos el predicado para la etiqueta "old male"
        Predicate<Person> isOldMale = dt.getPredicate("old male");
        
        // 2. Obtenemos el predicado para la etiqueta "female" (que viene de un 'otherwise')
        Predicate<Person> isFemale = dt.getPredicate("female");

        Person p1 = new Person("Pedro", 66, 75, 180, true);  // Es Old Male
        Person p2 = new Person("Ana", 47, 54, 158, false);    // Es Female
        Person p3 = new Person("Luis", 34, 75, 176, true);   // NO es Old Male (es Middle)

        System.out.println("--- Verificación de Predicados ---");
        System.out.println(p1.getName() + " es 'old male'?: " + isOldMale.test(p1)); // true
        System.out.println(p3.getName() + " es 'old male'?: " + isOldMale.test(p3)); // false
        System.out.println(p2.getName() + " es 'female'?: " + isFemale.test(p2));   // true
        
        // 3. Prueba con un Dataset
        Dataset<Person> dataSet = TestApartado2.buildDataSet();
        System.out.println("\n--- Filtrado de Dataset usando el predicado 'old male' ---");
        for(Person p : dataSet.getObjects()) {
            if(isOldMale.test(p)) {
                System.out.println("Encontrado: " + p);
            }
        }
    }
}
