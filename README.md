# Created a Course Registration System Using OOP as detailed below

1. Method Overriding
*	In the Course class, the toString() method of the Object class (which is the default parent class in Java) is overridden. Normally, the toString() method would return the memory address of the object. In this case, I have redefined it so that it returns the course object’s information rather than its memory address.
*	In the Admin class and Student class, we are overriding the methods defined in their respective interfaces AdminTasks, and StudentTasks. The methods that are overridden have @Override before them in my code. 

2. Inheritance 
* The Admin and Student classes extend User class. In this case User class is the parent class while Student and Admin are the subclasses. This is inheritance as it is a “is-a” relationship. Student is a User. Admin is a User. Student and Admin inherit all their common public methods and fields of User class and can access the private data fields through the getters and setters.

3. Polymorphism
* Polymorphism means having many forms. It is the ability to redefine methods in subclasses to make them more specialized. The toString() method in the Course class is an example of polymorphism as there are multiple forms of the same method. One of it is in the object class and the other is in the Course class.

4. Encapsulation: 
* Separates the methods for doing different actions from the rest of the code in the main method. Thus, the logic becomes clear, and the program is easier to read. The class implementation is encapsulated, meaning hidden from the user. Example in our code, the details of implementation of the Student, Admin, and User, and Course classes are kept encapsulated in their own files. 
*	Also did data field encapsulation by declaring data fields in the User class, Admin class, and, Course class, Student class private. This prevents any direct modifications of the fields. The fields can only be modified using getters and setters. Private data fields in Course class example: courseName, courseID, courseInstructor, etc.

5. Abstract Data Types: 
*	The classes User, Admin and Student and Courses are of Abstract data types because the user can invoke the methods of these classes without knowing how the work internally. In other words, we know what is being performed but we do not know how it is being performed.

