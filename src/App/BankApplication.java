package App;

import java.util.Objects;
import java.util.Scanner;
import java.util.List;

public class BankApplication {
    Admin admin = new Admin();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        Customer customer = null;

        while (true) {
            System.out.println("Aplikacja Bank");
            System.out.println("Wybierz opcję:");
            System.out.println("1. Zaloguj jako administrator");
            System.out.println("2. Zaloguj jako klient");
            System.out.println("3. Wyjdź");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    adminLoop:
                    while (true) {
                        System.out.println("Wybierz opcję:");
                        System.out.println("1. Dodaj klienta");
                        System.out.println("2. Usuń klienta");
                        System.out.println("3. Modyfikuj ustawienia klienta");
                        System.out.println("4. Wygeneruj raport operacji klienta");
                        System.out.println("5. Wyloguj");

                        int adminChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (adminChoice) {
                            case 1:
                                System.out.println("Podaj imię i nazwisko:");
                                String name = scanner.nextLine();

                                System.out.println("Podaj adres email:");
                                String email = scanner.nextLine();

                                String accountNumber = "";
                                while(!admin.accountNumberValidation(accountNumber)) {
                                    System.out.println("Podaj numer konta (16 cyfrowy format):");
                                    accountNumber = scanner.nextLine();
                                }

                                String phoneNumber = "";
                                while(!admin.phoneNumberValidation(phoneNumber)) {
                                    System.out.println("Podaj numer telefonu (9 cyfrowy format):");
                                    phoneNumber = scanner.nextLine();
                                }

                                admin.addCustomer(name, email, accountNumber, phoneNumber);
                                admin.getCustomerByMail(email);
                                break;
                            case 2:
                                System.out.println("Podaj email pracownika, którego chcesz usunąć:");
                                String customerToRemove = scanner.nextLine();
                                admin.removeCustomer(customerToRemove);
                                break;
                            case 3:
                                System.out.println("Możesz tylko zmienić nazwę klienta oraz numer telefonu!");

                                System.out.println("Podaj email klienta do edycji:");
                                String customerMailToModification = scanner.nextLine();
                                Customer customerToModification = admin.getCustomerByMail(customerMailToModification);

                                System.out.println("Podaj nową nazwę klienta:");
                                String newName = scanner.nextLine();

                                String newPhoneNumber = "";
                                while(!admin.phoneNumberValidation(newPhoneNumber)) {
                                    System.out.println("Podaj nowy numer telefonu klienta: (9 cyfrowy format):");
                                    newPhoneNumber = scanner.nextLine();
                                }

                                admin.modifyCustomerSettings(customerToModification, newName, newPhoneNumber);
                                break;
                            case 4:
                                System.out.println("Podaj email klienta:");
                                String customerMailToReport = scanner.nextLine();
                                Customer customerToReport = admin.getCustomerByMail(customerMailToReport);

                                List<String> report = customerToReport.getReport();
                                System.out.print(report);
                                break;
                            case 5:
                                System.out.println("Wylogowano administratora.");
                                break adminLoop;
                            default:
                                System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                        }
                    }
                    break;
                case 2:
                    customerLoop:
                    while (true) {
                        while (customer == null) {
                            System.out.println("0 - powrót!");
                            System.out.println("Podaj email:");
                            String input = scanner.nextLine();
                            if(Objects.equals(input, "0")) break customerLoop;
                            customer = admin.getCustomerByMail(input);
                        }


                        System.out.println("Wybierz opcję:");
                        System.out.println("1. Zasil konto");
                        System.out.println("2. Wykonaj przelew");
                        System.out.println("3. Weź kredyt (oprocentowanie 10%)");
                        System.out.println("4. Przegląd historii operacji");
                        System.out.println("5. Wyloguj");

                        System.out.println("\n Saldo: " + customer.getBalance() + " zł");

                        int customerChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (customerChoice) {
                            case 1:
                                System.out.println("Podaj kwotę:");
                                float depositAmount = scanner.nextFloat();
                                scanner.nextLine();
                                customer.deposit(depositAmount);
                                break;
                            case 2:
                                System.out.println("Podaj numer konta na który chcesz przelać pieniądze:");
                                String accountNumber = scanner.nextLine();
                                Customer recipient = admin.getCustomerByAccountNumber(accountNumber);

                                System.out.println("Podaj kwotę jaką chcesz przelać:");
                                float transferAmount = scanner.nextFloat();
                                scanner.nextLine();

                                customer.sendMoney(recipient, transferAmount);

                                break;
                            case 3:
                                System.out.println("Podaj kwotę jaką chcesz pożyczyć:");
                                float creditAmount = scanner.nextFloat();
                                scanner.nextLine();
                                float balanceWithCredit = customer.takeCredit(creditAmount, 0.1F);
                                System.out.println("\n Saldo przed spłatą kredytu: " + balanceWithCredit + " zł");
                                break;
                            case 4:
                                List<String> report = customer.getReport();
                                System.out.println(report);
                                break;
                            case 5:
                                System.out.println("Wylogowano klienta.");
                                customer = null;
                                break customerLoop;
                            default:
                                System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Zamknięcie!");
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }
}
