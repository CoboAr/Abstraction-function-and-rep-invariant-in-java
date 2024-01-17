package QueueOfDistinctStrings;

/**
 *
 * @author acobo
 */
 import java.util.ArrayList;
 public class QueueOfDistinctStrings {
     // Overview: QueueOfDistinctStrings are mutable, bounded
// collection of distinct strings that operate in
// FIFO (First-In-First-Out) order.
//
// The abstraction function is:
// a) Write the abstraction function here
//  AF(c) = an abstract QueueOfDistinctStrings d ( where c is a QueueOfDistinctStrings object)
//          where d.front = c.items.get(0), d.end = c.items.get(c.items.size()-1),
//          d.elements = c.items where c.items is an ArrayList representation of all the string elements on the queue,
//          for (int i=0; i<c.items.size(); i++){
//              d.elements = d.add(c.items.get(i)
//          }
//
//
// The rep invariant is:
// b) Write the rep invariant here
// RI(c) = true if 1) c.items is not null, 2)consists of unique elements, 3) of type string
//       = false otherwise
//
//
//the rep
     private ArrayList<String> items;
     // constructor
     public QueueOfDistinctStrings () {
// EFFECTS: Creates a new QueueOfDistinctStrings object
         items = new ArrayList<String>();
     }

     // MODIFIES: this
// EFFECTS: Appends the element at the end of the queue
// if the element is not in the queue, otherwise
// does nothing.
     public void enqueue(String element) throws Exception {
         if(element == null) throw new Exception();
         if(false == items.contains(element))
             items.add(element);
     }
     public String dequeue() throws Exception {
// MODIFIES: this
// EFFECTS: Removes an element from the front of the queue
         if (items.size() == 0) throw new Exception();
         return items.remove(0);
     }
     public boolean repOK() {
// EFFECTS: Returns true if the rep invariant holds for this
// object; otherwise returns false
// c) Write the code for the repOK() here

         if (items == null){
             return false;
         }
         // Check if all elements are of type String
         for (String item : items) {
             if (!(item instanceof String)) {
                 return false;
             }
         }

         // Check for duplicates
         for (int i = 0; i < items.size(); i++) {
             for (int j = i + 1; j < items.size(); j++) {
                 if (items.get(i).equals(items.get(j))) {
                     return false;
                 }
             }
         }

         return true;
     }

     public String toString() {
// EFFECTS: Returns a string that contains the strings in the
// queue, the front element and the end element.
// Implements the abstraction function.
// d) Write the code for the toString() here

         if (items.isEmpty()) {
             return "Empty Queue of Distinct Strings";
         }

         String front = items.get(0);
         String end = items.get(items.size() - 1);

         return "Front: " + front + "\nEnd: " + end + "\nQueue of Distinct Strings: " + items;
     }

     // Used for testing
//    public static void main (String[] args){
//        QueueOfDistinctStrings instance = new QueueOfDistinctStrings();
//        try{
//        instance.enqueue("AA");
//        instance.enqueue("BB");
//        instance.enqueue("CC");
//        instance.dequeue();
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
//        instance.items.add("BB");
//        System.out.println(instance.repOK());
//        System.out.println(instance);
//
//
//    }
     public static void main(String[] args) {
         // Test when representation invariant holds (true)
         QueueOfDistinctStrings instanceTrue = new QueueOfDistinctStrings();

         try {
             instanceTrue.enqueue("AA");
             instanceTrue.enqueue("BB");
             instanceTrue.enqueue("CC");
             instanceTrue.dequeue();
         } catch (Exception e) {
             System.out.println("Exception during enqueue or dequeue: " + e.getMessage());
             return;  // Exit the program after an exception
         }

         if (!instanceTrue.repOK()) {
             System.out.println("Representation Invariant Violated (Expected false)!");
             return;  // Exit the program if the representation invariant is violated
         }

         System.out.println("Representation Invariant Holds (Expected true):\n" + instanceTrue);

         // Test when representation invariant is violated (false)
         QueueOfDistinctStrings instanceFalse = new QueueOfDistinctStrings();

         try {
             instanceFalse.enqueue("AA");
             instanceFalse.enqueue("BB");
             instanceFalse.enqueue("CC");
             instanceFalse.dequeue();
         } catch (Exception e) {
             System.out.println("Exception during enqueue or dequeue: " + e.getMessage());
             return;  // Exit the program after an exception
         }

         // Violate representation invariant by adding a duplicate element directly to items
         instanceFalse.items.add("BB");

         if (instanceFalse.repOK()) {
             System.out.println("Representation Invariant Holds (Expected false)!");
             return;  // Exit the program if the representation invariant holds (unexpected)
         }

         System.out.println("Representation Invariant Violated (Expected false):\n" + instanceFalse);
     }

 }