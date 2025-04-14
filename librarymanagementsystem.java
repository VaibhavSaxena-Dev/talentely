import java.util.ArrayList;
import java.util.Scanner;

public class librarymanagementsystem {
    private ArrayList<Book> books;
    private ArrayList<User> users;

    public librarymanagementsystem() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
        System.out.println("Book added: " + title);
    }

    public void registerUser(String name) {
        if (findUser(name) == null) {
            users.add(new User(name));
            System.out.println("User registered: " + name);
        } else {
            System.out.println("User already exists.");
        }
    }

    public void borrowBook(String userName, String bookTitle) {
        User user = findUser(userName);
        Book book = findBook(bookTitle);

        if (user == null) {
            System.out.println("User not found.");
        } else if (book == null) {
            System.out.println("Book not found.");
        } else if (book.isBorrowed()) {
            System.out.println("Book is already borrowed.");
        } else {
            book.setBorrowed(true);
            user.borrowBook(book);
            System.out.println(userName + " borrowed " + bookTitle);
        }
    }

    public void returnBook(String userName, String bookTitle) {
        User user = findUser(userName);
        Book book = findBook(bookTitle);

        if (user == null) {
            System.out.println("User not found.");
        } else if (book == null) {
            System.out.println("Book not found.");
        } else if (!book.isBorrowed()) {
            System.out.println("Book is not currently borrowed.");
        } else {
            book.setBorrowed(false);
            user.returnBook(book);
            System.out.println(userName + " returned " + bookTitle);
        }
    }

    private User findUser(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    private Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void displayBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void main(String[] args) {
        librarymanagementsystem library = new librarymanagementsystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Register User");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Books");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                    break;
                case 2:
                    System.out.print("Enter user name: ");
                    String userName = scanner.nextLine();
                    library.registerUser(userName);
                    break;
                case 3:
                    System.out.print("Enter user name: ");
                    String borrower = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String borrowBook = scanner.nextLine();
                    library.borrowBook(borrower, borrowBook);
                    break;
                case 4:
                    System.out.print("Enter user name: ");
                    String returner = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String returnBook = scanner.nextLine();
                    library.returnBook(returner, returnBook);
                    break;
                case 5:
                    library.displayBooks();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

class Book {
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.isBorrowed = borrowed;
    }

    @Override
    public String toString() {
        return title + " by " + author + (isBorrowed ? " (Borrowed)" : " (Available)");
    }
}

class User {
    private String name;
    private ArrayList<Book> borrowedBooks;

    public User(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}
