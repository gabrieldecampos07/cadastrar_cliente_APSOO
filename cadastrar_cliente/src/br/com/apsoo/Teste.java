// Classe de teste para Cliente.java

package br.com.apsoo;
import java.time.LocalDate;
import br.com.apsoo.model.*;

public class Teste {

    public static void main (String[] args) {
        Cliente cliente = new Cliente(
            1, 
            "12345678910", 
            "Gabriel", 
            "Mansour",
            "156-b", 
            "jardim das meninas", 
            "Campo Grande",
            "MS", 
            "67998243177", 
            "campos@gmail.com", 
            LocalDate.of(2008, 9, 15)
        );

        // Exemplo para testar a classe no console:
        System.out.println("UF: " + cliente.getUf());
        System.out.println("Cliente criado: " + cliente.getNome());
        System.out.println("É maior de idade? " + cliente.isMaiorDeIdade());
    }
}
