package org.example.sliptwise;

import org.example.sliptwise.Commands.CommandRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"org.example.sliptwise.Repositories"})
public class SliptWiseApplication implements CommandLineRunner {

    private Scanner scanner;
    private CommandRegistry commandRegistry;
    @Autowired
    public SliptWiseApplication(CommandRegistry commandRegistry) {
        this.scanner = new Scanner(System.in);
        this.commandRegistry= commandRegistry;

    }

    public static void main(String[] args) {
        SpringApplication.run(SliptWiseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        while(true){
            System.out.println("Tell me what you want to do");
            String input = scanner.nextLine();
            commandRegistry.execute(input);

        }


    }
}
