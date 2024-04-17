import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private String nim;
    private String faculty;
    private String studyProgram;
    private static List<Book> borrowedBooks;
    private static Scanner scanner;

    public Student(String name, String nim, String faculty, String studyProgram) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.studyProgram = studyProgram;
        this.borrowedBooks = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public static void menuStudent() {
        int choice;
        do {
            System.out.println("Student Menu");
            System.out.println("1. Display Books");
            System.out.println("2. Borrowed Books");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Logout");
            System.out.println("Choose option: ");
            choice = readIntegerInput();

            switch (choice) {
                case 1:
                    displayBooks(Main.bookList);
                    break;
                case 2:
                    displayBorrowedBooks();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    System.out.println("Logging out from student account...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 4);
    }

    public static int readIntegerInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next(); // consume the invalid input
        }
        return scanner.nextInt();
    }

    public static void displayBooks(Book[] books) {
        System.out.println("Available Books:");
        for (Book book : books) {
            System.out.println(book.getTitle() + " (" + book.getAuthor() + ") - " + book.getStock() + " copies available");
        }
    }

    private static void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("You haven't borrowed any books.");
        } else {
            System.out.println("Borrowed Books:");
            for (Book book : borrowedBooks) {
                System.out.println(book.getTitle() + " (" + book.getAuthor() + ")");
            }
        }
    }

    private static void borrowBook() {
        System.out.println("Enter the ID of the book you want to borrow: ");
        scanner.nextLine();
        String bookId = scanner.nextLine();
        Book bookToBorrow = null;
        for (Book book : Main.bookList) {
            if (book.getId().equals(bookId)) {
                bookToBorrow = book;
                break;
            }
        }
        if (bookToBorrow != null) {
            if (bookToBorrow.getStock() > 0) {
                borrowedBooks.add(bookToBorrow);
                bookToBorrow.setStock(bookToBorrow.getStock() - 1);
                System.out.println("Book borrowed successfully.");
            } else {
                System.out.println("Sorry, the book is currently out of stock.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }
}
