# LibrarySystem
# Author: 6375071 Leo Ho
Final Project for Programming 2: Data Structures and OOP

The goal of this project is to develop a Library Management System that allows users to borrow, return, and search for library items. The system will utilize object-oriented programming concepts, including inheritance, polymorphism, and abstract classes/interfaces. The project will also incorporate exception handling, file I/O with CSV files, generics, recursion, and Java collections.

## Project Design

The project is organized around two main abstract classes: `User` and `Item`.

The `User` class is the parent class of `Student`, `Teacher`, and `Admin`. This allows common user information, such as ID, name, and borrowed items, to be shared between all user types. Students and teachers have different borrowing rules. Students can only borrow books, while teachers can borrow different types of items.

The `Item` class is the parent class of `Book`, `DVD`, and `Magazine`. Each item has an ID, a title, and a status. The status shows whether the item is in store, borrowed, or lost.

The `Library` class stores the list of users and the list of items. It acts as the central object that connects users and items together.

## Main Features

The system supports the following features:

- Loading users and items from CSV files
- Searching for items by title, author, publisher, or director
- Recursive search
- Stream search
- Borrowing items
- Returning items
- Checking borrowing limits
- Preventing invalid borrowing actions
- Generating admin reports
- Backing up users and items into CSV files
- Sorting users and items
- Avoiding duplicate search results when multiple copies of the same item exist
