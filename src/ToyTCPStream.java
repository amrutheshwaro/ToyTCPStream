public interface ToyTCPStream {
    void receive(int chunk, char[] data);
    int read(char[] data) throws Exception;
}
