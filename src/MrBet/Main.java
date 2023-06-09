package MrBet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main menu = new Main();
        menu.exibeMenu();
    }
    private final Scanner scanner;
    private final SistemaMrBet mrBet;
    public Main() {
        this.scanner = new Scanner(System.in);
        this.mrBet = new SistemaMrBet();
    }
    public void exibeMenu() {
        while (true) {
            System.out.print("""
                    (M)Minha inclusão de times
                    (R)Recuperar time
                    (.)Adicionar campeonato
                    (B)Bora incluir time em campeonato e Verificar se time está em campeonato
                    (E)Exibir campeonatos que o time participa
                    (T)Tentar a sorte e status
                    (!)Já pode fechar o programa!
                    (H)Histórico
                                    
                    Opção>\s""");

            cmd(scanner.nextLine().toUpperCase());
        }
    }

    private void cmd(String cmd) {
        if (cmd.isBlank()) {
            throw new IllegalArgumentException("Entrada inválida.");
        }

        switch (cmd) {
            case "M" -> inclusaoTimes();
            case "R" -> recuperaTime();
            case "." -> addCampeonato();
            case "B" -> adicionaTimeOuVerificaCampeonato();
            case "E" -> exibeCampeonatosTime();
            case "T" -> tentaSorte();
            case "!" -> encerrar();
            case "H" -> historico();
            default -> System.out.println("Entrada inválida. Tente novamente.");

        }
    }

    private void historico() {
        System.out.println(mrBet.historico());
    }

    private void tentaSorte() {
        System.out.print("(A)Apostar ou (S)Status das Apostas? ");
        String cmd = scanner.nextLine().toUpperCase();
        if (cmd.isBlank()) {
            throw new IllegalArgumentException("Entrada inválida.");
        }

        switch (cmd) {
            case "A" -> apostar();
            case "S" -> statusApostas();
        }
    }

    private void statusApostas() {
        mrBet.verApostas();
    }

    private void apostar() {
        System.out.print("Código:");
        String codigo = scanner.nextLine();
        if (codigo.isBlank()) {
            throw new IllegalArgumentException("Entrada inválida.");
        }
        System.out.print("Campeonato: ");
        String camp = scanner.nextLine();
        if (camp.isBlank()) {
            throw new IllegalArgumentException("Entrada inválida.");
        }
        System.out.print("Colocação: ");
        int colocacao = scanner.nextInt();
        scanner.nextLine();
        double valor = scanner.nextDouble();
        scanner.nextLine();
        System.out.println(mrBet.addAposta(codigo, camp, valor, colocacao));

    }

    private void exibeCampeonatosTime() {
        System.out.print("Time: ");
        String codigo = scanner.nextLine();
        if (codigo.isBlank()) {
            throw new IllegalArgumentException("Entrada inválida.");
        }
        System.out.println(mrBet.verCampeonatos(codigo));
    }

    private void encerrar() {
        System.out.println("Por hoje é só pessoal!");
        System.exit(0);
    }

    private void adicionaTimeOuVerificaCampeonato() {
        System.out.println("(I) Incluir time em campeonato ou (V) Verificar se time está em campeonato? ");
        String cmd = scanner.nextLine().toUpperCase();
        if (cmd.isBlank()) {
            throw new IllegalArgumentException("Entrada inválida.");
        }
        switch (cmd) {
            case "I" -> incluiTimeNoCampeonato();
            case "V" -> verificaEstaNoCampeonato();
        }
    }

    private void verificaEstaNoCampeonato() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        if (codigo.isBlank()) {
            throw new IllegalArgumentException("Entrada inválida.");
        }
        System.out.print("Campeonato: ");
        String campeonato = scanner.nextLine();
        if (campeonato.isBlank()) {
            throw new IllegalArgumentException("Entrada inválida.");
        }
        try {
            if (mrBet.timeNoCampeonato(codigo, campeonato)) {
                System.out.println("TIME ESTÁ NO CAMPEONATO");
            } else {
                System.out.println("TIME NÃO ESTÁ NO CAMPEONATO");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void incluiTimeNoCampeonato() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        if (codigo.isBlank()) {
            throw new IllegalArgumentException("Entrada inválida.");
        }
        System.out.print("Campeonato: ");
        String campeonato = scanner.nextLine();
        if (campeonato.isBlank()) {
            throw new IllegalArgumentException("Entrada inválida.");
        }
        System.out.println(mrBet.addAoCampeonato(codigo, campeonato));
    }

    private void addCampeonato() {
        System.out.print("Campeonato: ");
        String campeonato = scanner.nextLine();
        if (campeonato.isBlank()) {
            throw new IllegalArgumentException("Entrada inválida.");
        }
        System.out.print("Participantes: ");
        int participantes = Integer.parseInt(scanner.nextLine());
        System.out.println(mrBet.addCampeonato(campeonato, participantes));
    }

    private void recuperaTime() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        if (codigo.isBlank()) {
            throw new IllegalArgumentException("Entrada inválida.");
        }
        Time time = mrBet.getTime(codigo);
        if (time == null) {
            System.out.println("TIME NÃO EXISTE!");
        } else {
            System.out.println(time + "\n");
        }
    }

    private void inclusaoTimes() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        if (codigo.isBlank()) {
            throw new IllegalArgumentException("Entrada inválida.");
        }
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Entrada inválida.");
        }
        System.out.print("Mascote: ");
        String mascote = scanner.nextLine();
        if (mascote.isBlank()) {
            throw new IllegalArgumentException("Entrada inválida.");
        }
        System.out.println(mrBet.incluiTime(codigo, nome, mascote));
    }
}