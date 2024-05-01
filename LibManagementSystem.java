import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LibraryMngSystem2 {
    Scanner sc = new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<String> books = new ArrayList<>(20);
    ArrayList<String> issuedBooks = new ArrayList<>(20);
    ArrayList<String> names = new ArrayList<>(20);
    ArrayList<Integer> ids = new ArrayList<>(20);


    HashMap<Integer, Integer> map = new HashMap<>();
    //HashMap<String, String[]> issuedBookdict = new HashMap<>();

    //HashMap<String, ArrayList<String>> issuedBookdict = new HashMap<>();
    HashMap<Integer, ArrayList<String>> issuedBookdict = new HashMap<>();

    LibraryMngSystem2() {
        books.add("Bhagabad Gita");
        books.add("Rich Dad Poor dad");
        books.add("Power");
        books.add("The Power of the Subconscious mind");
        books.add("The Richest Man in Babylon");
    }

    void donateBooks() throws IOException {
        System.out.print("# How many books you want to donate? : ");
        int number = sc.nextInt();

        while (number > 0) {
            System.out.println("# Enter book name you've decided to add: ");
            String bookName = br.readLine();
            books.add(bookName);
            System.out.println(bookName + " -- Added successfully!");
            number--;
        }
        System.out.println("\nThanks for adding those valuable books!");

        System.out.println("# Our current book stock: ");
        this.availableBooks();
    }

    void availableBooks() {
        System.out.println("# Currently Available Books: ");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
        System.out.println();
    }

    void issueBooks() throws IOException {
        ArrayList<String> bookIssued = new ArrayList<>(3);

        System.out.println("** Book Issue section **\n");
        availableBooks();

        System.out.println("# How many books you want: ");
        int num = sc.nextInt();

        System.out.print("# Do you have an ID? (y/n): ");
        char ans = sc.next().charAt(0);

        int userId;
        if (ans == 'n' || ans == 'N') {
            userId = ids.size() + 1;
            System.out.println("Okay! Your System generated ID is: " + userId);
            System.out.println("** Note ** : Please do remember your ID! ");
        } else {
            System.out.print("Ok! Enter your User ID: ");
            userId = sc.nextInt();
        }

        System.out.print("# Enter your Full Name (CAPS): ");
        String arrName = br.readLine();

        if (!ids.contains(userId)) {
            names.add(arrName);
            map.put(userId, 0);
            //issuedBookdict.put(arrName, new ArrayList<>(3));
        }

        if (num > 3 || (map.get(userId) + num) > 3) {
            System.out.println("We can only issue 3 book to a user!");
            System.out.println("You want " + num + " books. You already have " + map.get(userId));

        } else if (map.get(userId) <= 3) {

            int i = num;
            while (i > 0) {
                System.out.println("# Enter the book serial number: ");
                int x = sc.nextInt();

                issuedBooks.add(books.get(x - 1)); // adding books to issued book list--
                bookIssued.add(books.get(x - 1));

                System.out.println(books.get(x - 1) + " -- Issued!\n");
                books.remove(x - 1); // removing that issued book --
                // changing book positions as a book is removed from books array --
                i--;
                availableBooks();
            }
            int val = map.get(userId) + num;
            map.put(userId, val);

            ids.add(userId);
            issuedBookdict.put(userId, bookIssued);
        }
    }

    void returnBook() throws IOException {
        // int nameArrLen = names.size();
        if (!ids.isEmpty()) {
            System.out.print("# Enter your User ID: ");
            int userId = sc.nextInt();

            // terminate if len == 0 --
            if (!issuedBooks.isEmpty() && ids.contains(userId)) {
                // show issued books to the particular user--
                System.out.println("We've issued these " + map.get(userId) + " following books to you: ");

                for (int i = 0; i < map.get(userId); i++) {
                    System.out.println((i + 1) + ". " + issuedBookdict.get(userId).get(i));
                    // issuedBookdict.get(userId).forEach(n-> System.out.println(n));
                }

                System.out.println("# How many books you want to return? : ");
                int Num = sc.nextInt();

//            System.out.print("Enter your User ID: ");
//            int usrName = sc.nextInt();

                //improve later --
//            if (names.contains(usrName)) {
//                map.put(usrName, map.get(usrName) - Num);
//            } else {
//                System.out.println("Seems like we didn't issue any book to you!");
//            }
                for (int i = 0; i < Num; i++) {
                    System.out.print("# Enter serial number to return: ");
                    int y = sc.nextInt();
                    // adding the returned book to the shelf --
                    books.add(issuedBookdict.get(userId).get(y - 1));
                    System.out.println("\nReceived the book : " + issuedBookdict.get(userId).get(y - 1));

                    // removing the book from the issued list --
                    issuedBooks.remove(issuedBookdict.get(userId).get(y - 1));

                    issuedBookdict.get(userId).remove(y - 1);

                    map.put(userId, map.get(userId) - 1);

                    // issuedBookdict.get(usrName).forEach(n-> System.out.println(n));
                    if (map.get(userId) != 0) {
                        System.out.println("\nBooks left to you: ");
                        for (int j = 0; j < issuedBookdict.get(userId).size(); j++) {
                            System.out.println((j + 1) + ". " + issuedBookdict.get(userId).get(j));
                        }
                    }
                }

            } else {
                System.out.println("# No books issued yet to you!");
            }
        } else {
            System.out.println("No books issued yet!");
        }
    }

    void libraryUsers() {
        System.out.println("# Users of this Library --");
        System.out.println("ID   -   Names");
        for (int i = 0; i < ids.size(); i++) {
            System.out.println(ids.get(i) + "\t\t" + names.get(i));
        }
    }

    public static void main(String[] args) throws IOException {
        LibraryMngSystem2 lib = new LibraryMngSystem2();
        Scanner s = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean exit = false;

        System.out.println("\nWelcome, to the Library!");
        // lib.availableBooks();
        System.out.println("# We got following things for you -->");

        while (exit != true) {

            System.out.println("1. Borrow books\n2. Return books\n3. Check Currently available books\n4. Donate Books\n5. Visit Readers List");
            // int userInp = Integer.parseInt((String.valueOf(br.readLine().charAt(0))));
            System.out.print("\n# Enter: ");
            int userInp = s.nextInt();

            // input validation --
            if (userInp < 1 || userInp > 5) {
                System.out.println("*** Invalid input! ***");
                System.out.println("Enter again!");
                continue;
            } else {
                if (userInp == 1)
                    lib.issueBooks();
                else if (userInp == 2) {
                    lib.returnBook();
                } else if (userInp == 3)
                    lib.availableBooks();
                else if (userInp == 4)
                    lib.donateBooks();
                else
                    lib.libraryUsers();
            }

            System.out.println("\n# Enter 'y' - to continue & 'n' - to exit");
            char askUser = s.next().charAt(0);
            if (askUser == 'y') {
                exit = false;
            } else {
                exit = true;
                System.out.println("\nThanks for using my Library Management System...\nHave a nice day!");
                break;
            }
        }
    }
}
