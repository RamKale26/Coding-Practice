import java.util.*;

class Book {
    int id;
    String name;
    double price;
    String author;

    public Book(int id, String name, double price, String author) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book [ID=" + id + ", Name=" + name + ", Price=" + price + ", Author=" + author + "]";
    }
}

class Library {
    private Book[] books = new Book[10];
    private int count = 0;

    void addNewBooks(Book... newBooks) {
        for (Book newBook : newBooks) {
            if (count >= books.length) {
                System.out.println("Library is full, cannot add more books.");
                return;
            }
            boolean duplicate = false;
            for (int i = 0; i < count; i++) {
                if (books[i].id == newBook.id) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                books[count++] = newBook;
            } else {
                System.out.println("Duplicate book entry not allowed: " + newBook.name);
            }
        }
    }

    void showBooks() {
        for (int i = 0; i < count; i++) {
            System.out.println(books[i]);
        }
    }

    Book getBookByName(int id) {
        for (int i = 0; i < count; i++) {
            if (books[i].id == id) {
                return books[i];
            }
        }
        return null;
    }

    Book[] getBookSortedByPrice() {
        Book[] sortedBooks = Arrays.copyOf(books, count);
        Arrays.sort(sortedBooks, (b1, b2) -> Double.compare(b1.price, b2.price));
        return sortedBooks;
    }

    Book deleteBookById(int id) {
        for (int i = 0; i < count; i++) {
            if (books[i].id == id) {
                Book deletedBook = books[i];
                for (int j = i; j < count - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[--count] = null;
                return deletedBook;
            }
        }
        return null;
    }

    Book[] findBooksByPrice() {
        int tempCount = 0;
        Book[] tempBooks = new Book[count];
        
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                if (books[i].price == books[j].price) {
                    tempBooks[tempCount++] = books[i];
                    tempBooks[tempCount++] = books[j];
                }
            }
        }
        return Arrays.copyOf(tempBooks, tempCount);
    }
}

public class LM{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add New Book");
            System.out.println("2. Show All Books");
            System.out.println("3. Get Book by ID");
            System.out.println("4. Get Books Sorted by Price");
            System.out.println("5. Delete Book by ID");
            System.out.println("6. Find Books by Same Price");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter Book Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Book Price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); 
                    System.out.print("Enter Author Name: ");
                    String author = scanner.nextLine();
                    library.addNewBooks(new Book(id, name, price, author));
                    break;
                case 2:
                    library.showBooks();
                    break;
                case 3:
                    System.out.print("Enter Book ID to search: ");
                    int searchId = scanner.nextInt();
                    Book foundBook = library.getBookByName(searchId);
                    System.out.println(foundBook != null ? foundBook : "Book not found.");
                    break;
                case 4:
                    System.out.println("Books sorted by price:");
                    for (Book b : library.getBookSortedByPrice()) {
                        System.out.println(b);
                    }
                    break;
                case 5:
                    System.out.print("Enter Book ID to delete: ");
                    int deleteId = scanner.nextInt();
                    Book deletedBook = library.deleteBookById(deleteId);
                    System.out.println(deletedBook != null ? "Deleted: " + deletedBook : "Book not found.");
                    break;
                case 6:
                    System.out.println("Books with the same price:");
                    for (Book b : library.findBooksByPrice()) {
                        System.out.println(b);
                    }
                    break;
                case 7:
                    System.out.println("Exiting...\n");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}	
