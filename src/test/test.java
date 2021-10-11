package test;

import consultas.Departamento;
import java.util.ArrayList;

public class test {
    
    public static void main(String[] args) {
        System.out.println("-- testing departamentos --");
        testAllDepartamentos();
    }
    
    private static void testAllDepartamentos() {
        System.out.println("all departamentos");
        Departamento dep = new Departamento();
        ArrayList<Departamento> deps = dep.getAll();
        for (int i = 0; i < deps.size(); i++) {
            System.out.println("id: "+deps.get(i).getId());
            System.out.println("nombre: "+deps.get(i).getNombre());
        }
    }
}
