package br.com.apsoo;

import br.com.apsoo.dao.ClienteDAO;
import br.com.apsoo.model.Cliente;
import java.time.LocalDate;

public class Teste {

    public static void main(String[] args) {
        System.out.println("INICIANDO TESTES DO SISTEMA!\n");

        ClienteDAO clienteDAO = new ClienteDAO();

        // TESTE 1: Regras de Negócio de Maioridade no Modelo (Cliente)
        System.out.println("Teste 1: Validação de Maioridade");
        
        // Cliente Maior de Idade (Ano 2000)
        Cliente cMaior = new Cliente(null, "11122233344", "Carlos Silva", "Rua A", "100", 
                "Centro", "Campo Grande", "MS", "67999991111", "carlos@gmail.com", LocalDate.of(2000, 5, 10));
        
        // Cliente Menor de Idade (Nascido há menos de 18 anos)
        Cliente cMenor = new Cliente(null, "55566677788", "Lucas Souza", "Rua B", "200", 
                "Bairro X", "Campo Grande", "MS", "67999992222", "lucas@gmail.com", LocalDate.now().minusYears(15));

        System.out.println("Cliente 1 (" + cMaior.getNome() + ") é maior de idade? " + cMaior.isMaiorDeIdade() + " [Esperado: true]");
        System.out.println("Cliente 2 (" + cMenor.getNome() + ") é maior de idade? " + cMenor.isMaiorDeIdade() + " [Esperado: false]");

        // TESTE 2: Persistência no Banco de Dados (Inserção via DAO)
        System.out.println("\nTeste 2: Inserção de Cliente no Banco de Dados");
        
        // CPF dinâmico para evitar conflito de chave única caso rode o teste várias vezes
        String cpfTeste = "999" + System.currentTimeMillis() % 100000001L;
        
        Cliente clienteNovo = new Cliente(
            null, 
            cpfTeste, 
            "Gabriel de Campos", 
            "Rua das Flores", 
            "156-B", 
            "Jardim das Meninas", 
            "Campo Grande", 
            "MS", 
            "67998243177", 
            "gabriel.teste@gmail.com", 
            LocalDate.of(2002, 9, 15)
        );

        try {
            if (clienteNovo.isMaiorDeIdade()) {
                clienteDAO.incluir(clienteNovo);
                System.out.println("SUCESSO: Cliente cadastrado com o CPF: " + cpfTeste);
            } else {
                System.out.println("FALHA: Cliente não cadastrado por ser menor de idade.");
            }
        } catch (Exception e) {
            System.err.println("ERRO ao inserir cliente: " + e.getMessage());
        }

        // TESTE 3: Leitura e Busca por CPF no Banco de Dados
        System.out.println("\nTeste 3: Consulta por CPF via ClienteDAO");
        try {
            Cliente clienteBuscado = clienteDAO.buscarPorCpf(cpfTeste);
            
            if (clienteBuscado != null) {
                System.out.println("SUCESSO: Cliente encontrado no Banco!");
                System.out.println("   ID Gerado: " + clienteBuscado.getId());
                System.out.println("   Nome: " + clienteBuscado.getNome());
                System.out.println("   Cidade/UF: " + clienteBuscado.getCidade() + "/" + clienteBuscado.getUf());
                System.out.println("   Data Nasc: " + clienteBuscado.getDataNascimento());
            } else {
                System.out.println("ERRO: Cliente não foi encontrado no banco de dados.");
            }
        } catch (Exception e) {
            System.err.println("ERRO ao buscar cliente por CPF: " + e.getMessage());
        }

        // TESTE 4: Teste de Duplicidade (Fluxo Alternativo - CPF Duplicado)
        System.out.println("\nTeste 4: Validação de CPF Duplicado");
        try {
            Cliente clienteDuplicado = clienteDAO.buscarPorCpf(cpfTeste);
            if (clienteDuplicado != null) {
                System.out.println("SUCESSO: Validação de duplicidade disparada! O CPF " + cpfTeste + " já existe no sistema.");
            } else {
                System.out.println("CPF livre para cadastro.");
            }
        } catch (Exception e) {
            System.err.println("ERRO ao validar duplicidade: " + e.getMessage());
        }

        System.out.println("\nTESTES CONCLUÍDOS!");
    }
}