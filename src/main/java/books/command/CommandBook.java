package books.command;

public interface CommandBook {
    int LOGOUT = 0;
    int ADD_BOOK = 1;
    int ALL_BOOKS = 2;
    int BY_AUTHOR_ALL_BOOKS = 3;
    int BY_GENRE_ALL_BOOKS = 4;
    int PRICE_RANGE = 5;
    int ADD_AUTHOR = 6;
    int PRINT_ALL_AUTHORS = 7;

    int EXIT = 0;
    int LOGIN = 1;
    int REGISTER = 2;

    int PRINT_ALL_BOOKS = 1;
    int PRINT_BY_AUTHOR_ALL_BOOKS = 2;
    int PRINT_BY_GENRE_ALL_BOOKS = 3;


    static void showCommands() {
        System.out.println("Please enter " + LOGOUT + " to logout. ");
        System.out.println("Please enter " + ADD_BOOK + " to add book. ");
        System.out.println("Please enter " + ALL_BOOKS + " to print all books. ");
        System.out.println("Please enter " + BY_AUTHOR_ALL_BOOKS + " to print books by author name. ");
        System.out.println("Please enter " + BY_GENRE_ALL_BOOKS + " to print books by genre. ");
        System.out.println("Please enter " + PRICE_RANGE + " to print books by price range. ");
        System.out.println("Please enter " + ADD_AUTHOR + " to add author. ");
        System.out.println("Please enter " + PRINT_ALL_AUTHORS + " to print all authors. ");
    }

    static void userCommands(){
        System.out.println("Please enter " + LOGOUT + " to logout. ");
        System.out.println("Please enter " + PRINT_ALL_BOOKS + " to print all books. ");
        System.out.println("Please enter " + PRINT_BY_AUTHOR_ALL_BOOKS + " to print books by author name. ");
        System.out.println("Please enter " + PRINT_BY_GENRE_ALL_BOOKS + " to print books by genre. ");
    }

    static void printLoginCommands() {
        System.out.println("Please enter " + EXIT + " to exit. ");
        System.out.println("Please enter " + LOGIN + " to login. ");
        System.out.println("Please enter " + REGISTER + " to register. ");
    }
}
