package books;

import books.command.CommandBook;
import books.enums.AuthorGender;
import books.enums.Role;
import books.exception.AuthorNotFoundException;
import books.model.Author;
import books.model.Book;
import books.model.User;
import books.storage.AuthorStorage;
import books.storage.BookStorage;
import books.storage.UserStorage;

import java.util.Scanner;

public class BookDemo implements CommandBook {

    private static final Scanner scannerBook = new Scanner(System.in);
    private static final BookStorage bookStorage = new BookStorage();
    private static final AuthorStorage authorStorage = new AuthorStorage();
    private static final UserStorage userStorage = new UserStorage();

    private static boolean isRunning = true;

    public static void main(String[] args) {

        userStorage.add(new User("admin", "admin", "admin@gmail.com", "123456", Role.ADMIN));

        startProgram();
    }

    private static void startProgram() {
        boolean isRunning = true;

        while (isRunning) {
            CommandBook.printLoginCommands();

            int command = getCommand();

            switch (command) {
                case EXIT:
                    isRunning = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                default:
                    System.out.println("Invalid command , please try again.");
                    break;
            }
        }
    }

    private static void login() {
        System.out.println("Please enter email.");
        String login = scannerBook.nextLine();

        System.out.println("Please enter password");
        String password = scannerBook.nextLine();

        String[] loginData = {login, password};

        User user = userStorage.getUserByEmail(loginData[0]);
        if (user == null) {
            System.out.println("User does not exists.");
        } else {
            if (!user.getPassword().equals(loginData[1])) {
                System.out.println("Password is wrong !");
            } else {
                if (user.getRole() == Role.ADMIN) {
                    loginAdmin();
                } else if (user.getRole() == Role.USER) {
                    loginUser();
                }
            }
        }
    }

    private static void register() {
        User user = new User();

        System.out.println("Please input name.");
        user.setName(scannerBook.nextLine());

        System.out.println("Please input surname.");
        user.setSurName(scannerBook.nextLine());

        System.out.println("Please input email.");
        user.setEmail(scannerBook.nextLine());

        System.out.println("Please input password.");
        user.setPassword(scannerBook.nextLine());

        user.setRole(Role.USER);
        if (userStorage.getUserByEmail(user.getEmail()) == null) {

            validateRegister(user);
            userStorage.add(user);

            System.out.println("Thanks " + user.getName() + " You're registered.");

        } else {
            System.out.println("User with " + user.getEmail() + " already exists.");
        }
    }

    private static void validateRegister(User user) {

        if (user.getName().equals("") || user.getSurName().equals("") || user.getEmail().equals("")
                || user.getPassword().equals("")) {
            System.out.println("Please input correct name/surname/email/password.");
            startProgram();
        }
    }

    private static void loginUser() {
        while (isRunning) {
            CommandBook.userCommands();
            int command = getCommand();

            switch (command) {
                case LOGOUT:
                    isRunning = false;
                    System.out.println("EXIT");
                    break;
                case ALL_BOOKS:
                    bookStorage.print();
                    break;
                case BY_AUTHOR_ALL_BOOKS:
                    printByAuthorName();
                    break;
                case BY_GENRE_ALL_BOOKS:
                    printByGenre();
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }

    private static void loginAdmin() {
        while (isRunning) {
            CommandBook.showCommands();
            int command = getCommand();

            switch (command) {
                case LOGOUT:
                    isRunning = false;
                    System.out.println("EXIT");
                    break;
                case ADD_BOOK:
                    addBook();
                    break;
                case ALL_BOOKS:
                    bookStorage.print();
                    break;
                case BY_AUTHOR_ALL_BOOKS:
                    printByAuthorName();
                    break;
                case BY_GENRE_ALL_BOOKS:
                    printByGenre();
                    break;
                case PRICE_RANGE:
                    printByPriceRange();
                    break;
                case ADD_AUTHOR:
                    addAuthor();
                    break;
                case PRINT_ALL_AUTHORS:
                    authorStorage.print();
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }

    private static int getCommand() {
        int command = 0;
        try {
            command = Integer.parseInt(scannerBook.nextLine());
        } catch (NumberFormatException exception) {
            System.out.println("Please input correct command.");
            getCommand();
        }
        return command;
    }

    private static void addAuthor() {
        System.out.println("Please input author name.");
        String name = scannerBook.nextLine();

        System.out.println("Please input author surname.");
        String surName = scannerBook.nextLine();

        System.out.println("Please input email.");
        String email = scannerBook.nextLine();

        System.out.println("Please input gender. (Male or Female)");
        AuthorGender gender = choseGender();

        Author author = new Author(name, surName, email, gender);
        authorStorage.add(author);
        System.out.println("Author created !");
    }

    private static AuthorGender choseGender() {
        AuthorGender gender;
        try {
            gender = AuthorGender.valueOf(scannerBook.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong. Please try again. ");
            gender = choseGender();
        }
        return gender;
    }

    private static void printByGenre() {
        System.out.println("Please input book genre. ");
        String genre = scannerBook.nextLine();
        bookStorage.printBookByGenre(genre);
    }

    private static void printByAuthorName() {
        System.out.println("Please input author name. ");
        String authorName = scannerBook.nextLine();
        bookStorage.printBookByAuthorName(authorName);
    }

    private static void printByPriceRange() {
        System.out.println("Please input first book price.  ");
        double minPrice = Double.parseDouble(scannerBook.nextLine());
        System.out.println("Please input second book price.  ");
        double maxPrice = Double.parseDouble(scannerBook.nextLine());
        bookStorage.printBookByPrice(minPrice, maxPrice);
    }

    private static void addBook() {
        if (authorStorage.getSize() == 0) {
            System.out.println("Please add author");
            addAuthor();
        } else {
            authorStorage.print();
            System.out.println("Please chose author index.");

            try {
                int authorIndex = Integer.parseInt(scannerBook.nextLine());
                Author author = authorStorage.getAuthorByIndex(authorIndex);

                System.out.println("Please input book title. ");
                String title = scannerBook.nextLine();

                double price = 0;
                price = getPrice(price);


                System.out.println("Please input book genre. ");
                String genre = scannerBook.nextLine();

                int count = 0;
                count = getCount(count);


                Book book = new Book(title, author, price, count, genre);
                bookStorage.add(book);
                System.out.println("Thank you ! Book added . ");
            } catch (NumberFormatException | AuthorNotFoundException e) {
                System.out.println(e.getMessage());
                addBook();
            }
        }
    }

    private static int getCount(int count) {
        try {
            System.out.println("Please input book count. ");
            count = Integer.parseInt(scannerBook.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please input correct count. ");
            getCount(count);
        }
        return count;
    }

    private static double getPrice(double price) {
        try {
            System.out.println("Please input book price.");
            price = Double.parseDouble(scannerBook.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please input correct price.");
            getPrice(price);
        }
        return price;
    }
}
