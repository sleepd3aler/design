package ru.pool;

public class StringPoolExample {
    public static void main(String[] args) {
        /** example 1 **/
//        String string1 = "Hello, world";
//        String string2 = "Hello, " + "world";
//        System.out.println(string1 == string2);
        /** example 2 (Runtime str3 example) **/
//        String string1 = "Hello, world";
//        String string2 = "Hello, ";
//        String string3 = string2  + "world";
//        System.out.println(string1 == string3);
        /** example 3 Intern demo */
//        String string1 = "Hello";
//        String string2 = new String("Hello");
//        String string3 = string2.intern();
//        System.out.println(string1 == string3);
        /** example 3.1 Intern v2 demo*/
//        System.out.println(new String("New string") == new String("New string"));
//        System.out.println(new String("Interned string").intern() == new String("Interned string").intern());

    }
}
