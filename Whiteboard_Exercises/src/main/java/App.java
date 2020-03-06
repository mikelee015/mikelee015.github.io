import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) {

        //#1)
        //System.out.println(reverseWord("milk"));

        //#2)
        //System.out.println(reverseSentence("Welcome to geeksforgeeks"));

        //#3)
        //int[] nums = {2, 2, 2, 1, 3, 5};
        //System.out.println(evenCount(nums));

        //#4)
        //int[] nums = {1, 2, 3, 1};
        //System.out.println(repeatNums(nums));

        //#5
        //System.out.println(findMax(4, 8, 1));

        //#6
        //fibonachi(21);

        //#7
        //fizzBuzz();

        //#8)
        //List<Integer> nums = Arrays.asList(1,2,3,4,5);
        //System.out.println(reverseList(nums));

        //#9)
        //int[] nums = {1, 2, 3, 4, 3, 2, 1};
        //System.out.println(palindrome(nums));

        //#10)
        //System.out.println(lengthOfStr("milk"));

        //#11)
        //System.out.println(strPalindrome("mom"));

        System.out.println(readString("+2b50 -50 +10b200"));

    }


    //#1) write a method that prints a word backwards.
    public static String reverseWord(String str) {

        String reversedString = "";

        for (int i = str.length() - 1; i >= 0; i--) {
            reversedString += str.charAt(i);
        }

        return reversedString;
    }

    //#2) Reverse words in a given String in Java.
    //Input : "Welcome to geeksforgeeks"
    //Output : "geeksforgeeks to Welcome"
    public static String reverseSentence(String str) {
        // Specifying the pattern to be searched
        Pattern pattern = Pattern.compile("\\s");

        // splitting String str with a pattern
        // (i.e )splitting the string whenever their is whitespace and store in temp array.
        String[] temp = pattern.split(str);
        String result = "";

        // Iterate over the temp array and store the string in reverse order.
        for(int i = 0; i < temp.length; i++) {
            if (i == temp.length - 1)
                result = temp[i] + result;
            else
                result = " " + temp[i] + result;
        }
        return result;
    }

    //#3) Write a method that takes in an array and counts all the even #s in the array.
    public static int evenCount(int[] nums) {
        int toReturn = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] % 2 == 0) {
                toReturn++;
            }
        }
        return toReturn;
    }

    //#4) Write a method that outputs a boolean, takes in an array, and outputs true if there are repeated #s in the array.
    public static boolean repeatNums(int[] nums) {
        boolean toReturn = false;

        for(int i = 0; i < nums.length-1; i++) {
            for(int j = i+1; j < nums.length; j++) {
                toReturn = (nums[i] == nums[j]) || toReturn;
            }
        }
        return toReturn;
    }

    //#5) Write a method that finds the max of 3 ints.
    public static int findMax(int a, int b, int c) {
        int toReturn = -1;
        if(a>b && a>c) {
            toReturn = a;
        } else if (b>a && b>c) {
            toReturn = b;
        } else {
            toReturn = c;
        }
        return toReturn;
    }

    //#6) Print out the Fibonachi sequence for numbers less that a given N.
    public static void fibonachi(int num) {
        int a = 1;
        int b = 1;
        int c = a + b;
        System.out.println(a);
        System.out.println(b);
        while(c < num) {
            System.out.println(c);
            a = b;
            b = c;
            c = a + b;
        }
    }

    //#7) For numbers 1 - 100, print "fizz" if divisble by 3, print "buzz" if divisible by 5 and print "fizz buzz" if divisible by both.
    public static void fizzBuzz() {
        for(int i = 0; i <= 15; i++) {
            System.out.println(i);
            if(i % 3 == 0 && i % 5 == 0) {
                System.out.println("fizz buzz");
            } else if (i % 3 == 0) {
                System.out.println("fizz");
            } else if (i % 5 == 0) {
                System.out.println("buzz");
            }
        }
    }

    //#8) Take in a list of #s and print out a list with the numbers in reverse order.
    public static List<Integer> reverseList(List<Integer> nums) {
        List<Integer> reverse = new ArrayList<>();
        for(int i = 0; i < nums.size(); i++) {
            reverse.add(nums.get( nums.size()-1-i ));
        }
        return reverse;
    }

    //#9) Check to see if the array is a palindrome (the same forwards as it is backwards) ex: "mom".
    public static boolean palindrome(int[] nums) {
        boolean toReturn = false;
        if(nums.length % 2 == 0) {
            return toReturn;
        }

        for(int i = 0; i < nums.length/2; i++) {
            int a = nums[i];
            int b = nums.length - 1 - i;
            int c = nums[b];
            if(a == c) {
                toReturn = true;
            }
        }

        return toReturn;
    }

    //#10) Find length of string.
    public static int lengthOfStr(String x) {
        return x.length();
    }

    //#11) Check to see if a string is a palindrome.
    public static boolean strPalindrome(String x) {
        boolean toReturn = false;
        int y = x.length();
        if (y / 2 == 0) {
            return toReturn;
        } else {
            for (int i = 0; i < x.length(); i++) {
                int a = x.charAt(i);
                int b = x.length() - 1 - i;
                int c = x.charAt(b);
                if (a == c) {
                    toReturn = true;
                }
            }
        }
        return toReturn;
    }

    //#12) "+2b50 -50 +10b200"
    //    Each space indicates a stop on the route for a truck.  A `+` denotes that the truck added weight, and a `-`
    //    denotes a truck removing weight for the stop. If there are multiple boxes, the number of boxes will be
    //    represented followed by "b" and finally the weight of each box. If there is only one box to add or remove on
    //    the route only the weight of that box will be provided (no b)
    //      In the above example the result would be `2050`
    //    Write a function that takes in the above enocded string and outputs the total weight of the truck.
    public static int readString(String str) {

        Pattern pattern = Pattern.compile("\\s");
        String[] stops = pattern.split(str);

        int totalWeight = 0;

        //iterates over stops array.
        for(int i = 0; i < stops.length; i++) {
            //check to see if there's a "b" in each index.
            if (stops[i].contains("b")) {
                //parse into int everything before the b but after the + or - sign.
                int numBoxes = Integer.parseInt(stops[i].split("b")[0].substring(1));
                //parse into int everything after the b.
                int weight = Integer.parseInt(stops[i].split("b")[1]);


                if (stops[i].charAt(0) == '+') {
                    totalWeight += numBoxes*weight;
                } else {
                    totalWeight -= numBoxes*weight;
                }
            } else {
                //parsing this into an int would keep the sign (positive or negative).
                totalWeight += Integer.parseInt(stops[i]);
            }
        }
        return totalWeight;
    }

    //RECURSION EXAMPLES
    //#13)
    public static void sayHi(int n) {
        if(n==0) {
            System.out.println("Done!");
        } else {
            System.out.println("hi!");
            n--;
            sayHi(n);
        }
        //Need a base case, so the method knows when to stop so it doesn't run infinitely.
    }

    //#14)
    public static void countBackwards(int n) {
        if(n==0) {
            System.out.println("done!");
        } else {
            System.out.println(n);
            n--;
            countBackwards(n);
        }
    }

    //#15)
    public static int factorial(int n) {
        //5! = 5 * 4 * 3 * 2 * 1 = 120
        if (n <= 1) {
            return 1;
        } else {
            return n * factorial(n-1);
        }
    }

    //#16)
    public static int loopFactorial(int n) {
        //Here is another way to find a factorial using a loop. (Not an example of recursion bc the method doesn't call itself.
        int fact = 1;
        for(int i = 1; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }

}

