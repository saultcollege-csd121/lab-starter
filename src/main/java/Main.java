import java.awt.Rectangle;

import static java.lang.IO.*;

void main() {
    println("Hello, World!");

//    greet();

    var r = new Rectangle();

//    print("Hello, no newline");
//    print("Another print");

    // Here's a change

//    boolean b = true;
//
//    char c = "A";
//    String s = 'S';
//
//    Object o = new Object();
//    o.getClass();
//    ArrayList list = new ArrayList();
//    String s = new String();
//
//    var p = Math.PI;
//

    IO.println("""
            This is one line
            This is another line
            A third line
       """);


//    var n = Integer.parseInt(IO.readln("Give me a number: "));
//
//    for ( int i : IntStream.range(0,n).toArray() ) {
//        IO.println("i = " + i);
//    }

//    int[] nums = new int[40];
//    nums[0] = 12;
//    nums[1] = 234;
//    IO.println(nums.length);

    var nums = List.of(1, 2, 3);
    // nums.add(12134);

    var productPrices = Map.of("CODE1", 12.34,
                          "CODE2", 23.45);
    HashMap<String, String> sizes = new HashMap<>();
    sizes.put("CODE1", "XXL");
    var keys = productPrices.keySet();
    for ( Map.Entry<String, Double> entry :  productPrices.entrySet() ) {

    }


    ArrayList<String> names = new ArrayList<>();
    names.add("Alice");

    for ( String name : names ) {
        IO.println(name);
    }

    int i = 0;
    while ( i < names.size() ) {
        IO.println(names.get(i));
        i++;
    }


    var L1 = new ArrayList<Integer>(List.of(1, 2, 3));
    var L2 = new ArrayList<Integer>(List.of(4, 5, 6));
    var L3 = new ArrayList<Integer>(List.of(7,8,9));

    var LA = new ArrayList<ArrayList<Integer>>(List.of(L1, L2, L3));
    var LB = List.copyOf(LA);

    LA.get(1).set(0, 12345);

    IO.println(LA.toString() +  LB.toString());

    try {
        var lines = Files.readString(Paths.get("data.txt"));

        Files.writeString(Paths.get("output.txt"), lines, StandardOpenOption.WRITE);
    } catch ( IOException e ) {
        IO.println("File not found" + 123);
    }

}

/**
 * Greet a person by name.
 * @param name the name of the person to greet
 * @return an integer value
 */
int greet(String name) {
    int x = 123;
    IO.println(x);
    return x;
}
