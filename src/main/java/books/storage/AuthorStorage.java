package books.storage;


import books.exception.AuthorNotFoundException;
import books.model.Author;

public class AuthorStorage {

    private Author[] array = new Author[10];
    private int size = 0;

    public void add(Author author) {
        if (array.length == size) {
            extend();
        }
        array[size++] = author;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + " " + array[i] + " ");
        }
    }

    private void extend() {
        Author[] temp = new Author[array.length + 10];
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


    public Author getAuthorByIndex(int authorIndex) throws AuthorNotFoundException {
        if(authorIndex >= 0 && authorIndex< size){
            return array[authorIndex];
        }
        throw new AuthorNotFoundException("Please input correct index.");
    }

}

