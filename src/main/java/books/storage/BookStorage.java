package books.storage;

import books.model.Book;

public class BookStorage {

    private Book[] array = new Book[10];
    private int size = 0;

    public void add(Book book) {
        if (array.length == size) {
            extend();
        }
        array[size++] = book;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + " " + array[i] + " ");
        }
    }

    private void extend() {
        Book[] temp = new Book[array.length + 10];
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void delete(int index) {
        if (index < size && index >= 0) {
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            size--;
            System.out.println("Student deleted");
        } else {
            System.out.println("Index out of bounds");
        }
    }

    public int getSize() {
        return size;
    }

    public void printBookByAuthorName(String authorName) {
        for (int i = 0; i < size; i++) {
            if (array[i].getAuthor().equals(authorName)) {
                System.out.println(array[i]);
            }
        }
    }

    public void printBookByGenre(String genre) {
        for (int i = 0; i < size; i++) {
            if (array[i].getGenre().equals(genre)) {
                System.out.println(array[i]);
            }
        }
    }

    public void printBookByPrice(double minPrice, double maxPrice) {
        for (int i = 0; i < size; i++) {
            if (array[i].getPrice() >= minPrice && array[i].getPrice() <= maxPrice) {
                System.out.println(array[i]);
            }
        }
    }
}

