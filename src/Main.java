import entities.Cliente;
import entities.Loja;
import entities.Pedido;
import entities.Produto;
import entities.Promocao;
import DAO.ClienteRepository;
import DAO.LojaRepository;
import DAO.PedidoRepository;
import service.LojaService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    // Na falta de um SQL vai uma lista .json mesmo
    static ClienteRepository clienteRepository =
            new ClienteRepository();

    static LojaRepository lojaRepository =
            new LojaRepository();

    static PedidoRepository pedidoRepository =
            new PedidoRepository();

    public static void main(String[] args) {

        try {

            clienteRepository.carregar();
            lojaRepository.carregar();
            pedidoRepository.carregar();

        } catch (Exception e) {

            e.printStackTrace();
        }


        telaLogin();

        //aqui o progama volta e se fecha
        System.out.println("Até logo!");
        sc.close();
    }

    // =============================================================
    // TELA LOGIN
    // =============================================================
    static void telaLogin() {
        while (true) {
            System.out.println("\n=== BEM-VINDO AO SALVÔ ===");
            System.out.println("[1] Entrar como Cliente");
            System.out.println("[2] Entrar como Loja");
            System.out.println("[3] Cadastrar-se como Cliente");
            System.out.println("[4] Cadastrar-se como Loja");
            System.out.println("[5] Gerenciar dados");
            System.out.println("[0] Sair");
            System.out.print("Escolha: ");
            int opcao = lerInt();

            switch (opcao) {
                case 1 -> logarCliente();
                case 2 -> logarLoja();
                case 3 -> cadastrarCliente();
                case 4 -> cadastrarLoja();
                case 5 -> telaGerenciarDados();
                case 0 -> { return; }
                default -> System.out.println("Opcao invalida.");
            }
        }
    }

    // =============================================================
    // TELA GERENCIAR DADOS
    // =============================================================
    static void telaGerenciarDados() {
        while (true) {
            System.out.println("\n=== GERENCIAR DADOS ===");
            System.out.println("[1] Clientes");
            System.out.println("[2] Lojas");
            System.out.println("[3] Pedidos");
            System.out.println("[0] Voltar");
            System.out.print("Escolha: ");
            int opcao = lerInt();

            switch (opcao) {
                case 1 -> telaGerenciarClientes();
                case 2 -> telaGerenciarLojas();
                case 3 -> telaGerenciarPedidos();
                case 0 -> { return; }
                default -> System.out.println("Opcao invalida.");
            }
        }
    }

    static void telaGerenciarClientes() {
        while (true) {
            System.out.println("\n=== CLIENTES ===");

            List<Cliente> clientes = clienteRepository.listar();

            if (clientes.isEmpty()) {
                System.out.println("Nenhum cliente cadastrado.");
                return;
            }

            for (int i = 0; i < clientes.size(); i++) {
                Cliente c = clientes.get(i);
                System.out.printf("[%d] ID:%-3d  %-20s  %s%n",
                        i + 1, c.getId(), c.getNome(), c.getEmail());
            }

            System.out.println("\n[D] Deletar cliente");
            System.out.println("[0] Voltar");
            System.out.print("Escolha: ");
            String opcao = sc.nextLine().trim().toUpperCase();

            switch (opcao) {
                case "D" -> {
                    System.out.print("ID do cliente a deletar: ");
                    int id = lerInt();
                    try {
                        boolean removido = clienteRepository.deletar(id);
                        if (removido) {
                            System.out.println("Cliente removido com sucesso.");
                        } else {
                            System.out.println("Cliente com ID " + id + " nao encontrado.");
                        }
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar apos deletar.");
                    }
                }
                case "0" -> { return; }
                default -> System.out.println("Opcao invalida.");
            }
        }
    }

    static void telaGerenciarLojas() {
        while (true) {
            System.out.println("\n=== LOJAS ===");

            List<Loja> lojas = lojaRepository.listar();

            if (lojas.isEmpty()) {
                System.out.println("Nenhuma loja cadastrada.");
                return;
            }

            for (int i = 0; i < lojas.size(); i++) {
                Loja l = lojas.get(i);
                System.out.printf("[%d] %-20s  CNPJ: %-20s  %s%n",
                        i + 1, l.getNome(), l.getCnpj(), l.getEmail());
            }

            System.out.println("\n[D] Deletar loja");
            System.out.println("[0] Voltar");
            System.out.print("Escolha: ");
            String opcao = sc.nextLine().trim().toUpperCase();

            switch (opcao) {
                case "D" -> {
                    System.out.print("Numero da loja a deletar: ");
                    int num = lerInt();
                    if (num < 1 || num > lojas.size()) {
                        System.out.println("Numero invalido.");
                    } else {
                        lojas.remove(num - 1);
                        try {
                            lojaRepository.salvar();
                            System.out.println("Loja removida com sucesso.");
                        } catch (Exception e) {
                            System.out.println("Erro ao salvar apos deletar.");
                        }
                    }
                }
                case "0" -> { return; }
                default -> System.out.println("Opcao invalida.");
            }
        }
    }

    static void telaGerenciarPedidos() {
        while (true) {
            System.out.println("\n=== PEDIDOS ===");

            List<Pedido> pedidos = pedidoRepository.listar();

            if (pedidos.isEmpty()) {
                System.out.println("Nenhum pedido cadastrado.");
                return;
            }

            for (int i = 0; i < pedidos.size(); i++) {
                Pedido p = pedidos.get(i);
                System.out.printf("[%d] Cliente: %-30s  R$ %.2f%n",
                        i + 1, p.getEmailCliente(), p.getValorTotal());
            }

            System.out.println("\n[D] Deletar pedido");
            System.out.println("[0] Voltar");
            System.out.print("Escolha: ");
            String opcao = sc.nextLine().trim().toUpperCase();

            switch (opcao) {
                case "D" -> {
                    System.out.print("Numero do pedido a deletar: ");
                    int num = lerInt();
                    if (num < 1 || num > pedidos.size()) {
                        System.out.println("Numero invalido.");
                    } else {
                        try {
                            pedidoRepository.deletar(num - 1);
                            System.out.println("Pedido removido com sucesso.");
                        } catch (IOException e) {
                            System.out.println("Erro ao salvar apos deletar.");
                        }
                    }
                }
                case "0" -> { return; }
                default -> System.out.println("Opcao invalida.");
            }
        }
    }

    static void logarCliente() {
        System.out.println("\n--- Login Cliente ---");
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Cliente clienteLogado = null;
        for (Cliente c : clienteRepository.listar()) {
            if (c.login(email, senha)) {
                clienteLogado = c;
                break;
            }
        }

        if (clienteLogado != null) {
            System.out.println("Bem-vindo, " + clienteLogado.getNome() + "!");
            telaMenuCliente(clienteLogado);
        } else {
            System.out.println("Email ou senha incorretos.");
        }
    }

    static void logarLoja() {
        System.out.println("\n--- Login Loja ---");
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Loja lojaLogada = null;
        for (Loja l : lojaRepository.listar()) {
            if (l.login(email, senha)) {
                lojaLogada = l;
                break;
            }
        }

        if (lojaLogada != null) {
            System.out.println("Bem-vinda, " + lojaLogada.getNome() + "!");
            telaMenuLoja(lojaLogada);
        } else {
            System.out.println("Email ou senha incorretos.");
        }
    }

    static void cadastrarCliente() {
        System.out.println("\n--- Cadastro de Cliente ---");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Endereço: ");
        String endereço = sc.nextLine();

        for (Cliente c : clienteRepository.listar()) {
            if (c.getEmail().equals(email)) {
                System.out.println("Esse email ja esta cadastrado.");
                return;
            }
        }

        try {
            clienteRepository.inserir(
                    new Cliente(nome, telefone, email, senha, endereço)
            );

            System.out.println("Cadastro realizado! Faca o login para continuar.");

        } catch (IOException e) {
            System.out.println("Erro ao salvar cliente.");
        }
    }

    static void cadastrarLoja() {
        System.out.println("\n--- Cadastro de Loja ---");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Cnpj: ");
        String cnpj = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();


        for (Cliente c : clienteRepository.listar()) {
            if (c.getEmail().equals(email)) {
                System.out.println("Esse email ja esta cadastrado.");
                return;
            }
        }

        try {
            lojaRepository.inserir(
                    new Loja(nome, telefone, email, senha, cnpj, endereco)
            );

            System.out.println("Cadastro realizado! Faca o login para continuar.");

        } catch (Exception e) {
            System.out.println("Erro ao salvar loja.");
        }
    }

    // =============================================================
    // TELA MENU CLIENTE
    // =============================================================
    static void telaMenuCliente(Cliente cliente) {

        List<Pedido> carrinho = new ArrayList<>();

        while (true) {
            System.out.println("\n=== MENU CLIENTE — " + cliente.getNome() + " ===");
            System.out.println("[1] Buscar lojas");
            System.out.println("[2] Meu carrinho  (" + carrinho.size() + " item(ns))");
            System.out.println("[3] Meus pedidos");
            System.out.println("[0] Logout");
            System.out.print("Escolha: ");
            int opcao = lerInt();

            switch (opcao) {
                case 1 -> telaBuscarLojas(cliente, carrinho);
                case 2 -> telaCarrinho(carrinho, cliente);
                case 3 -> telaPedidosCliente(cliente);
                case 0 -> { return; }
                default -> System.out.println("Opcao invalida.");
            }
        }
    }

    // =============================================================
    // TELA BUSCAR LOJAS
    // =============================================================
    static void telaBuscarLojas(Cliente cliente, List<Pedido> carrinho) {
        while (true) {
            System.out.println("\n=== LOJAS DISPONIVEIS ===");
            if (lojaRepository.listar().isEmpty()) {
                System.out.println("Nenhuma loja disponivel.");
                return;
            }

            for (int i = 0; i < lojaRepository.listar().size(); i++) {
                Loja l = lojaRepository.listar().get(i);
                System.out.printf("[%d] %-20s  %s%n", i + 1, l.getNome(), l.getEndereco());
            }
            System.out.println("[0] Voltar");
            System.out.print("Escolha uma loja: ");
            int opcao = lerInt();

            if (opcao == 0) return;

            if (opcao < 1 || opcao > lojaRepository.listar().size()) {
                System.out.println("Opcao invalida.");
                continue;
            }

            telaCardapio(cliente, lojaRepository.listar().get(opcao - 1), carrinho);
        }
    }

    static void telaCardapio(Cliente cliente, Loja loja, List<Pedido> carrinho) {
        while (true) {
            System.out.println("\n=== " + loja.getNome().toUpperCase() + " ===");

            List<Promocao> vitrine = loja.getVitrine();

            if (vitrine.isEmpty()) {
                System.out.println("Esta loja nao tem promocoes cadastradas.");
                return;
            }

            System.out.printf("%-4s %-20s %10s %10s  %s%n", "Nro", "Produto", "Original", "Promocao", "Validade");
            System.out.println("-".repeat(58));
            for (int i = 0; i < vitrine.size(); i++) {
                Promocao p = vitrine.get(i);
                System.out.printf("[%d] %-20s R$%7.2f   R$%7.2f  %s%n",
                        i + 1,
                        p.getProduto().getNome(),
                        p.getPrecoOriginal(),
                        p.getValorPromocional(),
                        p.getDataValidade()
                );
            }
            System.out.println("[0] Voltar");
            System.out.print("Escolha um item: ");
            int opcao = lerInt();

            if (opcao == 0) return;

            if (opcao < 1 || opcao > vitrine.size()) {
                System.out.println("Opcao invalida.");
                continue;
            }

            Promocao escolhida = vitrine.get(opcao - 1);

            System.out.print("Quantidade: ");
            int quantidade = lerInt();

            if (quantidade <= 0) {
                System.out.println("Quantidade invalida.");
                continue;
            }

            // cria o pedido e adiciona ao carrinho
            Pedido pedido = new Pedido(cliente);
            double valorTotal = escolhida.getValorPromocional() * quantidade;
            pedido.adicionarPromocao(escolhida, valorTotal);
            carrinho.add(pedido);

            System.out.println("Adicionado ao carrinho: " + quantidade + "x " + escolhida.getProduto().getNome());
        }
    }

    // =============================================================
    // TELA CARRINHO
    // =============================================================
    static void telaCarrinho(List<Pedido> carrinho, Cliente cliente) {
        while (true) {
            System.out.println("\n=== SEU CARRINHO ===");

            if (carrinho.isEmpty()) {
                System.out.println("Seu carrinho esta vazio.");
                return;
            }

            double totalGeral = 0;
            for (int i = 0; i < carrinho.size(); i++) {
                Pedido pedido = carrinho.get(i);
                String nomeProduto = pedido.getPromocoes().get(0).getProduto().getNome();
                System.out.printf("[%d] %-20s  R$ %6.2f%n", i + 1, nomeProduto, pedido.getValorTotal());
                totalGeral += pedido.getValorTotal();
            }

            System.out.println("-".repeat(36));
            System.out.printf("TOTAL                      R$ %6.2f%n", totalGeral);
            System.out.println("-".repeat(36));

            System.out.println("\n[1] Finalizar compra");
            System.out.println("[2] Remover um item");
            System.out.println("[0] Voltar");
            System.out.print("Escolha: ");
            int opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    telaCheckout(carrinho, totalGeral, cliente);
                    return;
                }
                case 2 -> {
                    System.out.print("Numero do item para remover: ");
                    int num = lerInt();
                    if (num >= 1 && num <= carrinho.size()) {
                        carrinho.remove(num - 1);
                        System.out.println("Item removido.");
                    } else {
                        System.out.println("Numero invalido.");
                    }
                }
                case 0 -> { return; }
                default -> System.out.println("Opcao invalida.");
            }
        }
    }

    // =============================================================
    // TELA CHECKOUT
    // =============================================================
    static void telaCheckout(List<Pedido> carrinho, double total, Cliente cliente) {
        System.out.println("\n=== FINALIZAR PEDIDO ===");
        System.out.printf("Total: R$ %.2f%n", total);

        System.out.print("Endereco de entrega: ");
        String endereco = sc.nextLine();

        System.out.println("Forma de pagamento:");
        System.out.println("[1] Pix");
        System.out.println("[2] Cartao");
        System.out.println("[3] Dinheiro");
        System.out.print("Escolha: ");
        int pagamento = lerInt();

        String formaPagamento = switch (pagamento) {
            case 1 -> "Pix";
            case 2 -> "Cartao";
            case 3 -> "Dinheiro";
            default -> "Dinheiro";
        };

        System.out.println("\n--- Confirmacao ---");
        System.out.println("Endereco : " + endereco);
        System.out.println("Pagamento: " + formaPagamento);
        System.out.printf("Total    : R$ %.2f%n", total);
        System.out.print("Confirmar? [S/N]: ");
        String resposta = sc.nextLine();

        if (resposta.equalsIgnoreCase("S")) {
            for (Pedido p : carrinho) {

                p.finalizar();

                try {
                    pedidoRepository.inserir(p);
                } catch (Exception e) {
                    System.out.println("Erro ao salvar pedido.");
                }
            }
            carrinho.clear();
            System.out.println("Pedido realizado com sucesso!");
        } else {
            System.out.println("Pedido cancelado. Carrinho mantido.");
        }
    }

    // =============================================================
    // TELA PEDIDOS DO CLIENTE
    // =============================================================
    static void telaPedidosCliente(Cliente cliente) {
        System.out.println("\n=== MEUS PEDIDOS ===");

        List<Pedido> pedidos =
                pedidoRepository.buscarPorCliente(
                        cliente.getEmail()
                );

        if (pedidos.isEmpty()) {
            System.out.println("Voce ainda nao fez nenhum pedido.");
            return;
        }

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            System.out.printf("[%d] Pedido #%03d  |  R$ %.2f%n", i + 1, i + 1, p.getValorTotal());
        }

        System.out.print("Escolha um pedido para ver detalhes (0 para voltar): ");
        int opcao = lerInt();

        if (opcao == 0 || opcao < 1 || opcao > pedidos.size()) return;

        Pedido pedido = pedidos.get(opcao - 1);
        System.out.println("\n--- Detalhes Pedido #" + String.format("%03d", opcao) + " ---");
        for (Promocao p : pedido.getPromocoes()) {
            System.out.println("   " + p.getProduto().getNome());
        }
        System.out.printf("Total: R$ %.2f%n", pedido.getValorTotal());
    }

    // =============================================================
    // TELA MENU LOJA
    // =============================================================
    static void telaMenuLoja(Loja loja) {
        while (true) {
            System.out.println("\n=== MENU LOJA — " + loja.getNome() + " ===");
            System.out.println("[1] Gerenciar cardapio");
            System.out.println("[2] Ver vitrine");
            System.out.println("[0] Logout");
            System.out.print("Escolha: ");
            int opcao = lerInt();

            switch (opcao) {
                case 1 -> telaGerenciarCardapio(loja);
                case 2 -> telaVerVitrine(loja);
                case 0 -> { return; }
                default -> System.out.println("Opcao invalida.");
            }
        }
    }

    static void telaVerVitrine(Loja loja) {
        System.out.println("\n=== VITRINE — " + loja.getNome() + " ===");

        if (loja.getVitrine().isEmpty()) {
            System.out.println("Nenhuma promocao cadastrada.");
            return;
        }

        for (Promocao p : loja.getVitrine()) {
            System.out.printf("%-20s  R$%.2f -> R$%.2f  val: %s%n",
                    p.getProduto().getNome(),
                    p.getPrecoOriginal(),
                    p.getValorPromocional(),
                    p.getDataValidade()
            );
        }
    }

    // =============================================================
    // TELA GERENCIAR CARDAPIO
    // =============================================================
    static void telaGerenciarCardapio(Loja loja) {
        LojaService lojaService = new LojaService(loja);

        while (true) {
            System.out.println("\n=== CARDAPIO — " + loja.getNome() + " ===");

            List<Produto> estoque = loja.getEstoque();

            if (estoque.isEmpty()) {
                System.out.println("Nenhum produto cadastrado.");
            } else {
                for (int i = 0; i < estoque.size(); i++) {
                    System.out.printf("[%d] %-20s  %s%n", i + 1, estoque.get(i).getNome(), estoque.get(i).getDescricao());
                }
            }

            System.out.println("\n[A] Adicionar produto");
            System.out.println("[P] Adicionar promocao");
            System.out.println("[0] Voltar");
            System.out.print("Escolha: ");
            String opcao = sc.nextLine().trim().toUpperCase();

            switch (opcao) {
                case "A" -> {
                    System.out.println("\n--- Novo Produto ---");
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Descricao: ");
                    String descricao = sc.nextLine();
                    System.out.println(lojaService.CadastrarProduto(nome, descricao));
                    try {
                        lojaRepository.salvar();
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar loja.");
                    }
                }
                case "P" -> {
                    if (estoque.isEmpty()) {
                        System.out.println("Cadastre um produto primeiro.");
                        continue;
                    }
                    System.out.println("\n--- Nova Promocao ---");
                    System.out.println("Escolha o produto:");
                    for (int i = 0; i < estoque.size(); i++) {
                        System.out.printf("[%d] %s%n", i + 1, estoque.get(i).getNome());
                    }
                    System.out.print("Produto: ");
                    int num = lerInt();
                    if (num < 1 || num > estoque.size()) {
                        System.out.println("Opcao invalida.");
                        continue;
                    }
                    Produto produto = estoque.get(num - 1);
                    System.out.print("Validade (dd/mm/aaaa): ");
                    String validade = sc.nextLine();
                    System.out.print("Preco original: R$ ");
                    double precoOriginal = lerDouble();
                    System.out.print("Preco promocional: R$ ");
                    double precoPromocional = lerDouble();
                    System.out.print("Quantidade disponivel: ");
                    int quantidade = lerInt();
                    System.out.println(lojaService.CadastrarPromoção(produto, validade, precoPromocional, precoOriginal, quantidade));
                    try {
                        lojaRepository.salvar();
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar loja.");
                    }
                }
                case "0" -> { return; }
                default  -> System.out.println("Opcao invalida.");
            }
        }
    }


    // =============================================================
    // os leitores pro codigo nn quebrar
    // =============================================================

    static int lerInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Digite um numero valido: ");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }

    static double lerDouble() {
        while (!sc.hasNextDouble()) {
            System.out.print("Digite um valor valido: ");
            sc.next();
        }
        double valor = sc.nextDouble();
        sc.nextLine();
        return valor;
    }
}