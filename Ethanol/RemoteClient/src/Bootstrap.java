import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

public class Bootstrap {
   private static final InetSocketAddress ADDRESS = new InetSocketAddress("_ftp._tcp.ethanol.rocks", 39997);

   public static void main(String... args) throws Throwable {
      if (args.length < 1) {
         System.err.println("Usage: <ID:Key>");
      } else {
         String[] split = args[0].split(":");
         if (split.length != 2) {
            System.err.println("Invalid format!");
         } else {
            UUID id = UUID.fromString(split[0]);
            UUID key = UUID.fromString(split[1]);
            Socket socket = new Socket();
            socket.setReuseAddress(true);
            socket.connect(ADDRESS);
            InetSocketAddress local = (InetSocketAddress)socket.getLocalSocketAddress();
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            outputStream.writeLong(id.getMostSignificantBits());
            outputStream.writeLong(id.getLeastSignificantBits());
            outputStream.writeLong(key.getMostSignificantBits());
            outputStream.writeLong(key.getLeastSignificantBits());
            int status = inputStream.read();
            if (status == 0) {
               InetSocketAddress target = readAddress(inputStream);
               socket.close();
               System.out.printf("Connecting to '%s'...%n", target);
               Socket sock = new Socket();
               sock.setReuseAddress(true);
               sock.bind(local);
               sock.connect(target, 10000);
               System.out.println("Successfully connected!");
               BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
               BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
               Scanner in = new Scanner(System.in);
               (new Thread(() -> {
                  while(true) {
                     try {
                        if (in.hasNextLine()) {
                           writer.write(in.nextLine());
                           writer.newLine();
                           writer.flush();
                        }
                     } catch (IOException var3) {
                     }
                  }
               })).start();

               while(true) {
                  while(!reader.ready()) {
                  }

                  System.out.println(reader.readLine());
               }
            }

            System.err.printf("Failed with status code '%s'!%n", status);
         }
      }
   }

   public static InetSocketAddress readAddress(DataInputStream dataInputStream) throws IOException {
      byte[] address = new byte[dataInputStream.readUnsignedByte()];
      if (address.length != 4 && address.length != 16) {
         throw new IllegalStateException("Invalid IP-Address");
      } else {
         dataInputStream.readFully(address);
         int port = dataInputStream.readUnsignedShort();
         return new InetSocketAddress(InetAddress.getByAddress(address), port);
      }
   }
}
