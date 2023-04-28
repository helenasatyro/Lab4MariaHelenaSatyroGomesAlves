package MrBet;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.exibeMenu();
    }
    private final Scanner scanner;
    private final MrBet mrBet;
    public Menu() {
        this.scanner = new Scanner(System.in);
        this.mrBet = new MrBet();
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
                                    
                    Opção> 
                    """);

            cmd(scanner.nextLine().toUpperCase());
        }
    }

    private void cmd(String cmd) {
        if (cmd == null || cmd.isBlank()) {
            throw new IllegalArgumentException("Comando não pode ser nulo ou vazio.");
        }

        switch (cmd) {
            case "M" -> inclusaoTimes();
            case "R" -> recuperaTime();
            case "." -> addCampeonato();
            case "B" -> adicionaTimeOuVerificaCampeonato();
            case "E" -> exibeCampeonatosTime();
            case "T" -> tentaSorte();
            case "!" -> encerrar();
        }
    }

    private void adicionaTimeOuVerificaCampeonato() {
        System.out.println("(I) Incluir time em campeonato ou (V) Verificar se time está em campeonato? ");
        String cmd = scanner.nextLine().toUpperCase();
        switch (cmd) {
            case "I" -> incluiTimeNoCampeonato();
            case "V" -> verificaEstaNoCampeonato();
        }
    }

    private void incluiTimeNoCampeonato() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.println("Campeonato: ");
        String campeonato = scanner.nextLine();
        if (mrBet.addAoCampeonato(codigo, campeonato)) {
            System.out.println("TIME INCLUÍDO NO CAMPEONATO!");
        }

    }

    private void addCampeonato() {
        System.out.print("Campeonato: ");
        String campeonato = scanner.nextLine();
        System.out.print("Participantes: ");
        int participantes = Integer.parseInt(scanner.nextLine());

        if (mrBet.addCampeonato(campeonato, participantes)) {
            System.out.println("CAMPEONATO ADICIONADO!");
        } else {
            System.out.println("CAMPEONATO JÁ EXISTE!");
        }
    }

    private void recuperaTime() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        Time time = mrBet.getTime(codigo);
        if (time == null) {
            System.out.println("TIME NÃO EXISTE!");
        } else {
            System.out.println(time);
        }
    }

    private void inclusaoTimes() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Mascote: ");
        String mascote = scanner.nextLine();

        if (mrBet.incluiTime(codigo, nome, mascote)) {
            System.out.println("INCLUSÃO REALIZADA!");
        } else {
            System.out.println("TIME JÁ EXISTE!");
        }
    }
}
