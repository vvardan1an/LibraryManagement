package books.storage;

import books.model.User;

public class UserStorage {

    private User[] array = new User[10];
    private int size = 0;

    public void add(User user) {
        if (array.length == size) {
            extend();
        }
        array[size++] = user;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + " " + array[i] + " ");
        }
    }

    private void extend() {
        User[] temp = new User[array.length + 10];
        System.arraycopy(array, 0, temp, 0, array.length);
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
            System.out.println("User deleted");
        } else {
            System.out.println("Index out of bounds");
        }
    }

    public int getSize() {
        return size;
    }

    public User getUserByEmail(String email) {
        for (int i = 0; i < size; i++) {
            if (array[i].getEmail().equals(email)) {
                return array[i];
            }
        }
        return null;
    }
}

