package person; 
 
 /**
 * Esta clase representa una persona con atributos básicos
 * como nombre, edad, peso, altura y género.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: Person.java
 */

public class Person {
    private String name;
    private int age;
    private double weight;
    private int height;
    private boolean male;

    /** 
     * Constructor de la clase.
     * @param name nombre de la persona
     * @param age edad de la persona
     * @param weight peso de la persona
     * @param height altura de la persona
     * @param male true si es hombre, false si es mujer
     */
    public Person(String name, int age, double weight, int height, boolean male) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.male = male;   
    }

    /** 
     * Obtiene el nombre de la persona.
     * @return nombre
     */
    public String getName() {
        return name;
    }

    /** 
     * Obtiene la edad de la persona.
     * @return edad
     */
    public int getAge() {
        return age;
    }

    /** 
     * Obtiene el peso de la persona.
     * @return peso
     */
    public double getWeight() {
        return weight;
    }

    /** 
     * Obtiene la altura de la persona.
     * @return altura
     */
    public int getHeight() {
        return height;
    }

    /** 
     * Indica si la persona es hombre.
     * @return true si es hombre, false en caso contrario
     */
    public boolean isMale() {
        return male;    
    }

    @Override
    /** 
     * Devuelve una representación en cadena de la persona.
     * @return representación en cadena
     */
    public String toString() {
        return name + "(age: " + age + ", " + (male ? "male" : "female") + ")";
    }
    
}