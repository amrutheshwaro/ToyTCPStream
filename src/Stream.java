import java.util.HashMap;

public class Stream implements ToyTCPStream{
    // ArrayList<Character> streamData;
    HashMap<Integer, Character> streamData;
    int pointer;

    public Stream() {
        streamData = new HashMap<>();
        pointer = 0;
    }

    @Override
    public void receive(int chunk, char[] data) {
        for (int i = 0; i < data.length; i++) {
            streamData.put(chunk + i, data[i]);
        }
    }

    @Override
    public int read(char[] data) throws Exception{
        if (!streamData.containsKey(Integer.valueOf(pointer)))
            return 0;
        else {
            int i = 0, count = 0;
            while (i < data.length && pointer < streamData.size() && streamData.containsKey(Integer.valueOf(pointer))) {
                data[i] = streamData.get(pointer);
                // System.out.println("Data " + data[i]);
                // System.out.println("Count " + count);
                // System.out.println("Pointer " + pointer);
                // System.out.println("i " + i);
                count++;
                pointer++;
                i++;
            }
            return count;
        }
    }
    
    public static void main(String[] args) {
        Stream stream = new Stream();
        char[] someArray = {'a', 'b', 'c'};
        stream.receive(3, someArray);
        char[] someArray2 = {'a', 'b', 'c'};
        stream.receive(7, someArray2);
        char[] data = new char[5];
        try {
            int temp = stream.read(data);
            if (temp != 0) {
                System.out.print(data);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // System.out.println(stream.streamData);

        char[] someArray3 = {'p'};
        stream.receive(6, someArray3);
        char[] someArray4 = {'x', 'y', 'z'};
        stream.receive(0, someArray4);
        char[] data2 = new char[4];
        try {
            int temp = stream.read(data2);
            if (temp != 0) {
                System.out.print(data2);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        char[] data3 = new char[9];
        char[] someArray5 = {'x', 'y', 'z'};
        stream.receive(10, someArray5);
        try {
            int temp = stream.read(data3);
            if (temp != 0) {
                System.out.print(data3);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
